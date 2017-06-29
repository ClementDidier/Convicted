package com.convicted.game.drawable.ui.screen.transition;

import com.convicted.game.drawable.ui.screen.ConvictedScreen;

public interface Transition
{
    void initialize();
    boolean act(float delta, ConvictedScreen screen);
}
