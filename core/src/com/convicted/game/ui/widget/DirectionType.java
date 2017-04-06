package com.convicted.game.ui.widget;

public enum DirectionType
{
    Isometric(8),
    Orthogonal(4);

    private int directionCount;

    DirectionType(int directionCount)
    {
        this.directionCount = directionCount;
    }

    public int getDirectionCount()
    {
        return this.directionCount;
    }
}
