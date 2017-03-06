package com.convicted.game.ui.screen.effect;

import com.badlogic.gdx.graphics.Color;
import com.convicted.game.ui.screen.AbstractScreen;

public class FadeInTransitionEffect extends TransitionEffect
{
    private float alpha;
    private float duration;

    /**
     * Créer un nouvel effet de transition de type Fade in (Alpha de 1f à 0f)
     * @param duration La durée de l'effet en seconde(s)
     */
    public FadeInTransitionEffect(float duration)
    {
        this.alpha = 1f;
        this.duration = duration;
    }

    @Override
    public void render(float delta, AbstractScreen current)
    {
        if(current != null && !isFinished())
        {
            Color color = current.getBatch().getColor();
            current.getBatch().setColor(color.r, color.g, color.b, this.alpha);
            this.alpha-=1f/this.duration * delta;
        }
    }

    @Override
    public boolean isFinished()
    {
        return alpha <= 0f;
    }
}
