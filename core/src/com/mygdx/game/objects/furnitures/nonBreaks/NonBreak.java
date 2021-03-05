package com.mygdx.game.objects.furnitures.nonBreaks;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.objects.furnitures.Furniture;

public class NonBreak extends Furniture {
    public NonBreak(float x, float y, Sprite sprite, boolean transitional, boolean fone) {
        super(x, y, sprite, transitional, fone, false);
    }
}
