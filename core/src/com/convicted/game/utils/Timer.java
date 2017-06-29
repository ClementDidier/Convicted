package com.convicted.game.utils;

public class Timer
{
    private float elapsed;

    public Timer()
    {
        this.elapsed = 0f;
    }

    public void update(float delta)
    {
        this.elapsed += delta;
    }

    public boolean ring(float miliseconds)
    {
        return this.elapsed >= miliseconds / 1000;
    }

    public void reset()
    {
        this.elapsed = 0f;
    }
}
