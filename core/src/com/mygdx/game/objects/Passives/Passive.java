package com.mygdx.game.objects.Passives;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.objects.GameObject;
import com.mygdx.game.objects.creatures.Creature;

public class Passive extends GameObject {
    public int time = -1;
    
    public Passive(Sprite sprite) {
        super(0, 0, sprite);
    }

    public void add(Creature creature){
        creature.passives.add(this);
    }

    public void live(Creature creature){
        if(time > 0){
            time--;
            if(time == 0){
                creature.removedPassives.add(this);
            }
        }
    }

    public void remove(Creature creature){
    }
}
