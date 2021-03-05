package com.mygdx.game.objects.items.equipment;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.objects.Passives.DoubleJump;
import com.mygdx.game.objects.Passives.MoveSpeedBoost;

public class Pants extends Equipment{
    public Pants() {
        super(new Texture("icons/pantsi.jpg"), new MoveSpeedBoost(0.2f), 5);
        name = "Pants";
        description = "Run faster";
    }
}
