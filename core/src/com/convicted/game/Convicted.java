package com.convicted.game;

public class Convicted
{
    private static ConvictedGame game;

    /**
     * Initialise une instance du jeu si necessaire et la retourne
     * @return L'instance du jeu
     */
    public static ConvictedGame getInstance()
    {
        if(game == null)
            game = new ConvictedGame();
        return game;
    }
}
