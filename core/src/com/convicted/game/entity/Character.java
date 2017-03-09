package com.convicted.game.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.convicted.game.action.EntityController;
import com.convicted.game.action.GameAction;

public abstract class Character extends Actor
{
    private final static Direction DEFAULT_DIRECTION = Direction.Bottom;
    private final static int DEFAULT_ANIMATION_FRAME_INDEX = 0;
    private final static Vector2 SKIN_REGIONS = new Vector2(4, 4);
    private final static float DEFAULT_SPEED = 1f;

    private Direction direction;
    private int animationFrameIndex;
    private Sprite sprite;
    private Vector2 spriteRegionSize;

    public float speed;

    private EntityController controller = null;

    public Character(Texture texture)
    {
        this.direction = DEFAULT_DIRECTION;
        this.animationFrameIndex = DEFAULT_ANIMATION_FRAME_INDEX;
        this.spriteRegionSize = new Vector2();
        this.spriteRegionSize.x = texture.getWidth() / SKIN_REGIONS.x;
        this.spriteRegionSize.y = texture.getHeight() / SKIN_REGIONS.y;
        this.sprite = new Sprite(texture,
                this.animationFrameIndex * (int)this.spriteRegionSize.x,
                this.direction.getIndex() * (int)this.spriteRegionSize.y,
                (int)this.spriteRegionSize.x,
                (int)this.spriteRegionSize.y);
        this.speed = DEFAULT_SPEED;
        Gdx.app.log("Texture", texture.getWidth() + "; " + texture.getHeight());
    }

    private GameAction action;
    @Override
    public void act(float delta)
    {
        super.act(delta);

        if(controller != null)
        {
            controller.act(delta);
            action = controller.consumeAction();
            if(action.isLegal())
                action.perform(delta);
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha)
    {
        this.sprite.draw(batch);
    }

    public Direction getDirection()
    {
        return this.direction;
    }

    public int getAnimationFrameIndex()
    {
        return this.animationFrameIndex;
    }

    public void setSkin(Texture texture)
    {
        this.spriteRegionSize.x = texture.getWidth() / SKIN_REGIONS.x;
        this.spriteRegionSize.y = texture.getHeight() / SKIN_REGIONS.y;
        this.sprite.setTexture(texture);
        this.sprite.setRegion(this.animationFrameIndex * (int)this.spriteRegionSize.x,
                this.direction.getIndex() * (int)this.spriteRegionSize.y,
                (int)this.spriteRegionSize.x,
                (int)this.spriteRegionSize.y);
    }

    @Override
    public void positionChanged()
    {
        super.positionChanged();
        sprite.setPosition(getX(), getY());
        /*this.sprite.setRegion(this.animationFrameIndex * (int)this.spriteRegionSize.x,
                this.direction.getIndex() * (int)this.spriteRegionSize.y,
                (int)this.spriteRegionSize.x,
                (int)this.spriteRegionSize.y);*/
    }

    public void setController(EntityController controller)
    {
        this.controller = controller;
    }
}
