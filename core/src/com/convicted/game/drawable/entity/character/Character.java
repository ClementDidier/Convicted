package com.convicted.game.drawable.entity.character;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.convicted.game.controller.CharacterController;
import com.convicted.game.drawable.entity.Entity;
import com.convicted.game.drawable.ui.screen.ConvictedBatch;
import com.convicted.game.utils.Timer;
import com.convicted.game.utils.Vector;

public abstract class Character extends Entity
{
    private final static boolean DEBUG = true;

    public final static float DEFAULT_SPEED = 40f;

    private final static float MOVEMENT_ERROR_RATE = 0.05f; // 5% de marge d'erreur pour le déplacement
    private final static boolean DEFAULT_LOOKING_LEFT_SIDE = false;
    private final static int FRAMES_COUNT = 3;
    private final static int IDLE_FRAME_INDEX = 1;
    private final static float MOVE_DELTA = DEFAULT_SPEED;
    private final static float INACTIVITY_TIMEOUT = 300f;

    private Sprite sprite;
    private float regionWidth;
    private boolean isLookingLeftSide;
    private byte frameAnimationIndex;
    private boolean isMoving;
    private Vector2 futurPosition, displacement;
    private float deltaX, deltaY;
    private float speed, displacementDistance;
    private CharacterController controller;
    private Timer inactivityTimer;

    private ShapeRenderer shapeRenderer;

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
        this.displacementDistance = 0f;
        this.inactivityTimer = new Timer();
        this.update(0);

        this.shapeRenderer = new ShapeRenderer();
    }

    @Override
    public void update(float delta)
    {
        if(this.controller != null)
            this.controller.act(delta);

        if(this.isMoving)
        {
            float distance = this.futurPosition.dst(this.getPosition());

            float percent = distance / this.displacementDistance;

            if(distance / this.displacementDistance > 1 || distance / this.displacementDistance <= MOVEMENT_ERROR_RATE)
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

                this.setPosition(this.getPosition().x + dx, this.getPosition().y + dy);

                this.updateMovementAnimation(dx, dy);
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

        if(DEBUG) {
            batch.end();
            Rectangle r = this.sprite.getBoundingRectangle();
            shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
            shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
            shapeRenderer.setColor(255f, 0, 0, 100f);
            shapeRenderer.rect(r.x, r.y, r.width, r.height);
            shapeRenderer.end();
            batch.begin();
        }
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

        if(this.deltaX + this.deltaY > MOVE_DELTA)
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
        this.displacementDistance = this.futurPosition.dst(this.getPosition());
        this.isMoving = true;
    }

    public void moveBy(float x, float y)
    {
        this.moveTo(this.getPosition().x + x, this.getPosition().y + y);
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

    public void setSpeed(float value)
    {
        this.speed = value;
    }

    public float getSpeed()
    {
        return this.speed;
    }
}
