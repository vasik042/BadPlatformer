package com.mygdx.game.objects.furnitures.nonBreaks.fone;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.objects.AnimationSprite;
import com.mygdx.game.objects.creatures.heroes.Player;
import com.mygdx.game.objects.furnitures.Furniture;

import java.util.ArrayList;

public class Tesla extends Furniture {
    float time;
    int spriteNum;
    ArrayList <AnimationSprite> sprites;

    public Tesla(float x, float y) {
        super(x, y, new Sprite(new Texture("tesla.png")), false, true, false);
        this.sprite.setSize(24, 24);
        this.sprite.setOriginCenter();
        sprites = new ArrayList<>();
        sprites.add(new AnimationSprite(50, new Texture("tesla.png")));
        sprites.add(new AnimationSprite(3, new Texture("tesla2.png")));
        sprites.add(new AnimationSprite(3, new Texture("tesla3.png")));
        sprites.add(new AnimationSprite(3, new Texture("tesla4.png")));
        sprites.add(new AnimationSprite(3, new Texture("tesla2.png")));
        sprites.add(new AnimationSprite(3, new Texture("tesla3.png")));
        sprites.add(new AnimationSprite(3, new Texture("tesla4.png")));
        sprites.add(new AnimationSprite(3, new Texture("tesla3.png")));
        sprites.add(new AnimationSprite(3, new Texture("tesla2.png")));

        for (AnimationSprite s:sprites) {
            s.sprite.setOriginBasedPosition(x, y);
        }
    }

    @Override
    public void live(Player player) {
        time++;
        sprites.get(spriteNum).time++;
        if(sprites.get(spriteNum).time > sprites.get(spriteNum).maxTime){
            sprites.get(spriteNum).time = 0;
            spriteNum++;
            if(spriteNum >= sprites.size()){
                spriteNum = 0;
            }
        }
        sprite = sprites.get(spriteNum).sprite;
        super.live(player);
    }
}
