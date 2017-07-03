package com.convicted.game.drawable.ui.widget;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.convicted.game.drawable.ui.screen.ConvictedBatch;

import java.util.ArrayList;
import java.util.List;

public class Button extends Widget
{
    //private Skin buttonSkin;
    private List<ClickListener> clickListenerList;
    private String caption;

    public Button(int x, int y, String caption)
    {
        super(x, y);
        this.caption = caption;
        this.clickListenerList = new ArrayList<ClickListener>();
    }

    @Override
    public void update(float delta)
    {

    }

    @Override
    public void draw(ConvictedBatch batch)
    {

    }

    public void addListener(ClickListener listener)
    {
        this.clickListenerList.add(listener);
    }

    private void callClickedEvent(InputEvent event, int x, int y)
    {
        for(ClickListener listener : this.clickListenerList)
            listener.clicked(event, x, y);
    }
}
