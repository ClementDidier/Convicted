package com.convicted.game.controller;

import com.convicted.game.drawable.entity.character.Character;

public abstract class CharacterController
{
    protected Character character;

    public CharacterController(Character character)
    {
        this.character = character;
    }

    public abstract void act(float delta);
}
