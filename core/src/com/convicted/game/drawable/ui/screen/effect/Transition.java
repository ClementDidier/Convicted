package com.convicted.game.drawable.ui.screen.effect;

import com.convicted.game.drawable.ui.screen.ConvictedScreen;

public abstract class Transition
{
    protected final static Transition FADE_IN = new FadeInTransition(2.0f);
    protected final static Transition FADE_OUT = new FadeOutTransition(2.0f);

    protected abstract void initialize();
    protected abstract boolean act(float delta, ConvictedScreen screen);
}
