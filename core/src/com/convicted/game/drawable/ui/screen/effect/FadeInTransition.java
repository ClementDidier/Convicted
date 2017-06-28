package com.convicted.game.drawable.ui.screen.effect;

import com.convicted.game.drawable.ui.screen.ConvictedScreen;

public class FadeInTransition extends Transition
{
    private float alpha;
    private float duration;
    private float elapsed;

    public FadeInTransition(float miliseconds)
    {
        this.duration = miliseconds;
    }

    @Override
    protected void initialize()
    {
        this.alpha = 1f;
        this.elapsed = 0f;
    }

    @Override
    protected boolean act(float delta, ConvictedScreen screen)
    {
        this.elapsed += delta;
        this.alpha = 1 - (this.elapsed / this.duration);

        if(this.alpha >= 0f)
            screen.getBatch().setAlpha(this.alpha);

        return this.elapsed < this.duration;
    }
}
