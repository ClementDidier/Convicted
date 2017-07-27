package com.convicted.game.drawable.entity.character;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.convicted.game.controller.CharacterController;
import com.convicted.game.drawable.entity.Entity;
import com.convicted.game.drawable.ui.screen.ConvictedBatch;
import com.convicted.game.utils.Timer;
import com.convicted.game.utils.Vector;

public abstract class Character extends Entity
{
    private final static boolean DEFAULT_LOOKING_LEFT_SIDE = false;
    private final static int FRAMES_COUNT = 3;
    private final static int IDLE_FRAME_INDEX = 1;
    private final static int DEFAULT_SPEED = 40;
    private final static float MOVE_DELTA = DEFAULT_SPEED * 10f;
    private final static float INACTIVITY_TIMEOUT = 200f;

    private Sprite sprite;
    private float regionWidth;
    private boolean isLookingLeftSide;
    private byte frameAnimationIndex;
    private boolean isMoving;
    private Vector2 futurPosition, displacement;
    private float deltaX, deltaY;
    private int speed;
    private CharacterController controller;
    private Timer inactivityTimer;

    public Character(Texture texture)
    {
        super();
        this.sprite = new Sprite(texture);
        this.regionWidth = this.sprite.getWidth() / FRAMES_COUNT;
        this.isLookingLeftSide = DEFAULT_LOOKING_LEFT_SIDE;
        this.frameAnimationIndex = IDLE_FRAME_INDEX;
        this.isMoving = false;
        this.futurPosition = new Vector2(this.getPosition().x, this.getPosition().y);
        this.displacement = new Vector2();
        this.speed = DEFAULT_SPEED;
        this.deltaX = 0;
        this.deltaY = 0;
        this.inactivityTimer = new Timer();
        this.update(0);
    }

    @Override
    public void update(float delta)
    {
        if(this.controller != null)
            this.controller.act(delta);

        if(this.isMoving)
        {
            if(this.futurPosition.dst(this.getPosition()) <= this.speed * delta)
            {
                this.futurPosition.x = this.getPosition().x;
                this.futurPosition.y = this.getPosition().y;
                this.isMoving = false;
            }
            else
            {
                this.displacement.x = this.futurPosition.x - this.getPosition().x;
                this.displacement.y = this.futurPosition.y - this.getPosition().y;
                Vector.normalize(this.displacement);

                float dx = this.displacement.x * this.speed * delta;
                float dy = this.displacement.y * this.speed * delta;

                this.updateMovementAnimation(dx, dy);

                this.setPosition(this.getPosition().x + dx, this.getPosition().y + dy);
            }
        }

        this.updateMovementInactivity(delta);

        this.sprite.setRegion((int)(this.regionWidth * this.frameAnimationIndex), 0, (int)this.regionWidth, (int)this.sprite.getHeight());
        this.sprite.setSize(this.regionWidth, this.sprite.getHeight());

        this.isLookingLeftSide = this.displacement.x < 0;
        this.sprite.setFlip(this.isLookingLeftSide, false);
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

    private void updateMovementAnimation(float dx, float dy)
    {
        if(this.isMoving)
        this.deltaX += Math.abs(dx);
        this.deltaY += Math.abs(dy);

        if(this.deltaX + this.deltaY > MOVE_DELTA / this.speed)
        {
            this.deltaX = 0;
            this.deltaY = 0;
            this.incrementAnimation();
        }
    }

    private void incrementAnimation()
    {
        if(this.frameAnimationIndex >= FRAMES_COUNT - 1)
        {
            this.frameAnimationIndex = 0;
        }
        else this.frameAnimationIndex++;
    }

    private void updateMovementInactivity(float delta)
    {
        if(!this.isMoving)
        {
            this.inactivityTimer.update(delta);
            if(this.inactivityTimer.ring(INACTIVITY_TIMEOUT))
            {
                this.frameAnimationIndex = IDLE_FRAME_INDEX;
                this.inactivityTimer.reset();
            }
        }
    }

    public void moveTo(float x, float y)
    {
        this.futurPosition.x = x;
        this.futurPosition.y = y;
        this.isMoving = true;
    }

    public void setScale(float scaleXY)
    {
        this.sprite.setScale(scaleXY);
    }

    public Rectangle getBounds()
    {
        return this.sprite.getBoundingRectangle();
    }

    public void hide()
    {
        this.sprite.setAlpha(0f);
    }

    public void show()
    {
        this.sprite.setAlpha(1f);
    }

    public void setController(CharacterController controller)
    {
        this.controller = controller;
    }

    public boolean isMoving()
    {
        return this.isMoving;
    }
}
