package com.convicted.game.drawable.entity.character;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.convicted.game.drawable.entity.Entity;
import com.convicted.game.drawable.ui.screen.ConvictedBatch;

public abstract class Character extends Entity
{
    private final static boolean DEFAULT_LOOKING_LEFT_SIDE = false;
    private final static int FRAMES_COUNT = 3;
    private final static int IDLE_FRAME_INDEX = 1;

    private Sprite sprite;
    private float frameRegionWidth;
    private boolean isLookingLeftSide;
    private byte frameAnimationIndex;

    public Character()
    {
        this.sprite = new Sprite();
        this.frameRegionWidth = this.sprite.getWidth() / FRAMES_COUNT;
        this.isLookingLeftSide = DEFAULT_LOOKING_LEFT_SIDE;
        this.frameAnimationIndex = IDLE_FRAME_INDEX;
    }

    @Override
    public void update(float delta)
    {
        if(!this.sprite.isFlipX())
            this.sprite.setFlip(this.isLookingLeftSide, false);

        this.sprite.setRegion(this.frameRegionWidth * this.frameAnimationIndex, 0, this.frameRegionWidth, this.sprite.getHeight());
    }

    @Override
    public void draw(ConvictedBatch batch)
    {
        batch.draw(this.sprite);
    }
}
