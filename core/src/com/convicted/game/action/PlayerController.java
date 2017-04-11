package com.convicted.game.action;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.convicted.game.entity.Character;
import com.convicted.game.ui.screen.GameContext;
import com.convicted.game.ui.widget.DirectionType;
import com.convicted.game.ui.widget.IJoystick;
import com.convicted.game.ui.widget.JoystickDirection;
import com.convicted.game.utils.Timer;

public class PlayerController extends EntityController
{
    private IJoystick moveJoystick;
    private IJoystick fireJoystick;

    private Timer fireTimer;

    public PlayerController(GameContext context, Character actor, IJoystick moveJoystick, IJoystick fireJoystick)
    {
        super(context, actor);
        this.moveJoystick = moveJoystick;
        this.fireJoystick = fireJoystick;

        this.fireTimer = new Timer();
    }

    @Override
    public void act(float delta)
    {
        this.fireTimer.act(delta);

        if(this.moveJoystick.moved())
        {
            this.actions.add(new MoveAction(this.context, this.actor,
                    this.moveJoystick.getDirection(DirectionType.Orthogonal).getX() * (float) this.moveJoystick.getPushedValue() * 10f,
                    this.moveJoystick.getDirection(DirectionType.Orthogonal).getY() * (float) this.moveJoystick.getPushedValue() * 10f));
        }

        if(this.fireJoystick.moved() && this.fireTimer.wait(0.5f))
        {
            JoystickDirection direction = this.fireJoystick.getDirection(DirectionType.Orthogonal);
            this.actions.add(new FireAction(this.context, this.actor, new Vector2(direction.getX(), direction.getY()), actor.getProjectileSpeed()));
        }

        for(int i = 0; i < this.actions.size(); i++)
        {
            GameAction action = this.actions.pop();
            if(action.isLegal())
            {
                Action act = action.generate(delta);
                if(act != null)
                    this.actor.addAction(act);
            }

        }
    }
}
