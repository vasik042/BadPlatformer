package com.mygdx.game.objects.attacks.effects;

import com.mygdx.game.objects.attacks.Spell;
import com.mygdx.game.objects.creatures.Creature;

public class Effect {
    public float time;
    public float maxTime;
    public float damage;
    public float periodic;
    public float maxPeriodic;

    public Effect(float maxTime, float damage, float maxPeriodic) {
        this.maxTime = maxTime;
        this.time = 0;
        this.damage = damage;
        this.maxPeriodic = maxPeriodic;
        this.periodic = 0;
    }

    public void addEffect(Creature creature, Spell spell){
        creature.effects.add(this);
    }

    public void effect(Creature creature){}

}
