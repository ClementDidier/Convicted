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
    private final static int DEFAULT_INNER_RADIUS = 75;     // Taille par défaut du joystick
    private final static int DEGREES = 360;
    private final static int DEAD_AREA_FACTOR = 2;
    private final static Color FOREGROUND_COLOR = Color.DARK_GRAY;
    private final static Color INNER_FOREGROUND_COLOR = Color.GRAY;
    private final static float OPACITY = 0.3f;

    private int radius, innerRadius, effectArea, deadArea;
    private ShapeRenderer renderer;
    private Vector2 joystickInnerPosition;
    private int pointerID; // Prise en compte des gestures multiples

    public SampleJoystick(int x, int y)
    {
        super(x, y);

        this.radius = DEFAULT_RADIUS;
        this.innerRadius = DEFAULT_INNER_RADIUS;
        this.effectArea = this.radius - this.innerRadius;
        this.deadArea = DEAD_AREA_FACTOR * this.radius;

        this.joystickInnerPosition = new Vector2(x, y);
        this.renderer = new ShapeRenderer();
        this.pointerID = -1;
    }

    @Override
    public void setScale(float scale)
    {
        this.radius *= scale;
        this.innerRadius *= scale;
        this.effectArea = this.radius - this.innerRadius;
        this.deadArea = DEAD_AREA_FACTOR * this.radius;
    }

    @Override
    public void draw(Batch batch, float parentAlpha)
    {
        batch.end();
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

        this.renderer.setProjectionMatrix(batch.getProjectionMatrix());
        this.renderer.begin(ShapeRenderer.ShapeType.Filled);
        this.renderer.setColor(FOREGROUND_COLOR.r, FOREGROUND_COLOR.g, FOREGROUND_COLOR.b, parentAlpha * OPACITY);
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
     * @return Le degrée d'orientation du joystick compris entre [0; 360] et dont le degré 0 est disposé sur l'axe vertical en haut
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
     * Obtient le vecteur directionnel actuel du joystick
     * @return Le vecteur directionnel actuel (x, y) où x et y compris entre [0; 1]
     */
    @Override
    public Vector2 getDirectionalVector()
    {
        double rad = this.getAngle(this.joystickInnerPosition);
        Vector2 dir = new Vector2((float)Math.sin(rad), (float)Math.cos(rad));
        return dir;
    }

    /**
     * Obtient le pourcentage de poussée du joystick
     * @return Le pourcentage de poussée du joystick compris entre [0; 1]
     */
    @Override
    public double getPushedValue()
    {
        return this.joystickInnerPosition.dst(this.getX(), this.getY()) / this.effectArea;
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
        float distanceBetweenTouchAndJoystick = worldTouchLocation.dst(this.getX(), this.getY());

        if(distanceBetweenTouchAndJoystick < this.deadArea)
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

        boolean isHandle = false;

        Vector2 worldTouchLocation = this.localToStageCoordinates(this.screenToLocalCoordinates(new Vector2(screenX, screenY)));
        float distanceBetweenTouchAndJoystick = worldTouchLocation.dst(this.getX(), this.getY());

        if(distanceBetweenTouchAndJoystick < this.effectArea)
        {
            isHandle = true;
            this.joystickInnerPosition = worldTouchLocation;
        }
        else if(distanceBetweenTouchAndJoystick < this.deadArea)
        {
            isHandle = true;
            double angle = Math.atan2(worldTouchLocation.y - this.getY(), worldTouchLocation.x - this.getX());
            this.joystickInnerPosition.x = (float) (Math.cos(angle) * this.effectArea) + this.getX();
            this.joystickInnerPosition.y = (float) (Math.sin(angle) * this.effectArea) + this.getY();
        }
        else
        {
            this.joystickInnerPosition.x = this.getX();
            this.joystickInnerPosition.y = this.getY();
        }


        return isHandle;
    }

    /**
     * Obtient la direction actuelle du joystick en teannt compte du mode donné
     * @param type Le mode de direction (Isometric : 8 directions, Orthogonal : 8 directions)
     * @return La direction en tenant compte du mode donné
     */
    public JoystickDirection getDirection(DirectionType type)
    {
        JoystickDirection[] directions = JoystickDirection.getDirections();

        final double PORTION = DEGREES / type.getDirectionCount();
        final double DEMI_PORTION = PORTION / 2d;

        double degree = getDegree();

        degree = (degree - DEMI_PORTION < 0) ? 0 : degree - DEMI_PORTION;
        int index = (int) Math.ceil(degree / PORTION);

        int directionIndex = type == DirectionType.Orthogonal ?
                (2 * index) % (directions.length - 1) :
                (index) % (directions.length - 1);

        return directions[directionIndex + 1];
    }

    @Override
    public boolean moved()
    {
        return getPushedValue() > 0;
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
