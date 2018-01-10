package com.convicted.game;

import com.convicted.game.drawable.Drawable;
import com.convicted.game.drawable.entity.Entity;
import com.convicted.game.drawable.entity.projectile.ProjectileManager;
import com.convicted.game.drawable.environment.Environment;
import com.convicted.game.drawable.ui.screen.ConvictedBatch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ConvictedRun implements Drawable
{
    private static Comparator<Entity> ENTITY_COMPARATOR;

    private ConvictedGame game;
    private Environment environment;

    public ProjectileManager projectileManager;

    // TODO: Use an EntityManager instead
    private List<Entity> entities;

    public ConvictedRun(ConvictedGame game)
    {
        this.game = game;
        this.environment = new Environment(this.game);
        this.entities = new ArrayList<Entity>();
        this.projectileManager = new ProjectileManager();
    }

    public void addEntity(Entity entity)
    {
        this.entities.add(entity);
    }

    public void removeEntity(Entity entity)
    {
        this.entities.remove(entity);
    }

    @Override
    public void update(float delta)
    {
        this.environment.update(delta);

        Collections.sort(this.entities, this.getEntityComparator());

        for(int i = 0; i < this.entities.size(); i++)
            this.entities.get(i).update(delta);

        this.projectileManager.updateAll(delta);

        //Gdx.app.log("Projectile", "Actives projectiles count : " + this.projectileManager.getActivesProjectilesCount());
    }

    @Override
    public void draw(ConvictedBatch batch)
    {
        batch.draw(this.environment);

        for(int i = 0; i < this.entities.size(); i++)
            this.entities.get(i).draw(batch);

        this.projectileManager.drawAll(batch);
    }

    private Comparator<Entity> getEntityComparator()
    {
        if(ENTITY_COMPARATOR != null)
            return ENTITY_COMPARATOR;

        ENTITY_COMPARATOR = new Comparator<Entity>() {
            @Override
            public int compare(Entity entity, Entity t1)
            {
                if(entity.getPosition().y == t1.getPosition().y)
                    return entity.hashCode() < t1.hashCode() ? 1 : entity.hashCode() > t1.hashCode() ? -1 : 0;
                return entity.getPosition().y < t1.getPosition().y ? 1 : -1;
            }
        };

        return ENTITY_COMPARATOR;
    }
}
