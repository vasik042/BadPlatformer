package com.mygdx.game.objects.items;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.objects.GameObject;
import com.mygdx.game.objects.Passives.Passive;
import com.mygdx.game.objects.creatures.Creature;

public class Item extends GameObject {
    public int quantity = -1;
    public Passive effect;
    public String name = "Name";
    public String description = "Description";

    public Item(Sprite sprite) {
        super(0, 0, sprite);
    }

    public void  use(Creature creature){

    }

    public void  remove(Creature creature){

    }
}
