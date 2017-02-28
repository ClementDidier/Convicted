package com.convicted.game.ui.widget;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import static com.convicted.game.utils.Converter.convertRadianToDegree;
import static com.convicted.game.utils.Converter.convertTouchYAxis;

public class SampleJoystick extends Widget implements IJoystick, InputProcessor
{
    private final static int DEFAULT_RADIUS = 200;          // Taille par défaut du support joystick
    private final static int DEFAULT_INNER_RADIUS = 100;    // Taille par défaut du joystick
    private final static int DEAD_AREA = 500;               // Zone de fin de prise en main de la gesture
    private final static int EFFECT_AREA = 200;             // Zone d'effet maximale du joystick
    private final static int DEGREES = 360;
    private final static int DIRECTIONS_COUNT = 8;          // Nombre de direction (Besoin de modifier JoystickDirection
                                                            // si modification)

    private int radius, innerRadius;
    private Boolean isUp;
    private ShapeRenderer renderer;
    private Vector2 joystickInnerPosition;

    public SampleJoystick(int x, int y)
    {
        super(x, y);

        this.radius = DEFAULT_RADIUS;
        this.innerRadius = DEFAULT_INNER_RADIUS;

        this.joystickInnerPosition = new Vector2(x, y);
        this.isUp = false;
        this.renderer = new ShapeRenderer();
    }

    @Override
    public void draw(Batch batch)
    {
        this.renderer.setProjectionMatrix(batch.getProjectionMatrix());

        this.renderer.begin(ShapeRenderer.ShapeType.Filled);
        this.renderer.setColor(Color.DARK_GRAY);
        this.renderer.circle(this.getOrigin().x, this.getOrigin().y, this.radius);
        this.renderer.setColor(Color.GRAY);
        this.renderer.circle(this.joystickInnerPosition.x, this.joystickInnerPosition.y, this.innerRadius);
        this.renderer.end();
    }

    /**
     * Obtient l'angle en radian correspondant entre les coordonnées données et le point de degré 0
     * @param vector Les coordonnées du point à prendre en compte
     * @return La valeur de l'angle en radian calculé
     */
    private double getAngle(Vector2 vector)
    {
        return Math.atan2(vector.x - this.getOrigin().x, vector.y - this.getOrigin().y);
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
        return this.joystickInnerPosition.dst(this.getOrigin().x, this.getOrigin().y) / EFFECT_AREA;
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

        return JoystickDirection.values()[index + 1];
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
        this.joystickInnerPosition = this.getOrigin();
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

        double angle = Math.atan2(screenY - this.getOrigin().y, screenX - this.getOrigin().x);

        Vector2 touchLocation = new Vector2(screenX, screenY);
        float d = touchLocation.dst(this.getOrigin().x, this.getOrigin().y);

        if(d < EFFECT_AREA)
            this.joystickInnerPosition = touchLocation;
        else if(d < DEAD_AREA)
            this.joystickInnerPosition = new Vector2(
                    (float)(Math.cos(angle) * this.radius) + this.getOrigin().x,
                    (float)(Math.sin(angle) * this.radius) + this.getOrigin().y);
        else
            this.joystickInnerPosition = new Vector2(this.getOrigin().x, this.getOrigin().y);
        return true;
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
