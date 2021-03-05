package com.mygdx.game.objects.attacks.range;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.objects.AnimationSprite;
import com.mygdx.game.objects.attacks.Spell;
import com.mygdx.game.objects.attacks.effects.FireEffect;
import com.mygdx.game.objects.attacks.effects.Push;
import com.mygdx.game.objects.room.Room;

import java.util.ArrayList;

public class FireBreath extends Spell {

    public FireBreath(){
        super(5, 40, 2.5f, 0.5f, new Sprite(new Texture("Enemy_bullet.png")), 1, 1, false, 5, 2, 2, (byte)2, (byte)1);
        this.icon = new Sprite(new Texture("icons/fireBreathi.jpg"));

        this.effects.add(new FireEffect(3));

        this.sprites.add(new AnimationSprite(3, new Texture("fireBreath/1.png")));
        this.sprites.add(new AnimationSprite(3, new Texture("fireBreath/2.png")));
        this.sprites.add(new AnimationSprite(3, new Texture("fireBreath/3.png")));
        this.sprites.add(new AnimationSprite(3, new Texture("fireBreath/4.png")));

        this.peirce = true;

        name = "Fire Breath";
        description = "Even more fire\n" +
                "+ speed\n" +
                "- range\n" +
                "- damage";
    }

    @Override
    public void live(SpriteBatch batch, Room room, ArrayList<Spell> addBullets) {

        ms -=0.125;
        size +=0.05;

        light.setScale(size*7);
        for (AnimationSprite s:sprites) {
            s.sprite.rotate(12*size);
            s.sprite.setScale(size);
        }

        super.live(batch, room, addBullets);
    }

    @Override
    public Spell fuse(Spell spell) {
        spell.reloadTime /= 4;
        spell.mp /= 4;
        if(spell.lifeTime >= 1 && spell.lifeTime <=2){
            spell.lifeTime = 1;
        }else {
            spell.lifeTime /= 4;
        }
        spell.singleSoot = false;

        spell.range += 5;

        spell.damage /= 4;
        if(spell.endSpell != null){
            spell.endSpell.reloadTime /= 4;
            spell.endSpell.mp /= 4;
            spell.endSpell.damage /= 4;

            spell.endSpell.singleSoot = false;
            spell.endSpell.range += 5;
        }
        if(spell.midSpell != null){
            spell.midSpell.reloadTime /= 4;
            spell.midSpell.mp /= 4;
            spell.midSpell.damage /= 4;
            if(spell.midSpell.lifeTime >= 1 && spell.midSpell.lifeTime <=2){
                spell.midSpell.lifeTime = 1;
            }else {
                spell.midSpell.lifeTime /= 4;
            }
            spell.midSpell.singleSoot = false;
            spell.midSpell.range += 5;
        }

        return spell;
    }
}
