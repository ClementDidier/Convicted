package com.convicted.game.drawable.ui.screen;

import com.convicted.game.ConvictedGame;
import com.convicted.game.drawable.ui.screen.transition.Transition;

public class ScreenNavigator
{
    public static void navigateTo(ConvictedScreen screen)
    {
        ConvictedGame.getInstance().setScreen(screen);
    }

    public static void navigateTo(ConvictedScreen screen, Transition firstScreenTransition, Transition secondScreenTransition)
    {
        TransitionScreen transitionScreen = new TransitionScreen(ConvictedGame.getInstance(), firstScreenTransition, screen, secondScreenTransition);
        ConvictedGame.getInstance().setScreen(transitionScreen);
    }
}
