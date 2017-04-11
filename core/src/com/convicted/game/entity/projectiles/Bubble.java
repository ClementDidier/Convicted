package com.convicted.game.entity.projectiles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.convicted.game.ui.screen.GameContext;

public class Bubble extends Projectile
{
    public Bubble(GameContext context, Texture texture, Vector2 origin, Vector2 direction, float speed)
    {
        super(context, texture, origin, direction, speed);
    }
}
