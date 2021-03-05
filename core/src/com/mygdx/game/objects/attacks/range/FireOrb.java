package com.mygdx.game.objects.attacks.range;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.IdentityMap;
import com.mygdx.game.objects.AnimationSprite;
import com.mygdx.game.objects.attacks.Spell;
import com.mygdx.game.objects.attacks.effects.FireEffect;
import com.mygdx.game.objects.attacks.effects.Push;
import com.mygdx.game.objects.room.Room;

import java.util.ArrayList;

public class FireOrb extends Spell {

    public FireOrb(){
        super(3.5f, 200, 15, 1f, new Sprite(new Texture("fireOrb/1.png")), 1, 1, true, 0, 60, 100, (byte)1, (byte)1);
        this.icon = new Sprite(new Texture("icons/fireOrbi.jpg"));

        this.effects.add(new FireEffect(5));
        this.effects.add(new Push(4f));

        this.sprites.add(new AnimationSprite(3, new Texture("fireOrb/1.png")));
        this.sprites.add(new AnimationSprite(3, new Texture("fireOrb/1.png")));

        Explode explode = new Explode();
        explode.effects.add(new Push(10));
        endSpell = explode;

        name = "Fire Orb";
        description = "EXUPULOTION!\n" +
                "Add EXUPULOTION!";
    }

    @Override
    public void live(SpriteBatch batch, Room room, ArrayList<Spell> addBullets) {
        super.live(batch, room, addBullets);

        light.setScale(1.3f*size);

        for (AnimationSprite s:sprites) {
            s.sprite.rotate(12*size);
            s.sprite.setScale(size);
        }

        size += 0.01;
        endSpell.size += 0.01;
        damage+=damage/100;
        endSpell.damage = damage;
    }


    @Override
    public Spell fuse(Spell spell) {
        if(spell.melee){
            if(spell.midSpell == null){
                spell.midSpell = this.makeCopy();
                spell.reloadTime += 100;
                spell.mp += 80;
            }else {
                spell.midSpell = fuse(spell.midSpell);
            }
        }else if (!spell.getClass().getSimpleName().equals("FireBreath")){
            Explode explode = new Explode();
            explode.damage = spell.damage/2;
            explode.enemy = this.enemy;
            if(spell.laser){
                explode.size = 1f;
            }else {
                explode.size = (spell.sprite.getHeight() + spell.sprite.getWidth())/48*spell.size;
            }
            spell.endSpell = explode;
            spell.reloadTime*=0.75;
            spell.mp*=1.2;
            if(spell.isStopped){
                spell.isStopped = false;
                spell.collisionEffect = 1;
            }
        }else {
            spell.ms /= 1.5;
            spell.lifeTime /= 1.5;
            spell.damage *= 1.2;
        }

        return spell;
    }
}

