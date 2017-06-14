package com.convicted.game.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Scaling;
import com.convicted.game.action.EntityController;

public abstract class Character extends Entity
{
    private final static Direction DEFAULT_DIRECTION = Direction.Right;
    private final static int DEFAULT_ANIMATION_FRAME_INDEX = 1;
    private final static int SKIN_REGIONS_X = 3;
    private final static Vector2 SCALE = new Vector2(2f, 6f);
    private final static float DEFAULT_SPEED = 30f;
    private final static double ANIMATION_DELTA = 30d;
    private final static double INACTIVITY_TIME = 0.3d; // Remise à 0 de l'animation après INACTIVITY_TIME secondes d'inactivité

    private EntityController controller = null;
    private Direction direction;
    private int animationFrameIndex;
    private double moveDelta;
    private double inactivityDeltaTime;
    private boolean hasMoved;
    private int width, height;

    public Character(Texture texture)
    {
        super(texture);

        this.direction = DEFAULT_DIRECTION;
        this.animationFrameIndex = DEFAULT_ANIMATION_FRAME_INDEX;
        this.speed = DEFAULT_SPEED;
        this.moveDelta = 0;
        this.inactivityDeltaTime = 0;
        this.hasMoved = false;

        this.width = (int)(this.sprite.getWidth() / SKIN_REGIONS_X);
        this.height = (int)this.sprite.getHeight();

        this.sprite.setScale(SCALE.x, SCALE.y);

        this.updateSpriteRegion();

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

    @Override
    public void positionChanged()
    {
        // Mise à jour de la direction du personnage
        this.direction = Direction.getDirectionFromInterpolation(this.sprite.getX(), this.sprite.getY(), getX(), getY());
        this.moveDelta += Math.sqrt(Math.pow(this.sprite.getX() - getX(), 2) +  Math.pow(this.sprite.getY() - getY(), 2));

        // Mise à jour de l'animation de déplacement du personnage
        if(this.moveDelta > ANIMATION_DELTA)
        {
            this.moveDelta = 0;
            this.animationFrameIndex = (this.animationFrameIndex + 1 + DEFAULT_ANIMATION_FRAME_INDEX) % SKIN_REGIONS_X;
        }

        this.sprite.setPosition(getX(), getY());
        this.updateSpriteRegion();

        this.hasMoved = true;
    }

    public void setController(EntityController controller)
    {
        this.controller = controller;
    }

    /**
     * Met à jour l'affichage du personnage
     */
    private void updateSpriteRegion()
    {
        // Mise à jour du personnage (affichage) en fonction des données d'animation
        int x = this.animationFrameIndex * this.width;
        this.sprite.setRegion(x, 0, this.width, this.height);
        this.sprite.setFlip(this.direction == Direction.Left, false);

        // Origin du personnage (origine de lancer des projectiles)
        this.setOrigin(this.getX() + this.width / 2, this.getY() + this.height / 2);
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
                if(this.animationFrameIndex != DEFAULT_ANIMATION_FRAME_INDEX)
                {
                    this.inactivityDeltaTime = 0;
                    this.animationFrameIndex = DEFAULT_ANIMATION_FRAME_INDEX;   // On met le personnage en position standard
                    this.updateSpriteRegion();      // On met à jour son affchage
                }
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
