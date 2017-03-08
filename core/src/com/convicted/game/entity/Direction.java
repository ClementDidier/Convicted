package com.convicted.game.entity;


public enum Direction
{
    Bottom(0),
    Left(1),
    Right(2),
    Top(3);

    private int index;

    Direction(int index)
    {
        this.index = index;
    }

    public int getIndex()
    {
        return this.index;
    }
}
