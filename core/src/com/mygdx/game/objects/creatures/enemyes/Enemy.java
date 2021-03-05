package com.mygdx.game.objects.creatures.enemyes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.objects.attacks.Spell;
import com.mygdx.game.objects.attacks.effects.Push;
import com.mygdx.game.objects.attacks.range.FireBall;
import com.mygdx.game.objects.creatures.Creature;
import com.mygdx.game.objects.creatures.heroes.Player;
import com.mygdx.game.objects.furnitures.Furniture;
import com.mygdx.game.objects.room.Room;

import java.util.ArrayList;
import java.util.List;

public class Enemy extends Creature {
    public float playerX;
    public float playerY;
    public float lastPlayerX;
    public float lastPlayerY;
    public float delX = 1000;
    public float delY = 1000;
    public float deltaX;
    public float deltaY;
    public boolean playerIsFind;
    public boolean playerIsHidden;
    public float findRange;
    public Spell spell;
    public float playerFoundTime;
    public float deadTime = 1;

    public Enemy(float x, float y, Sprite sprite, float maxHp, float ms, float findRange) {
        super(x, y, sprite, maxHp, ms);
        this.findRange = findRange;
        this.spell = new FireBall();
        this.playerFoundTime = 200;
    }


    public void live(Room room, SpriteBatch batch, ArrayList bullets, Player player) {
        super.live(room, batch, bullets);
        if(!dead){
            if(!player.dead){
                findPlayer(player, room);
            }else {
                playerIsFind = false;
                playerIsHidden = false;
            }
            move(room, bullets, player);
            this.spell.reload--;
        }
    }

    public void findPlayer(Player player, Room room){
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

        if (x > playerX && y == playerY){
            deltaX = -1;
        }else if(x < playerX && y == playerY){
            deltaX = 1;
        }

        boolean wasFind = playerIsFind;
        if(Math.sqrt(delX*delX + delY*delY) < findRange){
            for (int i = 1; i < findRange; i++) {
                if (((x + deltaX * i) >= (playerX - 16)) && ((x + deltaX * i) <= (playerX + 16)) && ((y + deltaY * i) >= (playerY - 16)) && ((y + deltaY * i) <= (playerY + 16))){
                    playerIsFind = true;
                    break;
                }
                Furniture collision = room.findCollision(x + deltaX * i, y + deltaY * i);
                if(collision != null && !collision.transitional){
                    playerIsFind = false;
                    break;
                }
            }
        }else {
            playerIsFind = false;
        }

        if (playerIsFind){
            lastPlayerX = player.x;
            lastPlayerY = player.y;
            playerIsHidden = false;
        }else if(wasFind){
            playerIsHidden = true;
            playerFoundTime = 200;
        }
        if(playerIsHidden){
            playerFoundTime--;
            if(playerFoundTime < 0){
                playerIsHidden = false;
                playerFoundTime = 200;
            }
        }
    }

    public void move(Room room, ArrayList<Spell> bullets, Player player){
    }

    public void attack(ArrayList<Spell> bullets, Room room, Player player){
//        if (spell.reload <= 0){
//            float ang = (float)Math.atan(delX/delY);
//            if (y > playerY){
//                ang += 3.1459;
//            }
//            if (deltaX > 0){
//                right = true;
//            }else{
//                right = false;
//            }
//            spell.setParams(x + deltaX*18, y+deltaY*18, ang, deltaX, deltaY, true);
//            bullets.add(spell.makeCopy());
//            spell.reload = spell.reloadTime;
//            attack = true;
//        }

        if (spell.reload <= 0){
                spell.reload = spell.reloadTime;
                float ang = (float)Math.atan(delX/delY);
                if (y > playerY){
                    ang += 3.1459;
                }

//                if(spell.hold){
//                    //for telekinesis
//                    spell.rizX = x;
//                    spell.rizY = y;
//
//                    spell.setParams(room);
//                    Spell s1 = null;
//                    for (Spell s :bullets) {
//                        if(s.getClass().getSimpleName().equals(spell.getClass().getSimpleName())){
//                            s1 = spell;
//                        }
//                    }
//                    if(s1 == null){
//                        spell.destroyed = false;
//                        spell.collision(room);
//                        bullets.add(spell);
//                    }
//                }else {
                    attack = true;

                    if (spell.shoots > 1){
                        float riz = (float)((float)spell.range/(float)spell.shoots/180*3.14159);
                        ang -= riz*((float) spell.shoots/2 + 0.5);

                        if ((int)y == (int)playerY){
                            ang += 3.1459;
                        }

                        for (int i = 0; i < spell.shoots; i++) {
                            ang+=riz;

                            float  deltaX = (float)Math.sin(ang);
                            float  deltaY = (float)Math.cos(ang);
                            float  sX = x;
                            float  sY = y;


                            sX = sX + 18*deltaX;
                            sY = sY + 18*deltaY;

                            spell.setParams(sX, sY, ang, deltaX, deltaY, true);
                            if (spell.missless){
                                spell.setParams(player);
                            }
                            bullets.add(spell.makeCopy());
                        }
                    }else {
                        ang-=(double)spell.range/180*3.14159*(Math.random()*2 -1);

                        if ((int)y == (int)playerY){
                            ang += 3.1459;
                        }

                        float  deltaX = (float)Math.sin(ang);
                        float  deltaY = (float)Math.cos(ang);
                        float  sX = x;
                        float  sY = y;



                        sX = sX + 18*deltaX;
                        sY = sY + 18*deltaY;

                        spell.setParams(sX, sY, ang, deltaX, deltaY, true);
                        if (spell.missless){
                            spell.setParams(player);
                        }
                        Spell spell = this.spell.makeCopy();

//                        if (spell.laser){
//                            Furniture collision = null;
//                            for (int i = 1; i < 100; i++) {
//                                spell.sprites.get(0).sprite.setSize(3, 1200);
//                                collision = room.findCollision((float) (spell.x1 + i * spell.deltaX*10), (float) (spell.y1 + i * spell.deltaY * 10), true);
//                                if(collision != null) {
//                                    if(collision.breakable){
//                                        collision.destroy(spell, room.furniture);
//                                    }
//                                    if(!collision.fone){
//                                        spell.sprites.get(0).sprite.setSize(3, (float) Math.sqrt((spell.x1 - collision.x)*(spell.x1 - collision.x) + (spell.y1 - collision.y)*(spell.y1 - collision.y)) -16);
//                                        break;
//                                    }
//                                }
//                            }
//                        }else if(spell.getClass().getSimpleName().equals("Dash")){
//                            spell.setParams(this);
//                        }
                        bullets.add(spell);

                        if (deltaX > 0){
                            right = true;
                        }else{
                            right = false;
                        }
                    }
//                }
        }
    }

    public void playerCollision(Player player) {
        if(!dead){
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
            Push push = new Push(10);
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
