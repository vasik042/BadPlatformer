package com.mygdx.game.objects.items.equipment;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.objects.Passives.DamageResist;
import com.mygdx.game.objects.Passives.DoubleJump;

public class Armor extends Equipment{
    public Armor() {
        super(new Texture("icons/armori.jpg"), new DamageResist(0.2f), 4);
        name = "Armor";
        description = "Protects you";
    }
}
