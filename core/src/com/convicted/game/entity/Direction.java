package com.convicted.game.entity;


import static com.convicted.game.utils.Converter.convertRadianToDegree;

public enum Direction
{
    Bottom(0),
    Left(1),
    Top(3),
    Right(2);

    private int index;

    Direction(int index)
    {
        this.index = index;
    }

    public int getIndex()
    {
        return this.index;
    }

    public static Direction getDirection(double oldX, double oldY, double newX, double newY)
    {
        final double PORTION = 360 / 4;

        double angleRadian = Math.atan2(oldX - newX, oldY - newY);
        double degree = convertRadianToDegree(angleRadian);
        if (angleRadian < 0)
            degree = 360 + convertRadianToDegree(angleRadian);

        degree = (degree < 0) ? 0 : degree;
        int index = (int) Math.ceil(degree / PORTION);

        if(index == 4) // Modulo like
        {
            return Direction.Bottom;
        }

        return Direction.values()[index];
    }
}
