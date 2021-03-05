package com.mygdx.game.objects.creatures.npcs;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.objects.AnimationSprite;
import com.mygdx.game.objects.attacks.range.*;
import com.mygdx.game.objects.creatures.heroes.Player;

public class Master extends Npc{
    public Master(float x, float y) {
        super(x, y);
        this.staySprites.add(new AnimationSprite(30, new Texture("Saller/1.png")));
        this.staySprites.add(new AnimationSprite(30, new Texture("Saller/2.png")));

        icon = new Sprite(new Texture("Saller/master.jpg"));
        icon.setSize(128, 128);

        spells.add(new MisslessOrb());
        spells.add(new Laser());
    }

    @Override
    public void dialog(SpriteBatch batch, Player player) {
        super.dialog(batch, player);
        switch (line){
            case 1:
                say("My name is Van.        \nI'm a wizard.        \nI'm a performance wizard.", 5 ,0, false, batch, player);
                break;
            case 2:
                say("If you want some magic...        \n" +
                        "I can sell you some.            ", 5 ,0, true, batch, player);
                if(time > 300){
                    player.stop = true;
                    shop = true;
                    dialog = true;
                }
                line = 2;
                break;
        }
    }
}
