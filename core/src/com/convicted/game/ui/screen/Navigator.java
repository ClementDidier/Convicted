package com.convicted.game.ui.screen;

import com.convicted.game.ConvictedGame;

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
}
