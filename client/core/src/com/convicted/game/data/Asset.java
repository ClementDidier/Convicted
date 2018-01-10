package com.convicted.game.data;

import com.badlogic.gdx.graphics.Texture;

public enum Asset
{
    SPLASH_BACKGROUND("splash.png", Texture.class),
    SPLASH_PROGRESS_BAR("progressbar.png", Texture.class),
    ROGUE("charset.png", Texture.class),
    GRUB("grub.png", Texture.class),
    START_BUTTON("buttonStart.png", Texture.class),
    BASIC_TILE("tileGround.png", Texture.class),
    WALL_TILE("tileWall.png", Texture.class),
    MAINSCREEN_BACKGROUND("mainscreen-background.png", Texture.class);

    private String assetFileName;
    private Class assetType;

    Asset(String assetFileName, Class type)
    {
        this.assetFileName = assetFileName;
        this.assetType = type;
    }

    public Class getAssetType()
    {
        return assetType;
    }

    public String getAssetFileName()
    {
        return assetFileName;
    }
}
