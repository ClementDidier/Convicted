package com.convicted.game.drawable.ui.widget;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.convicted.game.ConvictedGame;
import com.convicted.game.drawable.ui.screen.ConvictedBatch;
import com.convicted.game.utils.ButtonClickListener;

import java.util.ArrayList;
import java.util.List;

public class Button extends Widget implements InputProcessor
{
    private static final boolean debug = false;

    //private Skin buttonSkin;
    private Sprite imageButton;
    private ShapeRenderer renderer;
    private List<ButtonClickListener> clickListenerList;

    public Button(int x, int y, Texture texture)
    {
        super(x, y);
        this.imageButton = new Sprite(texture);
        this.clickListenerList = new ArrayList<ButtonClickListener>();
        this.renderer = new ShapeRenderer();

        this.update(0);
    }

    @Override
    public void update(float delta)
    {
        this.imageButton.setPosition(
                this.getPosition().x - (this.imageButton.getWidth() / 2),
                this.getPosition().y - (this.imageButton.getHeight() / 2));
    }

    @Override
    public void draw(ConvictedBatch batch)
    {
        batch.draw(this.imageButton);

        if(debug) {
            Rectangle rectangle = this.imageButton.getBoundingRectangle();
            this.renderer.setProjectionMatrix(batch.getProjectionMatrix());
            this.renderer.begin(ShapeRenderer.ShapeType.Filled);
            this.renderer.setColor(255f, 0, 0, 100f);
            this.renderer.rect(rectangle.x, rectangle.y, rectangle.getWidth(), rectangle.getHeight());
            this.renderer.end();
        }
    }

    public void addListener(ButtonClickListener listener)
    {
        this.clickListenerList.add(listener);
    }

    private void callClickedEvent(InputEvent event, int x, int y)
    {
        for(ButtonClickListener listener : this.clickListenerList)
            listener.onClick(event, x, y);
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button)
    {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button)
    {
        Vector2 worldTouchLocation = ConvictedGame.getInstance().getScreen().screenToLocalCoordinates(screenX, screenY);

        if(this.imageButton.getBoundingRectangle().contains(worldTouchLocation.x, worldTouchLocation.y))
        {
            this.callClickedEvent(null, (int)worldTouchLocation.x, (int)worldTouchLocation.y);
            return true;
        }
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    public void setScale(float scale) {
        this.imageButton.setScale(scale);
    }
}
