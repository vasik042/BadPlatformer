package com.mygdx.game.objects.creatures.enemyes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.objects.AnimationSprite;
import com.mygdx.game.objects.attacks.Spell;
import com.mygdx.game.objects.attacks.effects.Poison;
import com.mygdx.game.objects.creatures.heroes.Player;
import com.mygdx.game.objects.furnitures.Furniture;
import com.mygdx.game.objects.room.Room;

import java.util.ArrayList;

public class Maggot extends Enemy{
    public Maggot(float x, float y) {
        super(x, y, new Sprite(new Texture("Enemy.png")), 15, 1.5f, 0);
        walk = true;
        walkSprites.add(new AnimationSprite(10, new Texture("maggot/1.png")));
        walkSprites.add(new AnimationSprite(10, new Texture("maggot/2.png")));
        walkSprites.add(new AnimationSprite(10, new Texture("maggot/3.png")));
        walkSprites.add(new AnimationSprite(10, new Texture("maggot/2.png")));

        jumpSprites.add(new AnimationSprite(10, new Texture("maggot/1.png")));
        staySprites.add(new AnimationSprite(10, new Texture("maggot/1.png")));

        deadSprites.add(new AnimationSprite(15, new Texture("maggot/1.png")));
        deadSprites.add(new AnimationSprite(15, new Texture("maggot/d1.png")));
        deadSprites.add(new AnimationSprite(15, new Texture("maggot/d2.png")));
        deadSprites.add(new AnimationSprite(15, new Texture("maggot/d3.png")));
        deadSprites.add(new AnimationSprite(15, new Texture("maggot/d4.png")));

        collisionDamage = 20;
        deadTime = 75;

        roundCollision = true;

        light.setScale(0.5f);
        for (AnimationSprite s:fireSprites) {
            s.sprite.setSize(60, 20);
            s.sprite.setOrigin(20, -28);
        }
    }
    public Maggot(float x, float y, boolean right) {
        super(x, y, new Sprite(new Texture("Enemy.png")), 15, 1.5f, 0);
        walk = true;
        walkSprites.add(new AnimationSprite(10, new Texture("maggot/1.png")));
        walkSprites.add(new AnimationSprite(10, new Texture("maggot/2.png")));
        walkSprites.add(new AnimationSprite(10, new Texture("maggot/3.png")));
        walkSprites.add(new AnimationSprite(10, new Texture("maggot/2.png")));

        jumpSprites.add(new AnimationSprite(10, new Texture("maggot/1.png")));
        staySprites.add(new AnimationSprite(10, new Texture("maggot/1.png")));

        deadSprites.add(new AnimationSprite(15, new Texture("maggot/1.png")));
        deadSprites.add(new AnimationSprite(15, new Texture("maggot/d1.png")));
        deadSprites.add(new AnimationSprite(15, new Texture("maggot/d2.png")));
        deadSprites.add(new AnimationSprite(15, new Texture("maggot/d3.png")));
        deadSprites.add(new AnimationSprite(15, new Texture("maggot/d4.png")));

        collisionDamage = 20;
        deadTime = 75;

        light.setScale(0.5f);
        for (AnimationSprite s:fireSprites) {
            s.sprite.setSize(60, 20);
            s.sprite.setOrigin(20, -28);
        }
        this.right = right;
    }


    @Override
    public void live(Room room, SpriteBatch batch, ArrayList bullets, Player player) {
        super.live(room, batch, bullets, player);
        if (dead){
            deadTime--;
        }
    }

    @Override
    public void move(Room room, ArrayList<Spell> bullets, Player player) {
        findCollision(room);

        walk = true;
        if (right){
            x += ms;
        }
        if (!right){
            x -= ms;
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

    @Override
    public void playerCollision(Player player) {
        super.playerCollision(player);
        Poison poison = new Poison(1);
        poison.addEffect(player, new Spell());
    }
}
