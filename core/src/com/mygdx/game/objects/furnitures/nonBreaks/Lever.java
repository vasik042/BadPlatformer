package com.mygdx.game.objects.furnitures.nonBreaks;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.objects.creatures.heroes.Player;
import com.mygdx.game.objects.furnitures.Furniture;
import com.mygdx.game.objects.furnitures.nonBreaks.fone.Fone;
import com.mygdx.game.objects.furnitures.nonBreaks.fone.PickedUpItem;
import com.mygdx.game.objects.items.Item;

public class Lever extends Fone {

    boolean activated;
    Furniture furniture;
    public Sprite E;

    boolean playerNear = false;

    public Lever(float x, float y, Furniture furniture) {
        super(x, y, new Sprite(new Texture("Lever.gif")));

        this.furniture = furniture;

        E = new Sprite(new Texture("E.png"));
        E.setScale(0.6f);
        E.setOriginCenter();
    }

    @Override
    public void live(Player player) {
        super.live(player);
        if(Math.sqrt((player.x - x)*(player.x - x) + (player.y - y)*(player.y - y)) < 50){
            playerNear = true;

            if(Gdx.input.isKeyJustPressed(Input.Keys.E)){
                furniture.activated = !furniture.activated;
                sprite.flip(true, false);
            }
        }else {
            playerNear = false;
        }
    }

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
        if(playerNear){
            E.setOriginBasedPosition(x, y+ 35);
            E.draw(batch);
        }
    }
}
