package com.convicted.game.ui.screen;

public enum Scene
{
    GameScreen(new GameScreen());


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
