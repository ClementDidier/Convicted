package com.convicted.game.drawable.ui.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.convicted.game.ConvictedGame;
import com.convicted.game.ConvictedRun;
import com.convicted.game.controller.MonsterController;
import com.convicted.game.controller.PlayerController;
import com.convicted.game.data.Asset;
import com.convicted.game.data.Configuration;
import com.convicted.game.drawable.entity.character.Monster;
import com.convicted.game.drawable.entity.character.Player;
import com.convicted.game.drawable.ui.screen.effect.ShakeEffect;
import com.convicted.game.drawable.ui.widget.Button;
import com.convicted.game.drawable.ui.widget.Joystick;
import com.convicted.game.utils.ButtonClickListener;

public class GameScreen extends ConvictedScreen
{
    private ConvictedRun world;
    private Joystick movementJoystick, fireJoystick;
    private Button button;

    public GameScreen(ConvictedGame game)
    {
        super(game);
    }

    @Override
    public void load()
    {
        this.game.getAssetManager().load(Asset.START_BUTTON);
        this.game.getAssetManager().load(Asset.ROGUE);
        this.game.getAssetManager().load(Asset.GRUB);
        this.game.getAssetManager().finishLoading();

        this.world = new ConvictedRun(this.game);

        this.button = new Button(150, 600, this.game.getAssetManager().<Texture>get(Asset.START_BUTTON));


        this.movementJoystick = new Joystick(
                this.game.getConfiguration().getInteger(Configuration.PREFS_MOVE_JOYSTICK_ALIGN_X),
                this.game.getConfiguration().getInteger(Configuration.PREFS_MOVE_JOYSTICK_ALIGN_Y));
        this.addWidget(this.movementJoystick);

        this.fireJoystick = new Joystick(
                this.game.getConfiguration().getInteger(Configuration.PREFS_FIRE_JOYSTICK_ALIGN_X),
                this.game.getConfiguration().getInteger(Configuration.PREFS_FIRE_JOYSTICK_ALIGN_Y));
        this.addWidget(this.fireJoystick);

        final Player player = new Player(this.game.getAssetManager().<Texture>get(Asset.ROGUE));
        player.setController(new PlayerController(player, world, this.movementJoystick, this.fireJoystick));
        player.setPosition(500, 200);
        this.world.addEntity(player);

        this.button.addListener(new ButtonClickListener() {
            @Override
            public void onClick(InputEvent event, int x, int y)
            {
                startEffect(new ShakeEffect(10f, 500f));

                for(int i = 0; i < 100; i++)
                    world.projectileManager.fireProjectile(
                            player.getPosition().x + player.getBounds().getWidth() / 2,
                            player.getPosition().y + player.getBounds().getHeight() / 2,
                            (float)Math.cos(i), (float)Math.sin(i), 200);
                //ScreenNavigator.navigateTo(ConvictedScreen.MENU);//, FadeIn(1000f), FadeOut(1000f));
            }
        });
        this.addWidget(this.button);

        Monster monster = new Monster(this.game.getAssetManager().<Texture>get(Asset.GRUB));
        monster.setController(new MonsterController(monster, world));
        monster.setPosition(500, 200);
        this.world.addEntity(monster);
    }

    @Override
    public void unload()
    {
        this.game.getAssetManager().unload(Asset.START_BUTTON);
        this.game.getAssetManager().unload(Asset.ROGUE);
        this.game.getAssetManager().unload(Asset.GRUB);

        this.removeWidget(this.button);
        this.removeWidget(this.movementJoystick);
    }

    @Override
    public void show()
    {
        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(this.movementJoystick);
        inputMultiplexer.addProcessor(this.fireJoystick);
        inputMultiplexer.addProcessor(this.button);
        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    @Override
    public void update(float delta)
    {
        this.world.update(delta);
    }

    @Override
    public void draw(ConvictedBatch batch)
    {
        this.world.draw(batch);
    }

    @Override
    public void dispose()
    {
        this.game.getAssetManager().dispose();
        this.movementJoystick.dispose();
        super.dispose();
    }
}
