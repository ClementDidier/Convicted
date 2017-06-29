package com.convicted.game.drawable.ui.screen.transition;

import com.convicted.game.drawable.ui.screen.ConvictedScreen;

public class FadeOutTransition implements Transition
{
    private float alpha;
    private float duration;
    private float elapsed;

    public FadeOutTransition(float miliseconds)
    {
        this.duration = miliseconds;
    }

    @Override
    public void initialize()
    {
        this.alpha = 0f;
        this.elapsed = 0f;
    }

    @Override
    public boolean act(float delta, ConvictedScreen screen)
    {
        this.elapsed += delta;
        this.alpha = this.elapsed / this.duration;

        if(this.alpha <= 1f)
            screen.getBatch().setAlpha(this.alpha);

        return this.elapsed < this.duration;
    }
}
