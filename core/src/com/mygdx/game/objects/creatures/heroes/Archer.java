package com.mygdx.game.objects.creatures.heroes;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.objects.AnimationSprite;
import com.mygdx.game.objects.Passives.DamageResist;
import com.mygdx.game.objects.attacks.Aura;
import com.mygdx.game.objects.attacks.Spell;
import com.mygdx.game.objects.attacks.melee.Bow;
import com.mygdx.game.objects.attacks.melee.Fisting;
import com.mygdx.game.objects.attacks.melee.Spear;
import com.mygdx.game.objects.attacks.melee.Sword;
import com.mygdx.game.objects.attacks.range.*;

public class Archer extends Player{

    public Archer(float sx, float sy) {
        super(sx, sy, 100, 4, 100);

        this.jumpSprites.add(new AnimationSprite(12, new Texture("heroes/archer/jump.png")));

        this.walkSprites.add(new AnimationSprite(5, new Texture("heroes/archer/walk1.png")));
        this.walkSprites.add(new AnimationSprite(5, new Texture("heroes/archer/1.png")));
        this.walkSprites.add(new AnimationSprite(5, new Texture("heroes/archer/walk2.png")));
        this.walkSprites.add(new AnimationSprite(5, new Texture("heroes/archer/1.png")));

        this.staySprites.add(new AnimationSprite(20, new Texture("heroes/archer/1.png")));

        this.attackSprites.add(new AnimationSprite(5, new Texture("heroes/archer/attack1.png")));
        this.attackSprites.add(new AnimationSprite(5, new Texture("heroes/archer/attack2.png")));

        this.deadSprites.add(new AnimationSprite(20, new Texture("heroes/archer/dead.png")));

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

        spells.add(new Bow());
        spells.add(new Bomb());
        spells.add(new Spear());
        spells.add(new Sword());

        spell[0] = spells.get(0);
        spell[1] = spells.get(1);
        spell[2] = spells.get(2);
        spell[3] = spells.get(3);


    }
}
