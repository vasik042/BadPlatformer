package com.mygdx.game.objects.attacks.range;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.objects.AnimationSprite;
import com.mygdx.game.objects.attacks.Spell;

import com.mygdx.game.objects.attacks.effects.Effect;
import com.mygdx.game.objects.attacks.effects.Push;
import com.mygdx.game.objects.creatures.Creature;
import com.mygdx.game.objects.furnitures.Furniture;
import com.mygdx.game.objects.room.Room;

import java.util.ArrayList;

public class FallingSpell extends Spell {


    public FallingSpell(){
        super(5, 200, 25, 4, new Sprite(new Texture("Enemy_bullet.png")), 1, 1, true, 0, 40, 40, (byte)3, (byte)0);
        this.icon = new Sprite(new Texture("icons/falli.jpg"));

        this.effects.add(new Push(2f));
        this.jump = 7;
        this.areJump = 0;
        this.fall = true;
        this.fallSpeed = 5;

        this.sprites.add(new AnimationSprite(3, new Texture("Enemy_bullet.png")));

        for (AnimationSprite s: this.sprites) {
            s.sprite.setScale(size);
            s.sprite.setOriginCenter();
        }

        name = "Ball";
        description = "Falls like shit\n" +
                "Add gravity";
    }

    @Override
    public void live(SpriteBatch batch, Room room, ArrayList<Spell> addBullets) {
        super.live(batch, room, addBullets);
        sprite.rotate(-rizX*5);
    }

    @Override
    public Spell fuse(Spell spell) {
        if(!spell.getClass().getSimpleName().equals("Laser")){
            if(!spell.melee){
                if(spell.collisionType == 1){
                    spell.collisionType = 3;
                    spell.collisionEffect = 0;
                    spell.jump = 5;
                    spell.damage /= 2;
                }
                spell.fall = true;
                spell.fallSpeed = 5;
            }

            if (spell.midSpell != null){
                if(spell.midSpell.collisionType == 1 || spell.midSpell.collisionType == 2){
                    spell.midSpell.collisionType = 3;
                    spell.midSpell.collisionEffect = 0;
                    spell.midSpell.jump = 5;
                    spell.midSpell.damage /= 2;
                }
                spell.midSpell.fall = true;
                spell.midSpell.fallSpeed = 5;
            }
            if (spell.endSpell != null){
                if(spell.endSpell.collisionType == 1){
                    spell.endSpell.collisionType = 3;
                    spell.endSpell.collisionEffect = 0;
                    spell.endSpell.jump = 5;
                    spell.endSpell.damage /= 2;
                }
                spell.endSpell.fall = true;
                spell.endSpell.fallSpeed = 5;
            }
        }

        return spell;
    }
}
