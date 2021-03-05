package com.mygdx.game.objects.Passives;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.objects.creatures.Creature;

public class HpRegen  extends Passive{
    float hp;

    public HpRegen(float hp) {
        super(new Sprite(new Texture("heroes/mage/jump.png")));
        this.hp = hp;
    }

    @Override
    public void live(Creature creature) {
        super.live(creature);
        if(creature.hp < creature.maxHp){
            creature.hp += hp;
        }
    }
}
