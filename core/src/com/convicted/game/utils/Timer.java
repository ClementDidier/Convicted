package com.convicted.game.utils;

public class Timer
{
    private float elapsed;

    public Timer()
    {
        this.elapsed = 0;
    }

    public void act(float delta)
    {
        this.elapsed += delta;
    }

    public boolean wait(float duration)
    {
        if(this.elapsed >= duration)
        {
            this.elapsed = 0;
            return true;
        }

        return false;
    }

    public void restart()
    {
        this.elapsed = 0;
    }
}
