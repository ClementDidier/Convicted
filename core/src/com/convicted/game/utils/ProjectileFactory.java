package com.convicted.game.utils;

import com.badlogic.gdx.math.Vector2;
import com.convicted.game.entity.projectiles.Bubble;
import com.convicted.game.entity.projectiles.Projectile;
import com.convicted.game.entity.projectiles.ProjectileType;
import com.convicted.game.ui.screen.GameContext;

public class ProjectileFactory
{
    private GameContext context;

    public ProjectileFactory(GameContext context)
    {
        this.context = context;
    }

    public Projectile getProjectile(ProjectileType type, Vector2 origin, Vector2 direction, float speed)
    {
        switch (type)
        {
            case Bubble:
                return new Bubble(this.context, type.getTexture(), origin, direction, speed, type.getDecreaseSpeedValue());
        }
        return null;
    }
}
