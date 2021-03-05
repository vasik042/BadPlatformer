package com.mygdx.game.objects.attacks.range;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.objects.AnimationSprite;
import com.mygdx.game.objects.attacks.Spell;
import com.mygdx.game.objects.attacks.effects.Push;
import com.mygdx.game.objects.creatures.Creature;
import com.mygdx.game.objects.room.Room;

import java.util.ArrayList;

public class Explode  extends Spell {

    public Explode(){
        super(0, 19, 10, 1, new Sprite(new Texture("explode/1.png")), 1, 1, true, 0, 0, 0, (byte)4, (byte)0);
        this.icon = new Sprite(new Texture("explode/1.png"));

        this.effects.add(new Push(damage/5));

        light.setScale(size);
        ang = 0;

        this.sprites.add(new AnimationSprite(1, new Texture("explode/1.png")));
        this.sprites.add(new AnimationSprite(1, new Texture("explode/2.png")));
        this.sprites.add(new AnimationSprite(1, new Texture("explode/3.png")));
        this.sprites.add(new AnimationSprite(1, new Texture("explode/4.png")));
        this.sprites.add(new AnimationSprite(1, new Texture("explode/5.png")));
        this.sprites.add(new AnimationSprite(1, new Texture("explode/6.png")));
        this.sprites.add(new AnimationSprite(1, new Texture("explode/7.png")));
        this.sprites.add(new AnimationSprite(1, new Texture("explode/8.png")));
        this.sprites.add(new AnimationSprite(1, new Texture("explode/9.png")));
        this.sprites.add(new AnimationSprite(1, new Texture("explode/10.png")));

        this.peirce = true;
        this.unique = true;
    }

    @Override
    public void hitEffect(Creature creature) {
        if(time < 2){
            super.hitEffect(creature);
        }
    }
}
