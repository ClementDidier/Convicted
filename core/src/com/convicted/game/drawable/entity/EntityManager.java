package com.convicted.game.drawable.entity;

import com.convicted.game.drawable.ui.screen.ConvictedBatch;

import java.util.List;

public interface EntityManager<T extends Entity>
{
    void load(String entity, Class<T> type);

    boolean contains(String id);

    void unload(String id);

    void unloadAll();

    List<T> getAll();

    T get(String id, Class<T> type);

    void updateAll(float delta);

    void drawAll(ConvictedBatch batch);

    void dispose();
}
