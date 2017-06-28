package com.convicted.game.drawable.ui.screen.effect;

import com.convicted.game.drawable.ui.screen.ConvictedScreen;

public class FadeOutTransition extends Transition
{
    private float alpha;
    private float duration;
    private float elapsed;

    public FadeOutTransition(float miliseconds)
    {
        this.duration = miliseconds;
    }

    @Override
    protected void initialize()
    {
        this.alpha = 0f;
        this.elapsed = 0f;
    }

    @Override
    protected boolean act(float delta, ConvictedScreen screen)
    {
        this.elapsed += delta;
        this.alpha = this.elapsed / this.duration;

        if(this.alpha <= 1f)
            screen.getBatch().setAlpha(this.alpha);

        return this.elapsed < this.duration;
    }
}
