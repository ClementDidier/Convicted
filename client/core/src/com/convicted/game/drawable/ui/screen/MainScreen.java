package com.convicted.game.drawable.ui.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
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
    private Sprite background;

    public MainScreen(ConvictedGame game)
    {
        super(game);
    }

    @Override
    public void load()
    {
        this.game.getAssetManager().load(Asset.MAINSCREEN_BACKGROUND);
        this.game.getAssetManager().load(Asset.START_BUTTON);
        this.game.getAssetManager().finishLoading();

        this.background = new Sprite(this.game.getAssetManager().<Texture>get(Asset.MAINSCREEN_BACKGROUND));

        this.buttonStart = new Button(
                this.game.getConfiguration().getInteger(Configuration.PREFS_MAIN_BUTTON_START_ALIGN_X),
                this.game.getConfiguration().getInteger(Configuration.PREFS_MAIN_BUTTON_START_ALIGN_Y),
                this.game.getAssetManager().<Texture>get(Asset.START_BUTTON));
        this.addWidget(this.buttonStart);

        this.buttonStart.addListener(new ButtonClickListener()
        {
            @Override
            public void onClick(InputEvent event, int x, int y)
            {
                ScreenNavigator.navigateTo(ConvictedScreen.GAME, FadeIn(1000), FadeOut(1000));
            }
        });
    }

    @Override
    public void unload()
    {
        this.game.getAssetManager().unload(Asset.MAINSCREEN_BACKGROUND);
        this.game.getAssetManager().unload(Asset.START_BUTTON);

        this.removeWidget(this.buttonStart);
    }

    @Override
    public void show()
    {
        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(this.buttonStart);
        Gdx.input.setInputProcessor(multiplexer);
    }

    @Override
    public void update(float delta)
    {
        //this.buttonStart.update(delta);
    }

    @Override
    public void draw(ConvictedBatch batch)
    {
        batch.draw(this.background);
        //batch.draw(this.buttonStart);
    }

    @Override
    public void dispose()
    {
        this.game.getAssetManager().dispose();
        super.dispose();
    }
}
