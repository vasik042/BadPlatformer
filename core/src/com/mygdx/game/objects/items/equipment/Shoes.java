package com.mygdx.game.objects.items.equipment;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.objects.Passives.DoubleJump;
import com.mygdx.game.objects.Passives.MpRegen;
import com.mygdx.game.objects.Passives.Passive;

public class Shoes extends Equipment{
    public Shoes() {
        super(new Texture("icons/shoesi.jpg"), new DoubleJump(1), 6);
        name = "Double shoes";
        description = "Double jump";
    }
}
