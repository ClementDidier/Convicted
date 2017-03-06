package com.convicted.game.ui.screen.effect;

import com.badlogic.gdx.graphics.Color;
import com.convicted.game.ui.screen.AbstractScreen;

public class FadeOutTransitionEffect extends TransitionEffect
{
    private float alpha;
    private float duration;

    /**
     * Créer un nouvel effet de transition de type Fade out (Alpha de 0f à 1f)
     * @param duration La durée de l'effet en seconde(s)
     */
    public FadeOutTransitionEffect(float duration)
    {
        this.alpha = 0f;
        this.duration = duration;
    }

    @Override
    public void render(float delta, AbstractScreen current)
    {
        if(current != null && !isFinished())
        {
            Color color = current.getBatch().getColor();
            current.getBatch().setColor(color.r, color.g, color.b, this.alpha);
            this.alpha += 1f/this.duration * delta;
        }
    }

    @Override
    public boolean isFinished()
    {
        return alpha >= 1f;
    }
}
