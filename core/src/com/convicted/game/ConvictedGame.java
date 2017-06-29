package com.convicted.game;

import com.badlogic.gdx.Game;
import com.convicted.game.drawable.ui.screen.ConvictedScreen;
import com.convicted.game.drawable.ui.screen.ScreenNavigator;

public class ConvictedGame extends Game
{
	private ConvictedScreen screen;

	@Override
	public void create ()
	{
		ScreenNavigator.navigateTo(ConvictedScreen.MENU);
	}

	@Override
	public void render ()
	{
		super.render();
	}


	@Override
	public void dispose ()
	{
		if(this.screen != null)
			this.screen.dispose();
	}

	public void setScreen(ConvictedScreen screen)
	{
		screen.initialize();
		super.setScreen(screen);
		this.screen = screen;
	}

	@Override
	public ConvictedScreen getScreen()
	{
		return this.screen;
	}

	@Override
	@Deprecated
	public final void setScreen(com.badlogic.gdx.Screen screen)
	{
		throw new UnsupportedOperationException("Méthode \"setScreen(Screen)\" non supportée avec gdx.Screen en paramètre");
	}
}
