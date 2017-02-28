package com.convicted.game;

import com.badlogic.gdx.Game;
import com.convicted.game.ui.screen.Navigator;
import com.convicted.game.ui.screen.Scene;

public class ConvictedGame extends Game
{
	@Override
	public void create ()
	{
		Navigator.navigateTo(this, Scene.GameScreen);
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
}
