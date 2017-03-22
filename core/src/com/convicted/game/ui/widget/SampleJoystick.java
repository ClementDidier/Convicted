package com.convicted.game.ui.widget;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import static com.convicted.game.utils.Converter.convertRadianToDegree;

public class SampleJoystick extends Widget implements IJoystick, InputProcessor
{
    private final static int DEFAULT_RADIUS = 150;          // Taille par défaut du support joystick
    private final static int DEFAULT_INNER_RADIUS = 75;    // Taille par défaut du joystick
    private final static int DEAD_AREA = 300;               // Zone de fin de prise en main de la gesture
    private final static int EFFECT_AREA = DEFAULT_RADIUS - DEFAULT_INNER_RADIUS;  // Zone d'effet maximale du joystick
    private final static int DEGREES = 360;
    private final static int DIRECTIONS_COUNT = 8;          // Nombre de direction (Besoin de modifier JoystickDirection si modification)
    private final static Color FOREGROUND_COLOR = Color.DARK_GRAY;
    private final static Color INNER_FOREGROUND_COLOR = Color.GRAY;
    private final static float OPACITY = 0.3f;

    private int radius, innerRadius;
    private ShapeRenderer renderer;
    private Vector2 joystickInnerPosition;
    private int pointerID;

    public SampleJoystick(int x, int y)
    {
        super(x, y);

        this.radius = DEFAULT_RADIUS;
        this.innerRadius = DEFAULT_INNER_RADIUS;

        this.joystickInnerPosition = new Vector2(x, y);
        this.renderer = new ShapeRenderer();
        this.pointerID = -1;
    }

    @Override
    public void draw(Batch batch,  float parentAlpha)
    {
        batch.end();
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

        this.renderer.setProjectionMatrix(batch.getProjectionMatrix());
        this.renderer.begin(ShapeRenderer.ShapeType.Filled);
        this.renderer.setColor(FOREGROUND_COLOR.r, FOREGROUND_COLOR.g, FOREGROUND_COLOR.b, batch.getColor().a/1f * OPACITY);
        this.renderer.circle(this.getX(), this.getY(), this.radius);
        this.renderer.setColor(INNER_FOREGROUND_COLOR.r, INNER_FOREGROUND_COLOR.g, INNER_FOREGROUND_COLOR.b, batch.getColor().a/1f * OPACITY);
        this.renderer.circle(this.joystickInnerPosition.x, this.joystickInnerPosition.y, this.innerRadius);
        this.renderer.end();

        Gdx.gl.glDisable(GL20.GL_BLEND);
        batch.begin();
    }

    /**
     * Obtient l'angle en radian correspondant entre les coordonnées données et le point de degré 0
     * @param vector Les coordonnées du point à prendre en compte
     * @return La valeur de l'angle en radian calculé
     */
    private double getAngle(Vector2 vector)
    {
        return Math.atan2(vector.x - this.getX(), vector.y - this.getY());
    }

    /**
     * Obtient le processeur d'entrée du widget
     * @return Le processeur d'entrée
     */
    @Override
    public InputProcessor getProcessor()
    {
        return this;
    }

    /**
     * Obtient le degrée d'orientation du joystick
     * @return Le degrée d'orientation du joystick compris entre [0; 360]
     */
    @Override
    public double getDegree()
    {
        double angleRadian = getAngle(this.joystickInnerPosition);

        if(angleRadian < 0)
            return 360 + convertRadianToDegree(angleRadian);
        return convertRadianToDegree(angleRadian);
    }

    /**
     * Obtient le pourcentage de poussée du joystick
     * @return Le pourcentage de poussée du joystick compris entre [0; 1]
     */
    @Override
    public double getPushedValue()
    {
        return this.joystickInnerPosition.dst(this.getX(), this.getY()) / EFFECT_AREA;
    }

    /**
     * Obtient la direction du joystick
     * @return La direction du joystick (8 directions)
     */
    public JoystickDirection getDirection()
    {
        final double PORTION = DEGREES / DIRECTIONS_COUNT;
        final double DEMI_PORTION = PORTION / 2.0d;

        double degree = getDegree();

        degree = (degree - DEMI_PORTION < 0) ? 0 : degree - DEMI_PORTION;
        int index = (int) Math.ceil(degree / PORTION);

        if(index == DIRECTIONS_COUNT) // Modulo like
        {
            return JoystickDirection.Top;
        }

        return JoystickDirection.getDirections()[index + 1];
    }

    /**
     * Called when the screen was touched or a mouse button was pressed. The button parameter will be {@link Input.Buttons#LEFT} on iOS.
     *
     * @param screenX The x coordinate, origin is in the upper left corner
     * @param screenY The y coordinate, origin is in the upper left corner
     * @param pointer the pointer for the event.
     * @param button  the button
     * @return whether the input was processed
     */
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button)
    {
        Vector2 worldTouchLocation = this.localToStageCoordinates(this.screenToLocalCoordinates(new Vector2(screenX, screenY)));
        float d = worldTouchLocation.dst(this.getX(), this.getY());

        if(d < DEAD_AREA)
        {
            this.pointerID = pointer;
            return true;
        }

        return false;
    }

    /**
     * Called when a finger was lifted or a mouse button was released. The button parameter will be {@link Input.Buttons#LEFT} on iOS.
     *
     * @param screenX
     * @param screenY
     * @param pointer the pointer for the event.
     * @param button  the button   @return whether the input was processed
     */
    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button)
    {
        if(this.pointerID != pointer)
            return false;

        this.joystickInnerPosition.x = this.getX();
        this.joystickInnerPosition.y = this.getY();
        this.pointerID = -1;
        return true;
    }

    /**
     * Called when a finger or the mouse was dragged.
     *
     * @param screenX
     * @param screenY
     * @param pointer the pointer for the event.  @return whether the input was processed
     */
    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer)
    {
        if(pointerID != pointer)
            return false;

        boolean handle = false;

        Vector2 worldTouchLocation = this.localToStageCoordinates(this.screenToLocalCoordinates(new Vector2(screenX, screenY)));
        float d = worldTouchLocation.dst(this.getX(), this.getY());

        if(d < EFFECT_AREA)
        {
            handle = true;
            this.joystickInnerPosition = worldTouchLocation;
        }
        else if(d < DEAD_AREA)
        {
            handle = true;
            double angle = Math.atan2(worldTouchLocation.y - this.getY(), worldTouchLocation.x - this.getX());
            this.joystickInnerPosition.x = (float) (Math.cos(angle) * this.EFFECT_AREA) + this.getX();
            this.joystickInnerPosition.y = (float) (Math.sin(angle) * this.EFFECT_AREA) + this.getY();
        }
        else
        {
            this.joystickInnerPosition.x = this.getX();
            this.joystickInnerPosition.y = this.getY();
        }


        return handle;
    }

    //region Keys
    /**
     * Called when a key was pressed
     *
     * @param keycode one of the constants in {@link Input.Keys}
     * @return whether the input was processed
     */
    @Override
    public boolean keyDown(int keycode)
    {
        return false;
    }

    /**
     * Called when a key was released
     *
     * @param keycode one of the constants in {@link Input.Keys}
     * @return whether the input was processed
     */
    @Override
    public boolean keyUp(int keycode)
    {
        return false;
    }

    /**
     * Called when a key was typed
     *
     * @param character The character
     * @return whether the input was processed
     */
    @Override
    public boolean keyTyped(char character)
    {
        return false;
    }
    //endregion

    //region Mouse & Scroll
    /**
     * Called when the mouse was moved without any buttons being pressed. Will not be called on iOS.
     *
     * @param screenX
     * @param screenY
     * @return whether the input was processed
     */
    @Override
    public boolean mouseMoved(int screenX, int screenY)
    {
        return false;
    }

    /**
     * Called when the mouse wheel was scrolled. Will not be called on iOS.
     *
     * @param amount the scroll amount, -1 or 1 depending on the direction the wheel was scrolled.
     * @return whether the input was processed.
     */
    @Override
    public boolean scrolled(int amount)
    {
        return false;
    }
    //endregion
}
