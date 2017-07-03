package com.convicted.game.drawable.ui.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.convicted.game.ConvictedGame;
import com.convicted.game.data.Asset;
import com.convicted.game.data.Configuration;
import com.convicted.game.drawable.ui.widget.Joystick;

public class GameScreen extends ConvictedScreen
{
    private Sprite sprite;
    private Joystick joystick;

    public GameScreen(ConvictedGame game)
    {
        super(game);
    }

    @Override
    public void load()
    {
        Gdx.app.log("GameScreen", "load");
        this.sprite = new Sprite(this.game.getAssetManager().<Texture>get(Asset.ROGUE));
        this.sprite.setScale(1.5f);

        this.joystick = new Joystick(
                this.game.getConfiguration().getInteger(Configuration.PREFS_MOVE_JOYSTICK_ALIGN_X),
                this.game.getConfiguration().getInteger(Configuration.PREFS_MOVE_JOYSTICK_ALIGN_Y));

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
        this.game.getAssetManager().dispose();
        this.joystick.dispose();
        super.dispose();
    }
}
