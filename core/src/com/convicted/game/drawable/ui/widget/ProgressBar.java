package com.convicted.game.drawable.ui.widget;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.convicted.game.drawable.ui.screen.ConvictedBatch;

public class ProgressBar extends Widget
{
    private float percent;
    private int value;
    private int maximum;
    private Sprite bar;

    public ProgressBar(int x, int y)
    {
        super(x, y);
        this.percent = 0f;
        this.maximum = 100;
        this.bar = new Sprite(new Texture(Gdx.files.internal("progressbar.png")));
        this.bar.setPosition(x, y);
    }

    public ProgressBar()
    {
        this(0,0);
    }

    @Override
    public void update(float delta)
    {
        this.bar.setRegion(0, 0, this.getPercent(), 1);
        this.bar.setSize(this.getPercent() * this.getWidth(), this.getheight());
        this.bar.setPosition(this.getPosition().x, this.getPosition().y);
        this.bar.setOrigin(this.getPosition().x, this.getPosition().y);
    }

    @Override
    public void draw(ConvictedBatch batch)
    {
        batch.draw(this.bar);
    }

    public float getWidth()
    {
        return this.bar.getTexture().getWidth();
    }

    public float getheight()
    {
        return this.bar.getTexture().getHeight();
    }

    @Override
    public void setPosition(float x, float y)
    {
        super.setPosition(x, y);
        this.bar.setPosition(x, y);
    }

    public float getPercent()
    {
        return this.percent;
    }

    public int getValue()
    {
        return this.value;
    }

    public void setValue(int value)
    {
        if(value > this.getMaximum())
            this.value = this.getMaximum();
        else
            this.value = value;

        this.updatePercent();
    }

    public void setMaximum(int maximum)
    {
        if(maximum < this.value)
            this.value = maximum;
        else
            this.maximum = maximum;

        this.updatePercent();
    }

    public int getMaximum()
    {
        return maximum;
    }

    public void add(int value)
    {
        this.setValue(this.getValue() + value);
    }

    public boolean isComplete()
    {
        return this.getValue() == this.getMaximum();
    }

    private void updatePercent()
    {
        this.percent = this.getValue() * 1f / this.getMaximum();
    }

    @Override
    public void dispose()
    {
        if(this.bar != null)
            this.bar.getTexture().dispose();
    }
}
