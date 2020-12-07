
package com.openclassrooms.entrevoisins.neighbour_list;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;
import com.openclassrooms.entrevoisins.utils.DeleteViewAction;

import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import android.support.test.espresso.matcher.ViewMatchers;
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

    @Rule
    public ActivityTestRule<ListNeighbourActivity> mActivityRule =
            new ActivityTestRule(ListNeighbourActivity.class);

    @Before
    public void setUp() {
        mActivity = mActivityRule.getActivity();
        assertThat(mActivity, notNullValue());
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

    // Ajout

    /**
     * Test si la liste est vide
     */
    @Test
    public void myNeighboursList_FavoriteListEmpty() {
        onView(allOf(withId(R.id.container), isDisplayed())).perform(scrollRight());
        onView(allOf(withId(R.id.list_neighbours), isDisplayed())).check(withItemCount(0));
    }

    /**
     * Test si la liste n'est pas vide
     */
    @Test
    public void myNeighboursList_FavoriteListNotEmpty() {
        // Click sur l'objet 6, puis le bouton de favoris et ensuite retourne sur la liste des voisins
        onView(allOf(withId(R.id.list_neighbours), isDisplayed())).perform(RecyclerViewActions.actionOnItemAtPosition(5, click()));
        onView(allOf(withId(R.id.neighbour_add_fav), isDisplayed())).perform(click());
        pressBack();

        // Check item count on second list
        onView(allOf(withId(R.id.container), isDisplayed())).perform(scrollRight());
        onView(allOf(withId(R.id.list_neighbours), isDisplayed())).check(withItemCount(not(0)));
        onView(allOf(withId(R.id.list_neighbours), isDisplayed())).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(allOf(withId(R.id.neighbour_add_fav), isDisplayed())).perform(click());
    }

    /**
     * Ajouter un nouveau voisin aux favoris et verifier qu'il est dans la
     * liste des favoris
     */
    @Test
    public void myNeighboursList_checkNeighbInDetail() {
        // Click sur l'objet 4, puis le bouton de favoris et ensuite retourne sur la liste des voisins
        onView(allOf(withId(R.id.list_neighbours), isDisplayed())).perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));
        onView(allOf(withId(R.id.view_neighbour_name2), isDisplayed())).check(matches(withText("Jack")));
    }


}