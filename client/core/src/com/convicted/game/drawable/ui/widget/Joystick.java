package com.convicted.game.drawable.ui.widget;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;
import com.convicted.game.ConvictedGame;
import com.convicted.game.drawable.ui.screen.ConvictedBatch;
import com.convicted.game.drawable.ui.screen.ConvictedScreen;

public class Joystick extends Widget implements InputProcessor, Disposable
{
    private final static int DEFAULT_RADIUS = (int)(0.1f * ConvictedScreen.VIEWPORT.x); // Taille par défaut du support joystick
    private final static int DEFAULT_INNER_RADIUS = DEFAULT_RADIUS / 2;                 // Taille par défaut du joystick
    private final static int DEAD_AREA_FACTOR = 2;
    private final static Color FOREGROUND_COLOR = Color.DARK_GRAY;
    private final static Color INNER_FOREGROUND_COLOR = Color.GRAY;
    private final static float DEFAULT_OPACITY = 0.3f;

    private float opacity;
    private int radius, innerRadius, effectArea, deadArea;
    private ShapeRenderer renderer;
    private Vector2 joystickInnerPosition;
    private int pointerID; // Prise en compte des gestures multiples

    public Joystick(int x, int y)
    {
        super(x, y);

        this.opacity = DEFAULT_OPACITY;
        this.radius = DEFAULT_RADIUS;
        this.innerRadius = DEFAULT_INNER_RADIUS;
        this.effectArea = this.radius - this.innerRadius;
        this.deadArea = DEAD_AREA_FACTOR * this.radius;

        this.joystickInnerPosition = new Vector2(x, y);
        this.renderer = new ShapeRenderer();
        this.pointerID = -1;

        this.update(0);
    }

    public void setScale(float scaleXY)
    {
        this.radius *= scaleXY;
        this.innerRadius *= scaleXY;
        this.effectArea = this.radius - this.innerRadius;
        this.deadArea = DEAD_AREA_FACTOR * this.radius;
    }

    public void setOpacity(float opacity)
    {
        this.opacity = opacity;
    }

    @Override
    public void update(float delta)
    {

    }

    @Override
    public void draw(ConvictedBatch batch)
    {
        batch.end();
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

        this.renderer.setProjectionMatrix(batch.getProjectionMatrix());
        this.renderer.begin(ShapeRenderer.ShapeType.Filled);
        this.renderer.setColor(FOREGROUND_COLOR.r, FOREGROUND_COLOR.g, FOREGROUND_COLOR.b, batch.getAlpha()/1f * this.opacity);
        this.renderer.circle(this.getPosition().x, this.getPosition().y, this.radius);
        this.renderer.setColor(INNER_FOREGROUND_COLOR.r, INNER_FOREGROUND_COLOR.g, INNER_FOREGROUND_COLOR.b, batch.getAlpha()/1f * this.opacity);
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
        return Math.atan2(vector.x - this.getPosition().x, vector.y - this.getPosition().y);
    }

    /**
     * Obtient le degrée d'orientation du joystick
     * @return Le degrée d'orientation du joystick compris entre [0; 360] et dont le degré 0 est disposé sur l'axe vertical en haut
     */
    public double getDegree()
    {
        double angleRadian = getAngle(this.joystickInnerPosition);

        if(angleRadian < 0)
            return 360 + angleRadian * 180 / Math.PI;
        return angleRadian * 180 / Math.PI;
    }

    /**
     * Obtient le vecteur directionnel actuel du joystick
     * @return Le vecteur directionnel actuel (x, y) où x et y compris entre [0; 1]
     */
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
    public double getPushedValue()
    {
        return this.joystickInnerPosition.dst(this.getPosition().x, this.getPosition().y) / this.effectArea;
    }

    public boolean moved()
    {
        return getPushedValue() > 0;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button)
    {
        Vector2 worldTouchLocation = ConvictedGame.getInstance().getScreen().screenToLocalCoordinates(screenX, screenY);
        float distanceBetweenTouchAndJoystick = worldTouchLocation.dst(this.getPosition().x, this.getPosition().y);

        if(distanceBetweenTouchAndJoystick < this.deadArea)
        {
            this.pointerID = pointer;
            return true;
        }

        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button)
    {
        if(this.pointerID != pointer)
            return false;

        this.joystickInnerPosition.x = this.getPosition().x;
        this.joystickInnerPosition.y = this.getPosition().y;
        this.pointerID = -1;
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer)
    {
        if(pointerID != pointer)
            return false;

        boolean isHandle = false;

        Vector2 worldTouchLocation = ConvictedGame.getInstance().getScreen().screenToLocalCoordinates(screenX, screenY);

        float distanceBetweenTouchAndJoystick = worldTouchLocation.dst(this.getPosition().x, this.getPosition().y);

        if(distanceBetweenTouchAndJoystick < this.effectArea)
        {
            isHandle = true;
            this.joystickInnerPosition = worldTouchLocation;
        }
        else if(distanceBetweenTouchAndJoystick < this.deadArea)
        {
            isHandle = true;
            double angle = Math.atan2(worldTouchLocation.y - this.getPosition().y, worldTouchLocation.x - this.getPosition().x);
            this.joystickInnerPosition.x = (float) (Math.cos(angle) * this.effectArea) + this.getPosition().x;
            this.joystickInnerPosition.y = (float) (Math.sin(angle) * this.effectArea) + this.getPosition().y;
        }
        else
        {
            this.joystickInnerPosition.x = this.getPosition().x;
            this.joystickInnerPosition.y = this.getPosition().y;
        }

        return isHandle;
    }

    @Override
    public void dispose()
    {
        if(this.renderer != null)
            this.renderer.dispose();
    }

    @Override
    public boolean keyDown(int keycode)
    {
        return false;
    }

    @Override
    public boolean keyUp(int keycode)
    {
        return false;
    }

    @Override
    public boolean keyTyped(char character)
    {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY)
    {
        return false;
    }

    @Override
    public boolean scrolled(int amount)
    {
        return false;
    }
}
