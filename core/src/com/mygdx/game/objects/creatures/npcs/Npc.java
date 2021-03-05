package com.mygdx.game.objects.creatures.npcs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.mygdx.game.objects.AnimationSprite;
import com.mygdx.game.objects.attacks.Spell;
import com.mygdx.game.objects.creatures.Creature;
import com.mygdx.game.objects.creatures.heroes.Player;
import com.mygdx.game.objects.room.Room;

import java.util.ArrayList;
import java.util.List;

public class Npc extends Creature {

    public boolean shop = false;
    BitmapFont font;
    public boolean dialog = false;
    public Sprite icon;
    public Sprite E;
    public float time;
    public byte line = 1;
    public ArrayList<Spell> spells;

    public Npc(float x, float y) {
        super(x, y, new Sprite(new Texture("Bob/1.png")), 1000, 3);

        E = new Sprite(new Texture("E.png"));
        E.setScale(0.6f);
        E.setOriginCenter();

        right = true;

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Bitter-Regular.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 18;
        font = generator.generateFont(parameter);
        generator.dispose();

        jumpSprites.add(new AnimationSprite(30, new Texture("Saller/1.png")));

        spells = new ArrayList<>();
    }

    public void live(Room room, SpriteBatch batch, List<Spell> bullets, Player player) {
        super.live(room, batch, bullets);

        if(Math.sqrt((player.x - x)*(player.x - x) + (player.y - y)*(player.y - y)) < 100){
            E.setOriginBasedPosition(x, y+ 35);
            E.draw(batch);
            if(Gdx.input.isKeyJustPressed(Input.Keys.E) && !dialog){
                player.stop = !player.stop;
                dialog = !dialog;
            }
        }
        if(shop){
            dialog = false;
            player.stop = true;
        }
    }

    public void say(String s, float textSpeed, int timeLimit, boolean stop, SpriteBatch batch, Player player){
        time++;
        if((int)time/textSpeed < s.length()){
            font.draw(batch, s.substring(0, (int)(time/textSpeed)), 128+10, 128-10);
            if((Gdx.input.isKeyJustPressed(Input.Keys.E) || Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) && time > 10){
                time = s.length()*textSpeed;
            }
        }else {
            font.draw(batch, s, 128+10, 128-10);

            if(((Gdx.input.isKeyJustPressed(Input.Keys.E) || Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) && time > 10) || (timeLimit > 0 && time > s.length() * textSpeed + timeLimit)){
                time = 0;
                line++;
                if(stop){
                    dialog = false;
                    player.stop = false;
                }
            }
        }
    }

    public void dialog(SpriteBatch batch, Player player){
        font.setUseIntegerPositions(true);
        icon.draw(batch);
    }
}
