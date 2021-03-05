package com.mygdx.game.objects.creatures;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.objects.AnimationSprite;
import com.mygdx.game.objects.GameObject;
import com.mygdx.game.objects.Passives.Passive;
import com.mygdx.game.objects.attacks.Spell;
import com.mygdx.game.objects.attacks.effects.Effect;
import com.mygdx.game.objects.attacks.effects.Push;
import com.mygdx.game.objects.furnitures.Furniture;
import com.mygdx.game.objects.items.Item;
import com.mygdx.game.objects.room.Room;

import java.util.ArrayList;
import java.util.List;

public class Creature extends GameObject {
    public float mp;
    public float maxMp;

    public float damageBoost = 1;
    public float atcSpeedBoost = 1;

    public float hp;
    public float maxHp;
    public float ms;
    public float maxMs;
    public boolean right;
    public boolean walk;
    public boolean jump;
    public boolean attack;
    public String prevState;

    public ArrayList<AnimationSprite> walkSprites;
    public ArrayList<AnimationSprite> jumpSprites;
    public ArrayList<AnimationSprite> staySprites;
    public ArrayList<AnimationSprite> attackSprites;
    public ArrayList<AnimationSprite> deadSprites;

    public int spriteNum = 0;
    public float fallSpeed = 1;
    public float xSpeed;

    public ArrayList<Effect> effects;
    public ArrayList<Effect> dEffects;

    public boolean damaged = false;
    public int damagedTime = 15;

    public boolean down;
    public int downTime = 0;

    public Sprite light;
    public ArrayList<AnimationSprite> fireSprites;
    public int fireSpriteNum = 0;
    public boolean onFire = false;

    public boolean fly  = false;
    public boolean dead  = false;

    public float pushResist = 0;
    public float damageResist = 0;

    public boolean invincible  = false;
    public int invincibleTime = 0;

    public float collisionDamage = 10;
    public boolean poison = false;

    public boolean isCharging = false;
    public boolean charged = false;

    public float xPos = 0;
    public float yPos = 0;

    public ArrayList<Passive> passives;
    public ArrayList<Passive> removedPassives;

    public ArrayList<Item> items;
    public ArrayList<Item> removedItems;

    public boolean roundCollision = false;

    public Creature(float x, float y, Sprite sprite, float maxHp, float ms) {
        super(x, y, sprite);
        this.hp = maxHp;
        this.maxHp = maxHp;
        this.ms = ms;

        this.walkSprites = new ArrayList<>();
        this.jumpSprites = new ArrayList<>();
        this.staySprites = new ArrayList<>();
        this.attackSprites = new ArrayList<>();
        this.deadSprites = new ArrayList<>();

        this.prevState = "walk";
        this.effects = new ArrayList<>();
        this.dEffects = new ArrayList<>();

        this.light = new Sprite(new Texture("light.png"));
        this.light.setSize(sprite.getWidth()*5, sprite.getHeight()*4);
        this.light.setColor(1, 0.8f, 0, 0.1f);
        this.light.setOriginCenter();

        this.fireSprites = new ArrayList<>();
        this.fireSprites.add(new AnimationSprite(10, new Texture("fire/fire1.png")));
        this.fireSprites.add(new AnimationSprite(10, new Texture("fire/fire2.png")));
        this.fireSprites.add(new AnimationSprite(10, new Texture("fire/fire3.png")));
        this.fireSprites.add(new AnimationSprite(10, new Texture("fire/fire4.png")));

        this.passives = new ArrayList<>();
        this.removedPassives = new ArrayList<>();

        this.items = new ArrayList<>();
        this.removedItems = new ArrayList<>();

        for (AnimationSprite a: fireSprites) {
            a.sprite.setOriginCenter();
            a.sprite.setColor(1, 0.9f, 0.8f, 0.4f);
            a.sprite.setSize(sprite.getWidth()*3, sprite.getHeight()*4);
        }
    }

    public void live(Room room, SpriteBatch batch, List<Spell> bullets){
        if (hp <= 0 && !dead) {
            setDead();
        }
        if(!dead){
            speedCollision(room);
            if(down){
                downTime++;
                if(downTime > 10){
                    downTime = 0;
                    down = false;
                }
            }
        }
        invincible();
        wallCollision(room);
        jump(room);
        floreCollision(room);
        animation();
        effects();
        draw(batch);
        onFire(batch);

        if(!dead){
            poison = false;
        }

        // passives
        for (Passive p:passives) {
            p.live(this);
        }
        for (Passive p:removedPassives) {
            p.remove(this);
        }
        passives.removeAll(removedPassives);
        removedPassives.clear();

        items.removeAll(removedItems);
        removedItems.clear();
    }

    public void setDead(){
        dead = true;
        if(fly){
            fly = false;
            fallSpeed = 0;
        }
        spriteNum = 0;
        sprite = deadSprites.get(0).sprite;
    }

    public void invincible(){
        if (invincible){
            invincibleTime--;
            if(invincibleTime <= 0){
                invincible = false;
            }
        }
    }

    public void onFire(SpriteBatch batch){
        if (onFire){
            lite(batch);
            if(fireSpriteNum < fireSprites.size()){
                if (fireSprites.get(fireSpriteNum).time < fireSprites.get(fireSpriteNum).maxTime){
                    fireSprites.get(fireSpriteNum).sprite.setOriginBasedPosition(x - 10, y - 43);
                    fireSprites.get(fireSpriteNum).sprite.draw(batch);
                    fireSprites.get(fireSpriteNum).time++;
                }else {
                    fireSprites.get(fireSpriteNum).time = 0;
                    fireSpriteNum++;
                }
            }else {
                fireSpriteNum = 0;
                fireSprites.get(fireSpriteNum).sprite.setOriginBasedPosition(x - 10, y - 43);
                fireSprites.get(fireSpriteNum).sprite.draw(batch);
                fireSprites.get(fireSpriteNum).time = 0;
                onFire = false;
            }
        }
    }

    public void lite(SpriteBatch batch){
        light.setOriginBasedPosition(x, y);
        light.draw(batch);
    }

    public void floreCollision(Room room){
        Furniture collisionLeft = room.findCollision(x-sprite.getWidth()/2, y - (sprite.getHeight() / 2 + fallSpeed));
        Furniture collisionCentre = room.findCollision(x, y - (sprite.getHeight() / 2 + fallSpeed));
        Furniture collisionRight = room.findCollision(x+sprite.getWidth()/2, y - (sprite.getHeight() / 2 + fallSpeed));

        if(down){
            if (collisionLeft != null && collisionLeft.transitional){
                collisionLeft = null;
            }
            if (collisionRight != null && collisionRight.transitional){
                collisionRight = null;
            }
            if (collisionCentre != null && collisionCentre.transitional){
                collisionCentre = null;
            }
        }

        if (collisionLeft != null && collisionLeft.transitional && ((y - sprite.getHeight() / 2) + 5 - (collisionLeft.y + collisionLeft.spriteHeight/2)) < 0){
            collisionLeft = null;
        }
        if (collisionRight != null && collisionRight.transitional && ((y - sprite.getHeight() / 2) + 5 - (collisionRight.y + collisionRight.spriteHeight/2)) <0){
            collisionRight = null;
        }
        if (collisionCentre != null && collisionCentre.transitional && ((y - sprite.getHeight() / 2) + 5 - (collisionCentre.y + collisionCentre.spriteHeight/2)) <0){
            collisionCentre = null;
        }

        setYPos(collisionLeft);
        setYPos(collisionRight);
        setYPos(collisionCentre);

        if(collisionLeft == null && collisionRight == null && collisionCentre == null){
            jump = true;
        }
    }

    public void setYPos (Furniture f) {
        if(f != null){
            float y2 = f.y + f.spriteHeight/2 + sprite.getHeight()/2-1;
            if(y2 > y){
                y = f.y + f.spriteHeight/2 + sprite.getHeight()/2-1;
            }
            fallSpeed = 1;
            jump = false;
        }
    }

    public boolean spellCollision(float bx, float by){
        if(roundCollision){
            return Math.sqrt((x - bx) * (x - bx) + (y - by) * (y - by)) < sprite.getWidth()/2 - 2;
        }else {
            return x - sprite.getWidth() / 2 < bx &&
                    x + sprite.getWidth() / 2 > bx &&
                    y - sprite.getHeight() / 2 < by &&
                    y + sprite.getHeight() / 2 > by;
        }
    }

    public void effects(){
        if(!effects.isEmpty()){
            for (Effect e:effects) {
                e.effect(this);
                if(e.time >= e.maxTime){
                    dEffects.add(e);
                }
            }
            effects.removeAll(dEffects);
            dEffects.removeAll(dEffects);
        }
    }

    public void wallCollision(Room room){
        x += xSpeed;

        if(jump){
            xSpeed *= 0.98;
        }else if(fly || walk){
            xSpeed *= 0.8;
        }else{
            xSpeed *= 0.5;
        }


        if(xSpeed < 0.2 && xSpeed > -0.2){
            xSpeed = 0;
        }
        if(xSpeed > ms){
            xSpeed = ms;
        }
        if(xSpeed < -ms){
            xSpeed = -ms;
        }

        Furniture collision1 = room.findCollision(x - (sprite.getWidth() / 2) + xSpeed, y - sprite.getHeight() / 2 + 5);
        Furniture collision2 = room.findCollision(x - (sprite.getWidth() / 2) + xSpeed, y + sprite.getHeight() / 2 - 5);

        if (collision1 != null && collision1.transitional){
            collision1 = null;
        }
        if (collision2 != null && collision2.transitional){
            collision2 = null;
        }

        if(collision1 != null || collision2 != null){
            if (collision1 != null){
                x = collision1.x + collision1.spriteWidth/2 + sprite.getWidth()/2;
            }else{
                x = collision2.x + collision2.spriteWidth/2 + sprite.getWidth()/2;
            }
            walk = false;
        }
        Furniture collision3 = room.findCollision(x + (sprite.getWidth() / 2) + xSpeed, y - sprite.getHeight() / 2 + 5);
        Furniture collision4 = room.findCollision(x + (sprite.getWidth() / 2) + xSpeed, y + sprite.getHeight() / 2 - 5);

        if (collision3 != null && collision3.transitional){
            collision3 = null;
        }
        if (collision4 != null && collision4.transitional){
            collision4 = null;
        }

        if(collision3 != null || collision4 != null){
            if (collision3 != null){
                x = collision3.x - collision3.spriteWidth/2 - sprite.getWidth()/2;
                xSpeed*=0.8;
            }else{
                x = collision4.x - collision4.spriteWidth/2 - sprite.getWidth()/2;
                xSpeed*=0.8;
            }
            walk = false;
        }
    }

    public void speedCollision(Room room){
        ArrayList<Furniture> furnitures = room.findCollision(x, y, 10, true);
        for (Furniture collision:furnitures) {
            if(collision != null && collision.breakable){
                if (collision.fallSpeed > 6 || collision.xSpeed > 6 || collision.fallSpeed < -6 || collision.xSpeed < -6){
                    hp -= 10;
                    damaged = true;

                    Push p = null;
                    p = new Push(3f);

                    float ang = -(float)Math.atan((collision.x - x) /(collision.y - y));

                    if (y > collision.y){
                        ang += 3.1459;
                    }

                    p.deltaX = (float)Math.sin(ang);
                    p.deltaY = (float)Math.cos(ang);
                    effects.add(p);

                    Spell s = new Spell();
                    s.damage = 100;
                    s.ms = 6;
                    s.deltaX = (float)Math.sin(ang);
                    s.deltaY = (float)Math.cos(ang);

                    collision.destroy(s, room.furniture);
                }
            }
        }
    }

    public void jump(Room room){
        if(jump || fly){

            y -= fallSpeed;

            if(!fly){
                fallSpeed += 0.7;
            }else {
                fallSpeed *= 0.8;

                if(fallSpeed < 0.2 && fallSpeed > -0.2){
                    fallSpeed = 0;
                }
                if(fallSpeed > ms){
                    fallSpeed = ms;
                }
                if(fallSpeed < -ms){
                    fallSpeed = -ms;
                }
            }

            if(fallSpeed > 10){
                fallSpeed = 10;
            }

            Furniture upCollisionLeft = room.findCollision(x-sprite.getWidth()/2,y + (sprite.getHeight() / 2 - fallSpeed));
            Furniture upCollisionRight = room.findCollision(x+sprite.getWidth()/2, y + (sprite.getHeight() / 2 - fallSpeed));


            if (upCollisionLeft != null && upCollisionLeft.transitional){
                upCollisionLeft = null;
            }
            if (upCollisionRight != null && upCollisionRight.transitional){
                upCollisionRight = null;
            }


            if(upCollisionLeft != null || upCollisionRight != null){
                if (upCollisionLeft != null){
                    y = upCollisionLeft.y - upCollisionLeft.sprite.getHeight()/2 - sprite.getHeight()/2-1;
                }else {
                    y = upCollisionRight.y - upCollisionRight.sprite.getHeight()/2 - sprite.getHeight()/2-1;
                }
                if (!fly){
                    fallSpeed +=0.8;
                    if(fallSpeed>0){
                        fallSpeed = 0;
                    }
                }else {
                    if (upCollisionLeft != null){
                        y = upCollisionLeft.y - upCollisionLeft.sprite.getHeight()/2 - sprite.getHeight()/2-1 - ms;
                    }else {
                        y = upCollisionRight.y - upCollisionRight.sprite.getHeight()/2 - sprite.getHeight()/2-1 - ms;
                    }
                }
            }
        }
    }

    @Override
    public void draw(SpriteBatch batch) {
        if (right) {
            sprite.setFlip(false, false);
        }else {
            sprite.setFlip(true, false);
        }

        if(poison){
            sprite.setColor(1f, 0.5f, 1f, 1);
        }

        if(!dead){
            damaged();
        }

        if(invincible){
            sprite.setColor(0.8f, 0.8f, 1f, 0.8f);
        }

        sprite.setOriginBasedPosition(x, y);
        sprite.draw(batch);
        sprite.setColor(1, 1, 1, 1);
    }

    public void damaged(){
        if(damaged) {
            if (damagedTime == 0) {
                damagedTime = 15;
                damaged = false;
            } else if (damagedTime % 2 == 1) {
                sprite.setColor(0.8f, 0, 0, 1);
            } else {
                sprite.setColor(0.5f, 0, 0, 0.8f);
            }
            damagedTime--;
        }
    }

    public void animation() {
        if(dead){
            if (deadSprites.get(spriteNum).time <= deadSprites.get(spriteNum).maxTime){

                sprite = deadSprites.get(spriteNum).sprite;
                deadSprites.get(spriteNum).time++;
            }else if (spriteNum < deadSprites.size()-1){
                spriteNum++;
            }
        }else if(attack){
            if (!prevState.equals("attack")){
                spriteNum = 0;
                prevState = "attack";
            }
            if(spriteNum < attackSprites.size()){
                if (attackSprites.get(spriteNum).time <= attackSprites.get(spriteNum).maxTime){
                    sprite = attackSprites.get(spriteNum).sprite;
                    attackSprites.get(spriteNum).time++;
                }else {
                    attackSprites.get(spriteNum).time = 0;
                    spriteNum++;
                }
            }else {
                spriteNum = 0;
                sprite = attackSprites.get(spriteNum).sprite;
                attackSprites.get(spriteNum).time = 0;
                attack = false;
            }
        }else if(jump) {
            if (!prevState.equals("jump")) {
                spriteNum = 0;
                prevState = "jump";
            }
            if (spriteNum < jumpSprites.size()) {
                if (jumpSprites.get(spriteNum).time <= jumpSprites.get(spriteNum).maxTime) {
                    sprite = jumpSprites.get(spriteNum).sprite;
                    jumpSprites.get(spriteNum).time++;
                } else {
                    jumpSprites.get(spriteNum).time = 0;
                    spriteNum++;
                }
            } else {
                spriteNum = 0;
                sprite = jumpSprites.get(spriteNum).sprite;
                jumpSprites.get(spriteNum).time++;
            }
        }else if (walk){
            if (!prevState.equals("walk")){
                spriteNum = 0;
                prevState = "walk";
            }
            if(spriteNum < walkSprites.size()){
                if (walkSprites.get(spriteNum).time <= walkSprites.get(spriteNum).maxTime){
                    sprite = walkSprites.get(spriteNum).sprite;
                    walkSprites.get(spriteNum).time++;
                }else {
                    walkSprites.get(spriteNum).time = 0;
                    spriteNum++;
                }
            }else {
                spriteNum = 0;
                sprite = walkSprites.get(spriteNum).sprite;
                walkSprites.get(spriteNum).time++;
            }
        }else{
            if (!prevState.equals("stay")){
                spriteNum = 0;
                prevState = "stay";
            }
            if(spriteNum < staySprites.size()){
                if (staySprites.get(spriteNum).time <= staySprites.get(spriteNum).maxTime){
                    sprite = staySprites.get(spriteNum).sprite;
                    staySprites.get(spriteNum).time++;
                }else {
                    staySprites.get(spriteNum).time = 0;
                    spriteNum++;
                }
            }else {
                spriteNum = 0;
                sprite = staySprites.get(spriteNum).sprite;
                staySprites.get(spriteNum).time++;
            }
        }
    }
}
