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
import com.convicted.game.drawable.ui.widget.Button;
import com.convicted.game.drawable.ui.widget.Joystick;
import com.convicted.game.utils.ButtonClickListener;

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
        this.environment = new Environment(this.game);

        this.button = new Button(500, 300, this.game.getAssetManager().<Texture>get(Asset.START_BUTTON));
        this.button.addListener(new ButtonClickListener() {
            @Override
            public void onClick(InputEvent event, int x, int y)
            {
                startEffect(new ShakeEffect(10f, 500f));
            }
        });

        this.joystick = new Joystick(
                this.game.getConfiguration().getInteger(Configuration.PREFS_MOVE_JOYSTICK_ALIGN_X),
                this.game.getConfiguration().getInteger(Configuration.PREFS_MOVE_JOYSTICK_ALIGN_Y));

        this.monster = new Monster(this.game.getAssetManager().<Texture>get(Asset.GRUB));
        this.monster.setController(new MonsterController(this.monster));
        this.monster.setPosition(500, 200);

        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(this.joystick.getProcessor());
        inputMultiplexer.addProcessor(this.button);
        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    @Override
    public void show()
    {

    }

    @Override
    public void update(float delta)
    {
        this.environment.update(delta);
        this.monster.update(delta);
        this.button.update(delta);
    }

    @Override
    public void draw(ConvictedBatch batch)
    {
        batch.draw(this.environment);
        batch.draw(this.monster);
        batch.draw(this.joystick);
        batch.draw(this.button);
    }

    @Override
    public void dispose()
    {
        this.game.getAssetManager().dispose();
        this.joystick.dispose();
        super.dispose();
    }
}
