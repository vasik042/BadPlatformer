package com.mygdx.game.objects.furnitures.breaks;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.objects.attacks.Spell;
import com.mygdx.game.objects.furnitures.Furniture;

import java.util.ArrayList;

public class Hole extends  BreakBlock{
    public Hole(float x, float y) {
        super(x, y, new Sprite(new Texture("blocks/hole1.png")), new Sprite(new Texture("blocks/hole2.png")), false, false, 50);
    }

    @Override
    public void destroy(Spell spell, ArrayList<Furniture> furnitures) {
        hp -= spell.damage;
        if (hp <= maxHp/2){
            Sprite sprite2 = destroyedSprite;
            sprite2.setSize(spriteWidth, spriteHeight);
            sprite2.setOriginCenter();
            sprite2.setOriginBasedPosition(x, y);
            sprite = sprite2;
        }
        if (hp <= 0){
            Sprite sprite2 = new Sprite(new Texture("blocks/hole3.png"));
            sprite2.setSize(spriteWidth, spriteHeight);
            sprite2.setOriginCenter();
            sprite2.setOriginBasedPosition(x, y);
            sprite = sprite2;
            fone = true;
        }
    }
}
