package com.mygdx.game.objects.attacks.effects;


import com.mygdx.game.objects.attacks.Spell;
import com.mygdx.game.objects.creatures.Creature;

public class FireEffect extends Effect {

    public FireEffect(float damage) {
        super(100, damage, 20);
    }

    @Override
    public void addEffect(Creature creature, Spell spell) {
        boolean onFire = false;

        for (Effect e: creature.effects) {
            if(e.getClass() == this.getClass()){
                e.time = 0;
                if(e.damage < damage){
                    e.damage = damage;
                }

                e.maxTime = maxTime;
                e.maxPeriodic = maxPeriodic;
                onFire = true;
            }
        }

        if(!onFire){
            FireEffect fe = new FireEffect(damage);
            creature.effects.add(fe);
        }
    }

    @Override
    public void effect(Creature creature) {
        periodic ++;
        time ++;
        creature.onFire = true;
        if(periodic >= maxPeriodic){
            creature.hp -= damage*(1 - creature.damageResist);
            creature.damaged = true;
            creature.damagedTime = 10;
            periodic = 0;
        }
    }
}
