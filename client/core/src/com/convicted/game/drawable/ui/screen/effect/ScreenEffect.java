package com.convicted.game.drawable.ui.screen.effect;

import com.badlogic.gdx.graphics.OrthographicCamera;

public interface ScreenEffect
{
    void update(float delta, OrthographicCamera camera);
    boolean isFinished();
}
