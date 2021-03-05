package com.mygdx.game.objects.attacks.range;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.objects.AnimationSprite;
import com.mygdx.game.objects.attacks.Spell;
import com.mygdx.game.objects.creatures.Creature;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class Dash extends Spell {

    public Dash() {
        super(13, 8, 0, 2, new Sprite(new Texture("none.png")), 1, 5, true, 0, 0, 33, (byte)1, (byte)1);
        this.icon = new Sprite(new Texture("icons/dashi.jpg"));
        this.dash = true;
        this.peirce = true;
        this.sprites.add(new AnimationSprite(10, new Texture("none.png")));

        this.unique = true;

        name = "Dash";
        description = "Run bitch, ruuuuuuuun!";
    }


    @Override
    public void setParams(Creature creature) {
        this.creature = creature;
        this.creature.invincible = true;
        this.creature.invincibleTime = 5;
        this.creature.fallSpeed = 0;
    }


    @Override
    public void end(ArrayList<Spell> bullets) {
        creature.fly = false;
        creature.fallSpeed /= 2;
        creature.xSpeed /= 2;
        creature.invincibleTime = 0;
        creature.invincible = false;
    }
}
