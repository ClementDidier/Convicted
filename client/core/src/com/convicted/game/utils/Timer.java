package com.convicted.game.utils;

public class Timer
{
    private float duration;
    private float elapsed;

    public Timer()
    {
        this(0);
    }

    public Timer(float miliseconds)
    {
        this.duration = miliseconds;
        this.elapsed = 0f;
    }

    public void update(float delta)
    {
        this.elapsed += delta;
    }

    public boolean ring()
    {
        return this.elapsed >= this.duration / 1000;
    }

    public boolean ring(float miliseconds)
    {
        this.duration = miliseconds;
        return ring();
    }

    public void reset()
    {
        this.elapsed = 0f;
    }

    public float getPercent()
    {
        if(this.elapsed >= this.duration / 1000)
            return 1;
        return this.elapsed / (this.duration / 1000);
    }
}
