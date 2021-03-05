package com.mygdx.game.objects.attacks.effects;

import com.mygdx.game.objects.attacks.Spell;
import com.mygdx.game.objects.creatures.Creature;

public class Poison extends Effect{

    public Poison(float damage) {
        super(500, damage, 50);
    }

    @Override
    public void addEffect(Creature creature, Spell spell) {
        boolean poison = false;

        for (Effect e: creature.effects) {
            if(e.getClass() == this.getClass()){
                e.time = 0;
                e.damage = damage;
                e.maxTime = maxTime;
                e.maxPeriodic = maxPeriodic;
                poison = true;
            }
        }

        if(!poison){
            Poison p = new Poison(damage);
            creature.effects.add(p);
        }
    }

    @Override
    public void effect(Creature creature) {
        periodic ++;
        time ++;
        creature.poison = true;
        if(periodic >= maxPeriodic){
            creature.hp -= damage*(1 - creature.damageResist);
            creature.damaged = true;
            creature.damagedTime = 4;
            periodic = 0;
        }
    }
}
