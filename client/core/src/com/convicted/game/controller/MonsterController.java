package com.convicted.game.controller;

import com.convicted.game.ConvictedRun;
import com.convicted.game.drawable.entity.character.Character;
import com.convicted.game.drawable.ui.screen.ConvictedScreen;
import com.convicted.game.utils.Timer;

import java.util.Random;

public class MonsterController extends CharacterController
{
    private static final int MINIMUM_SPEED = -100;
    private static final int MAXIMUM_SPEED = 100;

    private Random random;
    private Timer timer;
    private int v, h;

    public MonsterController(Character character, ConvictedRun world)
    {
        super(character, world);
        this.random = new Random();
        this.timer = new Timer();
        this.v = 0;
        this.h = 0;
    }

    @Override
    public void act(float delta)
    {
        if(!this.character.isMoving()) {
            this.timer.update(delta);
        }
        else this.timer.reset();

        if(this.timer.ring(this.random.nextInt(2000) + 3000) && !this.character.isMoving())
        {
            this.v = MINIMUM_SPEED + this.random.nextInt(MAXIMUM_SPEED - MINIMUM_SPEED);
            this.h = MINIMUM_SPEED + this.random.nextInt(MAXIMUM_SPEED - MINIMUM_SPEED);

            float x = this.character.getPosition().x + this.h;
            float y = this.character.getPosition().y + this.v;

            this.character.moveTo(
                    (x >= 0) ? ((x <= ConvictedScreen.VIEWPORT.x) ? x : ConvictedScreen.VIEWPORT.x) : 0,
                    (y >= 0) ? ((y <= ConvictedScreen.VIEWPORT.y) ? y : ConvictedScreen.VIEWPORT.y) : 0);

            this.timer.reset();
        }
    }
}
