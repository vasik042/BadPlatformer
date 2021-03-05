package com.mygdx.game.objects.furnitures.traps;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.objects.AnimationSprite;
import com.mygdx.game.objects.attacks.Spell;
import com.mygdx.game.objects.creatures.heroes.Player;
import com.mygdx.game.objects.furnitures.Furniture;
import com.mygdx.game.objects.room.Room;

import java.util.ArrayList;

public class Spike extends Furniture {
    public ArrayList<AnimationSprite> sprites;
    public int spriteNum;
    public int damage;
    public Spell spell;

    public Spike(float x, float y) {
        super(x, y, new Sprite(new Texture("spike/1.png")), false, true, false);
        this.sprites = new ArrayList<>();
        this.sprites.add(new AnimationSprite(80, new Texture("spike/1.png")));
        this.sprites.add(new AnimationSprite(50, new Texture("spike/2.png")));
        this.sprites.add(new AnimationSprite(1, new Texture("spike/3.png")));
        this.sprites.add(new AnimationSprite(4, new Texture("spike/4.png")));
        this.sprites.add(new AnimationSprite(2, new Texture("spike/3.png")));
        this.sprites.add(new AnimationSprite(1, new Texture("spike/2.png")));

        this.damage = 100;

        for (AnimationSprite s: sprites) {
            s.sprite.setOriginBasedPosition(x, y);
        }

    }

    @Override
    public void live(Player player) {
        if(spriteNum < sprites.size()){
            if (sprites.get(spriteNum).time < sprites.get(spriteNum).maxTime){
                sprite = sprites.get(spriteNum).sprite;
                sprites.get(spriteNum).time++;
            }else {
                sprites.get(spriteNum).time = 0;
                spriteNum++;
            }
        }else {
            spriteNum = 0;
            sprite = sprites.get(spriteNum).sprite;
            sprites.get(spriteNum).time = 0;
        }
        damage(player);
    }

    public void damage(Player player) {
        if(spriteNum > 1 && spriteNum < 4){
            if(!player.damaged && player.x > x-32 && player.x < x+32 && player.y > y-32 && player.y < y+32){
                player.hp -= damage*(1-player.damageResist);
                if(damage*(1-player.damageResist) > 0){
                    player.damaged = true;
                    player.damagedTime = 25;
                }
                player.fallSpeed = -1.5f;
            }
        }
    }

    @Override
    public int getSpriteNum(){
        return spriteNum;
    }
}
