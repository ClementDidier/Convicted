package com.convicted.game.data;

import com.badlogic.gdx.assets.AssetManager;

public class ConvictedAssetManager extends AssetManager
{
    public void load(Asset asset)
    {
        super.load(asset.getAssetFileName(), asset.getAssetType());
    }

    public void unload(Asset asset)
    {
        super.unload(asset.getAssetFileName());
    }

    public <T> T get(Asset asset)
    {
        return (T)super.get(asset.getAssetFileName(), asset.getAssetType());
    }
}
