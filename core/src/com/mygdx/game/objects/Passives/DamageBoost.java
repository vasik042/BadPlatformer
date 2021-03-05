package com.mygdx.game.objects.Passives;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.objects.creatures.Creature;

public class DamageBoost extends Passive{
    float damage;

    public DamageBoost(float damage) {
        super(new Sprite(new Texture("heroes/mage/jump.png")));

        this.damage = damage;
    }

    @Override
    public void add(Creature creature) {
        super.add(creature);
        creature.damageBoost += damage;
    }

    @Override
    public void remove(Creature creature) {
        creature.damageBoost -= damage;
    }
}
