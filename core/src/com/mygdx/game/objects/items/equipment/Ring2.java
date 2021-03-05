package com.mygdx.game.objects.items.equipment;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.objects.Passives.DoubleJump;
import com.mygdx.game.objects.Passives.HpRegen;

public class Ring2 extends Equipment{
    public Ring2() {
        super(new Texture("icons/ring2i.jpg"), new HpRegen(0.1f), 7);
        name = "Ring";
        description = "Hp regen";
    }
}
