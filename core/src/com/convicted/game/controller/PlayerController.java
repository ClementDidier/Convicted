package com.convicted.game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.convicted.game.drawable.entity.character.Character;
import com.convicted.game.drawable.entity.character.Player;
import com.convicted.game.drawable.ui.widget.Joystick;

public class PlayerController extends CharacterController
{
    private Joystick movementJoystick;
    private Joystick fireJoystick;

    public PlayerController(Player player, Joystick movementJoystick, Joystick fireJoystick)
    {
         super(player);
         this.movementJoystick = movementJoystick;
         this.fireJoystick = fireJoystick;
    }

    @Override
    public void act(float delta)
    {
        float pushedValue = (float)this.movementJoystick.getPushedValue();

        if(pushedValue > 0)
        {
            float val = pushedValue * 10f * Character.DEFAULT_SPEED;
            Vector2 dir = this.movementJoystick.getDirectionalVector();
            this.character.setSpeed(val);
            this.character.moveBy(dir.x, dir.y);
        }
    }
}
