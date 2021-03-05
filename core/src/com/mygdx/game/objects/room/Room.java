package com.mygdx.game.objects.room;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.objects.GameObject;
import com.mygdx.game.objects.attacks.Spell;
import com.mygdx.game.objects.creatures.Creature;
import com.mygdx.game.objects.creatures.enemyes.Enemy;
import com.mygdx.game.objects.creatures.heroes.Player;
import com.mygdx.game.objects.creatures.npcs.Bob;
import com.mygdx.game.objects.creatures.npcs.Npc;
import com.mygdx.game.objects.furnitures.Furniture;
import com.mygdx.game.objects.items.Bag;

import java.util.ArrayList;

public class Room extends GameObject{

    public ArrayList<Furniture> furniture;
    public ArrayList<Furniture> closeFone;
    public ArrayList<Furniture> deadFurniture;
    public ArrayList<Furniture> spikes;

    boolean spikesAll = false;

    public ArrayList<Enemy> enemies;
    public ArrayList<Enemy> addEnemies;
    public ArrayList<Enemy> deadEnemies;

    public ArrayList<Npc> npc;

    public ArrayList<Bag> bags;
    public ArrayList<Bag> removedBags;
    public float spawnX;
    public float spawnY;
    public float width = 1280;
    public float height = 736;


    public Room() {
        this.sprite = new Sprite(new Texture("dark.png"));
        this.sprite.setCenter(500, 500);
        this.sprite.setOriginCenter();

        this.spawnX = 32;
        this.spawnY = 200;

        this.x = 0;
        this.y = 0;
        this.furniture = new ArrayList<>();
        this.closeFone = new ArrayList<>();
        this.enemies = new ArrayList<>();
        this.spikes = new ArrayList<>();
        this.deadFurniture = new ArrayList<>();
        this.deadEnemies = new ArrayList<>();
        this.addEnemies = new ArrayList<>();
        this.npc = new ArrayList<>();
        this.bags = new ArrayList<>();
        this.removedBags = new ArrayList<>();
    }

    public Room create(){
        return new Room();
    }

    public void live(SpriteBatch batch, ArrayList<Spell> bullets, Player player){
        for (Furniture f: furniture) {
            f.live(player);
            if (f.fall) {
                f.fall(this);
            }
            if (f.dead){
                deadFurniture.add(f);
            }
            if(f.getClass().getSimpleName().equals("Spike") && !spikesAll){
                spikes.add(f);
            }
        }
        spikesAll = true;

        furniture.removeAll(deadFurniture);

        for (Furniture f:deadFurniture) {
            f.destroy(f.deadSpell, furniture);
        }
        deadFurniture.clear();


        for (Furniture f: spikes) {
            if(f.getSpriteNum() > 1 && f.getSpriteNum() < 4){
                Furniture collision1 = findCollision(f.x - 10, f.y);
                Furniture collision2 = findCollision(f.x + 10, f.y);

                if((collision1 != null && collision1.breakable) || (collision2 != null && collision2.breakable)){
                    Spell spell = new Spell();
                    spell.deltaY = 1;
                    spell.deltaX = 0;
                    spell.ms = 5;
                    spell.x1 = f.x;
                    spell.y1 = f.y;
                    spell.damage = 1000;
                    if(collision1!= null){
                        collision1.destroy(spell, furniture);
                    }else {
                        collision2.destroy(spell, furniture);
                    }
                }
            }
        }

        for (Enemy e: enemies) {
            e.live(this, batch, bullets, player);
            if (e.deadTime <= 0){
                deadEnemies.add(e);
            }
        }
        enemies.addAll(addEnemies);
        addEnemies.clear();
        for (Npc n: npc) {
            n.live(this, batch, bullets, player);
        }
        enemies.removeAll(deadEnemies);
        deadEnemies.clear();



        for (Bag b: bags) {
            b.live(batch,  player, this);
            if (b.empty){
                removedBags.add(b);
            }
        }
        bags.removeAll(removedBags);
        removedBags.clear();
    }

    public Furniture findCollision(float oX, float oY) {
        Furniture isFind = null;
        for (Furniture f: furniture) {
            if (!f.fone){
                if ((oX > f.x - f.spriteWidth / 2 && oX < f.x + f.spriteWidth / 2)
                        && (oY > f.y - f.spriteHeight / 2 && oY < f.y + f.spriteHeight / 2)) {
                    isFind = f;
                    break;
                }
            }
        }
        return isFind;
    }

    public Furniture findCollision(float oX, float oY, boolean fone) {
        Furniture isFind = null;
        for (Furniture f: furniture) {
            if ((oX > f.x - f.spriteWidth / 2 && oX < f.x + f.spriteWidth / 2)
                    && (oY > f.y - f.spriteHeight / 2 && oY < f.y + f.spriteHeight / 2)) {
                isFind = f;
                break;
            }
        }
        return isFind;
    }

    public ArrayList<Furniture> findCollision(float oX, float oY, float radius, boolean fone) {
        ArrayList<Furniture> furnitures =new ArrayList<>();
        for (Furniture f: furniture) {
            if (fone){
                if(Math.sqrt((oX - f.x)*(oX - f.x) + (oY - f.y)*(oY - f.y)) < radius){
                    furnitures.add(f);
                }
            }else if (!f.fone){
                if(Math.sqrt((oX - f.x)*(oX - f.x) + (oY - f.y)*(oY - f.y)) < radius){
                    furnitures.add(f);
                }
            }
        }
        return furnitures;
    }

    public Room nextRoom(Player player){
        return null;
    }

    public void draw(SpriteBatch batch, boolean fone) {
        super.draw(batch);
        if(fone){
            for (Furniture f: furniture) {
                if(f.fone || f.transitional){
                    f.draw(batch);
                }
            }
        }else {
            for (Furniture f: furniture) {
                if(!f.fone){
                    if(!f.transitional){
                        f.draw(batch);
                    }
                }
            }
            for (Furniture f: closeFone) {
                f.draw(batch);
            }
        }
    }
}
