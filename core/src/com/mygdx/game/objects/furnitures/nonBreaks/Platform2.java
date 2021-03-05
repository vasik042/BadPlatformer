package com.mygdx.game.objects.furnitures.nonBreaks;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Platform2 extends NonBreak {
    Sprite sprite;

    public Platform2(float x, float y) {
        super(x, y, new Sprite(new Texture("blocks/lab/platform1f.png")), true, false);
        sprite = new Sprite(new Texture("blocks/lab/platform.png"));
        sprite.setOriginCenter();
        sprite.setOriginBasedPosition(x, y + 32);
        sprite.setColor(0.8f, 0.8f, 0.7f, 1);
        this.sprite.setColor(0.8f, 0.8f, 0.7f, 1);
    }

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
        sprite.draw(batch);
    }
}
