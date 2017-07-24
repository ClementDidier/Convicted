package com.convicted.game.drawable.ui.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.convicted.game.ConvictedGame;
import com.convicted.game.data.Asset;
import com.convicted.game.data.Configuration;
import com.convicted.game.drawable.ui.widget.Button;
import com.convicted.game.utils.ButtonClickListener;

import static com.convicted.game.drawable.ui.screen.transition.Transitions.FadeIn;
import static com.convicted.game.drawable.ui.screen.transition.Transitions.FadeOut;

public class MainScreen extends ConvictedScreen
{
    private Button buttonStart;
    private Sprite sprite;

    public MainScreen(ConvictedGame game)
    {
        super(game);
    }

    @Override
    public void load()
    {
        this.game.getAssetManager().load(Asset.START_BUTTON);
        this.game.getAssetManager().finishLoading();

        Gdx.app.log("MainScreen", "load");
        this.sprite = new Sprite(this.game.getAssetManager().<Texture>get(Asset.UNKNOW));
        this.buttonStart = new Button(
                this.game.getConfiguration().getInteger(Configuration.PREFS_MAIN_BUTTON_START_ALIGN_X),
                this.game.getConfiguration().getInteger(Configuration.PREFS_MAIN_BUTTON_START_ALIGN_Y),
                this.game.getAssetManager().<Texture>get(Asset.START_BUTTON));
        this.buttonStart.setScale(5f);

        this.buttonStart.addListener(new ButtonClickListener()
        {
            @Override
            public void onClick(InputEvent event, int x, int y)
            {
                ScreenNavigator.navigateTo(ConvictedScreen.GAME, FadeIn(1000), FadeOut(3000));
            }
        });

        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(this.buttonStart);
        Gdx.input.setInputProcessor(multiplexer);
    }

    @Override
    public void show()
    {
        Gdx.app.log("MenuScreen", "shown");
    }

    @Override
    public void update(float delta)
    {
        this.buttonStart.update(delta);
    }

    @Override
    public void draw(ConvictedBatch batch)
    {
        batch.draw(this.sprite);
        batch.draw(this.buttonStart);
    }

    @Override
    public void dispose()
    {
        this.game.getAssetManager().dispose();
        super.dispose();
    }
}
