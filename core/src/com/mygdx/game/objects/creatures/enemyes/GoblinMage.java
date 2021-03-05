package com.mygdx.game.objects.creatures.enemyes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.objects.AnimationSprite;
import com.mygdx.game.objects.attacks.Spell;
import com.mygdx.game.objects.attacks.range.FallingSpell;
import com.mygdx.game.objects.attacks.range.FireBall;
import com.mygdx.game.objects.attacks.range.Missless;
import com.mygdx.game.objects.attacks.range.MisslessOrb;
import com.mygdx.game.objects.creatures.heroes.Player;
import com.mygdx.game.objects.furnitures.Furniture;
import com.mygdx.game.objects.room.Room;

import java.util.ArrayList;

public class GoblinMage  extends Enemy {

    public ArrayList<AnimationSprite> attackSprites2;
    public int attackSpriteNum;
    public float fadeTime = 0;

    public GoblinMage(float x, float y) {
        super(x, y, new Sprite(new Texture("GoblinMage/1.png")), 75, 2, 2000);

        this.jumpSprites.add(new AnimationSprite(12, new Texture("GoblinMage/1.png")));

        this.walkSprites.add(new AnimationSprite(12, new Texture("GoblinMage/1.png")));

        this.staySprites.add(new AnimationSprite(12, new Texture("GoblinMage/4.png")));
        this.staySprites.add(new AnimationSprite(12, new Texture("GoblinMage/3.png")));
        this.staySprites.add(new AnimationSprite(12, new Texture("GoblinMage/2.png")));
        this.staySprites.add(new AnimationSprite(12, new Texture("GoblinMage/1.png")));

        this.attackSprites.add(new AnimationSprite(12, new Texture("GoblinMage/1.png")));
        this.attackSprites.add(new AnimationSprite(12, new Texture("GoblinMage/2.png")));
        this.attackSprites.add(new AnimationSprite(12, new Texture("GoblinMage/3.png")));
        this.attackSprites.add(new AnimationSprite(12, new Texture("GoblinMage/4.png")));

        this.attackSprites2 = new ArrayList<>();
        this.attackSprites2.add(new AnimationSprite(8, new Texture("GoblinMage/mag/1.png")));
        this.attackSprites2.add(new AnimationSprite(8, new Texture("GoblinMage/mag/2.png")));
        this.attackSprites2.add(new AnimationSprite(8, new Texture("GoblinMage/mag/3.png")));
        this.attackSprites2.add(new AnimationSprite(8, new Texture("GoblinMage/mag/4.png")));

        for (AnimationSprite s:attackSprites2) {
            s.sprite.setColor(1, 1, 1 ,0.5f);
            s.sprite.setScale(2);
            s.sprite.setOriginCenter();
        }

        for (AnimationSprite s:fireSprites) {
            s.sprite.setOrigin(20, 16);
            s.sprite.setSize(s.sprite.getWidth()/1.5f, 80);
        }

        this.deadSprites.add(new AnimationSprite(20, new Texture("GoblinMage/d1.png")));

        this.spell = new MisslessOrb();
    }

    @Override
    public void live(Room room, SpriteBatch batch, ArrayList bullets, Player player) {

        if(attack && !dead){
            if (fadeTime < 30){
                fadeTime++;
            }
        }else {
            if (fadeTime > 0){
                fadeTime--;
            }
        }

        if(attackSprites2.get(attackSpriteNum).time > attackSprites2.get(attackSpriteNum).maxTime){
            attackSprites2.get(attackSpriteNum).time = 0;
            attackSpriteNum++;
            if(attackSpriteNum >= attackSprites2.size()){
                attackSpriteNum = 0;
            }
        }
        for (AnimationSprite s:attackSprites2) {
            s.sprite.rotate(1);
            s.sprite.setColor(1, 1, 1, fadeTime/60);
        }

        attackSprites2.get(attackSpriteNum).time++;
        attackSprites2.get(attackSpriteNum).sprite.setOriginBasedPosition(x, y);
        attackSprites2.get(attackSpriteNum).sprite.draw(batch);

        if(attack && spriteNum == 3 && playerIsFind){
            attackSprites.get(spriteNum).time = 0;
        } else if(!attack && spriteNum == 3){
            staySprites.get(spriteNum).time = 0;
        }

        super.live(room, batch, bullets, player);
    }

    @Override
    public void move(Room room, ArrayList<Spell> bullets, Player player) {
        if (playerIsFind && !jump) {
            if(x > playerX){
                right = false;
            }else {
                right = true;
            }
            attack(bullets, room, player);
        }else {
            if(spriteNum > 3)
            walk = false;
        }
    }

}