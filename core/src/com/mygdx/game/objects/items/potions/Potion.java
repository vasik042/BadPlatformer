package com.mygdx.game.objects.items.potions;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.objects.Passives.Passive;
import com.mygdx.game.objects.creatures.Creature;
import com.mygdx.game.objects.items.Item;

public class Potion  extends Item {

    public int effectTime;

    public Potion(Passive effect, int time, Texture texture) {
        super(new Sprite(texture));
        this.effect = effect;
        this.effectTime = time;
        this.quantity = 1;
    }

    @Override
    public void use(Creature creature){
        effect.time = effectTime;
        effect.add(creature);
        quantity --;
        if(quantity == 0){
            creature.removedItems.add(this);
        }
    }
}
