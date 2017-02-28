package com.convicted.game.environment;

import com.badlogic.gdx.math.Vector2;

public class Environment
{
    private static final Vector2 DIMENSIONS = new Vector2(7, 5); // 7 x 5 maps

    private GameMap[][] maps;

    public Environment()
    {
        this.maps = new GameMap[(int)DIMENSIONS.x][(int)DIMENSIONS.y];
    }
}
