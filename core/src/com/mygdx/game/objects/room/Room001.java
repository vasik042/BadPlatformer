package com.mygdx.game.objects.room;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.objects.creatures.enemyes.Bat;
import com.mygdx.game.objects.creatures.enemyes.Enemy;
import com.mygdx.game.objects.creatures.enemyes.EnemyMage;
import com.mygdx.game.objects.creatures.enemyes.Eye;
import com.mygdx.game.objects.creatures.heroes.Player;
import com.mygdx.game.objects.furnitures.Furniture;
import com.mygdx.game.objects.furnitures.breaks.BreakBlock;

public class Room001 extends Room{

    public Room001() {
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
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},

                {1, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 1},

                {4, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 2},
                {4, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 2},
                {4, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 2},
                {4, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 2},
                {8, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 2},
                {14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 9},
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
        closeFone.add(new Furniture(640, 464, new Sprite(d), false, false, false));
        d.flip(false, true);
        d.setSize(36, 800);
        d.rotate90(true);
        closeFone.add(new Furniture(16, 400, new Sprite(d), false, false, false));
        d.flip(true, true);
        closeFone.add(new Furniture(1264, 400, new Sprite(d), false, false, false));

        for (int i = 0; i < 20; i++) {
            if(i%6 == 0 || i%6 == 1 || i%6 == 2){
                furniture.add(new BreakBlock(i * 32 + 320, 272, new Sprite(new Texture("blocks/block1.png")), new Sprite(new Texture("blocks/block1broke.png")), false, false, 100));
            }else {
                furniture.add(new BreakBlock(i * 32 + 320, 272+96, new Sprite(new Texture("blocks/block1.png")), new Sprite(new Texture("blocks/block1broke.png")), false, false, 100));
            }
        }



        Enemy enemy = new EnemyMage(600, 304);
        enemy.right = true;

        enemies.add(enemy);
    }

    @Override
    public Room create(){
        Room r = new Room001();
        r.spawnX = spawnX;
        r.spawnY = spawnY;
        return r;
    }

    @Override
    public Room nextRoom(Player player) {
        if (player.x > -10 && player.x < 10){
            Room nextRoom = new Room000();
            nextRoom.spawnX = 1250;
            nextRoom.spawnY = 200;
            player.x = 1250;
            player.y = 200;
            return nextRoom;
        }
        if(player.x > 1270){
            Room nextRoom = new Room002();
            nextRoom.spawnX = 64;
            nextRoom.spawnY = 200;
            player.x = 64;
            player.y = 200;
            return nextRoom;
        }else {
            return null;
        }
    }
}
