package com.mygdx.game.objects.attacks;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.objects.AnimationSprite;
import com.mygdx.game.objects.Passives.DoubleJump;
import com.mygdx.game.objects.Passives.Passive;
import com.mygdx.game.objects.creatures.Creature;

import java.util.ArrayList;

public class Aura extends Spell{
    public Passive effect;
    public boolean activated;

    public Aura() {
        super(0, 0, 0, 0, new Sprite(new Texture("icons/sheildi.jpg")), 1, 9, false, 0, 1, 25, (byte)1, (byte)4);

        this.sprites.add(new AnimationSprite(3, new Texture("bow/arrow.png")));

        this.icon = new Sprite(new Texture("icons/sheildi.jpg"));

        name = "Shield";
        description = "Become a rock!";
    }

    public Aura(Passive effect) {
        super(0, 0, 0, 0, new Sprite(new Texture("icons/sheildi.jpg")), 1, 9, false, 0, 1, 25, (byte)1, (byte)4);

        this.sprites.add(new AnimationSprite(3, new Texture("bow/arrow.png")));

        this.effect = effect;
        this.icon = new Sprite(new Texture("icons/sheildi.jpg"));

        name = "Shield";
        description = "Become a rock!";
    }

    @Override
    public Spell makeCopy() {
        Spell spell = super.makeCopy();
        try {
            spell.getClass().getDeclaredField("effect").set(spell, effect);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return spell;
    }

    @Override
    public void setParams(Creature creature) {
        if(activated){
            activated = false;
            creature.removedPassives.add(effect);
            icon.setColor(1f, 1f, 1, 1);
        }else {
            activated = true;
            effect.add(creature);
            icon.setColor(0.8f, 0.7f, 0.1f, 1);
        }
    }

    @Override
    public void hitEffect(Creature creature) {
        creature.mp -= mp;
        if(creature.mp <= 0){
            setParams(creature);
        }
    }
}
