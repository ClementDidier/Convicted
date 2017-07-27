package com.convicted.game.drawable.ui.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.convicted.game.ConvictedGame;
import com.convicted.game.controller.MonsterController;
import com.convicted.game.data.Asset;
import com.convicted.game.data.Configuration;
import com.convicted.game.drawable.entity.character.Monster;
import com.convicted.game.drawable.environment.Environment;
import com.convicted.game.drawable.ui.screen.effect.ShakeEffect;
import com.convicted.game.drawable.ui.screen.transition.SequenceTransition;
import com.convicted.game.drawable.ui.widget.Button;
import com.convicted.game.drawable.ui.widget.Joystick;
import com.convicted.game.utils.ButtonClickListener;

import static com.convicted.game.drawable.ui.screen.transition.Transitions.FadeIn;
import static com.convicted.game.drawable.ui.screen.transition.Transitions.FadeOut;

public class GameScreen extends ConvictedScreen
{
    private Monster monster;
    private Joystick joystick;
    private Button button;
    private Environment environment;

    public GameScreen(ConvictedGame game)
    {
        super(game);
    }

    @Override
    public void load()
    {
        this.game.getAssetManager().load(Asset.START_BUTTON);
        this.game.getAssetManager().load(Asset.GRUB);
        this.game.getAssetManager().finishLoading();

        this.environment = new Environment(this.game);

        this.button = new Button(100, 600, this.game.getAssetManager().<Texture>get(Asset.START_BUTTON));
        this.button.addListener(new ButtonClickListener() {
            @Override
            public void onClick(InputEvent event, int x, int y)
            {
                startEffect(new ShakeEffect(10f, 500f));
                //ScreenNavigator.navigateTo(ConvictedScreen.MENU);//, FadeIn(1000f), FadeOut(1000f));
            }
        });
        this.addWidget(this.button);

        this.joystick = new Joystick(
                this.game.getConfiguration().getInteger(Configuration.PREFS_MOVE_JOYSTICK_ALIGN_X),
                this.game.getConfiguration().getInteger(Configuration.PREFS_MOVE_JOYSTICK_ALIGN_Y));
        this.addWidget(this.joystick);

        this.monster = new Monster(this.game.getAssetManager().<Texture>get(Asset.GRUB));
        this.monster.setController(new MonsterController(this.monster));
        this.monster.setPosition(500, 200);
    }

    @Override
    public void unload()
    {
        this.game.getAssetManager().unload(Asset.START_BUTTON);
        this.game.getAssetManager().unload(Asset.GRUB);

        this.removeWidget(this.button);
        this.removeWidget(this.joystick);
    }

    @Override
    public void show()
    {
        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(this.joystick);
        inputMultiplexer.addProcessor(this.button);
        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    @Override
    public void update(float delta)
    {
        this.environment.update(delta);
        this.monster.update(delta);
    }

    @Override
    public void draw(ConvictedBatch batch)
    {
        batch.draw(this.environment);
        batch.draw(this.monster);
    }

    @Override
    public void dispose()
    {
        this.game.getAssetManager().dispose();
        this.joystick.dispose();
        super.dispose();
    }
}
