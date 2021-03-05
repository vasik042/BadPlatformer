package com.mygdx.game.objects.furnitures.nonBreaks.bricks;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.objects.creatures.heroes.Player;
import com.mygdx.game.objects.furnitures.nonBreaks.fone.Fone;

public class Corner extends Fone {

    boolean right = true;

    public Corner(float x, float y) {
        super(x, y, new Sprite(new Texture("blocks/lab/corner.png")));
    }

    @Override
    public void live(Player player) {
        if(player.x - player.sprite.getWidth()/2+5 < x + 16 && player.x - player.sprite.getWidth()/2+5 > x - 16){
            float height = x - (player.x - player.sprite.getWidth()/2);
            if(player.y - player.sprite.getHeight()/2 < y + 16 && player.y - player.sprite.getHeight()/2 < y + height){
                player.y = y + spriteHeight/2 + height;
            }
        }
    }
}
