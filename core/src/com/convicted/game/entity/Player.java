package com.convicted.game.entity;

import com.badlogic.gdx.graphics.Texture;
import com.convicted.game.ui.screen.GameContext;

public class Player extends Character
{
    private final static float DEFAULT_PROJECTILE_SPEED = 10f;
    private final static float DEFAULT_PROJECTILE_COOLDOWN = 0.5f;

    private float projectileSpeed;
    private float projectileCooldown;

    public Player(Texture texture, GameContext context) {
        super(texture, context);
        this.projectileSpeed = DEFAULT_PROJECTILE_SPEED;
        this.projectileCooldown = DEFAULT_PROJECTILE_COOLDOWN;
    }

    public float getProjectileSpeed()
    {
        return this.projectileSpeed;
    }

    public float getProjectileCooldown()
    {
        return this.projectileCooldown;
    }
}
