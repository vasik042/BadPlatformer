package com.mygdx.game.objects.creatures.enemyes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.objects.AnimationSprite;
import com.mygdx.game.objects.creatures.heroes.Player;
import com.mygdx.game.objects.room.Room;

import java.util.ArrayList;

public class Bat extends Enemy{

    public Bat(float x, float y) {
        super(x, y, new Sprite(new Texture("heroes/mage/stay1.png")), 100, 3, 500);

        this.jumpSprites.add(new AnimationSprite(12, new Texture("bat/pixil-frame-0 (38).png")));
        this.walkSprites.add(new AnimationSprite(12, new Texture("bat/pixil-frame-0 (38).png")));

        this.staySprites.add(new AnimationSprite(12, new Texture("bat/pixil-frame-0 (38).png")));
        this.staySprites.add(new AnimationSprite(12, new Texture("bat/pixil-frame-0 (39).png")));
        this.staySprites.add(new AnimationSprite(12, new Texture("bat/pixil-frame-0 (40).png")));
        this.staySprites.add(new AnimationSprite(12, new Texture("bat/pixil-frame-0 (41).png")));
        this.staySprites.add(new AnimationSprite(12, new Texture("bat/pixil-frame-0 (42).png")));
        this.staySprites.add(new AnimationSprite(20, new Texture("bat/pixil-frame-0 (48).png")));
        this.staySprites.add(new AnimationSprite(25, new Texture("bat/pixil-frame-0 (49).png")));
        this.staySprites.add(new AnimationSprite(25, new Texture("bat/pixil-frame-0 (42).png")));
        this.staySprites.add(new AnimationSprite(20, new Texture("bat/pixil-frame-0 (50).png")));
        this.staySprites.add(new AnimationSprite(25, new Texture("bat/pixil-frame-0 (42).png")));
        this.staySprites.add(new AnimationSprite(20, new Texture("bat/pixil-frame-0 (51).png")));
        this.staySprites.add(new AnimationSprite(25, new Texture("bat/pixil-frame-0 (42).png")));
        this.staySprites.add(new AnimationSprite(20, new Texture("bat/pixil-frame-0 (51).png")));
        this.staySprites.add(new AnimationSprite(10, new Texture("bat/pixil-frame-0 (52).png")));
        this.staySprites.add(new AnimationSprite(12, new Texture("bat/pixil-frame-0 (53).png")));
        this.staySprites.add(new AnimationSprite(15, new Texture("bat/pixil-frame-0 (54).png")));

        this.deadSprites.add(new AnimationSprite(15, new Texture("bat/pixil-frame-0 (38).png")));


        for (int i = 0; i < 16; i++) {
            this.staySprites.get(i).sprite.setSize(64, 64);
            this.staySprites.get(i).sprite.setOrigin(32, 50);
        }

        for (AnimationSprite s:fireSprites) {
            s.sprite.setOrigin(16, -15);
            s.sprite.setSize(s.sprite.getWidth(), 30);
        }
        light.setOrigin(45, 45);

        this.attackSprites.add(new AnimationSprite(12, new Texture("heroes/mage/attack1.png")));
        this.attackSprites.add(new AnimationSprite(12, new Texture("heroes/mage/attack2.png")));

        this.fly = true;
    }

    @Override
    public void live(Room room, SpriteBatch batch, ArrayList bullets, Player player) {
        super.live(room, batch, bullets, player);

        if (Gdx.input.isKeyPressed(Input.Keys.UP)){
            y++;
            y++;
            y++;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            y--;
            y--;
            y--;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            x--;
            x--;
            x--;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            x++;
            x++;
            x++;
        }
    }
}
