package com.mygdx.game.objects.furnitures.nonBreaks;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.objects.creatures.heroes.Player;
import com.mygdx.game.objects.furnitures.Furniture;

import java.util.ArrayList;

public class Elevator extends NonBreak {
    int time;
    ArrayList<Furniture> chains;
    int height;

    public Elevator(float x, float y, int height, int chainsHeight, boolean activated) {
        super(x, y, new Sprite(new Texture("blocks/lab/elevator.png")), true, false);

        this.chains = new ArrayList<>();

        this.height = height * 32;
        this.activated = activated;

        for (int i = 1; i < chainsHeight+1; i++) {
            chains.add(new Furniture(x-25, y + 32*i, new Sprite(new Texture("blocks/lab/chain.png")), false, true, false));
            chains.add(new Furniture(x+25, y + 32*i, new Sprite(new Texture("blocks/lab/chain.png")), false, true, false));
        }
    }

    @Override
    public void live(Player player) {
        if(activated){
            time++;
            if (time > 100 && time <= 100+height){
                y++;

                for (Furniture f: chains) {
                    f.y++;
                }
            }else if(time > 200 + height && time <= 200 + height*2){
                y--;

                for (Furniture f: chains) {
                    f.y--;
                }

                if(player.x > x - sprite.getWidth()/2 && player.x < x + sprite.getWidth()/2){
                    if(player.y - player.sprite.getHeight()/2 >= y + sprite.getHeight()/2 -5&& player.y -player.sprite.getHeight()/2<= y + sprite.getHeight()/2+5){
                        player.y --;
                    }
                }
            }else if(time > 200 + height*2){
                time = 0;
            }
            sprite.setOriginBasedPosition(x, y);
        }
    }

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
        for (Furniture f: chains) {
            f.sprite.setOriginBasedPosition(f.x, f.y);
            f.sprite.draw(batch);
        }
    }
}
