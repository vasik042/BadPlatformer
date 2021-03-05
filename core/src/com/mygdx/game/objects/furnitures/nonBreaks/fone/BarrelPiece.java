package com.mygdx.game.objects.furnitures.nonBreaks.fone;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.objects.furnitures.Furniture;
import com.mygdx.game.objects.room.Room;

public class BarrelPiece extends Fone{
    float deltaX;
    float deltaY;
    float ms;
    float rotation;
    float time = 0;

    public BarrelPiece(float x, float y, Sprite sprite, float deltaX, float deltaY, float ms) {
        super((x + (float) (Math.random()*14 -7)), y + (float) (Math.random()*22 -11), sprite);
        this.fall = true;
        this.deltaX = deltaX + (float) (Math.random() -0.5);
        this.deltaY = deltaY + (float) (Math.random() -0.5);
        this.ms = ms  + (float) (Math.random()*2 -1);
        this.rotation = (float) (Math.random()*40 - 20);
        this.fallSpeed = 0;
    }

    @Override
    public void fall(Room room) {
        time++;
        sprite.setColor(1, 1, 1, (1f -time/200f));
        if(time >200){
            dead = true;
        }

        Furniture collisionB = room.findCollision(x, y-spriteHeight/2);
        Furniture collisionL = room.findCollision(x-spriteWidth/2, y);
        Furniture collisionR = room.findCollision(x+spriteWidth/2, y);
        if(collisionB == null && collisionL == null && collisionR == null){
            sprite.rotate(rotation);
        }
        if (collisionB == null || collisionB.transitional){
            y += deltaY*ms;
            y -= fallSpeed;
        }else {
            y = collisionB.y + collisionB.spriteHeight/2 + spriteHeight/2 -1;
            ms *= 0.8;
        }
        if ((collisionL == null || collisionL.transitional) && (collisionR == null  || collisionR.transitional)){
            x += deltaX*ms;
        }else{
            deltaX = 0;
        }
        sprite.setOriginBasedPosition(x, y);
        fallSpeed +=0.2;
        ms *= 0.95;
    }

}

