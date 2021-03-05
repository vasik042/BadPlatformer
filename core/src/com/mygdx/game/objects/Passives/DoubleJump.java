package com.mygdx.game.objects.Passives;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.objects.creatures.Creature;

public class DoubleJump extends Passive{
    int jumps;
    int areJumped = 0;
    boolean doubleJump = true;

    public DoubleJump(int jumps) {
        super(new Sprite(new Texture("heroes/mage/jump.png")));
        this.jumps = jumps;
    }

    @Override
    public void live(Creature creature) {
        super.live(creature);
        if(creature.jump){
            if(Gdx.input.isKeyJustPressed(Input.Keys.W) && doubleJump){
                creature.fallSpeed = -10;
                if(Gdx.input.isKeyPressed(Input.Keys.A) && creature.xSpeed > 0){
                    creature.xSpeed = -1;
                }else if(Gdx.input.isKeyPressed(Input.Keys.D) && creature.xSpeed < 0){
                    creature.xSpeed = 1;
                }
                areJumped++;
                if(areJumped >= jumps){
                    doubleJump = false;
                }
            }
        }else {
            doubleJump = true;
            areJumped = 0;
        }
    }
}
