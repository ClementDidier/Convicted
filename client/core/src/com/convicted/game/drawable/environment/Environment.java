package com.convicted.game.drawable.environment;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.convicted.game.ConvictedGame;
import com.convicted.game.data.Asset;
import com.convicted.game.drawable.Drawable;
import com.convicted.game.drawable.ui.screen.ConvictedBatch;
import com.convicted.game.drawable.ui.screen.ConvictedScreen;

public class Environment implements Drawable
{
    public final static Vector2 SIZE = new Vector2(14, 7);

    private Tile[] tiles;
    private ConvictedGame game;

    public Environment(ConvictedGame game)
    {
        this.game = game;
        this.tiles = new Tile[(int)(SIZE.x * SIZE.y)];

        this.game.getAssetManager().load(Asset.WALL_TILE);
        this.game.getAssetManager().load(Asset.BASIC_TILE);
        this.game.getAssetManager().finishLoading();

        float borderX = (ConvictedScreen.VIEWPORT.x - (SIZE.x * Tile.SIZE.x)) / 2f;
        float borderY = (ConvictedScreen.VIEWPORT.y - (SIZE.y * Tile.SIZE.y)) / 2f;

        for(int i = 0; i < SIZE.x * SIZE.y; i++)
        {
            float x = (float)Math.floor(i / SIZE.y);
            float y = i - (int)(x * SIZE.y);

            this.tiles[i] = new Tile(
                    x * Tile.SIZE.x + borderX,
                    y * Tile.SIZE.y + borderY,
                    this.game.getAssetManager().<Texture>get(Asset.BASIC_TILE));
        }
    }

    public Tile getTile(int x, int y)
    {
        if(x * y >= tiles.length || x >= SIZE.x || y >= SIZE.y)
            throw new IndexOutOfBoundsException("Les coordonées pointent sur un index hors tableau");
        return this.tiles[y + (int)(x * SIZE.y)];
    }

    @Override
    public void update(float delta)
    {

    }

    @Override
    public void draw(ConvictedBatch batch)
    {
        for(int i = 0; i < SIZE.x * SIZE.y; i++)
            this.tiles[i].draw(batch);
    }
}
