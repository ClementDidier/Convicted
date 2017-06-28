package com.convicted.game.drawable;

import com.convicted.game.drawable.ui.screen.ConvictedBatch;

public interface Drawable
{
    void update(float delta);
    void draw(ConvictedBatch batch);
}
