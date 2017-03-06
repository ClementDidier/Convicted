package com.convicted.game.ui.screen;

import com.convicted.game.Convicted;

public enum Scene
{
    GameScreen(new GameScreen(Convicted.getGame())),
    MainScreen(new MainScreen(Convicted.getGame()));

    private AbstractScreen screen;

    Scene(AbstractScreen screen)
    {
        this.screen = screen;
    }

    /**
     * Obtient le screen attaché à la scene
     * @return Le screen associé
     */
    public AbstractScreen getScreen()
    {
        return this.screen;
    }
}
