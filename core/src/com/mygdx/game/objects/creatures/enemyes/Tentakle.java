package com.mygdx.game.objects.creatures.enemyes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.objects.AnimationSprite;
import com.mygdx.game.objects.attacks.Spell;
import com.mygdx.game.objects.attacks.effects.Poison;
import com.mygdx.game.objects.attacks.effects.Push;
import com.mygdx.game.objects.creatures.heroes.Player;
import com.mygdx.game.objects.furnitures.Furniture;
import com.mygdx.game.objects.room.Room;

import java.util.ArrayList;

public class Tentakle  extends Enemy{
    private float height;

    public Tentakle(float x, float y) {
        super(x, y, new Sprite(new Texture("tentakle/1.png")), 1500, 1.5f, 0);

        staySprites.add(new AnimationSprite(1, new Texture("tentakle/1.png")));

        walkSprites.add(new AnimationSprite(50, new Texture("tentakle/1.png")));
        for (int i = 2; i < 22; i++) {
            String s = "tentakle/"+ i + ".png";
            walkSprites.add(new AnimationSprite(2, new Texture(s)));
        }

        for (int i = 21; i > 0; i--) {
            String s = "tentakle/"+ i + ".png";
            walkSprites.add(new AnimationSprite(2, new Texture(s)));
            deadSprites.add(new AnimationSprite(2, new Texture(s)));
        }

        jumpSprites.add(new AnimationSprite(15, new Texture("tentakle/1.png")));
        attackSprites.add(new AnimationSprite(15, new Texture("tentakle/1.png")));

        for (AnimationSprite s:walkSprites) {
            s.sprite.setOrigin(s.sprite.getWidth()/2, s.sprite.getHeight()/2+8);
        }

        collisionDamage = 25;
        pushResist = 1;

        light.setScale(0.5f);
        for (AnimationSprite s:fireSprites) {
            s.sprite.setSize(60, 20);
            s.sprite.setOrigin(20, -28);
        }
    }

    @Override
    public void animation() {
        walk = true;
        jump = false;
        super.animation();
    }

    @Override
    public void playerCollision(Player player) {
    }

    @Override
    public void effects() {
    }

    @Override
    public void floreCollision(Room room) {
    }

    @Override
    public void live(Room room, SpriteBatch batch, ArrayList bullets, Player player) {
        if(spriteNum < 21 && !dead){
            height = (float) spriteNum * 4.3f;
        }else if(spriteNum >= 21 && spriteNum <= 34){
            height = (float) (42 - spriteNum)* 4.3f;
        }else {
            height = 0;
        }

        if(player.x > x - sprite.getWidth()/2 && player.x < x + sprite.getWidth()/2) {
            if (player.y-10 > y - sprite.getHeight()/2 && player.y-10 < y - sprite.getHeight()/2 + height){
                if(!dead && !player.damaged && !player.invincible){
                    player.hp -= collisionDamage*(1-player.damageResist);
                    if(collisionDamage*(1-player.damageResist) > 0){
                        player.damaged = true;
                        player.damagedTime = 25;
                    }else {
                        player.damaged = true;
                        player.damagedTime = 2;
                    }
                    player.fallSpeed = 0;
                    player.xSpeed = 0;
                    Push push = new Push(100);
                    push.deltaX = deltaX*10;
                    push.deltaY = deltaY*10;
                    player.effects.add(push);
                    Push push2 = new Push(2);
                    push2.deltaX = deltaX*(-2);
                    push2.deltaY = deltaY*(-2);
                    effects.add(push2);
                }
            }
        }
        super.live(room, batch, bullets, player);
        if (dead){
            deadTime--;
        }
    }


    @Override
    public void findPlayer(Player player, Room room) {
        playerX = player.x;
        playerY = player.y;

        delX = x - playerX;
        delY = y - playerY;

        float ang = (float)Math.atan(delX/delY);
        if (y > playerY){
            ang += 3.1459;
        }

        deltaX = (float)Math.sin(ang);
        deltaY = (float)Math.cos(ang);
    }

    @Override
    public boolean spellCollision(float bx, float by) {
        return x - sprite.getWidth() / 2 < bx &&
                x + sprite.getWidth() / 2 > bx &&
                y - sprite.getHeight() / 2 < by &&
                y + sprite.getHeight() / 2  - (90 -height)> by;
    }

    @Override
    public void draw(SpriteBatch batch) {
        if (right) {
            sprite.setFlip(false, false);
        }else {
            sprite.setFlip(true, false);
        }

        sprite.setColor(0.8f, 0.6f, 1f, 1f);

        sprite.setOriginBasedPosition(x, y);
        sprite.draw(batch);
    }
}