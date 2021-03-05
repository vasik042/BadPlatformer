package com.mygdx.game.objects.attacks.melee;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.objects.AnimationSprite;
import com.mygdx.game.objects.attacks.Spell;
import com.mygdx.game.objects.attacks.effects.Push;
import com.mygdx.game.objects.attacks.range.Arrow;
import com.mygdx.game.objects.creatures.Creature;
import com.mygdx.game.objects.furnitures.Furniture;
import com.mygdx.game.objects.room.Room;

import java.util.ArrayList;

public class Charge extends Melee{

    float holdTime;

    public Charge(){
        super(5, 1, 10, 1f, new Sprite(new Texture("charge/1.png")), 1, -1, false, 0, 0, 15, (byte) 0, (byte)0);
        this.icon = new Sprite(new Texture("charge/1.png"));

        this.sprites.add(new AnimationSprite((int)(100), new Texture("charge/1.png")));

        this.hold = true;
        this.unique = true;
    }

    @Override
    public void setParams(Creature creature) {
        super.setParams(creature);

        creature.isCharging = true;
        time = 0;
        if(holdTime < 300){
            holdTime += ms;
        }else {
            creature.charged = true;
            holdTime = 300;
        }
    }

    @Override
    public void live(SpriteBatch batch, Room room, ArrayList<Spell> addBullets) {
        super.live(batch, room, addBullets);
        x1 = creature.x;
        y1 = creature.y;
    }


    @Override
    public void end(ArrayList<Spell> bullets) {
        creature.charged = false;
        creature.isCharging = false;

        int getX = Gdx.input.getX();
        int getY = Gdx.input.getY();
        ang = (float)Math.atan((getX - x1) /((736 - getY) - y1));
        if (y1 > 736-Gdx.input.getY()){
            ang += 3.1459;
        }
        deltaX = (float)Math.sin(ang);
        deltaY = (float)Math.cos(ang);

        x1 += 18*deltaX;
        y1 += 18*deltaY;


        Spell arrow = midSpell.makeCopy();
        arrow.x1 = x1;
        arrow.y1 = y1;
        arrow.enemy = this.enemy;
        arrow.ms = midSpell.ms * (0.5f + holdTime/300);
        arrow.damage = midSpell.damage * (0.5f + holdTime/300);
        arrow.creature = this.creature;
        arrow.effects.addAll(effects);


        this.holdTime = 0;

        this.reload = reloadTime;

        if(midSpell.shoots > 1){
            float riz = (float)((float)midSpell.range/(float)midSpell.shoots/180*3.14159);
            ang -= riz*((float) midSpell.shoots/2 + 0.5);

            for (int i = 0; i < midSpell.shoots; i++) {

                ang+=riz;
                if (y1 > 736- Gdx.input.getY()){
                    ang += 3.1459;
                }
                arrow.ang = ang;

                arrow.deltaX = (float)Math.sin(ang);
                arrow.deltaY =  (float)Math.cos(ang);

                for (AnimationSprite s:arrow.sprites) {
                    s.sprite.setRotation((float) (this.ang*180/3.1415)* (-1));
                }

                if(arrow.fall){
                    deltaY *= 2;
                }

                bullets.add(arrow.makeCopy());
            }
        }else {
            ang-=(double)range/180*3.14159*(Math.random()*2 -1);
            arrow.ang = ang;

            arrow.deltaX = (float)Math.sin(ang);
            arrow.deltaY =  (float)Math.cos(ang);

            for (AnimationSprite s:arrow.sprites) {
                s.sprite.setRotation((float) (this.ang*180/3.1415)* (-1));
            }

            if(arrow.fall){
                deltaY *= 2;
            }

            bullets.add(arrow);
        }
    }
}
