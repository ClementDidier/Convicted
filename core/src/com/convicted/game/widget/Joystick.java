package com.convicted.game.widget;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import static com.convicted.game.utils.Converter.convertRadianToDegree;
import static com.convicted.game.utils.Converter.convertTouchYAxis;

public class Joystick extends Sprite implements InputProcessor
{
    private final static int DEFAULT_RADIUS = 200;
    private final static int DEFAULT_INNER_RADIUS = 100;
    private final static int DEPTH_AREA = 500;
    private final static int EFFECT_AREA = 200;

    private int radius, innerRadius;
    private Boolean isUp;
    private ShapeRenderer renderer;
    private Vector2 joystickInnerPosition;

    public Joystick(int x, int y)
    {
        this.setOrigin(x, y);
        this.radius = DEFAULT_RADIUS;
        this.innerRadius = DEFAULT_INNER_RADIUS;

        this.isUp = false;
        this.renderer = new ShapeRenderer();
        this.joystickInnerPosition = new Vector2();
    }

    @Override
    public void draw(Batch batch)
    {
        this.renderer.setProjectionMatrix(batch.getProjectionMatrix());

        this.renderer.begin(ShapeRenderer.ShapeType.Filled);
        this.renderer.setColor(Color.DARK_GRAY);
        this.renderer.circle(this.getOriginX(), this.getOriginY(), this.radius);
        this.renderer.setColor(Color.GRAY);
        this.renderer.circle(this.joystickInnerPosition.x, this.joystickInnerPosition.y,
                this.innerRadius);
        this.renderer.end();
    }

    private double getAngle(int x, int y)
    {
        return Math.atan2(x - this.getOriginX(), y - this.getOriginY());
    }

    /**
     * Calcule et retourne la direction du joystick
     * @param degree Le degree à prendre en compte pour le calcul de la direction
     * @return La direction du joystick en fonction du degré donné
     */
    private JoystickDirection getJoystickDirection(double degree)
    {
        // Divide circle in 8 portions : 360 / 8 = 45 degree
        // 0 => top (vertical axe from Origin)
        // 45 / 2.0 = 22.5

        degree = (degree - 22.5d < 0) ? 0 : degree - 22.5d;
        int index = (int) Math.floor(degree / 45);

        if(index == 8) // Modulo like
        {
            return JoystickDirection.Top;
        }

        return JoystickDirection.values()[index + 1];
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
        this.joystickInnerPosition = new Vector2(this.getOriginX(), this.getOriginY());
        return false;
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
        // Convertion de l'axe Y pour l'affichage
        screenY = convertTouchYAxis(screenY);


        double degree = convertRadianToDegree(getAngle(screenX, screenY));

        double angle = Math.atan2(screenY - this.getOriginY(), screenX - this.getOriginX());

        Vector2 touchLocation = new Vector2(screenX, screenY);
        float d = touchLocation.dst(this.getOriginX(), this.getOriginY());

        if(d < EFFECT_AREA)
            this.joystickInnerPosition = touchLocation;
        else if(d < DEPTH_AREA)
            this.joystickInnerPosition = new Vector2(
                    (float)(Math.cos(angle) * this.radius) + this.getOriginX(),
                    (float)(Math.sin(angle) * this.radius) + this.getOriginY());
        else
            this.joystickInnerPosition = new Vector2(this.getOriginX(), this.getOriginY());
        return true;
    }

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
