package com.convicted.game.entity;

import com.badlogic.gdx.graphics.Texture;
import com.convicted.game.ui.screen.GameContext;

public abstract class Monster extends Character
{
    public Monster(Texture texture, GameContext context) {
        super(texture, context);
    }
}
