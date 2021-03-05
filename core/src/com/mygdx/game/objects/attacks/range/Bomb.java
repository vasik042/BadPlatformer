package com.mygdx.game.objects.attacks.range;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.objects.AnimationSprite;
import com.mygdx.game.objects.attacks.Spell;
import com.mygdx.game.objects.attacks.effects.FireEffect;
import com.mygdx.game.objects.attacks.effects.Push;
import com.mygdx.game.objects.creatures.Creature;
import com.mygdx.game.objects.room.Room;

import java.util.ArrayList;

public class Bomb extends Spell {

    public Bomb(){
        super(5, 200, 2, 1, new Sprite(new Texture("Enemy_bullet.png")), 1, 1, true, 0, 40, 40, (byte)3, (byte)0);
        this.icon = new Sprite(new Texture("icons/falli.jpg"));

        this.effects.add(new Push(2f));

        this.fall = true;
        this.peirce = true;
        this.fallSpeed = 7;
        this.jump = 500;

        this.sprites.add(new AnimationSprite(3, new Texture("bomb/1.png")));
        this.sprites.add(new AnimationSprite(3, new Texture("bomb/2.png")));

        for (AnimationSprite s: this.sprites) {
            s.sprite.setScale(size);
            s.sprite.setOriginCenter();
        }

        Explode explode = new Explode();
        explode.damage = 100;
        explode.size = 1.5f;
        explode.effects.add(new Push(10));
        endSpell = explode;

        name = "Bomb";
        description = "Filled with magic";
    }

    @Override
    public void fallRotate() {
    }

    @Override
    public void live(SpriteBatch batch, Room room, ArrayList<Spell> addBullets) {
        if(time == 0){
            deltaX*=2;
        }

        for (AnimationSprite s: sprites) {
            s.sprite.rotate(-rizX*5);
        }
        if(deltaY == 0){
            deltaX *= 0.96;
        }else {
            deltaX *= 0.99;
        }
        super.live(batch, room, addBullets);
    }


    @Override
    public boolean collisionEffect(Room room) {
        boolean b = super.collisionEffect(room);
        if(b){
            if(deltaY < 0.25 && deltaY > 0 && room.findCollision(x1, y1 - 32) != null){
                deltaY = 0;
                fallSpeed = 0;
            }else {
                deltaY *= 0.5;
            }
        }
        return b;
    }
}
