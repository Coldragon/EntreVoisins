
package com.openclassrooms.entrevoisins.neighbour_list;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;
import com.openclassrooms.entrevoisins.utils.DeleteViewAction;

import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import android.support.test.espresso.matcher.ViewMatchers;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.ViewPagerActions.scrollLeft;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.contrib.ViewPagerActions.scrollRight;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.openclassrooms.entrevoisins.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.AllOf.allOf;
import static org.hamcrest.core.IsNull.notNullValue;


/**
 * Test class for list of neighbours
 */
@RunWith(AndroidJUnit4.class)
public class NeighboursListTest {

    // This is fixed
    private static int ITEMS_COUNT = 12;

    private ListNeighbourActivity mActivity;
    private NeighbourApiService mApiService;
    @Rule
    public ActivityTestRule<ListNeighbourActivity> mActivityRule =
            new ActivityTestRule(ListNeighbourActivity.class);


    /**
     * @return return le premier voisin qui n'est pas favoris
     */
    private Neighbour getFirstNeighbourNotInFavorites() {
        List<Neighbour> neighbourList = mApiService.getNeighbours();
        Neighbour notInFavorites = null;

        for (Neighbour neighbour : neighbourList) {
            if (!mApiService.getIsFavorite(neighbour)) {
                notInFavorites = neighbour;
                break;
            }
        }

        if (notInFavorites == null) {
            notInFavorites = neighbourList.get(0);
            mApiService.toggleIsFavorite(notInFavorites);
        }

        return notInFavorites;
    }

    /**
     * @return return le premier voisin qui est favoris
     */
    private Neighbour getFirstNeighbourInFavorites() {
        List<Neighbour> neighbourList = mApiService.getNeighbours();
        Neighbour inFavorites = null;
        for (Neighbour neighbour : neighbourList) {
            if (mApiService.getIsFavorite(neighbour)) {
                inFavorites = neighbour;
                break;
            }
        }
        if (inFavorites == null) {
            inFavorites = neighbourList.get(0);
            mApiService.toggleIsFavorite(inFavorites);
        }
        return inFavorites;
    }

    @Before
    public void setUp() {
        mActivity = mActivityRule.getActivity();
        mApiService = DI.getNeighbourApiService();
        assertThat(mActivity, notNullValue());
        assertThat(mApiService, notNullValue());
    }


    /**
     * We ensure that our recyclerview is displaying at least on item
     */
    @Test
    public void myNeighboursList_shouldNotBeEmpty() {
        // First scroll to the position that needs to be matched and click on it.
        onView(allOf(withId(R.id.list_neighbours), isDisplayed()))
                .check(matches(hasMinimumChildCount(1)));
    }

    /**
     * When we delete an item, the item is no more shown
     */
    @Test
    public void myNeighboursList_deleteAction_shouldRemoveItem() {
        Matcher matcher = allOf(withId(R.id.list_neighbours), isDisplayed());

        // Given : We remove the element at position 2
        onView(matcher).check(withItemCount(ITEMS_COUNT));
        // When perform a click on a delete icon
        onView(matcher)
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, new DeleteViewAction()));
        // Then : the number of element is 11
        onView(matcher).check(withItemCount(ITEMS_COUNT - 1));
    }

    // --------------------------------------- //

    /**
     * Verifie que le bons voisin a été ouvert dans les details
     */
    @Test
    public void myNeighboursList_openGoodNeighbourFromList() {
        Neighbour neighbour = mApiService.getNeighbours().get(0);
        onView(allOf(withId(R.id.list_neighbours), isDisplayed())).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(allOf(withId(R.id.view_neighbour_name2), isDisplayed())).check(matches(withText(neighbour.getName())));
    }

    /**
     * Test si favoris bien ajouté
     */
    @Test
    public void myNeighboursList_addFavorite() {
        int itemCountBefore = mApiService.getFavorites().size();
        onView(allOf(withId(R.id.list_neighbours), isDisplayed())).perform(RecyclerViewActions.actionOnItemAtPosition(mApiService.getNeighbours().indexOf(getFirstNeighbourNotInFavorites()), click()));
        onView(allOf(withId(R.id.neighbour_add_fav), isDisplayed())).perform(click());
        pressBack();

        onView(allOf(withId(R.id.container), isDisplayed())).perform(scrollRight());
        onView(allOf(withId(R.id.list_neighbours), isDisplayed())).check(withItemCount(greaterThan(itemCountBefore)));
    }

    /**
     * Test si favoris bien supprimer
     */
    @Test
    public void myNeighboursList_removeFavorite() {
        int itemCountBefore = mApiService.getNeighbours().size();
        onView(allOf(withId(R.id.list_neighbours), isDisplayed())).perform(RecyclerViewActions.actionOnItemAtPosition(mApiService.getNeighbours().indexOf(getFirstNeighbourInFavorites()), click()));
        onView(allOf(withId(R.id.neighbour_add_fav), isDisplayed())).perform(click());
        pressBack();

        onView(allOf(withId(R.id.container), isDisplayed())).perform(scrollRight());
        onView(allOf(withId(R.id.list_neighbours), isDisplayed())).check(withItemCount(lessThan(itemCountBefore)));
    }

    /**
     * Test si liste afficher correspond bien
     */
    @Test
    public void myNeighboursList_favoriteListDisplayed() {
        int itemCountBefore = mApiService.getFavorites().size();
        onView(allOf(withId(R.id.container), isDisplayed())).perform(scrollRight());
        onView(allOf(withId(R.id.list_neighbours), isDisplayed())).check(withItemCount(itemCountBefore));
    }
}