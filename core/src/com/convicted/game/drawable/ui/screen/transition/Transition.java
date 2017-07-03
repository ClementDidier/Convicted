package com.convicted.game.drawable.ui.screen.transition;

public interface Transition
{
    void initialize();
    boolean act(float delta);
}
