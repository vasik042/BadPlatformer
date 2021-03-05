package com.mygdx.game.objects.attacks.range;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.objects.AnimationSprite;
import com.mygdx.game.objects.attacks.Spell;
import com.mygdx.game.objects.attacks.effects.Push;
import com.mygdx.game.objects.creatures.Creature;
import com.mygdx.game.objects.room.Room;

import java.util.ArrayList;

public class Laser extends Spell {
    Sprite end;

    public Laser(){
        super(5, 1, 0.8f, 1f, new Sprite(new Texture("laser/laser.png")), 1, 0, false, 0, 0.5f, 0, (byte)0, (byte)0);
        this.icon = new Sprite(new Texture("icons/laseri.jpg"));

        this.effects.add(new Push(0.1f));

        this.sprites.add(new AnimationSprite(10000, new Texture("laser/laser.png")));
        this.sprites.get(0).sprite.setOrigin(1, 0);
        this.end = new Sprite(new Texture("laser/end.png"));
        end.setScale(2f);
        end.setOriginCenter();
        end.setColor(r, g, b, 1);
        end.setRotation((float)Math.random()*360);

        this.laser = true;

        name = "Laser";
        description = "Shorter than my dick\n" +
                "+ speed\n" +
                "- reload time\n" +
                "- damage";
    }

    @Override
    public void live(SpriteBatch batch, Room room, ArrayList<Spell> addBullets) {
        super.live(batch, room, addBullets);

        end.setOriginBasedPosition(x1 + sprites.get(0).sprite.getHeight()*deltaX, y1 + sprites.get(0).sprite.getHeight()*deltaY);
        end.draw(batch);
    }

    @Override
    public Spell fuse(Spell spell) {
        spell.reloadTime /= 2;
        spell.singleSoot = false;
        spell.mp /= 2;
        spell.ms *= 2;
        spell.damage /= 3;
        if(spell.endSpell != null){
            spell.endSpell.damage /= 3;
            spell.endSpell.reloadTime /= 2;
            spell.endSpell.singleSoot = false;
            spell.endSpell.mp /= 2;
            spell.endSpell.ms *= 2;
        }
        if(spell.midSpell != null){
            spell.midSpell.damage /= 3;
            spell.midSpell.reloadTime /= 2;
            spell.midSpell.singleSoot = false;
            spell.midSpell.mp /= 2;
            spell.midSpell.ms *= 2;
        }
        return spell;
    }

    @Override
    public void end(ArrayList<Spell> bullets) {
        if(Math.random() < 0.05f && endSpell != null){
            endSpell.damage*= 20;
            super.end(bullets);
        }
    }
}
