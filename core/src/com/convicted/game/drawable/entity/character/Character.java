package com.convicted.game.drawable.entity.character;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.convicted.game.drawable.entity.Entity;
import com.convicted.game.drawable.ui.screen.ConvictedBatch;

public abstract class Character extends Entity
{
    private final static boolean DEFAULT_LOOKING_LEFT_SIDE = false;
    private final static int FRAMES_COUNT = 3;
    private final static int IDLE_FRAME_INDEX = 1;

    private Sprite sprite;
    private float regionWidth;
    private boolean isLookingLeftSide;
    private byte frameAnimationIndex;


    public Character(Texture texture)
    {
        super();
        this.sprite = new Sprite(texture);
        this.regionWidth = this.sprite.getWidth() / FRAMES_COUNT;
        this.isLookingLeftSide = DEFAULT_LOOKING_LEFT_SIDE;
        this.frameAnimationIndex = IDLE_FRAME_INDEX;
    }

    @Override
    public void update(float delta)
    {
        if(!this.sprite.isFlipX())
            this.sprite.setFlip(this.isLookingLeftSide, false);

        this.sprite.setRegion((int)this.regionWidth * this.frameAnimationIndex, 0, (int)this.regionWidth, (int)this.sprite.getHeight());
        this.sprite.setSize(this.regionWidth, this.sprite.getHeight());
    }

    @Override
    public void draw(ConvictedBatch batch)
    {
        batch.draw(this.sprite);
    }

    @Override
    public void setPosition(float x, float y)
    {
        super.setPosition(x, y);
        this.sprite.setPosition(x, y);
    }

    public void setScale(float scaleXY)
    {
        this.sprite.setScale(scaleXY);
    }

    public float getRegionWidth()
    {
        return this.sprite.getRegionWidth();
    }

    public float getRegionHeight()
    {
        return this.sprite.getRegionHeight();
    }

    public void hide()
    {
        this.sprite.setAlpha(0f);
    }

    public void show()
    {
        this.sprite.setAlpha(1f);
    }
}
