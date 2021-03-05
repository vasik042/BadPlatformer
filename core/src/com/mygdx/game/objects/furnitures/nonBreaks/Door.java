package com.mygdx.game.objects.furnitures.nonBreaks;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.objects.AnimationSprite;
import com.mygdx.game.objects.creatures.heroes.Player;
import com.mygdx.game.objects.furnitures.Furniture;

import java.util.ArrayList;

public class Door extends NonBreak {

    public boolean open;
    public int openTime;
    public ArrayList<AnimationSprite> sprites;

    public Door(float x, float y, boolean open) {
        super(x, y, new Sprite(new Texture("door/door1.png")), false, false);
        if(open){
            this.open = true;
            this.openTime = 30;
            this.sprite = new Sprite(new Texture("door/door7.png"));
            this.fone = true;
        }
    }

    @Override
    public void draw(SpriteBatch batch) {

        if(open && openTime < 30){
            openTime++;
        }else if(!open && openTime > 0){
            openTime--;
        }
        switch (openTime){
            case 1:
                sprite = new Sprite(new Texture("door/door1.png"));
                fone = false;
                break;
            case 5:
                sprite = new Sprite(new Texture("door/door2.png"));
                break;
            case 10:
                sprite = new Sprite(new Texture("door/door3.png"));
                break;
            case 14:
                sprite = new Sprite(new Texture("door/door4.png"));
                break;
            case 20:
                sprite = new Sprite(new Texture("door/door5.png"));
                break;
            case 24:
                sprite = new Sprite(new Texture("door/door6.png"));
                break;
            case 29:
                sprite = new Sprite(new Texture("door/door7.png"));
                fone = true;
                break;
        }

        sprite.setOriginCenter();
        sprite.setOriginBasedPosition(x, y);
        sprite.draw(batch);
    }
}
