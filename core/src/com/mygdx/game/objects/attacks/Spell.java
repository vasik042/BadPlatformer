package com.mygdx.game.objects.attacks;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.objects.AnimationSprite;
import com.mygdx.game.objects.attacks.effects.Effect;
import com.mygdx.game.objects.creatures.Creature;
import com.mygdx.game.objects.furnitures.Furniture;
import com.mygdx.game.objects.room.Room;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;

public class Spell {
    public Sprite light;
    public float ms;
    public float lifeTime;
    public boolean enemy;
    public float damage;
    public float size;
    public float x1;
    public float y1;
    public float deltaX;
    public float deltaY;
    public float rizX;
    public float rizY;

    public String name = "name";
    public String description = "description";

    public Sprite sprite;
    public ArrayList<AnimationSprite> sprites;
    public float r =1;
    public float g =1;
    public float b =1;

    public int spriteNum;
    public Sprite icon = new Sprite(new Texture("spellIcon.png"));
    public Sprite iconDark = new Sprite(new Texture("fDark.png"));
    public float ang;
    public int shoots;
    public int sootType;
    public boolean singleSoot;
    public float time = 0;
    public float reload = 0;
    public float reloadTime = 0;
    public float mp = 0;
    public int range;
    public ArrayList<Effect> effects;
    public boolean destroyed = false;
    public boolean peirce = false;
    public float peirceTime = 0;
    public boolean laser = false;
    public boolean hold = false;
    public boolean isHold = false;
    public boolean aura = false;
    public boolean dash = false;
    public boolean melee = false;
    public byte collisionType;
    public byte collisionEffect;

    public int jump;
    public int areJump;
    public boolean wasJump;

    public Creature creature;
    public ArrayList<Creature> damagedCreatures;
    public float range2;

    public boolean swap = true;
    public boolean isSwapped = false;

    public double sin;


    public boolean fall = false;
    public float fallSpeed = 0;

    public boolean stop = false;
    public float stopDX = 0;
    public float stopDY = 0;
    public boolean isStopped = false;
    public boolean unique = false;
    public Spell endSpell = null;
    public Spell midSpell = null;

    public boolean missless = false;
    public float misslessMs = 10;

    public Spell(){
        this.effects = new ArrayList<>(1);
        this.time = 0;
    }


    public Spell(float ms, float lifeTime, float damage, float size, Sprite sprite, int shoots, int sootType ,boolean singleSoot, int range, float mp, float reloadTime, byte collisionType, byte collisionEffect) {
        this.ms = ms;
        this.lifeTime = lifeTime;
        this.damage = damage;
        this.size = size;
        this.sprite = sprite;
        this.shoots = shoots;
        this.sootType = sootType;
        this.singleSoot = singleSoot;
        this.range = range;
        this.effects = new ArrayList<>(1);
        this.sprites = new ArrayList<>();
        this.damagedCreatures = new ArrayList<>();
        this.mp = mp;
        this.reloadTime = reloadTime;

        this.collisionType = collisionType;
        this.collisionEffect = collisionEffect;

        this.light = new Sprite(new Texture("light.png"));
        this.light.setSize(sprite.getWidth()*2f, sprite.getHeight()*2f);
        this.light.setColor(1, 0.8f, 0, 0.1f);
        this.light.setScale(size);
        this.light.setOriginCenter();

        this.iconDark.setOrigin(24, 24);
        this.iconDark.setColor(1, 1, 1, 0.2f);
    }

    public Spell makeCopy() {
        Spell spell = new Spell();
        try {
            spell = this.getClass().getConstructor().newInstance();
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }

        spell.ms = this.ms;
        spell.lifeTime = this.lifeTime;
        spell.damage = this.damage;
        spell.size = this.size;

        spell.sprites = new ArrayList<>();
        for (AnimationSprite s: this.sprites) {
            AnimationSprite animationSprite = new AnimationSprite(s.maxTime, s.sprite.getTexture());
            animationSprite.sprite.setFlip(s.sprite.isFlipX(), s.sprite.isFlipY());
            animationSprite.sprite.setOrigin(s.sprite.getOriginX(), s.sprite.getOriginY());
            spell.sprites.add(animationSprite);
        }

        spell.r = this.r;
        spell.g = this.g;
        spell.b = this.b;

        spell.shoots = this.shoots;
        spell.sootType = this.sootType;
        spell.singleSoot = this.singleSoot;
        spell.range = this.range;
        spell.effects = new ArrayList<>();
        spell.effects.addAll(this.effects);
        spell.damagedCreatures = new ArrayList<>();
        spell.mp = this.mp;
        spell.reloadTime = this.reloadTime;
        spell.icon = new Sprite(this.icon);

        spell.enemy = this.enemy;
        spell.x1 = this.x1;
        spell.y1 = this.y1;
        spell.deltaX = this.deltaX;
        spell.deltaY = this.deltaY;

        spell.rizX  = this.rizX;
        spell.rizY  = this.rizY;

        spell.ang = this.ang;
        spell.time = 0;

        spell.destroyed = false;
        spell.peirce = this.peirce;
        spell.laser = this.laser;
        spell.hold = this.hold;
        spell.isHold = this.isHold;
        spell.aura = this.aura;
        spell.dash = this.dash;
        spell.melee = this.melee;
        spell.isStopped = this.isStopped;
        spell.collisionType = this.collisionType;
        spell.collisionEffect = this.collisionEffect;

        spell.jump = this.jump;
        spell.areJump = 0;
        spell.wasJump = this.wasJump;

        spell.creature = this.creature;
        spell.range2 = this.range2;

        spell.swap = this.swap;
        spell.isSwapped = this.isSwapped;

//        spell.sin = this.sin;


        spell.fall = this.fall;
        spell.fallSpeed = this.fallSpeed;
        spell.stop = false;
        spell.missless = this.missless;

        if(this.endSpell != null){
            spell.endSpell = this.endSpell.makeCopy();
        }
        if(this.midSpell != null){
            spell.midSpell = this.midSpell.makeCopy();
        }

        spell.name = this.name;
        spell.description = this.description;

        if(isSwapped){
            for (AnimationSprite s: spell.sprites) {
                s.sprite.setFlip(!swap, false);
            }
        }
        if(shoots == 1 && isSwapped){
            this.swap = !swap;
        }

        for (AnimationSprite s: spell.sprites) {
            s.sprite.setScale(size);
        }

//        for (AnimationSprite s:spell.sprites) {
//            if(enemy && (deltaX == 1 || deltaX == -1)){
//                s.sprite.setRotation((float) (this.ang*180/3.1415));
//            }else {
//                s.sprite.setRotation((float) (this.ang*180/3.1415)* (-1));
//            }
//        }

        for (AnimationSprite s:spell.sprites) {
            s.sprite.setRotation((float) (this.ang*180/3.1415)* (-1));
        }

        spell.sprite = this.sprites.get(0).sprite;

        return spell;
    }

    public void setParams(float x1, float y1, float ang, float deltaX, float deltaY, boolean enemy){
        this.x1 = x1;
        this.y1 = y1;
        this.enemy = enemy;
        this.ang = ang;
        this.deltaX = deltaX;
        this.deltaY = deltaY;
        this.sprite.setOriginBasedPosition((int)this.x1, (int)this.y1);
        if(this.fall){
            this.deltaY *=2;
        }
    }

    public void setParams(Room room){
    }

    public void setParams(Creature creature){
        this.creature = creature;
    }

    public void live(SpriteBatch batch, Room room, ArrayList<Spell> addBullets){
        animation();
        collisionEffect(room);
        draw(batch, addBullets);
        time ++;

        for (Effect e:effects) {
            if (e.getClass().getSimpleName().equals("FireEffect")){
                if(sootType != 8 || !fall){
                    light.setRotation((float) (this.ang*180/3.1415)* (-1));
                }
                light.setOriginBasedPosition(x1, y1);
                light.draw(batch);
                break;
            }
        }

        if(peirce && damagedCreatures.size() > 0){
            peirceTime++;
            if(peirceTime > 4){
                peirceTime = 0;
                damagedCreatures.removeAll(damagedCreatures);
            }
        }

        fallRotate();
    }

    public void fallRotate() {
        if((fall || jump>0) && !stop){
            for (AnimationSprite s:sprites) {
                float dY = deltaY;
                if(dY > 1){
                    dY = 1;
                }else if(dY < -1){
                    dY = -1;
                }
                float ang2 = (float) Math.acos(dY);
                if (ang2 <0 || ang2 >= 0){
                    if(deltaX < 0){
                        s.sprite.setRotation((float) (ang2*180/3.1415)* (1));
                    }else {
                        s.sprite.setRotation((float) (ang2*180/3.1415)* (-1));
                    }
                }
            }
        }
    }

    public boolean collision(Room room) {
        boolean isColl = false;
        switch (collisionType){
            case 1:
                //full collision round
                Furniture collision1 = room.findCollision(x1 + sprite.getWidth()*size/2 - 2*size, y1 +sprite.getHeight()*size/2 - 2*size);
                Furniture collision2 = room.findCollision(x1 + sprite.getWidth()*size/2 - 2*size, y1 -sprite.getHeight()*size/2 + 2*size);
                Furniture collision3 = room.findCollision(x1 - sprite.getWidth()*size/2 + 2*size, y1 +sprite.getHeight()*size/2 - 2*size);
                Furniture collision4 = room.findCollision(x1 - sprite.getWidth()*size/2 + 2*size, y1 -sprite.getHeight()*size/2 + 2*size);


                if(collision1 != null){
                    if(collision1.breakable){
                        collision1.destroy(this, room.furniture);
                        if(!peirce){
                            isColl = true;
                        }
                    }
                    if (!collision1.transitional){
                        isColl = true;
                    }
                }if (collision2 != null) {
                    if (collision2.breakable && collision2 != collision1) {
                        collision2.destroy(this, room.furniture);
                        if (!peirce) {
                            isColl = true;
                        }
                    }
                    if (!collision2.transitional) {
                        isColl = true;
                    }
                }if (collision3 != null){
                    if(collision3.breakable && collision3 != collision1 && collision3 != collision2){
                        collision3.destroy(this, room.furniture);
                        if(!peirce){
                            isColl = true;
                        }
                    }
                    if (!collision3.transitional){
                        isColl = true;
                    }
                }if (collision4 != null){
                    if(collision4.breakable && collision4 != collision1 && collision4 != collision2 && collision4 != collision3){
                        collision4.destroy(this, room.furniture);
                        if(!peirce){
                            isColl = true;
                        }
                    }
                    if (!collision4.transitional){
                        isColl = true;
                    }
                }
                return isColl;
            case 2:
                // arrow
                Furniture collision21 = room.findCollision(x1 + sprite.getHeight()*size*deltaX/2, y1 +sprite.getHeight()*size*deltaY/2);

                if(collision21 != null){
                    if(collision21.breakable){
                        collision21.destroy(this, room.furniture);
                    }
                    if (!collision21.transitional){
                        isColl = true;
                    }
                }
                return isColl;
            case 3:

                if(ms > 8){
                    ms = 8;
                }
                // full jump
                Furniture collisionR1 = room.findCollision(x1 + sprite.getWidth()*size/2 + ms*deltaX, y1 +sprite.getHeight()*size/2 - 1);
                Furniture collisionR2 = room.findCollision(x1 + sprite.getWidth()*size/2 + ms*deltaX, y1 -sprite.getHeight()*size/2 + 1);
                Furniture collisionL1 = room.findCollision(x1 - sprite.getWidth()*size/2 + ms*deltaX, y1 +sprite.getHeight()*size/2 - 1);
                Furniture collisionL2 = room.findCollision(x1 - sprite.getWidth()*size/2 + ms*deltaX, y1 -sprite.getHeight()*size/2 + 1);

                Furniture collisionU1 = room.findCollision(x1 + sprite.getWidth()*size/2 - 1, y1 +sprite.getHeight()*size/2 + ms*deltaY);
                Furniture collisionU2 = room.findCollision(x1 - sprite.getWidth()*size/2 + 1, y1 +sprite.getHeight()*size/2 + ms*deltaY);
                Furniture collisionD1 = room.findCollision(x1 + sprite.getWidth()*size/2 - 1, y1 -sprite.getHeight()*size/2 + ms*deltaY);
                Furniture collisionD2 = room.findCollision(x1 - sprite.getWidth()*size/2 + 1, y1 -sprite.getHeight()*size/2 + ms*deltaY);

                wasJump = false;

                if(collisionU1 != null){
                    if(collisionU1.breakable && !wasJump){
                        collisionU1.destroy(this, room.furniture);
                    }
                    if(!collisionU1.transitional || collisionU1.breakable){
                        wasJump = true;
                        y1 = collisionU1.y - collisionU1.spriteHeight/2 - sprite.getHeight()*size/2-1;
                        if(deltaY >= 0){
                            deltaY *= -1;
                        }
                    }
                }
                if(collisionU2 != null){
                    if(collisionU2.breakable && !wasJump){
                        collisionU2.destroy(this, room.furniture);
                    }
                    if(!collisionU2.transitional || collisionU2.breakable) {
                        wasJump = true;
                        y1 = collisionU2.y - collisionU2.spriteHeight / 2 - sprite.getHeight() * size/2 - 1;
                        if (deltaY >= 0) {
                            deltaY *= -1;
                        }
                    }
                }

                if(collisionD1 != null){
                    if(collisionD1.breakable && !wasJump){
                        collisionD1.destroy(this, room.furniture);
                    }
                    if(!collisionD1.transitional || collisionD1.breakable) {
                        wasJump = true;
                        y1 = collisionD1.y + collisionD1.spriteHeight/2 + sprite.getHeight()*size/2+1;
                        if(deltaY < 0){
                            deltaY *= -1;
                        }
                    }

                }
                if(collisionD2 != null){
                    if(collisionD2.breakable && !wasJump){
                        collisionD2.destroy(this, room.furniture);
                    }
                    if(!collisionD2.transitional || collisionD2.breakable) {
                        wasJump = true;
                        y1 = collisionD2.y + collisionD2.spriteHeight/2 + sprite.getHeight()*size/2+1;
                        if(deltaY < 0){
                            deltaY *= -1;
                        }
                    }
                }

                if(collisionL1 != null){
                    if(collisionL1.breakable && !wasJump){
                        collisionL1.destroy(this, room.furniture);
                    }
                    if(!collisionL1.transitional || collisionL1.breakable) {
                        wasJump = true;
                        x1 = collisionL1.x + collisionL1.spriteWidth/2 + sprite.getWidth()*size/2+1;
                        if(deltaX <= 0){
                            deltaX *= -1;
                        }
                    }
                }
                if(collisionL2 != null){
                    if(collisionL2.breakable && !wasJump){
                        collisionL2.destroy(this, room.furniture);
                    }
                    if(!collisionL2.transitional || collisionL2.breakable) {
                        wasJump = true;
                        x1 = collisionL2.x + collisionL2.spriteWidth/2 + sprite.getWidth()*size/2+1;
                        if(deltaX <= 0){
                            deltaX *= -1;
                        }
                    }
                }

                if(collisionR1 != null && !collisionR1.fone){
                    if(collisionR1.breakable && !wasJump){
                        collisionR1.destroy(this, room.furniture);
                    }
                    if(!collisionR1.transitional || collisionR1.breakable) {
                        wasJump = true;
                        x1 = collisionR1.x - collisionR1.spriteWidth/2 - sprite.getWidth()*size/2+1;
                        if(deltaX > 0){
                            deltaX *= -1;
                        }
                    }
                }
                if(collisionR2 != null && !collisionR2.fone){
                    if(collisionR2.breakable && !wasJump){
                        collisionR2.destroy(this, room.furniture);
                    }
                    if(!collisionR2.transitional || collisionR2.breakable) {
                        wasJump = true;
                        x1 = collisionR2.x - collisionR2.spriteWidth/2 - sprite.getWidth()*size/2+1;
                        if(deltaX > 0){
                            deltaX *= -1;
                        }
                    }
                }

                if(wasJump){
                    areJump++;
                }

                if(areJump >= jump){
                    destroyed = true;
                }
                return wasJump;
            case 4:
                //EXUPULOTION!
                if(time < 2){
                    ArrayList<Furniture> collision = room.findCollision(x1, y1, sprite.getWidth()*size, true);
                    for (Furniture f :collision) {
                        if(f.breakable){
                            f.destroy(this, room.furniture);
                        }
                    }
                }
                return false;
            case 5:
                // end of sprite
                Furniture collision51 = room.findCollision(x1 + sprite.getHeight()*size*deltaX - sprite.getOriginX()*deltaX, y1 +sprite.getHeight()*size*deltaY - sprite.getOriginY()*deltaY);

                if(collision51 != null){
                    if(collision51.breakable){
                        collision51.destroy(this, room.furniture);
                    }
                    if (!collision51.transitional){
                        isColl = true;
                    }
                }
                return isColl;
            case 6:
                //line
                float damage1 = damage;
                damage /= lifeTime;

                Furniture collision61 = room.findCollision(x1 + sprite.getHeight()*size*deltaX - sprite.getOriginX()*deltaX, y1 + sprite.getHeight()*size*deltaY- sprite.getOriginY()*deltaY);
                Furniture collision62 = room.findCollision(x1 + sprite.getHeight()*size*deltaX*2/3 - sprite.getOriginX()*deltaX, y1 + sprite.getHeight()*size*deltaY*2/3- sprite.getOriginY()*deltaY);
                Furniture collision63 = room.findCollision(x1 + sprite.getHeight()*size*deltaX/3 - sprite.getOriginX()*deltaX, y1 + sprite.getHeight()*size*deltaY/3- sprite.getOriginY()*deltaY);
                Furniture collision64 = room.findCollision(x1 - sprite.getOriginX()*deltaX, y1- sprite.getOriginY()*deltaY);


                if(collision61 != null){
                    if(collision61.breakable){
                        collision61.destroy(this, room.furniture);
                    }
                }if (collision62 != null){
                    if(collision62.breakable && collision62 != collision61){
                        collision62.destroy(this, room.furniture);
                    }
                    }if (collision63 != null){
                        if(collision63.breakable && collision63 != collision61 && collision63 != collision62){
                        collision63.destroy(this, room.furniture);
                    }
                }if (collision64 != null){
                    if(collision64.breakable && collision64 != collision61 && collision64 != collision62 && collision64 != collision63){
                        collision64.destroy(this, room.furniture);
                    }
                }
                damage = damage1;
                return false;
            default:
                return false;
        }
    }

    public boolean collisionEffect(Room room){
        boolean collision = collision(room);
        if(collision){
            switch (collisionEffect) {
                case 1:
                    destroyed = true;
                    break;
                case 2:
                    damage = 0;
                    break;
                case 3:
                    sin = -1;
                    damage = 0;
                    effects.removeAll(effects);
                    break;
                case 4:
                    damage = 0;
                    effects.removeAll(effects);
                    stop = true;
                    break;
            }
        }
        return collision;
    }

    public void animation(){
        if(spriteNum < sprites.size()){
            if (sprites.get(spriteNum).time < sprites.get(spriteNum).maxTime){
                sprite = sprites.get(spriteNum).sprite;
                sprites.get(spriteNum).time++;
            }else {
                sprites.get(spriteNum).time = 0;
                spriteNum++;
            }
        }else {
            spriteNum = 0;
            sprite = sprites.get(spriteNum).sprite;
            sprites.get(spriteNum).time = 0;
        }

        sprite.setColor(r, g, b, 1);
    }

    public void draw(SpriteBatch batch, ArrayList<Spell> addBullets){
        if(missless){
            float mX;
            float mY;
            if(!enemy){
                mX = Gdx.input.getX() +  + creature.xPos;
                mY = 736 - Gdx.input.getY() + creature.yPos;
            }else {
                mX = creature.x;
                mY = creature.y;
            }

            ang = (float)Math.atan((mX - x1) /(mY - y1));

            if (y1 > mY){
                ang += 3.1459;
            }

            float dX = (float)Math.sin(ang);
            float dY = (float)Math.cos(ang);

            deltaX = (deltaX*(200/misslessMs-1) + dX)/(200/misslessMs);
            deltaY = (deltaY*(200/misslessMs-1) + dY)/(200/misslessMs);

            for (AnimationSprite s: sprites) {
                if(enemy && (deltaX == 1 || deltaX == -1)){
                    s.sprite.setRotation((float) (this.ang*180/3.1415));
                }else {
                    s.sprite.setRotation((float) (this.ang*180/3.1415)* (-1));
                }
            }
        }


        switch (sootType){
            case -1:
                break;
            case 1:
                rizX = deltaX;
                rizY = deltaY;

                x1 += rizX*ms;
                y1 += rizY*ms;
                break;
            case 2:
                rizX = deltaX + (float) (Math.cos((time*20)*20/180*3.14159 + ang)*0.8*deltaY*deltaY)*ms;
                rizY = deltaY + (float) (Math.sin((time*20)*20/180*3.14159 - ang)*0.8*deltaX*deltaX)*ms;

                x1 += rizX*ms;
                y1 += rizY*ms;
                break;
            case 3:
                rizX = deltaX + (float )(Math.sin((time*30)*20/180*3.14159)+Math.sin((float)(time*30)/180*3.14159)*time/10);
                rizY = deltaY + (float) (Math.cos((time*30)*20/180*3.14159)+Math.cos((float)(time*30)/180*3.14159)*time/10);

                x1 += rizX*ms;
                y1 += rizY*ms;
                break;
            case 5:
                //dash
                creature.fly = false;
                creature.invincibleTime = 5;
                creature.fallSpeed = -deltaY*ms;
                creature.xSpeed = deltaX*ms;


                x1 += deltaX*ms;
                y1 += deltaY*ms;
                break;
            case 6:
                //sword
                int getX2 = (int) (Gdx.input.getX() + creature.xPos);
                int getY2 = (int) (736 - Gdx.input.getY() + creature.yPos);

                ang = (float)Math.atan((getX2 - creature.x) /(getY2 - creature.y));

                if (creature.y > getY2){
                    ang += 3.1459;
                }

                if(isSwapped){
                    ang += range2;
                    if(swap){
                        ang -= (lifeTime/2-time)/10;
                    }else {
                        ang += (lifeTime/2-time)/10;
                    }
                }


                deltaX = (float)Math.sin(ang);
                deltaY = (float)Math.cos(ang);

                for (AnimationSprite s: sprites) {
                    if(enemy && (deltaX == 1 || deltaX == -1)){
                        s.sprite.setRotation((float) (this.ang*180/3.1415));
                    }else {
                        s.sprite.setRotation((float) (this.ang*180/3.1415)* (-1));
                    }
                }

                if(midSpell != null && time == (int)lifeTime/2 && !hold){
                    Spell spell = midSpell.makeCopy();
                    spell.deltaX = deltaX;
                    spell.deltaY = deltaY;
                    spell.ang = ang;
                    spell.x1 = x1;
                    spell.y1 = y1;
                    addBullets.add(spell);
                }

                x1 = creature.x + deltaX*32;
                y1 = creature.y + deltaY*32;
                break;
            case 7:
                //spear
                if(sin >= -0.99){
                    sin = Math.sin(time/4);
                }

                if(time == 0){
                    x1 = creature.x + deltaX*32;
                    y1 = creature.y + deltaY*32;
                }

                if(sin <= -0.5){
                    castSpell(addBullets);
                    midSpell = null;
                }

                if(sin <= -0.99) {

                    ang = ((float)Math.atan((x1 - creature.x) /(y1 - creature.y)));
                    if (creature.y > y1){
                        ang += 3.1459;
                    }

                    damage = 0;

                    if(Math.sqrt((x1 - creature.x)*(x1 - creature.x) + (y1 - creature.y)*(y1 - creature.y)) < 32){
                        destroyed = true;
                    }


                    deltaX = (float)Math.sin(ang);
                    deltaY = (float)Math.cos(ang);

                }
                for (AnimationSprite s: sprites) {
                    if(enemy && (deltaX == 1 || deltaX == -1)){
                        s.sprite.setRotation((float) (this.ang*180/3.1415));
                    }else {
                        s.sprite.setRotation((float) (this.ang*180/3.1415)* (-1));
                    }
                }

                x1 += deltaX*sin*ms*3;
                y1 += deltaY*sin*ms*3;

                break;
            case 8:
                //arrow
                if(!stop){
                    if(fall){
                        double asin;
                        if(deltaY < -0.8){
                            asin = Math.asin(-0.8);
                        }else {
                            asin = Math.asin(deltaY);
                        }
                        if(deltaX > 0){
                            sprites.get(0).sprite.setRotation((float) (asin/3.1459*180)-90);
                            light.setRotation((float) (asin/3.1459*180)-90);
                        }else {
                            sprites.get(0).sprite.setRotation((float) -(asin/3.1459*180)+90);
                            light.setRotation((float) -(asin/3.1459*180)+90);
                        }
                    }

                    rizX = deltaX;
                    rizY = deltaY;

                    x1 += rizX*ms;
                    y1 += rizY*ms;
                }

                if(creature != null){
                    sprite.setOriginBasedPosition(creature.x + stopDX, creature.y + stopDY);
                }else {
                    sprite.setOriginBasedPosition(x1, y1);
                }

                if (time > 100){
                    sprites.get(0).sprite.setColor(1, 1, 1, (lifeTime-time)*0.02f);
                }

                sprite.draw(batch);
            case 9:
                //arrow Shower
                if(!stop){

                    rizX = deltaX;
                    rizY = deltaY;

                    x1 += rizX*ms;
                    y1 += rizY*ms;
                }

                sprite.setOriginBasedPosition(x1, y1);

                if (time > 100){
                    sprites.get(0).sprite.setColor(1, 1, 1, (lifeTime-time)*0.02f);
                }
        }


        if(fall){
            deltaY -= fallSpeed/100;
        }


        switch (sootType) {
            case 8:
            case -1:
                break;
            default:
                sprite.setOriginBasedPosition((int) x1, (int) y1);
                sprite.draw(batch);
                break;
        }
    }

    public void hitEffect(Creature creature) {
        if(!damagedCreatures.contains(creature)){
            if(jump > 0){
                creature.hp -= damage*(1 - creature.damageResist);
                areJump++;
                for (Effect e:effects) {
                    e.addEffect(creature, this);
                }
                if(damage*(1 - creature.damageResist) > 0){
                    creature.damaged = true;
                }


                if(!peirce){
                    float delX = x1 - creature.x;
                    float delY = y1 - creature.y;

                    float ang = (float)Math.atan(delX/delY);
                    if (creature.y > y1){
                        ang += 3.1459;
                    }
                    deltaX = (float)Math.sin(ang);
                    deltaY = (float)Math.cos(ang);
                }else {
                    damagedCreatures.add(creature);
                    peirceTime = 0;
                }
            } else {
                creature.hp -= damage*(1 - creature.damageResist);
                for (Effect e : effects) {
                    e.addEffect(creature, this);
                }
                if (damage*(1 - creature.damageResist) > 0) {
                    creature.damaged = true;
                }

                if (!peirce && !isStopped) {
                    destroyed = true;
                }

                if (melee || peirce) {
                    damagedCreatures.add(creature);
                    peirceTime = 0;
                }
                if (isStopped) {
                    if (!damagedCreatures.contains(creature) && !stop) {
                        damage = 0;
                        effects.removeAll(effects);

                        if (!peirce) {
                            stop = true;
                            stopDX = x1 - creature.x;
                            stopDY = y1 - creature.y;
                            this.creature = creature;
                        }
                    }
                }
            }
        }else {
            peirceTime = 0;
        }
    }

    public void castSpell(ArrayList<Spell> addBullets){
        if(midSpell != null){
            Spell spell = midSpell.makeCopy();
            spell.x1 = x1;
            spell.y1 = y1;
            spell.deltaX = deltaX;
            spell.deltaY = deltaY;
            spell.ang = ang;
            addBullets.add(spell);
        }
    }

    public void end(ArrayList<Spell> bullets){
        if(endSpell != null){
            endSpell.enemy = this.enemy;
            Spell spell = endSpell.makeCopy();
            if(laser){
                spell.x1 = x1+sprite.getHeight()*deltaX;
                spell.y1 = y1+sprite.getHeight()*deltaY;
            }else {
                spell.x1 = x1;
                spell.y1 = y1;
            }

            bullets.add(spell);
        }
    }

    public Spell fuse(Spell spell){
        return spell;
    }
}
