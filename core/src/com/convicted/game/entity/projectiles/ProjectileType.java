package com.convicted.game.entity.projectiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public enum ProjectileType
{
    Bubble("bubble.png", 0.1f);

    private Texture texture;
    private float decreaseSpeedValue;

    ProjectileType(String texture, float decreaseSpeedValue)
    {
        this.texture = new Texture(Gdx.files.internal(texture));
        this.decreaseSpeedValue = decreaseSpeedValue;
    }

    public Texture getTexture()
    {
        return this.texture;
    }

    public float getDecreaseSpeedValue()
    {
        return this.decreaseSpeedValue;
    }
}
