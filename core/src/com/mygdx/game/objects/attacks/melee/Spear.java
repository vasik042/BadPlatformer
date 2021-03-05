package com.mygdx.game.objects.attacks.melee;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.objects.AnimationSprite;
import com.mygdx.game.objects.attacks.effects.Push;
import com.mygdx.game.objects.room.Room;

public class Spear extends Melee {

    public Spear(){
        super(4, 200, 25, 1f, new Sprite(new Texture("spear.png")), 1, 7, false, 0, 15, 25, (byte) 5, (byte)3);
        this.icon = new Sprite(new Texture("icons/speari.jpg"));

        this.effects.add(new Push(2f));

        this.sprites.add(new AnimationSprite(3, new Texture("spear.png")));

        this.sprites.get(0).sprite.setOrigin(5, 0);
        this.sprite.setOrigin(5, 0);

        name = "Spear";
        description = "Nothing interesting";
    }
}
