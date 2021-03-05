package com.mygdx.game.objects.creatures.enemyes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.objects.AnimationSprite;
import com.mygdx.game.objects.attacks.Spell;
import com.mygdx.game.objects.attacks.range.FallingSpell;
import com.mygdx.game.objects.attacks.range.FireBall;
import com.mygdx.game.objects.creatures.heroes.Player;
import com.mygdx.game.objects.furnitures.Furniture;
import com.mygdx.game.objects.room.Room;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;

public class EnemyMage extends Enemy{

    public EnemyMage(float x, float y) {
        super(x, y,  new Sprite(new Texture("heroes/mage/stay1.png")), 250, 2, 300);

        this.jumpSprites.add(new AnimationSprite(12, new Texture("heroes/mage/jump.png")));
        this.walkSprites.add(new AnimationSprite(12, new Texture("heroes/mage/walk1.png")));
        this.walkSprites.add(new AnimationSprite(12, new Texture("heroes/mage/walk2.png")));
        this.walkSprites.add(new AnimationSprite(12, new Texture("heroes/mage/walk3.png")));
        this.walkSprites.add(new AnimationSprite(12, new Texture("heroes/mage/stay1.png")));
        this.staySprites.add(new AnimationSprite(12, new Texture("heroes/mage/stay1.png")));
        this.staySprites.add(new AnimationSprite(12, new Texture("heroes/mage/stay2.png")));

        this.attackSprites.add(new AnimationSprite(12, new Texture("heroes/mage/attack1.png")));
        this.attackSprites.add(new AnimationSprite(12, new Texture("heroes/mage/attack2.png")));

        this.deadSprites.add(new AnimationSprite(20, new Texture("heroes/mage/dead1.png")));

        for (AnimationSprite s: jumpSprites) {
            s.sprite.setSize(26, 32);
            s.sprite.setOriginCenter();
        }
        for (AnimationSprite s: walkSprites) {
            s.sprite.setSize(26, 32);
            s.sprite.setOriginCenter();
        }
        for (AnimationSprite s: staySprites) {
            s.sprite.setSize(26, 32);
            s.sprite.setOriginCenter();
        }
        for (AnimationSprite s: attackSprites) {
            s.sprite.setSize(26, 32);
            s.sprite.setOriginCenter();
        }
        for (AnimationSprite s: deadSprites) {
            s.sprite.setSize(32, 21);
            s.sprite.setOriginCenter();
        }

        this.spell = new FallingSpell();
    }

    @Override
    public void live(Room room, SpriteBatch batch, ArrayList bullets, Player player) {
        super.live(room, batch, bullets, player);

    }

    @Override
    public void move(Room room, ArrayList<Spell> bullets, Player player) {
        if (playerIsFind || playerIsHidden) {
            if (playerIsFind && (Math.sqrt(delX * delX + delY * delY) < 150) && !jump) {
                if(x > playerX){
                    right = false;
                }else {
                    right = true;
                }
                attack(bullets, room, player);
            } else if (!attack) {
                walk = true;
                if (lastPlayerX > x + 25) {
                    right = true;
                    xSpeed += ms/3;
                }else if (lastPlayerX < x - 25) {
                    right = false;
                    xSpeed -= ms/3;
                }else {
                    if(lastPlayerX > x){
                        lastPlayerX = playerX+100;
                    }else {
                        lastPlayerX = playerX-100;
                    }
                    lastPlayerY = playerY;
                }
                findJump(room);
            }
        } else {
            walk = true;
            if (right) {
                xSpeed += ms/6;
            }else {
                xSpeed -= ms/6;
            }
            findCollision(room);
        }
    }

    private void findCollision(Room room) {
        Furniture r = room.findCollision(x + sprite.getWidth() / 2 + 5, y);
        Furniture l = room.findCollision(x - sprite.getWidth() / 2 - 5, y);

        if(r != null){
            if(!r.transitional){
                right = false;
            }
        }

        if(l != null){
            if(!l.transitional){
                right = true;
            }
        }
    }

    public void findJump(Room room){
        if (walk && playerY > y+30){

            int i = 32;
            if(!right){
                i *= -1;
            }

            Furniture collision = room.findCollision(x + i, y + 96);
            Furniture collision0 = room.findCollision(x + i, y);
            Furniture collision1 = room.findCollision(x + i, y + 64);
            Furniture collision2 = room.findCollision(x + i, y + 32);
            Furniture collision3 = room.findCollision(x, y + 32);
            Furniture collision4 = room.findCollision(x, y + 64);
            Furniture collision5 = room.findCollision(x, y + 96);

            if((collision != null || collision1 != null || collision2 != null || collision0 != null) && (collision3 == null && collision4 == null && collision5 == null)){
                if(!jump){
                    fallSpeed = -12;
                }
            }
        }
    }

//    public void findWey(Room room){
//        if (playerY > y +32 && playerIsHidden){
//
//            Furniture collision;
//            Furniture collisionL = null;
//            Furniture collisionR = null;
//            for (int i = 1; i < 4; i++) {
//                collision = room.findCollision(x, y + i*32);
//
//                if (collision!=null){
//                    if (playerX < x){
//                        goLeft = true;
//                    }else {
//                        goRight = true;
//                    }
//                    break;
//                }
//            }
//        }
//    }
}
