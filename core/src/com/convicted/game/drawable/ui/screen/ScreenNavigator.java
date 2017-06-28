package com.convicted.game.drawable.ui.screen;

import com.convicted.game.Convicted;
import com.convicted.game.drawable.ui.screen.effect.Transition;
import com.convicted.game.drawable.ui.screen.effect.TransitionScreen;

public class ScreenNavigator
{
    public static void navigateTo(ConvictedScreen screen)
    {
        Convicted.getInstance().setScreen(screen);
    }

    // TODO : Merge transitions parameters to only one ?
    public static void navigateTo(ConvictedScreen screen, Transition firstScreenTransition, Transition secondScreenTransition)
    {
        TransitionScreen transitionScreen = new TransitionScreen(Convicted.getInstance().getScreen(), screen, firstScreenTransition, secondScreenTransition);
        Convicted.getInstance().setScreen(transitionScreen);
    }
}
