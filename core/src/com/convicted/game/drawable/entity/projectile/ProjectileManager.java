package com.convicted.game.drawable.entity.projectile;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.convicted.game.drawable.entity.Entity;
import com.convicted.game.drawable.entity.EntityManager;
import com.convicted.game.drawable.ui.screen.ConvictedBatch;

import java.util.List;

public class ProjectileManager implements EntityManager
{
    private Array<Projectile> activesProjectiles;
    private Pool<Projectile> projectilePool;

    public ProjectileManager()
    {
        this.activesProjectiles = new Array<Projectile>();
        this.projectilePool = new Pool<Projectile>() {
            @Override
            protected Projectile newObject() {
                return new Projectile();
            }
        };

    }

    @Override
    public void load(String entity, Class type) {

    }

    @Override
    public boolean contains(String id) {
        return false;
    }

    @Override
    public void unload(String id) {

    }

    @Override
    public void unloadAll() {

    }

    @Override
    public List getAll() {
        return null;
    }

    @Override
    public Entity get(String id, Class type) {
        return null;
    }

    @Override
    public void updateAll(float delta) {

    }

    @Override
    public void drawAll(ConvictedBatch batch) {

    }

    @Override
    public void dispose() {

    }
}
