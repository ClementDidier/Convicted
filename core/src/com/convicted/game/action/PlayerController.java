package com.convicted.game.action;

import com.convicted.game.entity.Character;
import com.convicted.game.ui.widget.IJoystick;

public class PlayerController extends EntityController
{
    private IJoystick moveJoystick;
    private IJoystick fireJoystick;
    private GameAction action;

    public PlayerController(Character actor, IJoystick moveJoystick, IJoystick fireJoystick)
    {
        super(actor);
        this.moveJoystick = moveJoystick;
        this.fireJoystick = fireJoystick;
        this.action = GameAction.NONE;
    }

    @Override
    public void act(float delta)
    {
        this.action = new MoveAction(this.actor,
                this.moveJoystick.getOrthogonalDirection().getX() * (float)this.moveJoystick.getPushedValue() * 10f,
                this.moveJoystick.getOrthogonalDirection().getY() * (float)this.moveJoystick.getPushedValue() * 10f);
    }

    @Override
    public GameAction consumeAction()
    {
        if(this.action == GameAction.NONE)
            return GameAction.NONE;

        GameAction act = this.action;
        this.action = GameAction.NONE; // Consume
        return act;
    }
}
