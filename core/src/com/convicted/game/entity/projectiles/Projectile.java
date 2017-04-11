package com.convicted.game.entity.projectiles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.convicted.game.entity.Entity;
import com.convicted.game.ui.screen.GameContext;

public abstract class Projectile extends Entity
{
    private GameContext context;
    private Vector2 direction;
    private float speed;

    public Projectile(GameContext context, Texture texture, Vector2 origin, Vector2 direction, float speed)
    {
        super(texture);

        this.context = context;
        this.direction = direction;
        this.speed = speed;

        this.setPosition(origin.x, origin.y);
    }

    @Override
    public void act(float delta)
    {
        if(this.getX() < 0 || this.getX() > this.context.getScreenWidth() || this.getY() < 0 || this.getY() > this.context.getScreenHeight())
            this.remove();

        this.setPosition(this.getX() + direction.x * speed, this.getY() + direction.y * speed);
        this.sprite.setPosition(getX(), getY());
    }

    @Override
    public void draw(Batch batch, float parentAlpha)
    {
        this.sprite.draw(batch);
    }
}
