package com.mygdx.game.objects.items.equipment;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.objects.Passives.Passive;
import com.mygdx.game.objects.creatures.Creature;
import com.mygdx.game.objects.items.Item;

public class Equipment extends Item {
    //1 - amulet
    //2 - gloves
    //3 - hat
    //4 - armor
    //5 - pants
    //6 - shoes
    //7 - ring

    public int type;

    public Equipment(Texture texture, Passive passive, int type) {
        super(new Sprite(texture));
        this.effect = passive;
        this.type = type;
    }

    @Override
    public void use(Creature creature) {
        effect.add(creature);
    }

    @Override
    public void remove(Creature creature) {
        creature.removedPassives.add(effect);
    }
}
