package com.mygdx.game.objects.attacks.range;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.objects.AnimationSprite;
import com.mygdx.game.objects.attacks.Spell;
import com.mygdx.game.objects.attacks.effects.FireEffect;
import com.mygdx.game.objects.attacks.effects.Push;
import com.mygdx.game.objects.room.Room;

import java.util.ArrayList;

public class Missless  extends Spell {

    public Missless(){
        super(7, 400, 7, 1f, new Sprite(new Texture("Enemy_bullet.png")), 1, 1, false, 0, 15, 15, (byte)1, (byte)1);
        this.icon = new Sprite(new Texture("icons/misslessi.jpg"));

        this.effects.add(new Push(2f));

        this.sprites.add(new AnimationSprite(5, new Texture("missless/1.png")));
        this.sprites.add(new AnimationSprite(5, new Texture("missless/1.png"), true));
        this.sprites.add(new AnimationSprite(5, new Texture("missless/2.png")));
        this.sprites.add(new AnimationSprite(5, new Texture("missless/2.png"), true));
        this.sprites.add(new AnimationSprite(5, new Texture("missless/3.png")));
        this.sprites.add(new AnimationSprite(5, new Texture("missless/3.png"), true));
        this.sprites.add(new AnimationSprite(5, new Texture("missless/4.png")));
        this.sprites.add(new AnimationSprite(5, new Texture("missless/4.png"), true));

        missless = true;

        name = "Missless spell";
        description = "When game is too hard for you\n" +
                "Add this shit to say bullet where it have to fly";
    }


    @Override
    public Spell fuse(Spell spell) {
        spell.missless = true;
        if(spell.midSpell != null){
            spell.midSpell.missless = true;
        }
        if(spell.endSpell != null && !spell.endSpell.getClass().getSimpleName().equals("Explode")){
            spell.endSpell.missless = true;
        }
        return spell;
    }
}
