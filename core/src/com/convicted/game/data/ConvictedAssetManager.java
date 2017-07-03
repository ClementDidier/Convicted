package com.convicted.game.data;

import com.badlogic.gdx.assets.AssetManager;
import com.convicted.game.data.Asset;

public class ConvictedAssetManager extends AssetManager
{
    public void load(Asset asset)
    {
        super.load(asset.getAssetFileName(), asset.getAssetType());
    }

    public <T> T get(Asset asset)
    {
        return (T)super.get(asset.getAssetFileName(), asset.getAssetType());
    }
}
