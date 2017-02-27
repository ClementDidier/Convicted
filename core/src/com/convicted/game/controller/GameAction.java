package com.convicted.game.controller;

import com.convicted.game.ConvictedGame;

public abstract class GameAction
{
    private ConvictedGame game;
    private java.lang.Character actor;

    public GameAction(java.lang.Character actor, ConvictedGame game)
    {
        this.game = game;
        this.actor = actor;
    }

    public abstract boolean isLegal();

    public abstract void perform();
}
