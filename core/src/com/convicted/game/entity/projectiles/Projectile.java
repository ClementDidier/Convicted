package com.convicted.game.entity.projectiles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.convicted.game.entity.Entity;
import com.convicted.game.ui.screen.GameContext;

import java.util.Random;

public abstract class Projectile extends Entity
{
    private GameContext context;
    private Vector2 direction;
    private Vector2 size;
    private Vector2 origin;
    private final float SPEED;
    private float speed;
    private float decreaseSpeedValue;
    private boolean removed;
    private float scale;

    public Projectile(GameContext context, Texture texture, Vector2 origin, Vector2 direction, float speed, float decreaseSpeedValue)
    {
        super(texture);

        this.context = context;
        this.direction = direction;
        this.speed = speed;
        this.size = new Vector2(texture.getWidth(), texture.getHeight());

        Random random = new Random();
        this.decreaseSpeedValue = decreaseSpeedValue * (random.nextFloat() + 1f) * 0.7f;
        this.origin = origin;
        this.removed = false;

        // TODO: Revoir
        this.SPEED = speed;
        this.scale = 0.5f * random.nextFloat() + 1f;

        this.setPosition(this.origin.x + this.size.x, this.origin.y + this.size.y / 2);
    }

    @Override
    public void act(float delta)
    {
        // TODO: Revoir
        float rate = -((1 - (speed / SPEED))-0.5f) * ((1 - (speed / SPEED))-0.5f);
        this.sprite.setScale(rate + this.scale);

        this.setPosition(this.getX() + direction.x * speed, this.getY() + direction.y * speed);
        this.sprite.setPosition(getX(), getY());
        this.speed -= this.decreaseSpeedValue;
        this.removed = this.speed <= 0;
    }

    @Override
    public void draw(Batch batch, float parentAlpha)
    {
        this.sprite.draw(batch);
    }

    public boolean isDead()
    {
        return this.removed || this.getX() < 0 || this.getX() > this.context.getScreenWidth() || this.getY() < 0 || this.getY() > this.context.getScreenHeight();
    }
}
