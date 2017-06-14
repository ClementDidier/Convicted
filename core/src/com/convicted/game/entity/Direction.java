package com.convicted.game.entity;


import static com.convicted.game.utils.Converter.convertRadianToDegree;

public enum Direction
{
    Right,
    Left;

    public static Direction getDirectionFromInterpolation(double oldX, double oldY, double newX, double newY)
    {
        double angleRadian = Math.atan2(oldX - newX, oldY - newY);
        double degree = ((angleRadian < 0) ? 360 : 0) + convertRadianToDegree(angleRadian);

        degree = (degree < 0) ? 0 : degree;
        int index = (int) Math.ceil(degree / 180);

        return Direction.values()[index % 2];
    }
}
