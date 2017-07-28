package com.convicted.game.drawable.entity.projectile;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.convicted.game.drawable.entity.Entity;
import com.convicted.game.drawable.ui.screen.ConvictedBatch;
import com.convicted.game.drawable.ui.screen.ConvictedScreen;

import java.util.Random;

public class Projectile extends Entity
{
    private final static float DEFAULT_SPEED = 1000f;
    private final static int MAXIMUM_SIZE = 40;
    private final static int MINIMUM_SIZE = 20;
    private final static float DEFAULT_LIFE_DURATION = 2f;

    private Random random;
    private float directionX, directionY;
    private float speed;
    private float size;
    private float lifeDuration;

    private ShapeRenderer shapeRenderer;

    public Projectile()
    {
        super();

        this.random = new Random();
        this.directionX = 0;
        this.directionY = 0;
        this.speed = DEFAULT_SPEED;
        this.size = this.random.nextInt(MAXIMUM_SIZE - MINIMUM_SIZE) + MINIMUM_SIZE;
        this.lifeDuration = DEFAULT_LIFE_DURATION;

        this.shapeRenderer = new ShapeRenderer();
    }

    @Override
    public void reset()
    {
        this.directionX = 0;
        this.directionY = 0;
        this.speed = DEFAULT_SPEED;
        this.size = this.random.nextInt(MAXIMUM_SIZE - MINIMUM_SIZE) + MINIMUM_SIZE;
        this.lifeDuration = DEFAULT_LIFE_DURATION;
    }

    @Override
    public void update(float delta)
    {
        if(isAlive())
        {
            this.lifeDuration -= delta;
            this.speed -= 7.5 * Math.pow(10, -5) * this.speed * this.speed;
            this.getPosition().add(this.directionX * this.speed * delta, this.directionY * this.speed * delta);
        }
    }

    @Override
    public void draw(ConvictedBatch batch)
    {
        batch.end();
        this.shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
        this.shapeRenderer.setColor(Color.BLACK);
        this.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        this.shapeRenderer.rect(this.getPosition().x, this.getPosition().y, this.size, this.size);
        this.shapeRenderer.setColor(0.44f, 0.61f, 0.37f, 1f);
        this.shapeRenderer.rect(this.getPosition().x + 4, this.getPosition().y + 4, this.size - 8, this.size - 8);
        this.shapeRenderer.end();
        batch.begin();
    }

    public boolean isAlive()
    {
        // TODO: Optimisation >> If outOfScreen Then isAlive = false
        return this.lifeDuration > 0 && ConvictedScreen.contains(this.getPosition().x, this.getPosition().y);
    }

    public void setSpeed(float speed)
    {
        this.speed = speed;
    }

    public void setDirection(float x, float y)
    {
        this.directionX = x;
        this.directionY = y;
    }

    @Override
    public void setPosition(float x, float y)
    {
        super.setPosition(x - this.size / 2f, y - this.size / 2f);
    }
}
