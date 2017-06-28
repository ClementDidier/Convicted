package com.convicted.game.drawable.ui.screen.effect;

import com.convicted.game.drawable.ui.screen.ConvictedBatch;
import com.convicted.game.drawable.ui.screen.ConvictedScreen;
import com.convicted.game.drawable.ui.screen.ScreenNavigator;

public class TransitionScreen extends ConvictedScreen
{
    private boolean finalAnimationStep;
    private ConvictedScreen current;
    private ConvictedScreen next;
    private Transition transition;
    private Transition secondTransition;

    public TransitionScreen(ConvictedScreen current, ConvictedScreen next, Transition firstScreenTransition, Transition secondScreenTransition)
    {
        super();
        this.finalAnimationStep = false;
        this.current = current;
        this.next = next;
        this.transition = firstScreenTransition;
        this.secondTransition = secondScreenTransition;

        firstScreenTransition.initialize();
        secondScreenTransition.initialize();
    }

    @Override
    public void update(float delta)
    {
        // TODO : Create a sequence of transition effects
        // Mets à jour l'écran actuel
        if (transition.act(delta, this.current))
            return;

        if(!finalAnimationStep)
        {
            // Change l'écran dessiné actuellement
            this.current = this.next;
            this.transition = this.secondTransition;
            this.finalAnimationStep = true;
        }
        else
        {
            // Navigue vers l'écran suivant
            ScreenNavigator.navigateTo(this.current);
        }
    }

    @Override
    public void draw(ConvictedBatch batch)
    {
        this.current.draw(batch);
    }

    @Override
    public void show()
    {

    }
}
