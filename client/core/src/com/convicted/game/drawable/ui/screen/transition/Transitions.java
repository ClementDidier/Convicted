package com.convicted.game.drawable.ui.screen.transition;

public final class Transitions
{
    public static Transition FadeIn(float miliseconds)
    {
        return new FadeInTransition(miliseconds);
    }

    public static Transition FadeOut(float miliseconds)
    {
        return new FadeOutTransition(miliseconds);
    }
}
