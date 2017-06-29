package com.convicted.game.drawable.ui.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.convicted.game.drawable.ui.widget.Joystick;

public class GameScreen extends ConvictedScreen
{
    private Sprite sprite;
    private Joystick joystick;

    public GameScreen()
    {
        super();
    }

    @Override
    public void load()
    {
        Gdx.app.log("GameScreen", "load");
        this.sprite = new Sprite(new Texture(Gdx.files.internal("rogue.png")));
        this.joystick = new Joystick((int)this.camera.viewportHeight * 1 / 5, (int)this.camera.viewportHeight * 1 / 5);

        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(this.joystick.getProcessor());
        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    @Override
    public void show()
    {
        Gdx.app.log("GameScreen", "shown");
    }

    @Override
    public void update(float delta)
    {

    }

    @Override
    public void draw(ConvictedBatch batch)
    {
        batch.draw(this.sprite);
        batch.draw(this.joystick);
    }

    @Override
    public void dispose()
    {
        this.sprite.getTexture().dispose();
        this.joystick.dispose();
        super.dispose();
    }
}
