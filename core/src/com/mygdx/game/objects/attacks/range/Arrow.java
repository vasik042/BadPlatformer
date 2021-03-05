package com.mygdx.game.objects.attacks.range;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.objects.AnimationSprite;
import com.mygdx.game.objects.attacks.Spell;
import com.mygdx.game.objects.attacks.effects.FireEffect;
import com.mygdx.game.objects.attacks.effects.Push;
import com.mygdx.game.objects.creatures.Creature;
import com.mygdx.game.objects.furnitures.Furniture;
import com.mygdx.game.objects.room.Room;

import java.util.ArrayList;

public class Arrow extends Spell {

    public Arrow(){
        super(7, 150, 10, 1f, new Sprite(new Texture("bow/arrow.png")), 1, 8, false, 0, 15, 15, (byte)2, (byte)4);
        this.icon = new Sprite(new Texture("icons/fireballIcon.png"));

        this.damagedCreatures = new ArrayList<>();

        this.sprites.add(new AnimationSprite(3, new Texture("bow/arrow.png")));

        this.isStopped = true;
        this.fallSpeed = 3;
        this.fall = true;
        this.unique = true;
    }
}
