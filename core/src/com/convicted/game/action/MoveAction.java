package com.convicted.game.action;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.convicted.game.entity.Character;
import com.convicted.game.ui.screen.GameContext;

public class MoveAction extends GameAction
{
    private float x, y;

    public MoveAction(GameContext context, Character actor, float x, float y)
    {
        super(context, actor);
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean isLegal()
    {
        return true;
    }

    @Override
    public Action generate(float delta)
    {
        return Actions.moveBy(x * this.actor.speed * delta, y * this.actor.speed * delta);
    }
}
