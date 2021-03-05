package com.mygdx.game.objects.creatures.enemyes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.objects.AnimationSprite;
import com.mygdx.game.objects.attacks.Spell;
import com.mygdx.game.objects.attacks.range.FireBall;
import com.mygdx.game.objects.attacks.range.FireOrb;
import com.mygdx.game.objects.attacks.range.Laser;
import com.mygdx.game.objects.creatures.heroes.Player;
import com.mygdx.game.objects.room.Room;

import java.util.ArrayList;

public class Eye extends Enemy{
    public float time;
    public float preAttackTime;

    public Eye(float x, float y) {
        super(x, y, new Sprite(new Texture("heroes/mage/stay1.png")), 150, 3, 700);

        this.staySprites.add(new AnimationSprite(12, new Texture("eye/1.png")));
        this.staySprites.add(new AnimationSprite(12, new Texture("eye/2.png")));
        this.staySprites.add(new AnimationSprite(12, new Texture("eye/3.png")));
        this.staySprites.add(new AnimationSprite(12, new Texture("eye/4.png")));
        this.staySprites.add(new AnimationSprite(12, new Texture("eye/5.png")));
        this.staySprites.add(new AnimationSprite(12, new Texture("eye/6.png")));
        this.staySprites.add(new AnimationSprite(12, new Texture("eye/7.png")));
        this.staySprites.add(new AnimationSprite(12, new Texture("eye/8.png")));
        this.staySprites.add(new AnimationSprite(12, new Texture("eye/9.png")));
        this.staySprites.add(new AnimationSprite(12, new Texture("eye/10.png")));
        this.staySprites.add(new AnimationSprite(12, new Texture("eye/11.png")));
        this.staySprites.add(new AnimationSprite(12, new Texture("eye/10.png")));
        this.staySprites.add(new AnimationSprite(12, new Texture("eye/9.png")));

        this.attackSprites.add(new AnimationSprite(12, new Texture("eye/5.png")));
        this.jumpSprites.add(new AnimationSprite(12, new Texture("eye/5.png")));

        this.deadSprites.add(new AnimationSprite(12, new Texture("eye/9.png")));
        this.deadSprites.add(new AnimationSprite(12, new Texture("eye/10.png")));
        this.deadSprites.add(new AnimationSprite(12, new Texture("eye/11.png")));

        for (AnimationSprite s: fireSprites) {
            s.sprite.setOrigin(35, 10);
            s.sprite.setSize(90, 80);
        }
        this.preAttackTime = 0;

        this.pushResist = 0.8f;
        this.fly = true;

        this.time = (float) Math.random()*40 - 20;
        this.spell = new FireOrb();
    }

    @Override
    public void live(Room room, SpriteBatch batch, ArrayList bullets, Player player) {
        super.live(room, batch, bullets, player);
        if(dead){
            for (AnimationSprite s: deadSprites) {
                s.sprite.rotate(-xSpeed);
            }
        }
    }

    @Override
    public void move(Room room, ArrayList<Spell> bullets, Player player) {
        time++;

        y -=(float) Math.sin(time/20)/5;

        if(playerIsFind){
            preAttackTime++;
            attack = true;
            if(preAttackTime > 50){
                attack(bullets, room, player);
            }
        }else {
            preAttackTime = 0;
        }
    }
}