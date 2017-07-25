package com.convicted.game.drawable.ui.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.convicted.game.ConvictedGame;
import com.convicted.game.drawable.Drawable;
import com.convicted.game.drawable.ui.screen.effect.ScreenEffect;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class ConvictedScreen implements com.badlogic.gdx.Screen, Drawable
{
    private final static Color CLEAR_COLOR = new Color(Color.BLACK);

    public final static Vector2 VIEWPORT = new Vector2(1280, 720);
    public final static SplashScreen SPLASH = new SplashScreen(ConvictedGame.getInstance());
    public final static MainScreen MENU = new MainScreen(ConvictedGame.getInstance());
    public final static GameScreen GAME = new GameScreen(ConvictedGame.getInstance());

    protected ConvictedBatch batch;
    protected OrthographicCamera camera;
    protected Viewport viewport;
    protected ConvictedGame game;

    private boolean initialized;
    private List<ScreenEffect> effects;
    private Vector2 cameraOrigin;

    protected ConvictedScreen(ConvictedGame game)
    {
        this.batch = new ConvictedBatch();
        this.camera = new OrthographicCamera();
        this.viewport = new FitViewport(VIEWPORT.x, VIEWPORT.y, camera);
        this.viewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);
        this.viewport.apply();
        this.game = game;

        this.initialized = false;
        this.effects = new ArrayList<ScreenEffect>();
    }

    /**
     * Initialise l'écran une et une seule fois, et appel la fonction enfant de chargement
     */
    public final void initialize()
    {
        if(!this.initialized)
        {
            this.load();
            this.cameraOrigin = new Vector2(this.camera.position.x, this.camera.position.y);
            this.initialized = true;
        }
    }

    /**
     * Fonction enfant de chargement, appelée une et une seule fois à l'ouverture de l'écran, avant son affichage
     */
    public abstract void load();

    /**
     * Ajoute et lance un nouvel effet d'écran
     * @param effect L'effet à lancer sur l'écran actuel
     */
    public void startEffect(ScreenEffect effect)
    {
        this.effects.add(effect);
    }

    @Override
    public final void render(float delta)
    {
        if(this.initialized)
        {
            this.camera.position.x = this.cameraOrigin.x;
            this.camera.position.y = this.cameraOrigin.y;

            this.update(delta);
            this.updateEffects(delta);
            this.camera.update();

            Gdx.gl.glClearColor(CLEAR_COLOR.r, CLEAR_COLOR.g, CLEAR_COLOR.b, CLEAR_COLOR.a);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

            batch.setProjectionMatrix(this.camera.combined);
            batch.begin();
            this.draw(batch);
            batch.end();
        }
    }

    /**
     * Mets à jour les effets d'écran
     * @param delta Le temps passé en milisecondes depuis la dernière mise à jour
     */
    private void updateEffects(float delta)
    {
        Iterator<ScreenEffect> iterator = this.effects.iterator();
        while(iterator.hasNext())
        {
            ScreenEffect effect = iterator.next();

            if(effect.isFinished())
                iterator.remove();
            else
                effect.update(delta, camera);
        }
    }

    /**
     * Converti les coordonnées d'écran vers des coordonnées relatives au monde du jeu
     * @param x L'alignement horizontal (X) de la gesture detectée sur l'écran
     * @param y L'alignement vertical (Y) de la gesture detectée sur l'écran
     * @return Les coordonnées converties
     */
    public Vector2 screenToLocalCoordinates(int x, int y)
    {
        Vector3 vector = this.camera.unproject(new Vector3(x, y, 0));
        return new Vector2(vector.x, vector.y);
    }

    @Override
    public void resize(int width, int height)
    {

    }

    @Override
    public void pause()
    {

    }

    @Override
    public void resume()
    {

    }

    @Override
    public void hide()
    {

    }

    @Override
    public void dispose()
    {
        this.batch.dispose();
        this.batch = null;
    }

    public ConvictedBatch getBatch()
    {
        return this.batch;
    }
}
