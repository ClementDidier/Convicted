package com.convicted.game.ui.screen.effect;

import com.convicted.game.ui.screen.AbstractScreen;

public abstract class TransitionEffect
{
    public abstract void render(float delta, AbstractScreen screen);
    public abstract boolean isFinished();

    public static TransitionEffect FadeIn()
    {
        return new FadeInTransitionEffect(1f);
    }

    public static TransitionEffect FadeOut()
    {
        return new FadeOutTransitionEffect(1f);
    }
}
