package com.convicted.game.ui.screen;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.convicted.game.entity.Entity;
import com.convicted.game.entity.projectiles.Projectile;
import com.convicted.game.utils.ProjectileFactory;

import java.util.ArrayList;
import java.util.List;

public class GameContext
{
    private Stage stage;

    public List<Projectile> projectiles;
    public List<Entity> entities;

    public ProjectileFactory projectileFactory;

    public GameContext(Stage stage)
    {
        this.stage = stage;
        this.projectiles = new ArrayList<Projectile>();
        this.entities = new ArrayList<Entity>();

        this.projectileFactory = new ProjectileFactory(this);
    }

    public float getScreenWidth()
    {
        return this.stage.getWidth();
    }

    public float getScreenHeight()
    {
        return this.stage.getHeight();
    }
}
