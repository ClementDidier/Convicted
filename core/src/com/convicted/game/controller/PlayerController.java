package com.convicted.game.controller;

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

    }
}
