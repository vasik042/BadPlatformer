package com.mygdx.game.objects.furnitures.nonBreaks;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Platform extends NonBreak {
    public Platform(float x, float y) {
        super(x, y, new Sprite(new Texture("platform.png")), true, false);
    }
}
