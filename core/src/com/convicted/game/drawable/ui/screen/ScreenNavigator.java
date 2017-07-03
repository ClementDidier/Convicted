package com.convicted.game.drawable.ui.screen;

import com.convicted.game.ConvictedGame;
import com.convicted.game.drawable.ui.screen.transition.Transition;
import com.convicted.game.drawable.ui.screen.transition.TransitionScreen;

public class ScreenNavigator
{
    public static void navigateTo(ConvictedScreen screen)
    {
        ConvictedGame.getInstance().setScreen(screen);
    }

    // TODO : Merge transitions parameters to only one ?
    public static void navigateTo(ConvictedScreen screen, Transition firstScreenTransition, Transition secondScreenTransition)
    {
        TransitionScreen transitionScreen = new TransitionScreen(ConvictedGame.getInstance(), firstScreenTransition, screen, secondScreenTransition);
        ConvictedGame.getInstance().setScreen(transitionScreen);
    }
}
