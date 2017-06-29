package com.convicted.game.drawable.ui.screen.transition;

import com.convicted.game.drawable.ui.screen.ConvictedScreen;

import java.util.ArrayList;
import java.util.List;

public final class SequenceTransition implements Transition
{
    private List<Transition> transitionList;
    private int currentTransitionIndex;

    public SequenceTransition(Transition...transitions)
    {
        this.transitionList = new ArrayList<Transition>();
        this.currentTransitionIndex = 0;

        for(Transition transition : transitions)
            this.transitionList.add(transition);
    }

    @Override
    public void initialize()
    {
        this.currentTransitionIndex = 0;
        this.transitionList.get(this.currentTransitionIndex).initialize();
    }

    @Override
    public boolean act(float delta, ConvictedScreen screen)
    {
        if(this.currentTransitionIndex >= this.transitionList.size())
            return false;

        if(!this.transitionList.get(this.currentTransitionIndex).act(delta, screen))
        {
            this.currentTransitionIndex++;
            this.transitionList.get(this.currentTransitionIndex).initialize();
        }

        return this.currentTransitionIndex < this.transitionList.size();
    }
}
