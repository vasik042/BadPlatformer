package com.mygdx.game.objects.room;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.objects.creatures.enemyes.Eye;
import com.mygdx.game.objects.creatures.heroes.Player;
import com.mygdx.game.objects.creatures.npcs.Bob;
import com.mygdx.game.objects.furnitures.Furniture;
import com.mygdx.game.objects.furnitures.breaks.Barrel;
import com.mygdx.game.objects.furnitures.breaks.BreakBlock;
import com.mygdx.game.objects.furnitures.traps.Spike;

public class Room000 extends Room{

    public Room000() {
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
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 5, 5, 5, 5, 5, 5, 5, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 5, 5, 5, 5, 5, 5, 5, 5, 1, 5, 8, 14, 14, 14, 14, 14, 14, 14, 9, 5, 1, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 1, 1, 1, 1},
                {1, 4, 14, 14, 14, 14, 14, 14, 14, 14, 10, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 10, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 0, 0, 0, 1},
                {5, 8, 14, 14, 14, 14, 14, 14, 14, 14, 10, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 10, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 0, 0, 0, 1},
                {14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 10, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 10, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 0, 0, 0, 1},
                {14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 10, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 10, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 0, 0, 0, 1},
             {14, 14, 14, 14, 6, 11, 11, 16, 14, 14, 12, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 9, 11, 11, 16, 14, 14, 14, 14, 15, 11, 11, 11, 11, 11, 5, 5, 5, 5},
                {14, 14, 14, 6, 4, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14},
                {14, 14, 14, 2, 4, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14},
                {14, 14, 14, 2, 4, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14},
                {3, 3, 3, 1, 1, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        };

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
        Sprite p = new Sprite(new Texture("blocks/pidor2.png"));
        p.setColor(0.5f, 0.5f, 0.5f, 1);
        furniture.add(new Furniture(16+37*32, 12*32, p, false, true, false));

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
        closeFone.add(new Furniture(640, 496, new Sprite(d), false, false, false));
        d.flip(false, true);
        d.setSize(36, 1000);
        d.rotate90(true);
        closeFone.add(new Furniture(16, 500, new Sprite(d), false, false, false));
        d.flip(true, true);
        closeFone.add(new Furniture(1264, 500, new Sprite(d), false, false, false));



        furniture.add(new Spike(200-32+8, 144+64));
        furniture.add(new Spike(208, 144+64));
        furniture.add(new Spike(240, 144+64));

        furniture.add(new Barrel(240, 144+64));

        furniture.add(new Barrel(500, 144+32+32));
        furniture.add(new Barrel(532, 144+32+32));
        furniture.add(new Barrel(516, 144+32+32));
        furniture.add(new Barrel(564, 144+32+32));
        furniture.add(new Barrel(548, 144+32+32));

        furniture.add(new Barrel(866, 144+32+32));
        furniture.add(new Barrel(898, 144+32+32));
        furniture.add(new Barrel(882, 144+32+32));

        Sprite s = new Sprite(new Texture("shield.png"));
        s.setScale(1.5f);

        furniture.add(new Furniture(500-90, 360, new Sprite(s), false, true, false));
        s.flip(true, false);
        furniture.add(new Furniture(564+90, 360, new Sprite(s), false, true, false));

        Sprite c = new Sprite(new Texture("candel.png"));
        Sprite cl = new Sprite(new Texture("light.png"));
        cl.setSize(120, 120);
        cl.setColor(0.6f, 0.8f, 0, 0.05f);
        c.setScale(2f);
        furniture.add(new Furniture(532, 480-22, new Sprite(c), false, true, false));
        furniture.add(new Furniture(532, 480-22, new Sprite(cl), false, true, false));
        enemies.add(new Eye(532, 350));

        npc.add(new Bob(1180, 320));
    }

    @Override
    public Room create(){
        Room r = new Room000();
        r.spawnX = spawnX;
        r.spawnY = spawnY;
        return r;
    }

    @Override
    public Room nextRoom(Player player) {
        if (player.x > 1280 && player.x< 1350 && player.y < 300){
            Room nextRoom = new Room001();
            nextRoom.spawnX = 32;
            nextRoom.spawnY = 200;
            player.x = 32;
            player.y = 200;
            return nextRoom;
        }else if (player.x > -10 && player.x < 10){
            Room nextRoom2 = new Room00();
            nextRoom2.spawnX = 1250;
            nextRoom2.spawnY = 200;
            player.x = 1250;
            player.y = 200;
            return nextRoom2;
        }else {
            return null;
        }
    }
}
