package com.mygdx.game.objects.furnitures.breaks;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.objects.attacks.Spell;
import com.mygdx.game.objects.furnitures.Furniture;
import com.mygdx.game.objects.furnitures.nonBreaks.fone.BarrelPiece;
import com.mygdx.game.objects.room.Room;

import java.util.ArrayList;

public class Barrel extends BreakBlock{
    float deltaX;

    public Barrel(float x, float y) {
        super(x, y, new Sprite(new Texture("barrel/barrel.png")), new Sprite(new Texture("icon.png")), true, false, 10);

        fall = true;
        fallSpeed = 1;
        deltaX = 0;
    }



    @Override
    public void destroy(Spell spell, ArrayList<Furniture> furnitures) {
        hp -= spell.damage;
        float ms = spell.ms;

        if(spell.deltaX == 0 && spell.deltaY == 0){

            float delX = spell.x1 - x;
            float delY = spell.y1 - y;

            float ang = (float)Math.atan(delX/delY);
            if (y > spell.y1){
                ang += 3.1459;
            }

            spell.deltaX = -(float)Math.sin(ang);
            spell.deltaY = -(float)Math.cos(ang);
            ms = 20;
        }

        if (hp <= 0){

            furnitures.add(new BarrelPiece(x, y ,new Sprite(new Texture("barrel/1.png")), spell.deltaX, spell.deltaY, ms));
            furnitures.add(new BarrelPiece(x, y ,new Sprite(new Texture("barrel/1.png")), spell.deltaX, spell.deltaY, ms));
            furnitures.add(new BarrelPiece(x, y ,new Sprite(new Texture("barrel/2.png")), spell.deltaX, spell.deltaY, ms));
            furnitures.add(new BarrelPiece(x, y ,new Sprite(new Texture("barrel/2.png")), spell.deltaX, spell.deltaY, ms));
            furnitures.add(new BarrelPiece(x, y ,new Sprite(new Texture("barrel/3.png")), spell.deltaX, spell.deltaY, ms));
            furnitures.add(new BarrelPiece(x, y ,new Sprite(new Texture("barrel/3.png")), spell.deltaX, spell.deltaY, ms));
            furnitures.add(new BarrelPiece(x, y ,new Sprite(new Texture("barrel/4.png")), spell.deltaX, spell.deltaY, ms));
            furnitures.add(new BarrelPiece(x, y ,new Sprite(new Texture("barrel/4.png")), spell.deltaX, spell.deltaY, ms));
            furnitures.add(new BarrelPiece(x, y ,new Sprite(new Texture("barrel/5.png")), spell.deltaX, spell.deltaY, ms));
            furnitures.add(new BarrelPiece(x, y ,new Sprite(new Texture("barrel/5.png")), spell.deltaX, spell.deltaY, ms));

            furnitures.remove(this);
        }
    }

    @Override
    public void fall(Room room) {
        float y1 = y-spriteHeight/2 - fallSpeed;
        float y2 = y+spriteHeight/2 - fallSpeed;
        float x1 = x-spriteWidth/2 + xSpeed;
        float x2 = x+spriteWidth/2 + xSpeed;

        Furniture collisionB = room.findCollision(x, y1);
        Furniture collisionU = room.findCollision(x, y2);
        Furniture collisionL = room.findCollision(x1, y);
        Furniture collisionR = room.findCollision(x2, y);

        if(collisionB == this){
            collisionB = null;
        }if(collisionU == this){
            collisionU = null;
        }if(collisionL == this){
            collisionL = null;
        }if(collisionR == this){
            collisionR = null;
        }

        if(collisionB != null || collisionU != null || collisionL != null || collisionR != null){
            transitional = true;
            fone = false;
            if(fallSpeed > 7 || fallSpeed < -7 || xSpeed > 7 || xSpeed < -7){
                deadSpell = new Spell();
                deadSpell.damage = 100;
                deadSpell.ms = 6;
                deadSpell.deltaX = xSpeed/8;
                deadSpell.deltaY = fallSpeed/8;
                dead = true;
            }
        }else {
            transitional = false;
            fone = true;
        }

        if (collisionB != null){
            y = collisionB.y + collisionB.spriteHeight/2 + spriteHeight/2;
            fallSpeed = 0;
            xSpeed *= 0.85f;
        }

        if (collisionU != null && !collisionU.transitional){
            y = collisionU.y - collisionU.spriteHeight/2 - spriteHeight/2;
            fallSpeed = 1;
        }

        if ((collisionL != null) || (collisionR != null)){
            if(collisionL != null){
                x = collisionL.x + collisionL.sprite.getWidth()/2 + spriteWidth/2;
                if(collisionL.fall){
                    xSpeed /= 2;
                    collisionL.xSpeed = xSpeed;
                }else if (xSpeed < 0){
                    xSpeed = 0;
                }
            }else {
                x = collisionR.x - collisionR.sprite.getWidth()/2 - spriteWidth/2;
                if(collisionR.fall){
                    xSpeed /= 2;
                    collisionR.xSpeed = xSpeed;
                }else if (xSpeed > 0){
                    xSpeed = 0;
                }
            }
        }

        fallSpeed += 0.5;
        if(fallSpeed > 8){
            fallSpeed = 8;
        }

        if (xSpeed <8 && xSpeed > 0){
            xSpeed *= 0.99;
        }
        if(xSpeed > -0.5 && xSpeed <= 0.5){
            xSpeed = 0;
        }
        if(xSpeed > 8){
            xSpeed = 8;
        }

        y -= fallSpeed;
        x += xSpeed;

        sprite.setOriginBasedPosition(x, y);
    }
}
