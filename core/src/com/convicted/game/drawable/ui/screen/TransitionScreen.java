package com.convicted.game.drawable.ui.screen;

import com.badlogic.gdx.Gdx;
import com.convicted.game.ConvictedGame;
import com.convicted.game.drawable.ui.screen.transition.SequenceTransition;
import com.convicted.game.drawable.ui.screen.transition.Transition;

public class TransitionScreen extends ConvictedScreen
{
    private ConvictedScreen current;
    private Transition transition;

    public TransitionScreen(ConvictedGame game, Transition transition1, final ConvictedScreen nextScreen, Transition transition2)
    {
        super(game);
        final ConvictedScreen prev = game.getScreen();
        this.current = game.getScreen();

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
                    public boolean act(float delta)
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
                        // On libère les ressouces associées à l'écran précédent
                        prev.uninitialize();

                        // Navigue vers l'écran suivant
                        ScreenNavigator.navigateTo(nextScreen);
                    }

                    @Override
                    public boolean act(float delta)
                    {
                        return false;
                    }
                });

        this.transition.initialize();
    }

    @Override
    public void load()
    {
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void unload()
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
        this.transition.act(delta);
    }

    @Override
    public void draw(ConvictedBatch batch)
    {
        this.current.draw(batch);
        this.current.drawWidgets(batch);
    }

}
