package com.convicted.game.action;

import com.convicted.game.entity.Character;
import com.convicted.game.ui.widget.DirectionType;
import com.convicted.game.ui.widget.IJoystick;

public class PlayerController extends EntityController
{
    private IJoystick moveJoystick;
    private IJoystick fireJoystick;

    public PlayerController(Character actor, IJoystick moveJoystick, IJoystick fireJoystick)
    {
        super(actor);
        this.moveJoystick = moveJoystick;
        this.fireJoystick = fireJoystick;
    }

    @Override
    public void act(float delta)
    {
        GameAction action = GameAction.NONE;

        if(this.moveJoystick.moved())
        {
            action = new MoveAction(this.actor,
                    this.moveJoystick.getDirection(DirectionType.Orthogonal).getX() * (float) this.moveJoystick.getPushedValue() * 10f,
                    this.moveJoystick.getDirection(DirectionType.Orthogonal).getY() * (float) this.moveJoystick.getPushedValue() * 10f);
        }

        if(action != GameAction.NONE && action.isLegal())
            this.actor.addAction(action.generate(delta));
    }
}
