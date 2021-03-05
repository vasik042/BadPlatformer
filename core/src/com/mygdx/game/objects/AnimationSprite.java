package com.mygdx.game.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class AnimationSprite {
    public int time;
    public int maxTime;
    public Sprite sprite;

    public AnimationSprite(int maxTime, Texture texture) {
        this.maxTime = maxTime;
        this.sprite = new Sprite(texture);
        this.time = 0;
        this.sprite.setOriginCenter();
    }
    public AnimationSprite(int maxTime, Texture texture, boolean flipX) {
        this.maxTime = maxTime;
        Sprite sprite1 = new Sprite(texture);
        sprite1.setFlip(true, false);
        this.sprite = sprite1;
        this.time = 0;
        this.sprite.setOriginCenter();
    }
}
