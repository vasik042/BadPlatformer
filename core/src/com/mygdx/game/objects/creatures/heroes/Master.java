package com.mygdx.game.objects.creatures.heroes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.objects.AnimationSprite;
import com.mygdx.game.objects.attacks.Spell;
import com.mygdx.game.objects.attacks.melee.Bow;
import com.mygdx.game.objects.attacks.melee.Spear;
import com.mygdx.game.objects.attacks.melee.Sword;
import com.mygdx.game.objects.attacks.range.*;

public class Master  extends Player{

    public Master(float sx, float sy) {
        super(sx, sy, 100, 4, 100);

        this.jumpSprites.add(new AnimationSprite(12, new Texture("heroes/mage2/jump.png")));

        this.walkSprites.add(new AnimationSprite(10, new Texture("heroes/mage2/walk1.png")));
        this.walkSprites.add(new AnimationSprite(10, new Texture("heroes/mage2/walk2.png")));
        this.walkSprites.add(new AnimationSprite(10, new Texture("heroes/mage2/walk3.png")));
        this.walkSprites.add(new AnimationSprite(10, new Texture("heroes/mage2/walk4.png")));
        this.walkSprites.add(new AnimationSprite(10, new Texture("heroes/mage2/walk5.png")));
        this.walkSprites.add(new AnimationSprite(10, new Texture("heroes/mage2/walk6.png")));

        this.staySprites.add(new AnimationSprite(20, new Texture("heroes/mage2/stay1.png")));
        this.staySprites.add(new AnimationSprite(20, new Texture("heroes/mage2/stay2.png")));

        this.attackSprites.add(new AnimationSprite(5, new Texture("heroes/mage2/attack1.png")));
        this.attackSprites.add(new AnimationSprite(5, new Texture("heroes/mage2/attack2.png")));
        this.attackSprites.add(new AnimationSprite(5, new Texture("heroes/mage2/attack3.png")));

        this.deadSprites.add(new AnimationSprite(20, new Texture("heroes/mage2/dead.png")));


        for (AnimationSprite s: jumpSprites) {
            s.sprite.setSize(25, 32);
            s.sprite.setOriginCenter();
        }
        for (AnimationSprite s: walkSprites) {
            s.sprite.setSize(25, 32);
            s.sprite.setOriginCenter();
        }
        for (AnimationSprite s: staySprites) {
            s.sprite.setSize(25, 32);
            s.sprite.setOriginCenter();
        }
        for (AnimationSprite s: attackSprites) {
            s.sprite.setSize(25, 32);
            s.sprite.setOriginCenter();
        }
        for (AnimationSprite s: deadSprites) {
            s.sprite.setSize(32, 18);
            s.sprite.setOriginCenter();
        }


        spell[0] = new Sword();
        spell[1] = new Spear();
        spell[2] = new Bow();
        spell[3] = new Telekinesis();
        spell[3].icon = new Sprite(new Texture("barrel/barrel.png"));
        spell[4] = new FallingSpell();
        spell[5] = new Laser();
        spell[6] =  new FireOrb();
        spell[7] = new Dash();

        for (Spell s: spell) {
            spells.add(s.makeCopy());
        }
    }
}
