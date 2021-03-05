package com.mygdx.game.objects.creatures.npcs;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.objects.AnimationSprite;
import com.mygdx.game.objects.creatures.heroes.Player;


public class Bob extends Npc {


    public Bob(float x, float y) {
        super(x, y);

        this.jumpSprites.add(new AnimationSprite(12, new Texture("Bob/1.png")));
        this.walkSprites.add(new AnimationSprite(20, new Texture("Bob/1.png")));

        this.staySprites.add(new AnimationSprite(15, new Texture("Bob/1.png")));
        this.staySprites.add(new AnimationSprite(15, new Texture("Bob/2.png")));
        this.staySprites.add(new AnimationSprite(15, new Texture("Bob/3.png")));
        this.staySprites.add(new AnimationSprite(15, new Texture("Bob/2.png")));

        this.attackSprites.add(new AnimationSprite(5, new Texture("Bob/1.png")));

        this.deadSprites.add(new AnimationSprite(20, new Texture("Bob/1.png")));

        icon = new Sprite(new Texture("Bob/bob-ross.jpg"));
        icon.setSize(128, 128);
    }

    @Override
    public void dialog(SpriteBatch batch, Player player){
        super.dialog(batch, player);
        switch (line){
            case 1:
                say("There are no mistakes, just happy little accidents.", 3 ,0, false, batch, player);
                break;
            case 2:
                say("Lets build a happy little cloud.     \n" +
                    "Lets build some happy little trees.", 3 ,0, false, batch, player);
                break;
            case 3:
                say("There's nothing wrong with having a tree as a friend.", 3 ,0, false, batch, player);
                break;
            case 4:
                say("I guess I’m a little weird.    I like to talk to trees and animals.        \nThat’s okay though.    I have more fun than most people.", 3 ,0, true, batch, player);
                break;
            case 5:
                say("Believe that you can do it.        \nCause you can do it.", 4 ,100, true, batch, player);
                line = 5;
                break;
        }
    }
}
