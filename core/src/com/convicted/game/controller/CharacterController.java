package com.convicted.game.controller;

import com.convicted.game.ConvictedRun;
import com.convicted.game.drawable.entity.character.Character;

public abstract class CharacterController
{
    protected Character character;
    protected ConvictedRun world;

    public CharacterController(Character character, ConvictedRun world)
    {
        this.character = character;
        this.world = world;
    }

    public abstract void act(float delta);
}
