package com.convicted.game.ui.screen;

import com.convicted.game.ConvictedGame;
import com.convicted.game.ui.screen.effect.TransitionEffect;

public class Navigator
{
    /**
     * Navigue de l'écran actuel à l'écran donné
     * @param game L'état de jeu
     * @param scene Le nouvel écran
     */
    public static void navigateTo(ConvictedGame game, Scene scene)
    {
        game.setScreen(scene.getScreen());
    }

    public static void navigateTo(final ConvictedGame game, final Scene scene,
                                  TransitionEffect before, TransitionEffect after)
    {
        TransitionScreen screen = new TransitionScreen(game, game.getScreen(), scene.getScreen(), before, after);
        game.setScreen(screen);
    }
}
