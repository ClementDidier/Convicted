package com.convicted.game.ui.widget;

import com.badlogic.gdx.math.Vector2;

public interface IJoystick
{
    double getDegree();
    Vector2 getDirectionalVector();
    double getPushedValue();
    JoystickDirection getDirection(DirectionType type);
    boolean moved();
}
