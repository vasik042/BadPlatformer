package com.mygdx.game.objects.attacks.melee;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.objects.AnimationSprite;
import com.mygdx.game.objects.attacks.Spell;
import com.mygdx.game.objects.attacks.effects.FireEffect;
import com.mygdx.game.objects.attacks.effects.Push;
import com.mygdx.game.objects.room.Room;

import java.util.ArrayList;

public class Fisting  extends Spell {


    public Fisting(){
        super(3, 10, 10, 2f, new Sprite(new Texture("fisting/1.png")), 1, 1, false, 20, 3, 5, (byte)5, (byte)3);
        this.icon = new Sprite(new Texture("icons/fistingi.jpg"));

        this.effects.add(new Push(5f));

        this.sprites.add(new AnimationSprite(10, new Texture("fisting/1.png")));

        name = "Fisting";
        description = "Ora Ora Ora!";
    }

    @Override
    public void setParams(float x1, float y1, float ang, float deltaX, float deltaY, boolean enemy) {
        super.setParams(x1, y1, ang, deltaX, deltaY, enemy);
    }

    @Override
    public void live(SpriteBatch batch, Room room, ArrayList<Spell> addBullets) {
        if(time == 1){
            ms = creature.ms;
            deltaX+= creature.xSpeed/4;
        }
        super.live(batch, room, addBullets);
    }

    @Override
    public Spell fuse(Spell spell) {
        return spell;
    }
}