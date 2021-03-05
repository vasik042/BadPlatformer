package com.mygdx.game.objects.room;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.objects.creatures.heroes.Player;
import com.mygdx.game.objects.furnitures.Furniture;
import com.mygdx.game.objects.furnitures.nonBreaks.Platform;
import com.mygdx.game.objects.creatures.enemyes.GoblinMage;

public class Basement3 extends Room {
    public Basement3() {
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
        //1 - 17
        //fone2 - 18

        //19
        //20 pl



        height = 1472;


        byte[][] arr = {
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {17, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 17},
                {41, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 21},
                {41, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 21},
                {41, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 21},
                {41, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 21},
                {41, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 21},
                {41, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 21},
                {41, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 21},
                {41, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 21},
                {41, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 23, 23, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 21},
                {41, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 21},
                {41, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 21},
                {81, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 23, 23, 23, 23, 23, 23, 23, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 91},
                {18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18},
                {18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18},
                {18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 23, 23, 23, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18},
                {71, 23, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 23, 23, 23, 23, 23, 23, 23, 61},
                {41, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 23, 23, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 21},
                {41, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 21},
                {41, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 23, 23, 23, 23, 23, 23, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 21},
                {41, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 21},
                {41, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 23, 23, 23, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 23, 23, 23, 18, 18, 18, 18, 18, 18, 21},

                //

                {4, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 2},
                {4, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 2},
                {4, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 22, 22, 22, 22, 14, 14, 14, 14, 22, 22, 22, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 2},
                {4, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 22, 22, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 2},
                {4, 14, 14, 14, 14, 22, 22, 22, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 22, 22, 22, 22, 14, 14, 14, 14, 14, 14, 2},
                {4, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 2},
                {4, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 2},
                {4, 14, 14, 14, 14, 14, 14, 14, 14, 22, 22, 22, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 22, 22, 2},
                {4, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 22, 22, 22, 22, 22, 14, 14, 14, 14, 14, 14, 2},
                {4, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 2},
                {4, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 22, 22, 22, 22, 22, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 2},
                {4, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 22, 22, 22, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 2},
                {4, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 2},
                {4, 14, 14, 14, 14, 14, 14, 22, 22, 22, 22, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 2},
                {4, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 22, 22, 22, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 2},
                {8, 14, 14, 22, 22, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 2},
                {14, 14, 14, 14, 14, 14, 22, 22, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 22, 22, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 2},
                {14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 2},
                {3, 7, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 6, 3},
                {1, 1, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 1, 1},

                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        };

        Sprite w = new Sprite(new Texture("blocks/brik2/f.png"));
        w.setColor(0.5f, 0.5f, 0.5f, 1);
        Sprite w2 = new Sprite(new Texture("blocks/block.png"));
        w2.setColor(0.5f, 0.5f, 0.5f, 1);
        for (int i = 0; i < 46; i++) {
            for (int j = 0; j < 40; j++) {
                switch (arr[i][j]){
                    case 1:
                        furniture.add(new Furniture(16+j*32, 16+(46-i)*32, new Sprite(new Texture("blocks/brik2/f.png"))));
                        break;
                    case 17:
                        furniture.add(new Furniture(16+j*32, 16+(46-i)*32, new Sprite(new Texture("blocks/block.png"))));
                        break;
                    case 2:
                        furniture.add(new Furniture(16+j*32, 16+(46-i)*32, new Sprite(new Texture("blocks/brik2/l.png"))));
                        break;
                    case 21:
                        furniture.add(new Furniture(16+j*32, 16+(46-i)*32, new Sprite(new Texture("blocks/blockl.png"))));
                        break;
                    case 3:
                        furniture.add(new Furniture(16+j*32, 16+(46-i)*32, new Sprite(new Texture("blocks/brik2/u.png"))));
                        break;
                    case 4:
                        furniture.add(new Furniture(16+j*32, 16+(46-i)*32, new Sprite(new Texture("blocks/brik2/r.png"))));
                        break;
                    case 41:
                        furniture.add(new Furniture(16+j*32, 16+(46-i)*32, new Sprite(new Texture("blocks/blockr.png"))));
                        break;
                    case 5:
                        furniture.add(new Furniture(16+j*32, 16+(46-i)*32, new Sprite(new Texture("blocks/brik2/d.png"))));
                        break;
                    case 51:
                        furniture.add(new Furniture(16+j*32, 16+(46-i)*32, new Sprite(new Texture("blocks/blockd.png"))));
                        break;
                    case 6:
                        furniture.add(new Furniture(16+j*32, 16+(46-i)*32, new Sprite(new Texture("blocks/brik2/lu.png"))));
                        break;
                    case 7:
                        furniture.add(new Furniture(16+j*32, 16+(46-i)*32, new Sprite(new Texture("blocks/brik2/ur.png"))));
                        break;
                    case 8:
                        furniture.add(new Furniture(16+j*32, 16+(46-i)*32, new Sprite(new Texture("blocks/brik2/rd.png"))));
                        break;
                    case 9:
                        furniture.add(new Furniture(16+j*32, 16+(46-i)*32, new Sprite(new Texture("blocks/brik2/ld.png"))));
                        break;
                    case 61:
                        furniture.add(new Furniture(16+j*32, 16+(46-i)*32, new Sprite(new Texture("blocks/blocklu.png"))));
                        break;
                    case 71:
                        furniture.add(new Furniture(16+j*32, 16+(46-i)*32, new Sprite(new Texture("blocks/blockur.png"))));
                        break;
                    case 81:
                        furniture.add(new Furniture(16+j*32, 16+(46-i)*32, new Sprite(new Texture("blocks/blockrd.png"))));
                        break;
                    case 91:
                        furniture.add(new Furniture(16+j*32, 16+(46-i)*32, new Sprite(new Texture("blocks/blockld.png"))));
                        break;
                    case 10:
                        furniture.add(new Furniture(16+j*32, 16+(46-i)*32, new Sprite(new Texture("blocks//brik2/lr.png"))));
                        break;
                    case 11:
                        furniture.add(new Furniture(16+j*32, 16+(46-i)*32, new Sprite(new Texture("blocks//brik2/ud.png"))));
                        break;
                    case 12:
                        furniture.add(new Furniture(16+j*32, 16+(46-i)*32, new Sprite(new Texture("blocks//brik2/lrd.png"))));
                        break;
                    case 13:
                        furniture.add(new Furniture(16+j*32, 16+(46-i)*32, new Sprite(new Texture("blocks//brik2/lur.png"))));
                        break;
                    case 14:
                        furniture.add(new Furniture(16+j*32, 16+(46-i)*32, new Sprite(w), false, true, false));
                        break;
                    case 16:
                        furniture.add(new Furniture(16+j*32, 16+(46-i)*32, new Sprite(new Texture("blocks/block.png"))));
                        break;
                    case 18:
                        furniture.add(new Furniture(16+j*32, 16+(46-i)*32, new Sprite(w2), false, true, false));
                        break;
                    case 22:
                        furniture.add(new Furniture(16+j*32, 16+(46-i)*32, new Sprite(w), false, true, false));
                        furniture.add(new Platform(16+j*32, 16+(46-i)*32));
                        break;
                    case 23:
                        furniture.add(new Furniture(16+j*32, 16+(46-i)*32, new Sprite(w2), false, true, false));
                        furniture.add(new Platform(16+j*32, 16+(46-i)*32));
                        break;
                }
            }
        }


        sprite = new Sprite(new Texture("dark.png"));
        Sprite d = new Sprite(new Texture("dark.png"));
        d.setSize(1280, 32);
        d.setColor(1, 1, 1, 1);
        closeFone.add(new Furniture(640, 144, new Sprite(d)));
        d.flip(false, true);
        closeFone.add(new Furniture(640, 736*2-16, new Sprite(d)));
        d.flip(false, true);
        d.setSize(36, 2000);
        d.rotate90(true);
        closeFone.add(new Furniture(16, 1000, new Sprite(d)));
        d.flip(true, true);
        closeFone.add(new Furniture(1280-16, 1000, new Sprite(d)));

        Sprite t = new Sprite(new Texture("blocks//brik2/truba.png"));
        t.setSize(32, 32);

        enemies.add(new GoblinMage(532, 1300));
    }

    @Override
    public Room create(){
        Room r = new Basement3();
        r.spawnX = spawnX;
        r.spawnY = spawnY;
        return r;
    }

    @Override
    public Room nextRoom(Player player) {
        if (player.x < 0){
            if(player.y > 500){
                Room nextRoom = new Basement4();
                nextRoom.spawnX = 1260;
                nextRoom.spawnY = 200;
                player.x = 1260;
                player.y = 200;
                return nextRoom;
            }else {
                Room nextRoom = new Basement2();
                nextRoom.spawnX = 1260;
                nextRoom.spawnY = 200;
                player.x = 1260;
                player.y = 200;
                return nextRoom;
            }
        }else if (player.x > 1270){
            Room nextRoom = new Room00();
            nextRoom.spawnX = 32;
            nextRoom.spawnY = 200;
            player.x = 32;
            player.y = 200;
            return nextRoom;
        }else {
            return null;
        }
    }
}