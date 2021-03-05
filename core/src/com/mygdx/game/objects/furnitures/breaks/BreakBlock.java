package com.mygdx.game.objects.furnitures.breaks;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.objects.attacks.Spell;
import com.mygdx.game.objects.attacks.melee.Sword;
import com.mygdx.game.objects.furnitures.Furniture;

import java.util.ArrayList;

public class BreakBlock  extends Furniture {
    public float hp;
    public float maxHp;
    public Sprite destroyedSprite;

    public BreakBlock(float x, float y, Sprite sprite, Sprite destroyedSprite, boolean transitional, boolean fone, float maxHp) {
        super(x, y, sprite, transitional, fone, true);
        this.destroyedSprite = destroyedSprite;
        this.maxHp = maxHp;
        this.hp = this.maxHp;
        this.deadSpell = new Sword();
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
            dead = true;
        }
    }
}
