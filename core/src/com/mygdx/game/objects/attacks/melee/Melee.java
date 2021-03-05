package com.mygdx.game.objects.attacks.melee;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.objects.AnimationSprite;
import com.mygdx.game.objects.attacks.Spell;
import com.mygdx.game.objects.creatures.Creature;


import java.util.ArrayList;

public class Melee extends Spell {

    public Melee(float ms, float lifeTime, float damage, float size, Sprite sprite, int shoots, int sootType, boolean singleSoot, int range, float mp, float reloadTime, byte collisionType, byte collisionEffect) {
        super(ms, lifeTime, damage, size, sprite, shoots, sootType, singleSoot, range, mp, reloadTime,  collisionType, collisionEffect);

        this.melee = true;
        this.peirce = true;
    }

    @Override
    public void setParams(float x1, float y1, float ang, float deltaX, float deltaY, boolean enemy) {
        super.setParams(x1, y1, ang, deltaX, deltaY, enemy);
        if(shoots > 1){
            int getX = Gdx.input.getX();
            int getY = Gdx.input.getY();
            float ang2 = (float)Math.atan((getX - creature.x) /((736 - getY) - creature.y));

            if (creature.y > 736-Gdx.input.getY() + creature.yPos){
                ang2 += 3.1459;
            }

            range2 = ang2 - ang;
        }
    }
}
