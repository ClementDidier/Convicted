package com.convicted.game.entity.monsters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.RunnableAction;
import com.convicted.game.action.EntityController;
import com.convicted.game.entity.Monster;
import com.convicted.game.ui.screen.GameContext;

import java.util.Random;

public class Grub extends Monster {

    public Grub(final GameContext context)
    {
        super(new Texture(Gdx.files.internal("grub.png")), context);
        this.speed = 10f;
        this.setController(new EntityController(this)
        {
            @Override
            public boolean act(float delta)
            {
                this.addAction(
                        Actions.moveTo(context.player.getX(), context.player.getY(), 2f));
                return true;
            }
        });
    }
}
