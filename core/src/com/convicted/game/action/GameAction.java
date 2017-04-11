package com.convicted.game.action;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.convicted.game.entity.Entity;
import com.convicted.game.ui.screen.GameContext;

public abstract class GameAction
{
    protected Entity actor;
    protected GameContext context;

    public GameAction(GameContext context, Entity actor)
    {
        this.context = context;
        this.actor = actor;
    }

    public abstract boolean isLegal();

    public abstract Action generate(float delta);
}
