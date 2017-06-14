package com.convicted.game.action;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.DelegateAction;
import com.convicted.game.entity.Entity;
import com.convicted.game.entity.projectiles.ProjectileType;
import com.convicted.game.ui.screen.GameContext;

public class FireAction extends DelegateAction
{
    private Vector2 direction;
    private float speed;
    private GameContext context;

    public FireAction(GameContext context, Entity actor, Vector2 direction, float speed)
    {
        super();
        this.context = context;
        this.setActor(actor);
        this.direction = direction;
        this.speed = speed;
    }

    @Override
    protected boolean delegate(float delta)
    {
        Vector2 origin = new Vector2(actor.getOriginX(), actor.getOriginY());
        this.context.projectiles.add(context.projectileFactory.getProjectile(ProjectileType.Bubble, origin, direction, speed));
        return true;
    }
}
