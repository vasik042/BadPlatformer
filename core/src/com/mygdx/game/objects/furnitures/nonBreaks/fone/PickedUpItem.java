package com.mygdx.game.objects.furnitures.nonBreaks.fone;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.objects.creatures.heroes.Player;
import com.mygdx.game.objects.furnitures.Furniture;

public class PickedUpItem extends Furniture {
    float time;

    public PickedUpItem(Sprite sprite, float time) {
        super(0, 0, sprite, false, true, false);
        this.sprite.setSize(24, 24);
        this.sprite.setOriginCenter();
        this.time = time;
    }

    @Override
    public void live(Player player) {
        time++;
        if(time > 0){
            sprite.setOriginBasedPosition(player.x, player.y +32 + time/10);
            sprite.setColor(1, 1, 1, 1 - time/100);
            if(time > 100){
                dead = true;
            }
            super.live(player);
        }
    }
}
