package com.convicted.game.ui.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.convicted.game.ConvictedGame;

public abstract class AbstractScreen extends Stage implements com.badlogic.gdx.Screen
{
    private final static Color CLEAR_COLOR = new Color(Color.WHITE);
    private final static Vector2 VIEWPORT = new Vector2(1280, 720);
    private final ConvictedGame game;

    public AbstractScreen(final ConvictedGame game)
    {
        this.game = game;
        FitViewport viewport = new FitViewport(VIEWPORT.x, VIEWPORT.y);

        this.setViewport(viewport);
        this.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);
        this.getViewport().apply();
    }

    @Override
    public final void render(float delta)
    {
        Gdx.gl.glClearColor(CLEAR_COLOR.r, CLEAR_COLOR.g, CLEAR_COLOR.b, CLEAR_COLOR.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        this.update(delta);
        super.act(delta);

        this.draw(this.getBatch());
        super.draw();
    }

    public abstract void update(float delta);

    public abstract void draw(Batch batch);

    public final ConvictedGame getGame()
    {
        return this.game;
    }
}
