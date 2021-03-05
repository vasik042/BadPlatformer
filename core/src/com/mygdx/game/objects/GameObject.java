package com.mygdx.game.objects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameObject {
    public float x;
    public float y;
    public Sprite sprite;

    public GameObject() {
    }

    public GameObject(float x, float y, Sprite sprite) {
        this.x = x;
        this.y = y;
        this.sprite = sprite;
        this.sprite.setOriginCenter();
        this.sprite.setOriginBasedPosition(x, y);
    }

    public void draw(SpriteBatch batch){
        sprite.draw(batch);
    }
}
