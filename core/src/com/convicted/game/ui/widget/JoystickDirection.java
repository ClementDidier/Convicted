package com.convicted.game.ui.widget;

public enum JoystickDirection
{

    None(0f, 0f),
    Top(0f, 1f),
    Right_Top(0.75f, 0.75f),
    Right(1f, 0f),
    Right_Bottom(0.75f, -0.75f),
    Bottom(0f, -1f),
    Left_Bottom(-0.75f, -0.75f),
    Left(-1f, 0f),
    Left_Top(-0.75f, 0.75f);

    private static JoystickDirection[] directions;
    private float x, y;

    JoystickDirection(float x, float y)
    {
        this.x = x;
        this.y = y;
    }

    public float getX()
    {
        return x;
    }

    public float getY()
    {
        return y;
    }

    public static JoystickDirection[] getDirections()
    {
        if(directions == null)
            directions = JoystickDirection.values();
        return directions;
    }
}
