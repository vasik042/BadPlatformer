package com.mygdx.game.objects.furnitures;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.objects.GameObject;
import com.mygdx.game.objects.attacks.Spell;
import com.mygdx.game.objects.creatures.heroes.Player;
import com.mygdx.game.objects.room.Room;

import java.util.ArrayList;

public class Furniture extends GameObject {
    public float spriteWidth;
    public float spriteHeight;
    public boolean transitional;
    public boolean activated;
    public boolean fone;
    public boolean breakable;
    public boolean dead;
    public boolean fall;
    public float fallSpeed;
    public float xSpeed;
    public Spell deadSpell;

    public Furniture(float x, float y, Sprite sprite) {
        super(x, y, sprite);

        this.sprite.setOriginCenter();
        this.transitional = false;
        this.fone = false;
        this.breakable = false;
        this.fall = false;
        this.dead = false;

        spriteWidth = sprite.getWidth();
        spriteHeight = sprite.getHeight();
    }
    public Furniture(float x, float y, Sprite sprite, boolean transitional, boolean fone, boolean breakable) {
        super(x, y, sprite);

        this.sprite.setOriginCenter();
        this.transitional = transitional;
        this.fone = fone;
        this.breakable = breakable;
        this.fall = false;
        this.dead = false;

        spriteWidth = sprite.getWidth();
        spriteHeight = sprite.getHeight();
    }

    public void destroy(Spell spell, ArrayList<Furniture> furnitures){
    }

    public void fall(Room room){
    }

    public void live(Player player) {
    }

    public int getSpriteNum(){
        return 0;
    }
}
