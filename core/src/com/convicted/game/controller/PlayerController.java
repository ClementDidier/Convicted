package com.convicted.game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.convicted.game.ConvictedRun;
import com.convicted.game.drawable.entity.character.Character;
import com.convicted.game.drawable.entity.character.Player;
import com.convicted.game.drawable.ui.widget.Joystick;
import com.convicted.game.utils.Timer;

public class PlayerController extends CharacterController
{
    private Joystick movementJoystick;
    private Joystick fireJoystick;
    private Timer fireCooldown;

    public PlayerController(Player player, ConvictedRun world, Joystick movementJoystick, Joystick fireJoystick)
    {
         super(player, world);
         this.movementJoystick = movementJoystick;
         this.fireJoystick = fireJoystick;
        this.fireCooldown = new Timer();
    }

    @Override
    public void act(float delta)
    {
        this.fireCooldown.update(delta);

        float pushedValue = (float)this.movementJoystick.getPushedValue();

        if(this.movementJoystick.moved() && pushedValue > 0.20f)
        {
            float val = pushedValue * 10f * Character.DEFAULT_SPEED;
            Vector2 dir = this.movementJoystick.getDirectionalVector();
            this.character.setSpeed(val);
            this.character.moveBy(dir.x, dir.y);
        }

        if(this.fireJoystick.moved() && this.fireCooldown.ring(this.character.getFireCooldown()))
        {
            Vector2 dir = this.fireJoystick.getDirectionalVector();
            this.world.projectileManager.fireProjectile(
                    this.character.getPosition().x + this.character.getBounds().getWidth() / 2,
                    this.character.getPosition().y + this.character.getBounds().getHeight() / 2,
                    dir.x, dir.y, 450);
            this.fireCooldown.reset();
        }
    }
}
