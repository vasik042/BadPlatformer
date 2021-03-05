package com.mygdx.game.objects.room;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.objects.creatures.enemyes.Maggot;
import com.mygdx.game.objects.creatures.heroes.Player;
import com.mygdx.game.objects.furnitures.Furniture;
import com.mygdx.game.objects.furnitures.traps.Spike;

public class Basement2 extends Room {
    public Basement2() {
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


        byte[][] arr = {
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},

                {1, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 1},
                {4, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 2},
                {4, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 2},
                {4, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 2},
                {4, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 2},
                {8, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 9},
                {14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14},
                {14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14},
                {3, 7, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 6, 3},
                {1, 1, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 1, 1},

                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        };

        Sprite w = new Sprite(new Texture("blocks//brik2/f.png"));
        w.setColor(0.5f, 0.5f, 0.5f, 1);
        for (int i = 0; i < 23; i++) {
            for (int j = 0; j < 40; j++) {
                switch (arr[i][j]){
                    case 1:
                        furniture.add(new Furniture(16+j*32, 16+(23-i)*32, new Sprite(new Texture("blocks//brik2/f.png"))));
                        break;
                    case 2:
                        furniture.add(new Furniture(16+j*32, 16+(23-i)*32, new Sprite(new Texture("blocks//brik2/l.png"))));
                        break;
                    case 3:
                        furniture.add(new Furniture(16+j*32, 16+(23-i)*32, new Sprite(new Texture("blocks//brik2/u.png"))));
                        break;
                    case 4:
                        furniture.add(new Furniture(16+j*32, 16+(23-i)*32, new Sprite(new Texture("blocks//brik2/r.png"))));
                        break;
                    case 5:
                        furniture.add(new Furniture(16+j*32, 16+(23-i)*32, new Sprite(new Texture("blocks//brik2/d.png"))));
                        break;
                    case 6:
                        furniture.add(new Furniture(16+j*32, 16+(23-i)*32, new Sprite(new Texture("blocks//brik2/lu.png"))));
                        break;
                    case 7:
                        furniture.add(new Furniture(16+j*32, 16+(23-i)*32, new Sprite(new Texture("blocks//brik2/ur.png"))));
                        break;
                    case 8:
                        furniture.add(new Furniture(16+j*32, 16+(23-i)*32, new Sprite(new Texture("blocks//brik2/rd.png"))));
                        break;
                    case 9:
                        furniture.add(new Furniture(16+j*32, 16+(23-i)*32, new Sprite(new Texture("blocks//brik2/ld.png"))));
                        break;
                    case 10:
                        furniture.add(new Furniture(16+j*32, 16+(23-i)*32, new Sprite(new Texture("blocks//brik2/lr.png"))));
                        break;
                    case 11:
                        furniture.add(new Furniture(16+j*32, 16+(23-i)*32, new Sprite(new Texture("blocks//brik2/ud.png"))));
                        break;
                    case 12:
                        furniture.add(new Furniture(16+j*32, 16+(23-i)*32, new Sprite(new Texture("blocks//brik2/lrd.png"))));
                        break;
                    case 13:
                        furniture.add(new Furniture(16+j*32, 16+(23-i)*32, new Sprite(new Texture("blocks//brik2/lur.png"))));
                        break;
                    case 14:
                        furniture.add(new Furniture(16+j*32, 16+(23-i)*32, new Sprite(w), false, true, false));
                        if(23 - i == 5){
                            furniture.add(new Spike(16+j*32, 16+(23-i)*32));
                        }
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
        closeFone.add(new Furniture(640, 432, new Sprite(d)));
        d.flip(false, true);
        d.setSize(36, 800);
        d.rotate90(true);
        closeFone.add(new Furniture(16, 400, new Sprite(d)));
        d.flip(true, true);
        closeFone.add(new Furniture(1280-16, 400, new Sprite(d)));

        Sprite t = new Sprite(new Texture("blocks//brik2/truba.png"));
        t.setSize(32, 32);

        furniture.add(new Furniture(160, 16+200, new Sprite(t), true, false, false));
        furniture.add(new Furniture(320, 16+200, new Sprite(t), true, false, false));
        furniture.add(new Furniture(480, 16+200, new Sprite(t), true, false, false));
        furniture.add(new Furniture(640, 16+200, new Sprite(t), true, false, false));
        furniture.add(new Furniture(800, 16+200, new Sprite(t), true, false, false));
        furniture.add(new Furniture(960, 16+200, new Sprite(t), true, false, false));
        furniture.add(new Furniture(1120, 16+200, new Sprite(t), true, false, false));


//        Spike s1 = new Spike(400, 208);
//        Spike s2 = new Spike(432, 208);
//        Spike s3 = new Spike(464, 208);
//        Spike s4 = new Spike(498, 208);
//        Spike s5 = new Spike(530, 208);
//        Spike s6 = new Spike(562, 208);
//        Spike s7 = new Spike(594, 208);
//        Spike s8 = new Spike(626, 208);
//        Spike s9 = new Spike(658, 208);
//        Spike s10 = new Spike(690, 208);


//        s1.sprites.get(0).time = 80;
//        s2.sprites.get(0).time = 60;
//        s3.sprites.get(0).time = 40;
//        s4.sprites.get(0).time = 20;
//        s5.sprites.get(0).time = 0;
//        s6.spriteNum = 1;
//        s6.sprites.get(1).time = 40;
//
//        s7.spriteNum = 1;
//        s7.sprites.get(1).time = 20;
//
//        s8.spriteNum = 1;
//
//        s9.sprites.get(0).time = 60;
//        s10.sprites.get(0).time = 40;
//
//        furniture.add(s1);
//        furniture.add(s2);
//        furniture.add(s3);
//        furniture.add(s4);
//        furniture.add(s5);
//        furniture.add(s6);
//        furniture.add(s7);
//        furniture.add(s8);
//        furniture.add(s9);
//        furniture.add(s10);
    }

    @Override
    public Room create(){
        Room r = new Basement2();
        r.spawnX = spawnX;
        r.spawnY = spawnY;
        return r;
    }

    @Override
    public Room nextRoom(Player player) {
        if (player.x < 0){
            Room nextRoom = new Basement1();
            nextRoom.spawnX = 1260;
            nextRoom.spawnY = 200;
            player.x = 1260;
            player.y = 200;
            return nextRoom;
        }else if (player.x > 1270){
            Room nextRoom = new Basement3();
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
