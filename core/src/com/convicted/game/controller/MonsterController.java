package com.convicted.game.controller;

import com.convicted.game.drawable.entity.character.Character;
import com.convicted.game.utils.Timer;

import java.util.Random;

public class MonsterController extends CharacterController
{
    private static final int MINIMUM_SPEED = -300;
    private static final int MAXIMUM_SPEED = 300;

    private Random random;
    private Timer timer, moveTimer;
    private int wait;
    private int v, h;

    public MonsterController(Character character)
    {
        super(character);
        this.random = new Random();
        this.timer = new Timer();
        this.moveTimer = new Timer();
        this.wait = 0;
        this.v = 0;
        this.h = 0;
    }

    @Override
    public void act(float delta)
    {
        this.timer.update(delta);
        this.moveTimer.update(delta);

        if(wait == 0)
            wait = this.random.nextInt(4000) + 1000;

        if(timer.ring(wait))
        {
            this.v = MINIMUM_SPEED + this.random.nextInt(MAXIMUM_SPEED - MINIMUM_SPEED);
            this.h = MINIMUM_SPEED + this.random.nextInt(MAXIMUM_SPEED - MINIMUM_SPEED);

            timer.reset();
            wait = 0;
        }

        if(moveTimer.ring(100))
        {
            this.character.setPosition(
                    this.character.getPosition().x + this.h * delta,
                    this.character.getPosition().y + this.v * delta);

            moveTimer.reset();
        }
    }
}
