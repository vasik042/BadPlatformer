package com.mygdx.game.objects.attacks.melee;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.objects.AnimationSprite;
import com.mygdx.game.objects.attacks.Spell;
import com.mygdx.game.objects.attacks.effects.Push;
import com.mygdx.game.objects.furnitures.Furniture;
import com.mygdx.game.objects.room.Room;

public class Sword extends Melee{

    public Sword() {
        super(5, 10, 25, 1.5f, new Sprite(new Texture("sword/1.png")), 1, 6, false, 0, 15, 20, (byte)6, (byte)2);
        this.icon = new Sprite(new Texture("icons/swordi.jpg"));

        this.effects.add(new Push(2f));

        this.swap = true;
        this.isSwapped = true;

        this.sprites.add(new AnimationSprite(1, new Texture("sword/1.png")));
        this.sprites.add(new AnimationSprite(2, new Texture("sword/2.png")));
        this.sprites.add(new AnimationSprite(((int)lifeTime - 5), new Texture("sword/3.png")));
        this.sprites.add(new AnimationSprite(2, new Texture("sword/2.png")));

        name = "Sword";
        description = "Sharp\n" +
                "Add peirce";
    }

    @Override
    public Spell fuse(Spell spell) {
        spell.peirce = true;
        if(spell.endSpell != null){
            if(!spell.endSpell.peirce){
                spell.endSpell.peirce = true;
            }
        }if(spell.midSpell != null){
            if(!spell.midSpell.peirce){
                spell.midSpell.peirce = true;
            }
        }
        return spell;
    }
}
