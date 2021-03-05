package com.mygdx.game.objects.attacks.range;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.objects.AnimationSprite;
import com.mygdx.game.objects.attacks.Spell;
import com.mygdx.game.objects.furnitures.Furniture;
import com.mygdx.game.objects.room.Room;

import java.util.ArrayList;


public class Telekinesis extends Spell {
    Furniture furniture;


    public Telekinesis(){
        super(0, 10000, 0, 2f, new Sprite(new Texture("Enemy_bullet.png")), 1, 1, false, 0, 1, 0, (byte)0, (byte)0);
        this.icon = new Sprite(new Texture("icons/telekinesi.jpg"));

        this.hold = true;
        this.peirce = true;
        this.unique = true;

        this.sprites.add(new AnimationSprite(10, new Texture("Enemy_bullet.png")));

        name = "Telekinesis";
        description = "Barrels deserve it";
    }

    @Override
    public void setParams(Room room){
        time = 0;
        x1 = Gdx.input.getX();
        y1 = 736 - Gdx.input.getY();

        Furniture collision = null;

        if(furniture == null){
            float ang = (float)Math.atan((rizX - x1) /(rizY - y1));

            if (y1 > rizY){
                ang += 3.1459;
            }

            float dX = -(float)Math.sin(ang);
            float dY = -(float)Math.cos(ang);

            for (int i = 0; i < Math.sqrt((rizX - x1)*(rizX - x1) + (rizY - y1)*(rizY - y1))/20; i++) {
                collision = room.findCollision(rizX + dX * 20 * i, rizY + dY * 20 * i);
                if(collision != null){
                    if(!collision.fall){
                        reload = 50;
                        x1 = rizX + dX * 20 * i;
                        y1 = rizY + dY * 20 * i;
                        destroyed = true;
                        if(furniture != null){
                            furniture.fone = false;
                        }
                        break;
                    }
                }
            }
        }else {
            float ang = (float)Math.atan((rizX - furniture.x) /(rizY - furniture.y));

            if (furniture.y > rizY){
                ang += 3.1459;
            }

            float dX = -(float)Math.sin(ang);
            float dY = -(float)Math.cos(ang);

            for (int i = 0; i < Math.sqrt((rizX - furniture.x)*(rizX - furniture.x) + (rizY - furniture.y)*(rizY - furniture.y))/20; i++) {
                collision = room.findCollision(rizX + dX * 20 * i, rizY + dY * 20 * i);
                if(collision != null){
                    if(!collision.fall){
                        reload = 50;
                        x1 = rizX + dX * 20 * i;
                        y1 = rizY + dY * 20 * i;
                        destroyed = true;
                        if(furniture != null){
                            furniture.fone = false;
                        }
                        break;
                    }
                }
            }
        }
    }



    @Override
    public boolean collision(Room room) {
        furniture = null;
        furniture = room.findCollision(x1, y1);

        if(furniture != null && !furniture.fall){
            furniture = null;
        }

        if (furniture == null){
            reload = 50;
        }else {
            furniture.fone = true;
        }
        return false;
    }

    @Override
    public void live(SpriteBatch batch, Room room, ArrayList<Spell> addBullets) {
        animation();
        draw(batch, addBullets);
        time ++;

        if (furniture != null && !destroyed){
            ms = (float) Math.sqrt((x1 - furniture.x)*(x1 - furniture.x) + (y1 - furniture.y)*(y1 - furniture.y))/10;
            if(ms > 10){
                ms = 10;
            }
            float ang = (float)Math.atan((furniture.x - x1) /(furniture.y - y1));

            if (y1 > furniture.y){
                ang += 3.1459;
            }

            float dX = (float)Math.sin(ang);
            float dY = (float)Math.cos(ang);

            furniture.xSpeed = -dX*ms;
            furniture.fallSpeed = dY*ms;
        }

        if(time > 10){
            destroyed = true;
            if(furniture != null){
                furniture.fone = false;
            }
        }
    }
}
