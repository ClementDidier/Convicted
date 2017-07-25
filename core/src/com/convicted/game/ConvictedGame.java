package com.convicted.game;

import com.badlogic.gdx.Game;
import com.convicted.game.data.Configuration;
import com.convicted.game.data.ConvictedAssetManager;
import com.convicted.game.drawable.ui.screen.ConvictedScreen;
import com.convicted.game.drawable.ui.screen.ScreenNavigator;

public class ConvictedGame extends Game
{
	private static ConvictedGame game;

	private ConvictedScreen screen;
	private Configuration config;
	private ConvictedAssetManager assetManager;

	@Override
	public void create ()
	{
		this.assetManager = new ConvictedAssetManager();
		ScreenNavigator.navigateTo(ConvictedScreen.SPLASH);
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

	@Override
	public ConvictedScreen getScreen()
	{
		return this.screen;
	}

	public void setScreen(ConvictedScreen screen)
	{
		screen.initialize();
		super.setScreen(screen);
		this.screen = screen;
	}

	@Override
	@Deprecated
	public final void setScreen(com.badlogic.gdx.Screen screen)
	{
		throw new UnsupportedOperationException("Méthode \"setScreen(Screen)\" non supportée avec gdx.Screen en paramètre");
	}

	/**
	 * Obtient une instance de configuration de jeu
	 * @return L'instance de configuration
	 */
	public Configuration getConfiguration()
	{
		if(config == null)
			config = new Configuration();
		return config;
	}

	/**
	 * Obtient le gestionnaire de dépendances
	 * @return Le gestionnaire de dépendances
	 */
	public ConvictedAssetManager getAssetManager()
	{
		return this.assetManager;
	}

	/**
	 * Initialise une instance du jeu si necessaire et la retourne
	 * @return L'instance du jeu
	 */
	public static ConvictedGame getInstance()
	{
		if(game == null)
			game = new ConvictedGame();
		return game;
	}
}
