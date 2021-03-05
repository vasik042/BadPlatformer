package com.mygdx.game.objects.items.equipment;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.objects.Passives.MpRegen;
import com.mygdx.game.objects.Passives.Passive;

public class Hat extends Equipment{
    public Hat() {
        super(new Texture("icons/hati.jpg"), new MpRegen(0.1f), 3);
        name = "Wizard hat";
        description = "Help you to concentrate";
    }
}
