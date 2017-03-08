package com.convicted.game.action;

import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.convicted.game.entity.Character;

public class MoveAction extends GameAction
{
    private float x, y;

    public MoveAction(Character actor, float x, float y)
    {
        super(actor);
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean isLegal()
    {
        return true;
    }

    @Override
    public void perform(float delta)
    {
        this.actor.addAction(Actions.moveBy(
                x * this.actor.speed * delta,
                y * this.actor.speed * delta));
    }
}
