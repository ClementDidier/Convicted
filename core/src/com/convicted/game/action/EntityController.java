package com.convicted.game.action;

import com.convicted.game.entity.Character;
import com.convicted.game.ui.screen.GameContext;

import java.util.Stack;

public abstract class EntityController
{
    protected Stack<GameAction> actions;
    protected GameContext context;
    protected Character actor;

    public EntityController(GameContext context, Character actor)
    {
        this.actions = new Stack<GameAction>();
        this.context = context;
        this.actor = actor;
    }

    public abstract void act(float delta);
}
