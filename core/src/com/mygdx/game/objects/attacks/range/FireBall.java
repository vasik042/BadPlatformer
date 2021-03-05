package com.mygdx.game.objects.attacks.range;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.objects.AnimationSprite;
import com.mygdx.game.objects.attacks.Spell;
import com.mygdx.game.objects.attacks.effects.FireEffect;
import com.mygdx.game.objects.attacks.effects.Push;
import com.mygdx.game.objects.room.Room;

public class FireBall extends Spell {

    public FireBall(){
        super(7, 200, 7, 1f, new Sprite(new Texture("Enemy_bullet.png")), 1, 1, false, 0, 15, 15, (byte)1, (byte)1);
        this.icon = new Sprite(new Texture("icons/fireballi.jpg"));

        this.effects.add(new FireEffect(4));
        this.effects.add(new Push(2f));

        this.sprites.add(new AnimationSprite(3, new Texture("fireball/fireball1.png")));
        this.sprites.add(new AnimationSprite(3, new Texture("fireball/fireball2.png"), true));
        this.sprites.add(new AnimationSprite(3, new Texture("fireball/fireball1.png")));
        this.sprites.add(new AnimationSprite(3, new Texture("fireball/fireball2.png")));

        name = "Fireball";
        description = "Kill them with fire!\n" +
                "Add fire";
    }

    @Override
    public Spell fuse(Spell spell) {
        spell.effects.add(new FireEffect(spell.reloadTime/20 + 1));
        for (AnimationSprite s: spell.sprites) {
            s.sprite.setColor(1, 0.5f, 0.5f, 1f);
        }
        spell.r = 1;
        spell.g = 0.5f;
        spell.b = 0.5f;
        return spell;
    }
}
