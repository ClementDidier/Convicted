package com.convicted.game.utils;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class Vector
{
    public static void normalize(Vector2 vector)
    {
        double norme = norme(vector);
        if(norme == 0)
        {
            vector.x = 0;
            vector.y = 0;
            return;
        }

        vector.x /= norme;
        vector.y /= norme;
    }

    public static double norme(Vector2 vector)
    {
        return Math.sqrt(vector.x * vector.x + vector.y * vector.y);
    }
}
