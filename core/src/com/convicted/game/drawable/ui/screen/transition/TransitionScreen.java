package com.convicted.game.drawable.ui.screen.transition;

import com.convicted.game.drawable.ui.screen.ConvictedBatch;
import com.convicted.game.drawable.ui.screen.ConvictedScreen;
import com.convicted.game.drawable.ui.screen.ScreenNavigator;

public class TransitionScreen extends ConvictedScreen
{
    private ConvictedScreen current;
    private Transition transition;

    public TransitionScreen(final ConvictedScreen currentScreen, Transition transition1, final ConvictedScreen nextScreen, Transition transition2)
    {
        super();
        this.current = currentScreen;

        this.transition = new SequenceTransition(
                transition1,
                new Transition()
                {
                    @Override
                    public void initialize()
                    {
                        current = nextScreen;
                        current.initialize();
                    }

                    @Override
                    public boolean act(float delta, ConvictedScreen screen)
                    {
                        return false;
                    }
                },
                transition2,
                new Transition()
                {
                    @Override
                    public void initialize()
                    {
                        // Navigue vers l'écran suivant
                        ScreenNavigator.navigateTo(nextScreen);
                    }

                    @Override
                    public boolean act(float delta, ConvictedScreen screen)
                    {
                        return false;
                    }
                });

        this.transition.initialize();
    }

    @Override
    public void load()
    {
        // Nothing
    }

    @Override
    public void show()
    {
        // Nothing
    }

    @Override
    public void update(float delta)
    {
        this.transition.act(delta, this.current);
    }

    @Override
    public void draw(ConvictedBatch batch)
    {
        this.current.draw(batch);
    }

}
