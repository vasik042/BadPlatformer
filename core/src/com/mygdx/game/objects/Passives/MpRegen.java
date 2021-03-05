package com.mygdx.game.objects.Passives;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.objects.creatures.Creature;

public class MpRegen extends Passive{
    float mp;

    public MpRegen(float mp) {
        super(new Sprite(new Texture("heroes/mage/jump.png")));
        this.mp = mp;

    }

    @Override
    public void live(Creature creature) {
        super.live(creature);
        if(creature.mp < creature.maxMp && !creature.attack){
            creature.mp += mp;
        }
    }
}
