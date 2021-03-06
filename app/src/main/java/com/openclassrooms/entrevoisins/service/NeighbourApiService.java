package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.List;


/**
 * Neighbour API client
 */
public interface NeighbourApiService {

    /**
     * Get all my Neighbours
     *
     * @return {@link List}
     */
    List<Neighbour> getNeighbours();

    /**
     * @return FavoriteList
     */
    List<Neighbour> getFavorites();


    /**
     * Deletes a neighbour
     *
     * @param neighbour
     */
    void deleteNeighbour(Neighbour neighbour);

    /**
     * Create a neighbour
     *
     * @param neighbour
     */
    void createNeighbour(Neighbour neighbour);

    /**
     * Deletes favorite
     *
     * @param neighbour
     */
    void deleteFavorite(Neighbour neighbour);

    /**
     * Add favorite
     *
     * @param neighbour
     */
    void addFavorite(Neighbour neighbour);

    /**
     * Is in fav
     *
     * @param neighbour
     * @return is in favorite list ?
     */
    boolean getIsFavorite(Neighbour neighbour);

    /**
     * Add or remove neighbour from neighbour list in favorite list
     *
     * @param neighbour
     */
    void toggleIsFavorite(Neighbour neighbour);
}
