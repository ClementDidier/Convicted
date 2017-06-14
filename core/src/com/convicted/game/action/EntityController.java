package com.convicted.game.action;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.DelegateAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.convicted.game.entity.Entity;
import com.convicted.game.ui.screen.GameContext;

import java.util.Stack;

public abstract class EntityController
{
    private Stack<Action> actions;

    protected Entity entity;

    public EntityController(Entity entity)
    {
        this.actions = new Stack<Action>();
        this.entity = entity;
    }

    public final void update(float delta)
    {
        this.actions.clear();

        if(this.act(delta)) {
            this.entity.clearActions();
            
            for (int i = 0; i < this.actions.size(); i++) {
                Action action = this.actions.pop();

                if (action != null)
                    this.entity.addAction(action);
            }
        }
    }

    public abstract boolean act(float delta);

    public final void addAction(Action action)
    {
        this.actions.add(action);
    }
}
