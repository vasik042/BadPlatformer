package com.mygdx.game.objects.attacks.range;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.objects.AnimationSprite;
import com.mygdx.game.objects.attacks.Spell;
import com.mygdx.game.objects.attacks.effects.FireEffect;
import com.mygdx.game.objects.attacks.effects.Push;
import com.mygdx.game.objects.room.Room;

import java.util.ArrayList;

public class MisslessOrb   extends Spell {

    public MisslessOrb(){
        super(3, 400, 10, 2f, new Sprite(new Texture("missleOrb/1.png")), 1, 1, false, 0, 15, 150, (byte)1, (byte)1);
        this.icon = new Sprite(new Texture("missleOrb/1.png"));

        this.effects.add(new Push(5f));

        this.sprites.add(new AnimationSprite(3, new Texture("missleOrb/1.png")));
        this.sprites.add(new AnimationSprite(3, new Texture("missleOrb/1.png")));
        this.sprites.add(new AnimationSprite(3, new Texture("missleOrb/1.png")));
        this.sprites.add(new AnimationSprite(3, new Texture("missleOrb/1.png")));

        missless = true;
        misslessMs = 4;

        Explode explode = new Explode();
        explode.damage = 10;
        explode.size = size*0.55f;
        explode.x1 = x1;
        explode.y1 = y1;
        explode.effects.add(new Push(10));

        endSpell = explode;
    }

    @Override
    public void live(SpriteBatch batch, Room room, ArrayList<Spell> addBullets) {
        if(time == 0){
            y1 += 80 + Math.random()*40;
            x1 += Math.random()*100-50 - 18*deltaX;
        }

        super.live(batch, room, addBullets);
    }

    @Override
    public void draw(SpriteBatch batch, ArrayList<Spell> addBullets) {
        if(time > 50){
            super.draw(batch, addBullets);
        }else if(enemy){
            ang = (float)Math.atan((x1 - creature.x) /(y1 - creature.y));

            if (creature.y > y1){
                ang += 3.1459;
            }

            deltaX = -(float)Math.sin(ang);
            deltaY = -(float)Math.cos(ang);

            for (AnimationSprite s: sprites) {
                if(deltaX == 1 || deltaX == -1){
                    s.sprite.setRotation((float) (this.ang*180/3.1415));
                }
            }

            sprite.setOriginBasedPosition(x1, y1);
            sprite.draw(batch);
        }else {
            float getX = Gdx.input.getX();
            float getY = Gdx.input.getY() - creature.yPos;

            ang = (float)Math.atan((x1 - getX) /(y1 - (736 - getY)));

            if ((736 - getY > y1)){
                ang += 3.1459;
            }

            deltaX = -(float)Math.sin(ang);
            deltaY = -(float)Math.cos(ang);

            for (AnimationSprite s: sprites) {
                s.sprite.setRotation((float) (this.ang*180/3.1415 + 180)* (-1));
            }
            sprite.setOriginBasedPosition(x1, y1);
            sprite.draw(batch);
        }
    }

    @Override
    public Spell fuse(Spell spell) {
        spell.missless = true;
        return spell;
    }
}