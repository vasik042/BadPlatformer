package com.mygdx.game.objects.attacks.effects;

import com.mygdx.game.objects.attacks.Spell;
import com.mygdx.game.objects.creatures.Creature;

public class Push extends Effect {

    public float power;
    public float deltaX;
    public float deltaY;
    public Push(float power) {
        super(10, 0, 0);
        this.power = power;
    }

    @Override
    public void addEffect(Creature creature, Spell spell) {
        super.addEffect(creature, spell);

        if(spell.deltaX == 0 && spell.deltaY == 0){
            float delX = spell.x1 - creature.x;
            float delY = spell.y1 - creature.y;

            float ang = (float)Math.atan(delX/delY);
            if (creature.y > spell.y1){
                ang += 3.1459;
            }

            deltaX = -(float)Math.sin(ang)*power;
            deltaY = -(float)Math.cos(ang)*power;

        }else {
            deltaX = spell.deltaX*power;
            deltaY = spell.deltaY*power;
        }

    }

    @Override
    public void effect(Creature creature) {
        if(time == 0){
            creature.fallSpeed = 0;
            creature.xSpeed = 0;
        }

        creature.xSpeed += deltaX*(1-creature.pushResist);
        if(creature.fly){
            creature.fallSpeed -= deltaY*(1-creature.pushResist);
        }else if (creature.jump){
            creature.fallSpeed -= deltaY*(1-creature.pushResist)/2;
        }else {
            creature.fallSpeed -= deltaY*(1-creature.pushResist)/5;
        }
        deltaY /= 2;
        deltaX /= 2;
        time++;
    }
}
