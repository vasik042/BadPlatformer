package com.mygdx.game.objects.furnitures.nonBreaks.bricks;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.objects.furnitures.nonBreaks.NonBreak;

public class Block extends NonBreak {
    public Block(float x, float y, Sprite sprite, boolean transitional) {
        super(x, y, sprite, transitional, false);
    }
}
