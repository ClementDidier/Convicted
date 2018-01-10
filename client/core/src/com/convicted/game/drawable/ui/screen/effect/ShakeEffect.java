package com.convicted.game.drawable.ui.screen.effect;

import com.badlogic.gdx.graphics.OrthographicCamera;

import java.util.Random;

public class ShakeEffect implements ScreenEffect
{
    private float elapsed;

    private final float duration;
    private final float intensity;
    private final Random random;

    public ShakeEffect(float intensity, float duration) {
        this.elapsed = 0f;
        this.duration = duration / 1000f;
        this.intensity = intensity;
        this.random = new Random();
    }

    @Override
    public void update(float delta, OrthographicCamera camera)
    {
        if(this.elapsed < this.duration)
        {
            float shakePower = this.intensity * camera.zoom * ((this.duration - this.elapsed) / this.duration);
            float x = (random.nextFloat() - 0.5f) * 2 * shakePower;
            float y = (random.nextFloat() - 0.5f) * 2 * shakePower;
            camera.translate(-x, -y, 0);

            this.elapsed += delta;
        }
    }

    @Override
    public boolean isFinished() {
        return this.elapsed >= this.duration;
    }
}
