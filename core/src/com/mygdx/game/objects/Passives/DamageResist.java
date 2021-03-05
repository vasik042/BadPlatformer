package com.mygdx.game.objects.Passives;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.objects.creatures.Creature;

public class DamageResist extends Passive{
    float res;

    public DamageResist(float res) {
        super(new Sprite(new Texture("heroes/mage/jump.png")));

        this.res = res;
    }

    @Override
    public void add(Creature creature) {
        super.add(creature);
        creature.damageResist += res;
    }

    @Override
    public void remove(Creature creature) {
        creature.damageResist -= res;
    }
}
