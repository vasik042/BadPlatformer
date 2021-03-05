package com.mygdx.game.objects.attacks.melee;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.objects.AnimationSprite;
import com.mygdx.game.objects.attacks.Spell;
import com.mygdx.game.objects.attacks.effects.FireEffect;
import com.mygdx.game.objects.attacks.effects.Push;
import com.mygdx.game.objects.attacks.range.Arrow;
import com.mygdx.game.objects.attacks.range.Explode;
import com.mygdx.game.objects.attacks.range.FireOrb;
import com.mygdx.game.objects.creatures.Creature;
import com.mygdx.game.objects.room.Room;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class Bow extends Melee {

    float holdTime;

    public Bow(){
        super(5, 1, 10, 1f, new Sprite(new Texture("bow/4.png")), 1, 6, false, 0, 15, 25, (byte) 0, (byte)0);
        this.icon = new Sprite(new Texture("icons/bowi.jpg"));

        this.sprites.add(new AnimationSprite((int)(100/ms), new Texture("bow/1.png")));
        this.sprites.add(new AnimationSprite((int)(100/ms), new Texture("bow/2.png")));
        this.sprites.add(new AnimationSprite((int)(100/ms), new Texture("bow/3.png")));
        this.sprites.add(new AnimationSprite(10000, new Texture("bow/4.png")));

        hold = true;
        midSpell = new Arrow();

        name = "Bow";
        description = "It gets stronger when i pull on it\n" +
                "Make spell charged";
    }

    @Override
    public void setParams(Creature creature) {
        super.setParams(creature);
        time = 0;
        if(holdTime <= 300){
            holdTime += ms;
        }else {
            holdTime = 300;
        }
    }

    @Override
    public void hitEffect(Creature creature) {
    }

    @Override
    public Spell makeCopy() {
        Spell spell = super.makeCopy();

        spell.sprites = null;
        spell.sprites = new ArrayList<>();

        spell.sprites.add(new AnimationSprite((int)(100/ms), new Texture("bow/1.png")));
        spell.sprites.add(new AnimationSprite((int)(100/ms), new Texture("bow/2.png")));
        spell.sprites.add(new AnimationSprite((int)(100/ms), new Texture("bow/3.png")));
        spell.sprites.add(new AnimationSprite(10000, new Texture("bow/4.png")));

        return spell;
    }

    @Override
    public void end(ArrayList<Spell> bullets) {
        Spell arrow = midSpell.makeCopy();
        arrow.effects.addAll(effects);
        arrow.effects.add(new Push(holdTime/300));
        arrow.x1 = x1;
        arrow.y1 = y1;
        arrow.enemy = this.enemy;
        arrow.ms = ms + holdTime/60;
        arrow.damage = damage + holdTime/15;
        arrow.fallSpeed = 3 - holdTime/100;
        if(arrow.missless){
            arrow.creature = this.creature;
            arrow.isStopped = false;
            arrow.stop = false;
            arrow.sootType = 1;
            arrow.collisionType = 1;
            arrow.collisionEffect = 1;
        }

        if(!arrow.peirce){
            arrow.peirce = (holdTime >= 300);
        }

        this.spriteNum = 0;
        for (AnimationSprite s: sprites) {
            s.time = 0;
        }
        this.sprites.get(2).time = 0;

        this.reload = reloadTime;

        if(shoots > 1){
            float riz = (float)((float)range/(float)shoots/180*3.14159);
            ang -= riz*((float) shoots/2 + 0.5);

            for (int i = 0; i < shoots; i++) {
                ang+=riz;
                if (y1 > 736- Gdx.input.getY() + creature.yPos){
                    ang += 3.1459;
                }
                arrow.ang = ang;
                arrow.deltaX = (float)Math.sin(ang) * (holdTime/300f);
                arrow.deltaY =  (float)Math.cos(ang) * (holdTime/300f);
                arrow.sprites.get(0).sprite.setRotation((float) (this.ang*180/3.1415)* (-1));

                bullets.add(arrow.makeCopy());
            }
        }else {
            ang-=(double)range/180*3.14159*(Math.random()*2 -1);
            arrow.ang = ang;
            arrow.deltaX = (float)(Math.sin(ang) * (holdTime/300f));
            arrow.deltaY =  (float)(Math.cos(ang) * (holdTime/300f));
            arrow.sprites.get(0).sprite.setRotation((float) (this.ang*180/3.1415)* (-1));
            bullets.add(arrow);
        }
        this.holdTime = 0;
    }

    @Override
    public Spell fuse(Spell spell) {
        if (!spell.getClass().getSimpleName().equals("Laser")) {
            Spell spell1 = new Charge();
            spell1.midSpell = spell;
            spell1.name = spell.name;
            spell1.description = spell.description;
            spell1.icon = spell.icon;
            spell1.ms = 1 + 80/spell.reloadTime;
            spell1.reloadTime = spell.reloadTime;
            spell1.mp = spell.mp;
            return spell1;
        }else {
            return spell;
        }
    }
}
