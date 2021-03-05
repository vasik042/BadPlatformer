package com.mygdx.game.objects.creatures.enemyes.boses;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.objects.AnimationSprite;
import com.mygdx.game.objects.attacks.Spell;
import com.mygdx.game.objects.attacks.range.ArrowShower;
import com.mygdx.game.objects.attacks.range.FallingSpell;
import com.mygdx.game.objects.attacks.range.FireBall;
import com.mygdx.game.objects.attacks.range.MisslessOrb;
import com.mygdx.game.objects.creatures.enemyes.Enemy;
import com.mygdx.game.objects.creatures.enemyes.Maggot;
import com.mygdx.game.objects.creatures.heroes.Player;
import com.mygdx.game.objects.furnitures.Furniture;
import com.mygdx.game.objects.furnitures.nonBreaks.Door;
import com.mygdx.game.objects.furnitures.nonBreaks.fone.Fone;
import com.mygdx.game.objects.room.Room;

import java.util.ArrayList;

public class Boss1 extends Enemy {

    public float cd = 100;
    public float atTime;
    public int at = (int)(Math.random()*5);

    Sprite hpBar;
    Sprite hpBar1;
    Door door = new Door(1266, 208+16, false);


    public Spell[] spells;

    public Boss1(float x, float y) {
        super(x, y, new Sprite(new Texture("boss1/1.png")), 1000, 3, 100000);

        this.jumpSprites.add(new AnimationSprite(12, new Texture("boss1/1.png")));
        this.walkSprites.add(new AnimationSprite(5, new Texture("boss1/1.png")));
        this.walkSprites.add(new AnimationSprite(5, new Texture("boss1/2.png")));
        this.walkSprites.add(new AnimationSprite(5, new Texture("boss1/3.png")));
        this.walkSprites.add(new AnimationSprite(5, new Texture("boss1/4.png")));
        this.walkSprites.add(new AnimationSprite(5, new Texture("boss1/5.png")));
        this.walkSprites.add(new AnimationSprite(5, new Texture("boss1/6.png")));
        this.walkSprites.add(new AnimationSprite(5, new Texture("boss1/7.png")));
        this.walkSprites.add(new AnimationSprite(5, new Texture("boss1/8.png")));
        this.staySprites.add(new AnimationSprite(12, new Texture("boss1/1.png")));

        this.attackSprites.add(new AnimationSprite(12, new Texture("boss1/1.png")));

        this.deadSprites.add(new AnimationSprite(4, new Texture("boss1/d1.png")));
        this.deadSprites.add(new AnimationSprite(4, new Texture("boss1/d2.png")));
        this.deadSprites.add(new AnimationSprite(4, new Texture("boss1/d3.png")));
        this.deadSprites.add(new AnimationSprite(4, new Texture("boss1/d4.png")));
        this.deadSprites.add(new AnimationSprite(4, new Texture("boss1/d5.png")));
        this.deadSprites.add(new AnimationSprite(4, new Texture("boss1/d6.png")));
        this.deadSprites.add(new AnimationSprite(4, new Texture("boss1/d7.png")));
        this.deadSprites.add(new AnimationSprite(4, new Texture("boss1/d8.png")));
        this.deadSprites.add(new AnimationSprite(4, new Texture("boss1/d9.png")));

        pushResist = 1;
        collisionDamage = 0;

        spells = new Spell[4];

        spells[0] = new MisslessOrb();
        spells[0].reloadTime = 10;
        spells[0].ms = 5;
        spells[0].damage = 1;
        spells[0].endSpell.damage = 10;

        spells[1] = new FireBall();
        spells[1].reloadTime = 1;
        spells[1].range = 360;
        spells[1].ms = 5;
        spells[1].damage = 5;

        spells[2] = new ArrowShower();
        spells[2].enemy = true;

        spells[3] = new FallingSpell();
        spells[3].enemy = true;
        spells[3].range = 360;
        spells[3].shoots = 9;
        spells[3].damage = 10;

        hpBar = new Sprite(new Texture("hpbar.png"));
        hpBar.setSize(1080, 10);
        hpBar.setPosition(100, 700);
        hpBar1 = new Sprite(new Texture("hpbar1.png"));
        hpBar1.setSize(1082, 12);
        hpBar1.setPosition(99, 699);

        for (AnimationSprite s: fireSprites) {
            s.sprite.setSize(1, 1);
        }

    }

    @Override
    public void playerCollision(Player player) {
    }

    @Override
    public void live(Room room, SpriteBatch batch, ArrayList bullets, Player player) {
        if(hp/maxHp >= 0){
            hpBar1.draw(batch);
            hpBar.setSize(hp/maxHp*1080, 10);
            hpBar.draw(batch);
            if(!room.furniture.contains(door)){
                room.furniture.add(door);
            }
        }else {
            door.open = true;
        }

        spells[2].creature = player;
        walk = true;
        if(!dead){
            if(player.x > x){
                right = true;
            }else {
                right = false;
            }
        }else {
            effects.removeAll(effects);
        }
        super.live(room, batch, bullets, player);
    }

    @Override
    public void move(Room room, ArrayList<Spell> bullets, Player player) {
        cd--;
        if(cd == 0){
            atTime = 0;
        }
        if(cd <= 0 && at == 0) {
            spell = spells[0];
            attack(bullets, room, player);
            atTime++;
            if (atTime > 61) {
                cd = 250;
                while (at == 0){
                    at = (int)(Math.random()*5);
                }
            }
        }

        if(at == 1 && cd <= 0){
            spell = spells[1];
            attack(bullets, room, player);
            atTime++;
            if(atTime > 100){
                cd = 100;
                while (at == 1){
                    at = (int)(Math.random()*5);
                }
            }
        }

        if(at == 2 && cd <= 0){
            room.addEnemies.add(new Maggot(x-150, 500));
            room.addEnemies.add(new Maggot(x-100, 500));
            room.addEnemies.add(new Maggot(x-50, 500));

            room.addEnemies.add(new Maggot(x+50, 500, true));
            room.addEnemies.add(new Maggot(x+100, 500, true));
            room.addEnemies.add(new Maggot(x+150, 500, true));
            atTime++;
            if(atTime > 0){
                cd = 250;
                while (at == 2){
                    at = (int)(Math.random()*5);
                }
            }
        }
        if(at == 3 && cd <= 0){
            spell = spells[2];
            attack(bullets, room, player);
            atTime++;
            if(atTime > 250){
                cd = 120;
                while (at == 3){
                    at = (int)(Math.random()*5);
                }
            }
        }
        if(at == 4 && cd <= 0){
            spell = spells[3];
            attack(bullets, room, player);
            atTime++;
            if(atTime > 50){
                cd = 200;
                while (at == 4){
                    at = (int)(Math.random()*5);
                }
            }
        }
    }
}
