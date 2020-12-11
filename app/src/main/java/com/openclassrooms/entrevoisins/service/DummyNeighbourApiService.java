package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourApiService implements NeighbourApiService {

    private List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();
    private List<Neighbour> favorites = new ArrayList<Neighbour>();

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Neighbour> getNeighbours() {
        return neighbours;
    }

    @Override
    public List<Neighbour> getFavorites() {
        return favorites;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteNeighbour(Neighbour neighbour) {
        neighbours.remove(neighbour);
    }

    /**
     * {@inheritDoc}
     *
     * @param neighbour
     */
    @Override
    public void createNeighbour(Neighbour neighbour) {
        neighbours.add(neighbour);
    }

    @Override
    public void deleteFavorite(Neighbour neighbour) {
        if (favorites.contains(neighbour))
            favorites.remove(neighbour);
    }

    @Override
    public void addFavorite(Neighbour neighbour) {
        favorites.add(neighbour);
    }

    public boolean getIsFavorite(Neighbour neighbour) {
        return favorites.contains(neighbour);
    }

    public void toggleIsFavorite(Neighbour neighbour) {
        if (getIsFavorite(neighbour))
            deleteFavorite(neighbour);
        else
            addFavorite(neighbour);

    }
}
