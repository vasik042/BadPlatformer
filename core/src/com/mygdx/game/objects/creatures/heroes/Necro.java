package com.mygdx.game.objects.creatures.heroes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.objects.AnimationSprite;
import com.mygdx.game.objects.Passives.DamageResist;
import com.mygdx.game.objects.Passives.DoubleJump;
import com.mygdx.game.objects.attacks.Aura;
import com.mygdx.game.objects.attacks.Spell;
import com.mygdx.game.objects.attacks.melee.Bow;
import com.mygdx.game.objects.attacks.melee.Fisting;
import com.mygdx.game.objects.attacks.melee.Spear;
import com.mygdx.game.objects.attacks.melee.Sword;
import com.mygdx.game.objects.attacks.range.*;
import com.mygdx.game.objects.items.equipment.*;
import com.mygdx.game.objects.items.potions.HpPotion;

public class Necro extends Player{

    public Necro(float sx, float sy) {
        super(sx, sy, 100, 4, 100);

        this.jumpSprites.add(new AnimationSprite(12, new Texture("heroes/necro/jump.png")));

        this.walkSprites.add(new AnimationSprite(5, new Texture("heroes/necro/walk1.png")));
        this.walkSprites.add(new AnimationSprite(5, new Texture("heroes/necro/1.png")));

        this.staySprites.add(new AnimationSprite(20, new Texture("heroes/necro/1.png")));

        this.attackSprites.add(new AnimationSprite(5, new Texture("heroes/necro/attack1.png")));
        this.attackSprites.add(new AnimationSprite(5, new Texture("heroes/necro/attack2.png")));

        this.deadSprites.add(new AnimationSprite(20, new Texture("heroes/necro/dead.png")));

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
            s.sprite.setSize(32, 22);
            s.sprite.setOriginCenter();
        }


        spell[0] = new Bomb();
        spell[1] = new Triple();
        spell[2] = new FireBreath();
        spell[3] = new Aura(new DamageResist(1));
        spell[4] = new FallingSpell();
        spell[5] = new Laser();
        spell[6] =  new FireOrb();
        spell[7] = new Dash();

        for (Spell s: spell) {
            spells.add(s.makeCopy());
        }

        spells.add(new Spear());
        spells.add(new Sword());
        spells.add(new Bow());
        spells.add(new Missless());
        spells.add(new Fisting());
        spells.add(new Telekinesis());
        spells.add(new FireBall());

        items.add(new HpPotion());
        items.get(0).quantity = 4;
        items.add(new Hat());
        items.add(new Shoes());
        items.add(new Amulet());
        items.add(new Armor());
        items.add(new Gloves());
        items.add(new Ring1());
        items.add(new Ring2());
        items.add(new Pants());
    }
}
