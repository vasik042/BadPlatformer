package com.mygdx.game.objects.Passives;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.objects.creatures.Creature;

public class MoveSpeedBoost extends Passive{
    float up;

    public MoveSpeedBoost(float up) {
        super(new Sprite(new Texture("heroes/mage/jump.png")));

        this.up = up + 1;
    }

    @Override
    public void add(Creature creature) {
        super.add(creature);
        creature.ms *= up;
        creature.maxMs *= up;
    }

    @Override
    public void remove(Creature creature) {
        creature.ms /= up;
        creature.maxMs /= up;
    }
}
