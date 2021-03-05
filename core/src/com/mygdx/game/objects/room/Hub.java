package com.mygdx.game.objects.room;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.objects.creatures.enemyes.Enemy;
import com.mygdx.game.objects.creatures.enemyes.Tentakle;
import com.mygdx.game.objects.creatures.enemyes.boses.Boss1;
import com.mygdx.game.objects.creatures.heroes.Player;
import com.mygdx.game.objects.creatures.npcs.Master;
import com.mygdx.game.objects.furnitures.Furniture;
import com.mygdx.game.objects.furnitures.breaks.BreakBlock;
import com.mygdx.game.objects.furnitures.nonBreaks.Door;
import com.mygdx.game.objects.items.Bag;
import com.mygdx.game.objects.items.equipment.Ring1;
import com.mygdx.game.objects.items.equipment.Ring2;
import com.mygdx.game.objects.items.potions.HpPotion;

public class Hub extends Room{

    public Hub() {
        super();

        //0 - 1
        //l - 2
        //u - 3
        //r - 4
        //d - 5
        //lu - 6
        //ur - 7
        //rd - 8
        //ld - 9
        //lr - 10
        //ud - 11
        //lrd - 12
        //lur - 13
        //fone - 14
        //lud - 15
        //urd - 16

        //2b - 29
        //1b - 19

        byte[][] arr = {
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},

                {1, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 1},

                {4, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 2},
                {4, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 2},
                {4, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 2},
                {4, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 2},
                {4, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 2},
                {4, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 2},
                {1, 5, 5, 5, 5, 5, 16, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 2},
                {4, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 2},
                {4, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 2},
                {4, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 2},
                {5, 11, 11, 11, 11, 16, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 9},
                {14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14},
                {14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14},
                {3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3},

                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        };

        Sprite w = new Sprite(new Texture("blocks/block.png"));
        w.setColor(0.5f, 0.5f, 0.5f, 1);
        for (int i = 0; i < 23; i++) {
            for (int j = 0; j < 40; j++) {
                switch (arr[i][j]){
                    case 1:
                        furniture.add(new Furniture(16+j*32, 16+(23-i)*32, new Sprite(new Texture("blocks/block.png"))));
                        break;
                    case 2:
                        furniture.add(new Furniture(16+j*32, 16+(23-i)*32, new Sprite(new Texture("blocks/blockl.png"))));
                        break;
                    case 3:
                        furniture.add(new Furniture(16+j*32, 16+(23-i)*32, new Sprite(new Texture("blocks/blocku.png"))));
                        break;
                    case 4:
                        furniture.add(new Furniture(16+j*32, 16+(23-i)*32, new Sprite(new Texture("blocks/blockr.png"))));
                        break;
                    case 5:
                        furniture.add(new Furniture(16+j*32, 16+(23-i)*32, new Sprite(new Texture("blocks/blockd.png"))));
                        break;
                    case 6:
                        furniture.add(new Furniture(16+j*32, 16+(23-i)*32, new Sprite(new Texture("blocks/blocklu.png"))));
                        break;
                    case 7:
                        furniture.add(new Furniture(16+j*32, 16+(23-i)*32, new Sprite(new Texture("blocks/blockur.png"))));
                        break;
                    case 8:
                        furniture.add(new Furniture(16+j*32, 16+(23-i)*32, new Sprite(new Texture("blocks/blockrd.png"))));
                        break;
                    case 9:
                        furniture.add(new Furniture(16+j*32, 16+(23-i)*32, new Sprite(new Texture("blocks/blockld.png"))));
                        break;
                    case 10:
                        furniture.add(new Furniture(16+j*32, 16+(23-i)*32, new Sprite(new Texture("blocks/blocklr.png"))));
                        break;
                    case 11:
                        furniture.add(new Furniture(16+j*32, 16+(23-i)*32, new Sprite(new Texture("blocks/blockud.png"))));
                        break;
                    case 12:
                        furniture.add(new Furniture(16+j*32, 16+(23-i)*32, new Sprite(new Texture("blocks/blocklrd.png"))));
                        break;
                    case 13:
                        furniture.add(new Furniture(16+j*32, 16+(23-i)*32, new Sprite(new Texture("blocks/blocklur.png"))));
                        break;
                    case 14:
                        furniture.add(new Furniture(16+j*32, 16+(23-i)*32, new Sprite(w), false, true, false));
                        break;
                    case 15:
                        furniture.add(new Furniture(16+j*32, 16+(23-i)*32, new Sprite(new Texture("blocks/blocklud.png"))));
                        break;
                    case 16:
                        furniture.add(new Furniture(16+j*32, 16+(23-i)*32, new Sprite(new Texture("blocks/blockurd.png"))));
                        break;
                    case 19:
                        furniture.add(new BreakBlock(16+j*32, 16+(23-i)*32, new Sprite(new Texture("blocks/block.png")), new Sprite(new Texture("blocks/blockbroke.png")), false,false, 50));
                        break;
                    case 29:
                        furniture.add(new BreakBlock(16 + j * 32, 16 + (23 - i) * 32, new Sprite(new Texture("blocks/blocklbroke.png")), new Sprite(new Texture("blocks/blocklbroke.png")), false, false, 50));
                        break;
                }
            }
        }
        sprite = new Sprite(new Texture("dark.png"));
        Sprite d = new Sprite(new Texture("dark.png"));
        d.setSize(1280, 32);
        d.setColor(1, 1, 1, 1);
        closeFone.add(new Furniture(640, 176, new Sprite(d), false, false, false));
        d.flip(false, true);
        closeFone.add(new Furniture(640, 624, new Sprite(d), false, false, false));
        d.flip(false, true);
        d.setSize(36, 800);
        d.rotate90(true);
        closeFone.add(new Furniture(16, 400, new Sprite(d), false, false, false));
        d.flip(true, true);
        closeFone.add(new Furniture(1264, 400, new Sprite(d), false, false, false));


        furniture.add(new Door(16, 224, true));
        furniture.add(new Furniture(640, 312, new Sprite(new Texture("statue.png")), false, true, false));

        npc.add(new Master(165, 305));

        enemies.add(new Tentakle(500, 240));

        Bag bag = new Bag(400, 500);
        bag.items.add(new HpPotion());
        bag.items.add(new HpPotion());
        bag.items.add(new HpPotion());
        bags.add(bag);

        Sprite shody = new Sprite(new Texture("blocks/block.png"));
        shody.setSize(5, 32);

        furniture.add(new Furniture(498-16, 300, new Sprite(new Texture("blocks/block.png"))));
        furniture.add(new Furniture(498-16-32, 300, new Sprite(new Texture("blocks/block.png"))));
        furniture.add(new Furniture(500, 300, new Sprite(shody)));
        furniture.add(new Furniture(505, 305, new Sprite(shody)));
        furniture.add(new Furniture(510, 310, new Sprite(shody)));
        furniture.add(new Furniture(515, 315, new Sprite(shody)));
        furniture.add(new Furniture(520, 320, new Sprite(shody)));
        furniture.add(new Furniture(525, 325, new Sprite(shody)));
        furniture.add(new Furniture(530, 330, new Sprite(shody)));
        furniture.add(new Furniture(564-16, 300+32, new Sprite(new Texture("blocks/block.png"))));
        furniture.add(new Furniture(564-16+32, 300+32, new Sprite(new Texture("blocks/block.png"))));
    }

    @Override
    public Room create(){
        Room r = new Hub();
        r.spawnX = spawnX;
        r.spawnY = spawnY;
        return r;
    }

    @Override
    public Room nextRoom(Player player) {
        if (player.x > -10 && player.x < 10){
            Room nextRoom = new Room002();
            nextRoom.spawnX = 1250;
            nextRoom.spawnY = 200;
            player.x = 1250;
            player.y = 200;
            return nextRoom;
        }else {
            return null;
        }
    }
}