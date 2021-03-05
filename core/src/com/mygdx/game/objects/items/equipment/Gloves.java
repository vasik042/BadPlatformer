package com.mygdx.game.objects.items.equipment;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.objects.Passives.AtcSpeedBoost;
import com.mygdx.game.objects.Passives.DoubleJump;

public class Gloves extends Equipment{
    public Gloves() {
        super(new Texture("icons/glovesi.jpg"), new AtcSpeedBoost(0.1f), 2);
        name = "Gloves";
        description = "AtcSpeed boost";
    }
}
