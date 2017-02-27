package com.convicted.game.controller;

import com.convicted.game.ConvictedGame;

public abstract class ActorController
{
    public abstract GameAction getAction(ConvictedGame game);
}
