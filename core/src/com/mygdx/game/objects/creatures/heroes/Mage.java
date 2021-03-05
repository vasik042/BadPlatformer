package com.mygdx.game.objects.creatures.heroes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.objects.AnimationSprite;
import com.mygdx.game.objects.Passives.*;
import com.mygdx.game.objects.attacks.Aura;
import com.mygdx.game.objects.attacks.Spell;
import com.mygdx.game.objects.attacks.melee.*;
import com.mygdx.game.objects.attacks.range.*;

public class Mage  extends Player{

    public Mage(float sx, float sy) {
        super(sx, sy, 100, 4, 100);

        this.jumpSprites.add(new AnimationSprite(12, new Texture("heroes/mage/jump.png")));
        this.walkSprites.add(new AnimationSprite(20, new Texture("heroes/mage/walk1.png")));
        this.walkSprites.add(new AnimationSprite(20, new Texture("heroes/mage/walk2.png")));
        this.walkSprites.add(new AnimationSprite(20, new Texture("heroes/mage/walk3.png")));
        this.staySprites.add(new AnimationSprite(20, new Texture("heroes/mage/stay1.png")));
        this.staySprites.add(new AnimationSprite(20, new Texture("heroes/mage/stay2.png")));
        this.attackSprites.add(new AnimationSprite(5, new Texture("heroes/mage/attack1.png")));
        this.attackSprites.add(new AnimationSprite(5, new Texture("heroes/mage/attack2.png")));

        this.deadSprites.add(new AnimationSprite(20, new Texture("heroes/mage/dead1.png")));


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


        spell[0] = new FireBall();
        spell[0].damage = 100000;
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

    }
}
