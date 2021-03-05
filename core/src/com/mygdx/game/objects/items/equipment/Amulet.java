package com.mygdx.game.objects.items.equipment;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.objects.Passives.DamageBoost;
import com.mygdx.game.objects.Passives.DoubleJump;

public class Amulet extends Equipment{
    public Amulet() {
        super(new Texture("icons/amulet1i.jpg"), new DamageBoost(0.2f), 1);
        name = "Amulet";
        description = "Damage boost";
    }
}
