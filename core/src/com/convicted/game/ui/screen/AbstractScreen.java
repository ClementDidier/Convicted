package com.convicted.game.ui.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.convicted.game.ConvictedGame;

public abstract class AbstractScreen extends Stage implements com.badlogic.gdx.Screen
{
    private final static Color CLEAR_COLOR = new Color(Color.BLACK);
    private final ConvictedGame game;

    public float alpha;

    public AbstractScreen(final ConvictedGame game)
    {
        this.game = game;
    }

    @Override
    public void render(float delta)
    {
        Gdx.gl.glClearColor(CLEAR_COLOR.r, CLEAR_COLOR.g, CLEAR_COLOR.b, CLEAR_COLOR.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        this.act(delta);

        this.draw();


    }

    @Override
    public void draw()
    {
        super.draw();
    }

    public final ConvictedGame getGame()
    {
        return this.game;
    }
}
