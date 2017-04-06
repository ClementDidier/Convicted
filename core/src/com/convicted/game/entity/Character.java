package com.convicted.game.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.convicted.game.action.EntityController;

public abstract class Character extends Image
{
    private final static Direction DEFAULT_DIRECTION = Direction.Bottom;
    private final static int DEFAULT_ANIMATION_FRAME_INDEX = 0;
    private final static Vector2 SKIN_REGIONS = new Vector2(4, 4);
    private final static float DEFAULT_SPEED = 30f;
    private final static double ANIMATION_DELTA = 30d;
    private final static double INACTIVITY_TIME = 0.3d; // Remise à 0 de l'animation après INACTIVITY_TIME secondes d'inactivité

    private EntityController controller = null;

    private Direction direction;
    private int animationFrameIndex;
    private Sprite sprite;
    private Vector2 spriteRegionSize;
    private double moveDelta;
    private double inactivityDeltaTime;
    private boolean hasMoved;

    public float speed;

    public Character(Texture texture)
    {
        super();
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
        this.sprite.scale(2);
        this.moveDelta = 0;
        this.inactivityDeltaTime = 0;
        this.hasMoved = false;
    }

    @Override
    public void act(float delta)
    {
        super.act(delta);

        if(controller != null)
            controller.act(delta);

        // Verifie et met à jour l'inactivité du personnage
        this.updateMovementInactivity(delta);
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
        // Mise à jour de la direction du personnage
        this.direction = Direction.getDirectionFromInterpolation(sprite.getX(), sprite.getY(), getX(), getY());
        moveDelta += Math.sqrt(Math.pow(sprite.getX() - getX(), 2) +  Math.pow(sprite.getY() - getY(), 2));

        // Mise à jour de l'animation de déplacement du personnage
        if(moveDelta > ANIMATION_DELTA)
        {
            moveDelta = 0;
            this.animationFrameIndex = (this.animationFrameIndex + 1) % (int)SKIN_REGIONS.x;
        }

        sprite.setPosition(getX(), getY());

        this.refreshSprite();
        this.hasMoved = true;
    }

    public void setController(EntityController controller)
    {
        this.controller = controller;
    }

    /**
     * Met à jour l'affichage du personnage
     */
    private void refreshSprite()
    {
        // Mise à jour du personnage (affichage) en fonction des données d'animation et de direction
        this.sprite.setRegion(this.animationFrameIndex * (int)this.spriteRegionSize.x,
                this.direction.getIndex() * (int)this.spriteRegionSize.y,
                (int)this.spriteRegionSize.x,
                (int)this.spriteRegionSize.y);
    }

    /**
     * Vérifie et met à jour les informations d'inactivité du personnage
     * @param delta Le temps effectif en seconde depuis le dernier appel de la méthode
     */
    private void updateMovementInactivity(float delta)
    {
        // Si le personnage n'a toujours pas bougé depuis le dernier appel de la méthode
        if(!this.hasMoved)
        {
            // Si le temps d'inactivité à dépasser les INACTIVITY_TIME secondes
            if(this.inactivityDeltaTime > INACTIVITY_TIME)
            {
                this.inactivityDeltaTime = 0;
                this.animationFrameIndex = 0;   // On met le personnage en position standard
                this.refreshSprite();           // On met à jour son affchage
            }
            else inactivityDeltaTime += delta;  // Sinon on ajoute le temps effectif dans le temps d'inactivité
        }
        else if(this.inactivityDeltaTime != 0)
        {
            this.inactivityDeltaTime = 0;
        }

        this.hasMoved = false;
    }
}
