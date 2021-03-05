package com.mygdx.game.objects.items.equipment;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.objects.Passives.DoubleJump;
import com.mygdx.game.objects.Passives.MpRegen;

public class Ring1 extends Equipment{
    public Ring1() {
        super(new Texture("icons/ring1i.jpg"), new MpRegen(0.1f), 7);
        name = "Ring";
        description = "Mp regen";
    }
}
