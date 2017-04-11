package com.convicted.game.entity.projectiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public enum ProjectileType
{
    Bubble("bubble.png");

    private Texture texture;

    ProjectileType(String texture)
    {
        this.texture = new Texture(Gdx.files.internal(texture));
    }

    public Texture getTexture()
    {
        return this.texture;
    }
}
