package com.convicted.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.convicted.game.ui.screen.AbstractScreen;
import com.convicted.game.ui.screen.Navigator;
import com.convicted.game.ui.screen.Scene;

public class ConvictedGame extends Game
{
	private AbstractScreen screen;

	@Override
	public void create ()
	{
		Navigator.navigateTo(this, Scene.MainScreen);
	}

	@Override
	public void render ()
	{
		super.render();
	}


	@Override
	public void dispose ()
	{
	}

	public void setScreen(AbstractScreen screen)
	{
		super.setScreen(screen);
		this.screen = screen;
	}

	public AbstractScreen getScreen()
	{
		return this.screen;
	}

	@Override
	@Deprecated
	public void setScreen(Screen screen)
	{
		throw new UnsupportedOperationException("Méthode \"setScreen(Screen)\" non supportée dans la classe AbstractScreen");
	}
}
