package com.mygdx.game.objects.attacks.range;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.objects.AnimationSprite;
import com.mygdx.game.objects.attacks.Spell;
import com.mygdx.game.objects.attacks.effects.FireEffect;
import com.mygdx.game.objects.attacks.effects.Push;
import com.mygdx.game.objects.room.Room;

import java.util.ArrayList;

public class ArrowShower  extends Spell {

    public ArrowShower(){
        super(5, 150, 15, 1f, new Sprite(new Texture("bow/arrow.png")), 1, 9, false, 0, 15, 4, (byte)1, (byte)4);
        this.icon = new Sprite(new Texture("icons/fireballIcon.png"));

        this.sprites.add(new AnimationSprite(3, new Texture("bow/arrow.png")));

        this.isStopped = true;
        this.fallSpeed = 3;
        this.fall = true;
        this.unique = true;
    }

    @Override
    public void setParams(float x1, float y1, float ang, float deltaX, float deltaY, boolean enemy) {
    }

    @Override
    public void live(SpriteBatch batch, Room room, ArrayList<Spell> addBullets) {
        if(time == 0){
            this.x1 = (float) (creature.x+Math.random()*100-50);
            this.y1 = (float) (500+Math.random()*100-50);
            this.deltaX = 0;
            this.deltaY = -1;

            this.sprites.get(0).sprite.setSize(10, 50);
            this.sprites.get(0).sprite.rotate(180);
        }
        super.live(batch, room, addBullets);
    }
}