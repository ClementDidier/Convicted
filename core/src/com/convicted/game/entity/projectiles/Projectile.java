package com.convicted.game.entity.projectiles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.convicted.game.entity.Entity;
import com.convicted.game.ui.screen.GameContext;
import java.util.Random;

public abstract class Projectile extends Entity {
    private GameContext context;
    private Vector2 direction;
    private Vector2 size;
    private Vector2 origin;
    private final float SPEED;
    private float speed;
    private float decreaseSpeedValue;
    private boolean removed;
    private float scale;

    public Projectile(GameContext context, Texture texture, Vector2 origin, Vector2 direction, float speed, float decreaseSpeedValue) {
        super(texture);
        this.context = context;
        this.direction = direction;
        this.speed = speed;
        this.size = new Vector2((float)texture.getWidth(), (float)texture.getHeight());
        Random random = new Random();
        this.decreaseSpeedValue = decreaseSpeedValue * (random.nextFloat() + 1.0F) * 0.7F;
        this.origin = origin;
        this.removed = false;
        this.SPEED = speed;
        this.scale = 0.5F * random.nextFloat() + 1.0F;
        this.setPosition(this.origin.x + this.size.x, this.origin.y + this.size.y / 2.0F);
    }

    public void act(float delta) {
        float rate = -(1.0F - this.speed / this.SPEED - 0.5F) * (1.0F - this.speed / this.SPEED - 0.5F);
        this.sprite.setScale(rate + this.scale);
        this.setPosition(this.getX() + this.direction.x * this.speed, this.getY() + this.direction.y * this.speed);
        this.sprite.setPosition(this.getX(), this.getY());
        this.speed -= this.decreaseSpeedValue;
        this.removed = this.speed <= 0.0F;
    }

    public void draw(Batch batch, float parentAlpha) {
        this.sprite.draw(batch);
    }

    public boolean isDead() {
        return this.removed || this.getX() < 0.0F || this.getX() > this.context.getScreenWidth() || this.getY() < 0.0F || this.getY() > this.context.getScreenHeight();
    }
}
