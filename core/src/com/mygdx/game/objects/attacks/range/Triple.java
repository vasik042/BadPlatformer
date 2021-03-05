package com.mygdx.game.objects.attacks.range;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.objects.AnimationSprite;
import com.mygdx.game.objects.attacks.Spell;
import com.mygdx.game.objects.attacks.effects.Push;
import com.mygdx.game.objects.room.Room;

import java.util.ArrayList;

public class Triple extends Spell {

    public Triple(){
        super(4, 180, 5, 2f, new Sprite(new Texture("triple/1.png")), 3, 1, false, 30, 20, 25, (byte)1, (byte)1);
        this.icon = new Sprite(new Texture("icons/triplei.jpg"));

        this.effects.add(new Push(2f));

        this.sprites.add(new AnimationSprite(3, new Texture("triple/1.png")));

        name = "Triple";
        description = "Three is like two, but more\n" +
                "+ 2 projectiles";
    }

    @Override
    public void live(SpriteBatch batch, Room room, ArrayList<Spell> addBullets) {
        super.live(batch, room, addBullets);
        sprites.get(0).sprite.rotate((float) (Math.random()*90));
    }

    @Override
    public Spell fuse(Spell spell) {
        spell.damage *= spell.shoots;
        spell.shoots += 2;
        spell.range += 15;
        spell.damage /= spell.shoots;
        if(spell.endSpell != null){
            spell.endSpell.damage /= spell.shoots;
        }
        if(spell.midSpell != null){
            spell.midSpell.damage /= spell.shoots;
        }
        return spell;
    }
}