package com.mygdx.game.objects.creatures.heroes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.objects.attacks.Spell;
import com.mygdx.game.objects.attacks.effects.Effect;
import com.mygdx.game.objects.creatures.Creature;
import com.mygdx.game.objects.creatures.enemyes.Enemy;
import com.mygdx.game.objects.furnitures.Furniture;
import com.mygdx.game.objects.items.Item;
import com.mygdx.game.objects.room.Room;

import java.util.ArrayList;
import java.util.List;


public class Player extends Creature {

    public int spellNum;
    public int isShooting = 0;
    public int isShootingTime = 0;
    public byte willJump;
    public byte jumpPower = 0;
    public byte afterJumpTime = 0;
    public Spell[] spell;
    public Item[] item;
    public Item[] equipment;
    public ArrayList<Spell> spells;

    public Sprite charge1 = new Sprite(new Texture("charge/1.png"));
    public Sprite charge2 = new Sprite(new Texture("charge/2.png"));
    public Sprite charge3 = new Sprite(new Texture("charge/3.png"));
    public Sprite charge4 = new Sprite(new Texture("charge/4.png"));
    public Sprite aura = new Sprite(new Texture("aura.png"));
    public float chargingTime = 0;
    public boolean stop;

    private final Sound sound;

    public Player(float x, float y, float maxHp, float ms, float maxMp) {
        super(x, y, new Sprite(new Texture("heroes/mage/stay1.png")), maxHp, ms);
        this.maxMs = ms;
        this.mp = maxMp;
        this.maxMp = maxMp;
        this.right = true;
        this.spell = new Spell[8];
        this.item = new Item[8];
        this.equipment = new Item[8];
        this.spriteNum = 0;
        this.attack = false;
        this.spells = new ArrayList();

        charge1.setColor(1, 1, 1, 0.4f);
        charge2.setColor(1, 1, 1, 0.4f);
        charge3.setColor(1, 1, 1, 0.6f);
        charge4.setColor(1, 1, 1, 0.6f);
        aura.setColor(0.8f, 0.6f, 0.1f, 0.2f);
        aura.setSize(32, 42);
        aura.setOriginCenter();

        sound = Gdx.audio.newSound(Gdx.files.internal("ora.mp3"));
    }

    public void auras(ArrayList<Spell> bullets, SpriteBatch batch){
        for (Spell s: spell) {
            if(s != null){
                if(s.getClass().getSimpleName().equals("Aura")){
                    try {
                        if(s.getClass().getDeclaredField("activated").getBoolean(s)){
                            s.hitEffect(this);
                            aura.setOriginBasedPosition(x, y);
                            aura.draw(batch);
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (NoSuchFieldException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void live(Room room, SpriteBatch batch, ArrayList<Spell> bullets, float xp, float yp) {
        xPos = xp;
        yPos = yp;

        super.live(room, batch, bullets);
        if(!dead){
            auras(bullets, batch);
            if(!stop){
                move(bullets,room);
            }
            enemyCollision(room);

            if (mp < maxMp && !attack){
                mp += 0.5;
                if (mp > maxMp){
                    mp = maxMp;
                }
            }
            if (hp > maxHp){
                hp = maxHp;
            }

            for (Spell s:spell) {
                if(s != null){

                    if (s.reload > 0){
                        if(s.getClass().getSimpleName().equals("Dash") && jump && s.reload > s.reloadTime-2){

                        }else {
                            s.reload -= 1;
                        }
                    }
                }
            }
        }

        if(isShooting >= 0){
            isShootingTime++;
            if(isShootingTime > 50){
                isShooting = -1;
            }
        }

        chargingTime++;
        if(charged){
            if(chargingTime%10 > 4){
                charge3.setPosition(x-sprite.getWidth()/2-4, y-sprite.getHeight()/2);
                charge3.draw(batch);
            }else {
                charge4.setPosition(x-sprite.getWidth()/2-4, y-sprite.getHeight()/2);
                charge4.draw(batch);
            }
        }else if(isCharging){
            if(chargingTime%10 > 4){
                charge1.setPosition(x-sprite.getWidth()/2-4, y-sprite.getHeight()/2);
                charge1.draw(batch);
            }else {
                charge2.setPosition(x-sprite.getWidth()/2-4, y-sprite.getHeight()/2);
                charge2.draw(batch);
            }
        }

        for (int i = 0; i < 8; i++) {
            if(item[i] != null){
                if (item[i].quantity == 0){
                    item[i] = null;
                }
            }
        }
    }

    public void enemyCollision(Room room){
        if (!invincible && !damaged) {
            for (Enemy e: room.enemies) {
                if (Math.sqrt(e.delX*e.delX + e.delY*e.delY) < e.sprite.getWidth()-5){
                    e.playerCollision(this);
                    afterJumpTime = 10;
                }
            }
        }
    }

    private void move(List<Spell> bullets, Room room) {
        if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) && !jump){
            ms = 2.2f;
        }else {
            ms = maxMs;
        }

        if(!invincible){
            if (Gdx.input.isKeyPressed(Input.Keys.A)){
                if(!attack){
                    right = false;
                }
                if(jump){
                    xSpeed -= ms/15;
                    if(xSpeed < 0){
                        xSpeed *=1.10;
                    }
                }else {
                    xSpeed -= ms/10;
                    if(xSpeed < 0){
                        xSpeed *=1.15;
                    }
                    walk = true;
                }
            }

            if (Gdx.input.isKeyPressed(Input.Keys.D)){
                if(!attack){
                    right = true;
                }
                if(jump){
                    xSpeed += ms/15;
                    if(xSpeed > 0){
                        xSpeed *=1.10;
                    }
                }else {
                    xSpeed += ms/10;
                    if(xSpeed > 0){
                        xSpeed *=1.15;
                    }
                    walk = true;
                }
            }
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.S) && !jump){
            down = true;
        }

        if (!Gdx.input.isKeyPressed(Input.Keys.D) && !Gdx.input.isKeyPressed(Input.Keys.A)){
            walk = false;
        }

        if(jump){
            afterJumpTime++;
        }else {
            afterJumpTime = 0;
        }



        if (Gdx.input.isKeyJustPressed(Input.Keys.W)){
            willJump = 6;
        }if(willJump > 0){
            willJump --;
        }
        if ((willJump > 0  && !jump) || (Gdx.input.isKeyJustPressed(Input.Keys.W) && afterJumpTime < 6)){
            jump = true;
            fallSpeed = -8;
            willJump = 0;
            afterJumpTime = 10;
            jumpPower = 11;
        }

        if (jumpPower > 0){
            jumpPower--;
            for (Effect e: effects) {
                if(e.getClass().getSimpleName().equals("Push")){
                    jumpPower = 0;
                }
            }
        }

        if (Gdx.input.isKeyPressed(Input.Keys.W)){
            if(jumpPower > 0 && jumpPower <= 3){
                fallSpeed *= 1.3;
            }else if(jumpPower == 4){
                fallSpeed *= 1.15;
            }else if(jumpPower == 5){
                fallSpeed *= 1.1;
            }else if(jumpPower == 6){
                fallSpeed *= 1.05;
            }
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.P)){
            System.out.println(x);
            System.out.println(y);
        }

        if(spell[6] != null && (isShooting == 6 || isShooting == -1)){
            if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && !spell[6].singleSoot){
                shoot(bullets, 6, room);
            }else if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && spell[6].singleSoot){
                shoot(bullets, 6, room);
            }
        }

        if(spell[7] != null && (isShooting == 7 || isShooting == -1)){
            if (Gdx.input.isButtonPressed(Input.Buttons.RIGHT) && !spell[7].singleSoot){
                shoot(bullets, 7, room);
            }else if (Gdx.input.isButtonJustPressed(Input.Buttons.RIGHT) && spell[7].singleSoot){
                shoot(bullets, 7, room);
            }
        }

        if(spell[spellNum] != null && (isShooting == spellNum || isShooting == -1)){
            if (Gdx.input.isButtonPressed(Input.Buttons.LEFT) && !spell[spellNum].singleSoot){
                shoot(bullets, spellNum, room);

            }else if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && spell[spellNum].singleSoot){
                shoot(bullets, spellNum, room);

            }
            if(spell[spellNum].getClass().getSimpleName().equals("Fisting") && Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)){
                sound.play();
            }

            if (!Gdx.input.isButtonPressed(Input.Buttons.LEFT) || !spell[spellNum].getClass().getSimpleName().equals("Fisting")) {
                sound.stop();
            }
        }


        chooseSpell();
    }

    void choose(int i){
        if(spell[i] != null){
            if(spell[i].getClass().getSimpleName().equals("Aura")){
                spell[i].setParams(this);
            }else {
                spellNum = i;
                spell[spellNum].reload = 10;
            }
        }else if(item[i] != null){
            item[i].use(this);
        }
    }

    void chooseSpell(){
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_1)){
            choose(0);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_2)){
            choose(1);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_3)){
            choose(2);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_4)){
            choose(3);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_5)){
            choose(4);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_6)){
            choose(5);
        }
    }

    void shoot(List<Spell> bullets, int ii, Room room){
        spell[ii].damage *= damageBoost;

        isShooting = ii;

        right = Gdx.input.getX() + xPos > x;

        float getX = Gdx.input.getX() + xPos;
        float getY = Gdx.input.getY() - yPos;

        float ang = (float)Math.atan((getX - x) /((736 - getY) - y));

        if((spell[ii].reloadTime <= 10 || spell[ii].hold) && spriteNum == attackSprites.size()-1 &&  mp > spell[ii].mp){
            attackSprites.get(attackSprites.size()-1).time = attackSprites.get(attackSprites.size()-1).maxTime;
        }

        if (spell[ii].reload <= 0){
            if (mp >= spell[ii].mp || spell[ii].hold) {
                attack = true;

                if(spell[ii].hold){

                    //for telekinesis
                    spell[ii].rizX = x;
                    spell[ii].rizY = y;


                    spell[ii].setParams(this);
                    spell[ii].setParams(room);

                    Spell s1 = null;
                    if(mp > spell[ii].mp/200){
                        for (Spell s :bullets) {
                            if(s.getClass().getSimpleName().equals(spell[ii].getClass().getSimpleName())){
                                s1 = spell[ii];
                                s.time = 0;
                            }
                        }
                        if(s1 == null && mp > spell[ii].mp){
                            mp -= spell[ii].mp;
                            spell[ii].destroyed = false;
                            spell[ii].collision(room);
                            bullets.add(spell[ii]);
                        }else {
                            mp -= spell[ii].mp/200;
                        }
                    }else {
                        spell[ii].reload = 10;
                    }
                }else {
                    if(spell[ii].getClass().getSimpleName().equals("Dash")){
                        spell[ii].reload = spell[ii].reloadTime;
                    }else {
                        spell[ii].reload = spell[ii].reloadTime/atcSpeedBoost;
                    }

                    mp -= spell[ii].mp;
                    if (spell[ii].shoots > 1){
                        float riz = (float)((float)spell[ii].range/(float)spell[ii].shoots/180*3.14159);
                        ang -= riz*((float) spell[ii].shoots/2 + 0.5);
                        if (y > 736-Gdx.input.getY() + yPos){
                            ang += 3.1459;
                        }
                        for (int i = 0; i < spell[ii].shoots; i++) {
                            ang+=riz;

                            float  deltaX = (float)Math.sin(ang);
                            float  deltaY = (float)Math.cos(ang);
                            float  sX = x + 18*deltaX;
                            float  sY = y + 18*deltaY;

                            spell[ii].setParams(this);
                            spell[ii].setParams(room);

                            spell[ii].setParams(sX, sY, ang, deltaX, deltaY, false);

                            Spell spell = this.spell[ii].makeCopy();

                            if (spell.laser){
                                if(spriteNum == attackSprites.size()-1){
                                    attackSprites.get(attackSprites.size()-1).time = attackSprites.get(attackSprites.size()-1).maxTime;
                                }
                                Furniture collision = null;
                                for (int j = 1; j < 130; j++) {
                                    spell.sprites.get(0).sprite.setSize(3, 1200);
                                    collision = room.findCollision((float) (spell.x1 + j * spell.deltaX*10), (spell.y1 + j * spell.deltaY * 10));
                                    if(collision != null) {
                                        if(collision.breakable){
                                            collision.destroy(spell, room.furniture);
                                        }
                                        if (!collision.transitional || collision.breakable) {
                                            spell.sprites.get(0).sprite.setSize(3, (float) Math.sqrt((j * spell.deltaX*10)*(j * spell.deltaX*10) + (j * spell.deltaY * 10)*(j * spell.deltaY * 10))-5);
                                            break;
                                        }
                                    }
                                }
                            }else if(spell.getClass().getSimpleName().equals("Dash")){
                                spell.setParams(this);
                            }

                            bullets.add(spell);
                        }
                    }else {
                        ang-=(double)spell[ii].range/180*3.14159*(Math.random()*2 -1);
                        if (y > 736-Gdx.input.getY() + yPos){
                            ang += 3.1459;
                        }
                        float  deltaX = (float)Math.sin(ang);
                        float  deltaY = (float)Math.cos(ang);
                        float  sX = x + 18*deltaX;
                        float  sY = y + 18*deltaY;

                        spell[ii].setParams(this);
                        spell[ii].setParams(room);

                        spell[ii].setParams(sX, sY, ang, deltaX, deltaY, false);

                        Spell spell = this.spell[ii].makeCopy();

                        if (spell.laser){
                            if(spriteNum == attackSprites.size()-1){
                                attackSprites.get(attackSprites.size()-1).time = attackSprites.get(attackSprites.size()-1).maxTime;
                            }
                            Furniture collision = null;
                            for (int i = 1; i < 130; i++) {
                                spell.sprites.get(0).sprite.setSize(3, 1200);
                                collision = room.findCollision((float) (spell.x1 + i * spell.deltaX*10), (spell.y1 + i * spell.deltaY * 10));
                                if(collision != null) {
                                    if(collision.breakable){
                                        collision.destroy(spell, room.furniture);
                                    }
                                    if (!collision.transitional || collision.breakable) {
                                        spell.sprites.get(0).sprite.setSize(3, (float) Math.sqrt((i * spell.deltaX*10)*(i * spell.deltaX*10) + (i * spell.deltaY * 10)*(i * spell.deltaY * 10))-5);
                                        break;
                                    }
                                }
                            }
                        }else if(spell.getClass().getSimpleName().equals("Dash")){
                            spell.setParams(this);
                        }
                        bullets.add(spell);
                    }
                }
            }else {
                spell[ii].reload = 40;
            }
        }
        spell[ii].damage /= damageBoost;
    }
}
