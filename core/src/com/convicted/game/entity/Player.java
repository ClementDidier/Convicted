package com.convicted.game.entity;

import com.badlogic.gdx.graphics.Texture;

public class Player extends Character
{
    private final static float DEFAULT_PROJECTILE_SPEED = 10f;
    private final static float DEFAULT_PROJECTILE_COOLDOWN = 0.1f;

    private float projectileSpeed;
    private float projectileCooldown;

    public Player(Texture texture) {
        super(texture);
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
