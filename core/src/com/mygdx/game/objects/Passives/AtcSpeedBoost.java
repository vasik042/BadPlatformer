package com.mygdx.game.objects.Passives;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.objects.creatures.Creature;

public class AtcSpeedBoost extends Passive{
    float boost;

    public AtcSpeedBoost(float boost) {
        super(new Sprite(new Texture("heroes/mage/jump.png")));

        this.boost = boost;
    }

    @Override
    public void add(Creature creature) {
        super.add(creature);
        creature.atcSpeedBoost += boost;
    }

    @Override
    public void remove(Creature creature) {
        creature.atcSpeedBoost -= boost;
    }
}
