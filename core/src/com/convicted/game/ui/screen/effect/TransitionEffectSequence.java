package com.convicted.game.ui.screen.effect;

import com.convicted.game.ui.screen.AbstractScreen;

import java.util.ArrayList;
import java.util.List;

public class TransitionEffectSequence extends TransitionEffect
{
    private List<TransitionEffect> transitions;
    private int indexCurrentEffect;

    public TransitionEffectSequence()
    {
        transitions = new ArrayList<TransitionEffect>();
        indexCurrentEffect = 0;
    }

    public TransitionEffectSequence(List<TransitionEffect> transitions)
    {
        this();
        this.transitions = transitions;
    }

    public void add(TransitionEffect transitionEffect)
    {
        this.transitions.add(transitionEffect);
    }

    public void render(float delta, AbstractScreen screen)
    {
        if(!isFinished())
            return;

        if(!transitions.get(indexCurrentEffect).isFinished())
            transitions.get(indexCurrentEffect).render(delta, screen);
        else
            indexCurrentEffect++;
    }

    public boolean isFinished()
    {
        return indexCurrentEffect >= transitions.size();
    }
}
