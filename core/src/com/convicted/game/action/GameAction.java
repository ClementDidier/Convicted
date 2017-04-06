package com.convicted.game.action;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.convicted.game.entity.Character;

public abstract class GameAction
{
    public final static GameAction NONE = null;

    protected Character actor;

    public GameAction(Character actor)
    {
        this.actor = actor;
    }

    public abstract boolean isLegal();

    public abstract Action generate(float delta);
}
