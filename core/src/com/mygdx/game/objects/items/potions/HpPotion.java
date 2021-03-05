package com.mygdx.game.objects.items.potions;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.objects.Passives.HpRegen;
import com.mygdx.game.objects.Passives.Passive;

public class HpPotion extends Potion{
    public HpPotion() {
        super(new HpRegen(2), 25, new Texture("icons/potioni.jpg"));
        name = "Healing potion";
        description = "Regen 50 hp";
    }
}
