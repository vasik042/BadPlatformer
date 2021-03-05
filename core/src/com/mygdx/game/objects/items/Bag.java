package com.mygdx.game.objects.items;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.objects.GameObject;
import com.mygdx.game.objects.creatures.heroes.Player;
import com.mygdx.game.objects.furnitures.Furniture;
import com.mygdx.game.objects.furnitures.nonBreaks.fone.PickedUpItem;
import com.mygdx.game.objects.room.Room;

import java.util.ArrayList;

public class Bag extends GameObject {
    public ArrayList<Item> items;
    public Sprite E;
    public boolean empty = false;
    public boolean stopped = false;

    public Bag(float x, float y) {
        super(x, y, new Sprite(new Texture("bag.png")));

        items = new ArrayList<>();

        E = new Sprite(new Texture("E.png"));
        E.setScale(0.6f);
        E.setOriginCenter();
    }

    public void live(SpriteBatch batch, Player player, Room room){
        sprite.setOriginBasedPosition(x, y);
        sprite.draw(batch);

        pickUp(player, batch, room);

        if(!stopped){
            fall(room);
        }
    }

    private void fall(Room room) {
        Furniture collision = room.findCollision(x, (y - sprite.getHeight() / 2) - 8);
        if(collision == null){
            y -= 8;
        }else {
            y = collision.y + collision.sprite.getHeight()/2 + sprite.getHeight()/2;
            stopped = true;
        }
    }

    public void pickUp(Player player, SpriteBatch batch, Room room){
        if(Math.sqrt((player.x - x)*(player.x - x) + (player.y - y)*(player.y - y)) < 50){
            E.setOriginBasedPosition(x, y+ 35);
            E.draw(batch);

            int j = 0;

            if(Gdx.input.isKeyJustPressed(Input.Keys.E)){
                for (Item i: items) {
                    boolean find = false;
                    for (Item pi: player.items) {
                        if(pi.getClass().getSimpleName().equals(i.getClass().getSimpleName()) && pi.quantity > 0){
                            pi.quantity ++;
                            find = true;
                        }
                    }
                    if(!find){
                        player.items.add(i);
                    }
                    room.furniture.add(new PickedUpItem(new Sprite(i.sprite), -(j*25)));
                    j++;
                }

                empty = true;
            }
        }
    }
}
