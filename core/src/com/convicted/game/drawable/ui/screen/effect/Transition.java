package com.convicted.game.drawable.ui.screen.effect;

import com.convicted.game.drawable.ui.screen.ConvictedScreen;

public abstract class Transition
{
    public final static Transition FADE_IN = new FadeInTransition(2.0f);
    public final static Transition FADE_OUT = new FadeOutTransition(2.0f);

    protected abstract void initialize();
    protected abstract boolean act(float delta, ConvictedScreen screen);
}
