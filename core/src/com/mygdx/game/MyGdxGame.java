package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.mygdx.game.objects.attacks.Spell;
import com.mygdx.game.objects.creatures.enemyes.Enemy;
import com.mygdx.game.objects.creatures.heroes.*;
import com.mygdx.game.objects.creatures.npcs.Npc;
import com.mygdx.game.objects.items.equipment.Equipment;
import com.mygdx.game.objects.items.Item;
import com.mygdx.game.objects.room.*;
import com.mygdx.game.objects.room.lab.Lab1;
import com.mygdx.game.objects.room.lab.Lab2;
import sun.jvm.hotspot.gc.shared.Space;

import java.util.ArrayList;

public class MyGdxGame extends ApplicationAdapter {

	float width = 1280;
	float height = 736;

	float xPos;
	float yPos;

	SpriteBatch batch;
	Player player;

	Room room;
	ArrayList<Spell> bullets;
	ArrayList<Spell> addBullets;
	ArrayList<Spell> Dbullets;
	ArrayList<Room> rooms;

	Sprite hpBar;
	Sprite hpBar1;
	Sprite manaBar;
	Sprite manaBar1;

	Sprite spellHolder;
	Sprite textHolder;

	Sprite dark;
	Sprite youAreDead;
	int deadTime;

	//menu
	private boolean isPaused;
	private byte menuType;
	Sprite menu;
	Sprite items;
	Sprite shopMenu;

	Spell holdSpell;
	Spell infoSpell;

	private Item holdItem;
	private Item infoItem;

	Spell[] toFuse;

	BitmapFont font;

	private OrthographicCamera cam;

	@Override
	public void create () {
		rooms = new ArrayList<>();
		bullets = new ArrayList<>();
		addBullets = new ArrayList<>();
		Dbullets = new ArrayList<>();
		batch = new SpriteBatch();
//		room = new Lab2();
		room = new Room0();
//		room = new Hub();
		rooms.add(room);

		player = new Necro(room.spawnX, room.spawnY);

		hpBar = new Sprite(new Texture("hpbar.png"));
		hpBar1 = new Sprite(new Texture("hpbar1.png"));
		manaBar = new Sprite(new Texture("manabar.png"));
		manaBar1 = new Sprite(new Texture("hpbar1.png"));

		textHolder = new Sprite(new Texture("fDark.png"));
		textHolder.setSize(1300, 128);

		spellHolder = new Sprite(new Texture("spellsHolder.jpg"));
		spellHolder.setScale(0.8f);
		spellHolder.setOriginCenter();

		menu = new Sprite(new Texture("menu.png"));
		shopMenu = new Sprite(new Texture("shopMenu.png"));
		menuType = 1;
		items = new Sprite(new Texture("items.png"));

		dark = new Sprite(new Texture("fDark.png"));
		dark.setSize(1280, 736);
		youAreDead = new Sprite(new Texture("youSuck.png"));
		deadTime = 0;

		hpBar.setSize(233, 10);
		hpBar1.setSize(235, 12);
		manaBar.setSize(233, 10);
		manaBar1.setSize(235, 12);

		cam = new OrthographicCamera();

		cam.zoom = 1;

		cam.position.x = width/2f;
		cam.position.y = height/2f;

		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Bitter-Regular.ttf"));
		FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.size = 18;
		font = generator.generateFont(parameter);
		generator.dispose();

	}

	private void reCreate() {
		rooms = new ArrayList<>();
		bullets = new ArrayList<>();
		Dbullets = new ArrayList<>();
		room = room.create();
		rooms.add(room);

		player.x = room.spawnX;
		player.y = room.spawnY;
		player.hp = player.maxHp;
		player.effects = new ArrayList<>();
		player.dead = false;

		deadTime = 0;

	}

	private void handleInput() {
		xPos = cam.position.x - width/2;
		yPos = cam.position.y - height/2;

		if(room.height > height){
			if(player.y > height/2){
				if(player.y > room.height-height/2){
					cam.position.y = (int)(cam.position.y * 9 + room.height-height/2) / 10;
				}else {
					cam.position.y = (int)(cam.position.y * 9 + player.y) / 10;
				}
			}else {
				cam.position.y = (int)(cam.position.y * 9 + height/2) / 10;
			}
		}else {
			cam.position.y = height/2f;
		}

		if(room.width > width){
			if(player.x > width/2){
				if(player.x > room.width-width/2){
					cam.position.x = (int)(cam.position.x * 9 + room.width-width/2) / 10;
				}else {
					cam.position.x = (int)(cam.position.x * 9 + player.x) / 10;
				}
			}else{
				cam.position.x = (int)(cam.position.x * 9 + width/2) / 10;
			}
		}else {
			cam.position.x = width/2f;
		}


//		if(Gdx.input.isKeyPressed(Input.Keys.G)){
//			cam.position.x += Math.random()*2*time1 - time1;
//			cam.position.y += Math.random()*2*time1 - time1;
//
//			time1 -= 0.01;
//			System.out.println(time1);
//		}
	}

	@Override
	public void resize(int width, int height) {
		cam.viewportWidth = width;
		cam.viewportHeight = height;
	}

	@Override
	public void render () {
		batch.setProjectionMatrix(cam.combined);
		handleInput();
		cam.update();

		Gdx.gl.glClearColor(0f, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();

		room.draw(batch, true);

		room.live(batch, bullets, player);

		bulletCollision();

		player.live(room, batch, bullets, xPos, yPos);

		room.draw(batch, false);

		nextRoom(player);

		heroBar(batch);
		ui(batch);

		boolean dialog = false;
		for (Npc npc: room.npc) {
			if(npc.dialog){
				textHolder.draw(batch);
				npc.dialog(batch, player);
				dialog = true;
			}
			if(npc.shop){
				shop(batch, npc);
				dialog = true;
			}
		}

		menu(dialog);

		deadScreen(batch);

		batch.end();
	}
	void shop(SpriteBatch batch, Npc npc){
		if(npc.spells.size() > 0){
			if(!npc.spells.contains(infoSpell)){
				infoSpell = npc.spells.get(0);
			}
		}else {
			infoSpell = null;
		}

		if(Gdx.input.isKeyJustPressed(Input.Keys.TAB) || Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
			player.stop = false;
			npc.shop = false;
			npc.time = 0;
		}

		shopMenu.draw(batch);
		int a = 0;
		int b = 1;
		for (Spell s: npc.spells) {
			s.icon.setSize(32, 32);
			s.icon.setPosition(78 + a*40  + xPos, 650-b*40  + yPos);
			s.icon.draw(batch);
			a++;
			if (a == 22){
				a = 0;
				b++;
			}
		}
		if(Gdx.input.isButtonJustPressed(Input.Buttons.RIGHT) || Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)){
			float inpX = Gdx.input.getX() + xPos;
			float inpY = 736 - Gdx.input.getY() + yPos;
			Spell spell = null;


			for (Spell s: npc.spells){
				if(s.icon.getX() < inpX && s.icon.getX() + s.icon.getWidth()  > inpX
						&& s.icon.getY() < inpY && s.icon.getY() + s.icon.getHeight()  > inpY){
					spell = s;
				}
			}
			if(spell != null){
				if(Gdx.input.isButtonJustPressed(Input.Buttons.RIGHT)){
					player.spells.add(spell);
					npc.spells.remove(spell);
				}else {
					infoSpell = spell;
				}
			}
		}

		//info

		if(infoSpell != null){
			infoSpell.icon.setSize(100, 100);
			infoSpell.icon.setOriginCenter();
			infoSpell.icon.setOriginBasedPosition(877, 630);
			infoSpell.icon.draw(batch);


			font.draw(batch, infoSpell.name, 937, 670);
			font.draw(batch, infoSpell.description, 837, 570);
			font.draw(batch, "damage - " + infoSpell.damage + "\n" +
					"reload - " + infoSpell.reloadTime/60 + " s\n" +
					"manacost - " + infoSpell.mp , 837, 470);

		}

	}

	void marge(float inpX, float inpY){
		if (1058 < inpX && 1132  > inpX
				&& 228 < inpY && 299 > inpY && !holdSpell.unique){
			for (int i = 0; i < 5; i++) {
				if (toFuse[i] != null){
					if(toFuse[i].getClass().getSimpleName().equals(holdSpell.getClass().getSimpleName())){
						toFuse[i] = null;
					}
				}
			}

			toFuse[0] = holdSpell.makeCopy();
			toFuse[0].icon.setSize(74, 74);
			toFuse[0].icon.setPosition(1058 + xPos, 228 + yPos);
		}
		if (992 < inpX && 1048  > inpX
				&& 309 < inpY && 366 > inpY && !holdSpell.unique){
			for (int i = 0; i < 5; i++) {
				if (toFuse[i] != null){
					if(toFuse[i].getClass().getSimpleName().equals(holdSpell.getClass().getSimpleName())){
						toFuse[i] = null;
					}
				}
			}

			toFuse[1] = holdSpell.makeCopy();
			toFuse[1].icon.setSize(54, 54);
			toFuse[1].icon.setPosition(992 + xPos, 309 + yPos);
		}
		if (1140 < inpX && 1195  > inpX
				&& 309 < inpY && 366 > inpY && !holdSpell.unique){
			for (int i = 0; i < 5; i++) {
				if (toFuse[i] != null){
					if(toFuse[i].getClass().getSimpleName().equals(holdSpell.getClass().getSimpleName())){
						toFuse[i] = null;
					}
				}
			}

			toFuse[2] = holdSpell.makeCopy();
			toFuse[2].icon.setSize(54, 54);
			toFuse[2].icon.setPosition(1140 + xPos, 309 + yPos);
		}
		if (992 < inpX && 1048  > inpX
				&& 162 < inpY && 216 > inpY && !holdSpell.unique){
			for (int i = 0; i < 5; i++) {
				if (toFuse[i] != null){
					if(toFuse[i].getClass().getSimpleName().equals(holdSpell.getClass().getSimpleName())){
						toFuse[i] = null;
					}
				}
			}

			toFuse[3] = holdSpell.makeCopy();
			toFuse[3].icon.setSize(54, 54);
			toFuse[3].icon.setPosition(992 + xPos, 162 + yPos);
		}
		if (1140 < inpX && 1195  > inpX
				&& 162 < inpY && 221 > inpY && !holdSpell.unique){
			for (int i = 0; i < 5; i++) {
				if (toFuse[i] != null){
					if(toFuse[i].getClass().getSimpleName().equals(holdSpell.getClass().getSimpleName())){
						toFuse[i] = null;
					}
				}
			}

			toFuse[4] = holdSpell.makeCopy();
			toFuse[4].icon.setSize(54, 54);
			toFuse[4].icon.setPosition(1140 + xPos, 162 + yPos);
		}
	}

	void fuse(){
		if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)){
			float inpX = Gdx.input.getX();
			float inpY = 736 - Gdx.input.getY();

			if(Math.sqrt((inpX-1094)*(inpX-1094) + (inpY-428)*(inpY-428)) < 45 && toFuse[0] != null){
				Spell s = toFuse[0].makeCopy();
				s.icon.setColor(0.6f, 0.6f, 1, 1);
				toFuse[0] = null;
				for (int i = 1; i < 5; i++) {
					if(toFuse[i] != null){
						s = toFuse[i].fuse(s);
					}
				}
				s.unique = true;
				player.spells.add(s);
			}
		}
	}

	void menu(boolean dialog){
		if(Gdx.input.isKeyJustPressed(Input.Keys.TAB) && !dialog){
			player.stop = !isPaused;
			isPaused = !isPaused;
			toFuse = new Spell[5];
			infoSpell = player.spells.get(0);
			if(!player.items.isEmpty()){
				infoItem = player.items.get(0);
			}else {
				infoItem = null;
			}
		}
		if(isPaused){
			if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
				float inpX2 = Gdx.input.getX();
				float inpY2 = 736 - Gdx.input.getY();

				if (60 < inpX2 && 227 > inpX2
						&& 632 < inpY2 && 682 > inpY2) {
					menuType = 1;
				} else if (240 < inpX2 && 395 > inpX2
						&& 632 < inpY2 && 682 > inpY2) {
					menuType = 2;
				}
			}

			switch (menuType) {
				case 1:
					menu.setPosition(xPos, yPos);
					menu.draw(batch);

					for (int i = 0; i < 8; i++) {
						if (player.spell[i] != null) {
							player.spell[i].icon.setSize(50, 50);
							player.spell[i].icon.setPosition(i * 56 + 365 + xPos, 33 + yPos);
							player.spell[i].icon.draw(batch);
						}
					}

					for (int i = 0; i < 8; i++) {
						if (player.item[i] != null) {
							player.item[i].sprite.setSize(50, 50);
							player.item[i].sprite.setPosition(i * 56 + 365 + xPos, 33 + yPos);
							player.item[i].sprite.draw(batch);
							if(player.item[i].quantity > 0){
								font.draw(batch, ""+player.item[i].quantity, i * 56 + 405 + xPos, 43 + yPos);
							}
						}
					}

					int a = 0;
					int b = 1;
					for (Spell s : player.spells) {
						s.icon.setSize(32, 32);
						s.icon.setPosition(78 + a * 40 + xPos, 600 - b * 40 + yPos);
						s.icon.draw(batch);
						a++;
						if (a == 12) {
							a = 0;
							b++;
						}
					}

					for (int i = 0; i < 5; i++) {
						if (toFuse[i] != null) {
							toFuse[i].icon.draw(batch);
						}
					}

					//mouse move
					if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
						float inpX = Gdx.input.getX() + xPos;
						float inpY = 736 - Gdx.input.getY() + yPos;

						for (Spell s : player.spells) {
							if (s.icon.getX() < inpX && s.icon.getX() + s.icon.getWidth() > inpX
									&& s.icon.getY() < inpY && s.icon.getY() + s.icon.getHeight() > inpY) {
								holdSpell = s;
								infoSpell = s;
								holdSpell.icon.setSize(32, 32);
								holdSpell.icon.setOrigin(inpX - s.icon.getX(), inpY - s.icon.getY());
							}
						}
					}

					if (Gdx.input.isButtonPressed(Input.Buttons.LEFT) && holdSpell != null) {
						infoSpell.icon.setOriginCenter();
						holdSpell.icon.setOriginBasedPosition(Gdx.input.getX() + xPos, 736 - Gdx.input.getY() + yPos);
						holdSpell.icon.draw(batch);
					} else if (holdSpell != null) {

						float inpX = Gdx.input.getX();
						float inpY = 736 - Gdx.input.getY();

						int areThere = -1;

						for (int i = 0; i < 8; i++) {
							if (player.spell[i] == holdSpell) {
								areThere = i;
							}
						}

						for (int i = 0; i < 8; i++) {
							if (365 + i * 56 < inpX
									&& 365 + i * 56 + 50 > inpX
									&& 33 < inpY
									&& 33 + 50 > inpY) {
								if (areThere >= 0) {
									player.spell[areThere] = null;
								}
								player.spell[i] = holdSpell;
								player.item[i] = null;
							}
						}

						marge(inpX, inpY);

						holdSpell = null;
					}

					fuse();

					// info

					if (infoSpell != null) {
						infoSpell.icon.setSize(100, 100);
						infoSpell.icon.setOriginCenter();
						infoSpell.icon.setOriginBasedPosition(622 + xPos, 565 + yPos);
						infoSpell.icon.draw(batch);


						font.draw(batch, infoSpell.name, 682 + xPos, 605 + yPos);
						font.draw(batch, infoSpell.description, 582 + xPos, 505 + yPos);
						font.draw(batch, "damage - " + infoSpell.damage + "\n" +
								"reload - " + infoSpell.reloadTime / 60 + " s\n" +
								"manacost - " + infoSpell.mp, 582 + xPos, 405 + yPos);

					}
					break;
				case 2:
					items.setPosition(xPos, yPos);
					items.draw(batch);

					// info

					if (infoItem != null) {
						infoItem.sprite.setSize(100, 100);
						infoItem.sprite.setOriginCenter();
						infoItem.sprite.setOriginBasedPosition(622 + xPos, 565 + yPos);
						infoItem.sprite.draw(batch);


						font.draw(batch, infoItem.name, 682 + xPos, 605 + yPos);
						font.draw(batch, infoItem.description, 582 + xPos, 505 + yPos);
					}

					for (int i = 0; i < 8; i++) {
						if (player.spell[i] != null) {
							player.spell[i].icon.setSize(50, 50);
							player.spell[i].icon.setPosition(i * 56 + 365 + xPos, 33 + yPos);
							player.spell[i].icon.draw(batch);
						}
					}

					for (int i = 0; i < 8; i++) {
						if (player.item[i] != null) {
							player.item[i].sprite.setSize(50, 50);
							player.item[i].sprite.setPosition(i * 56 + 365 + xPos, 33 + yPos);
							player.item[i].sprite.draw(batch);
							if(player.item[i].quantity > 0){
								font.draw(batch, ""+player.item[i].quantity, i * 56 + 405 + xPos, 43 + yPos);
							}
						}
					}

					for (int i = 0; i < 8; i++) {
						if (player.equipment[i] != null) {
							float x21;
							float y21;

							if(i < 2){
								x21 = 978;
								if(i == 0){
									y21 = 550;
								}else{
									y21 = 483;
								}
							}else if(i < 6){
								x21 = 1062;
								if(i == 2){
									y21 = 550;
								}else if(i == 3){
									y21 = 483;
								}else if(i == 4){
									y21 = 417;
								}else{
									y21 = 353;
								}
							}else {
								x21 = 1140;
								if(i == 6){
									y21 = 550;
								}else{
									y21 = 483;
								}
							}

							player.equipment[i].sprite.setSize(55, 55);
							player.equipment[i].sprite.setPosition(x21 + xPos, y21 + yPos);
							player.equipment[i].sprite.draw(batch);
//							if(player.equipment[i].quantity > 0){
//								font.draw(batch, ""+player.equipment[i].quantity, i * 56 + 405 + xPos, 200 + yPos);
//							}
						}

					}

					int a1 = 0;
					int b1 = 1;

					for (Item i : player.items) {
						i.sprite.setSize(32, 32);
						i.sprite.setPosition(78 + a1 * 40 + xPos, 600 - b1 * 40 + yPos);
						i.sprite.draw(batch);
						if(i.quantity > 0){
							font.draw(batch, ""+i.quantity, 98 + a1 * 40 + xPos, 615 - b1 * 40 + yPos);
						}
						a1++;
						if (a1 == 12) {
							a1 = 0;
							b1++;
						}
					}


					//mouse move
					if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) || Gdx.input.isButtonJustPressed(Input.Buttons.RIGHT)) {
						float inpX = Gdx.input.getX() + xPos;
						float inpY = 736 - Gdx.input.getY() + yPos;

						for (Item i : player.items) {
							if (i.sprite.getX() < inpX && i.sprite.getX() + i.sprite.getWidth() > inpX
									&& i.sprite.getY() < inpY && i.sprite.getY() + i.sprite.getHeight() > inpY) {
								holdItem = i;
								infoItem = i;
							}
						}
						if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && holdItem != null){
							holdItem.sprite.setSize(32, 32);
							holdItem.sprite.setOrigin(inpX - holdItem.sprite.getX(), inpY - holdItem.sprite.getY());
						}else if(holdItem != null){
							if(holdItem.getClass().getSuperclass().getSimpleName().equals("Equipment")){
								int type = ((Equipment)holdItem).type;
								if(type == 7){
									if(player.equipment[7] == null){
										setEquip(7, 7);
									}else {
										setEquip(6, 7);
									}
								}else {
									setEquip(type -1, type);
								}
							}else if(holdItem.getClass().getSuperclass().getSimpleName().equals("Potion")){
								holdItem.use(player);
							}
						}
					}

					if (Gdx.input.isButtonJustPressed(Input.Buttons.RIGHT)) {
						float inpX = Gdx.input.getX() + xPos;
						float inpY = 736 - Gdx.input.getY() + yPos;

						if(inpX >978 && inpX < 1034){
							if(inpY < 603 && inpY > 550){
								removeEquip(0);
							}else if(inpY < 536 && inpY > 483){
								removeEquip(1);
							}
						}else if(inpX > 1062 && inpX < 1118){
							if(inpY < 603 && inpY > 550){
								removeEquip(2);
							}else if(inpY < 536 && inpY > 483){
								removeEquip(3);
							}else if(inpY < 483 && inpY > 417){
								removeEquip(4);
							}else if(inpY < 406 && inpY > 353){
								removeEquip(5);
							}
						}else if(inpX > 1140 && inpX < 1200){
							if(inpY < 603 && inpY > 550){
								removeEquip(6);
							}else if(inpY < 536 && inpY > 483){
								removeEquip(7);
							}
						}
					}

					if (Gdx.input.isButtonPressed(Input.Buttons.LEFT) && holdItem != null) {
						infoItem.sprite.setOriginCenter();
						holdItem.sprite.setOriginBasedPosition(Gdx.input.getX() + xPos, 736 - Gdx.input.getY() + yPos);
						holdItem.sprite.draw(batch);
					} else if (holdItem != null) {

						float inpX = Gdx.input.getX();
						float inpY = 736 - Gdx.input.getY();

						int areThere = -1;

						for (int i = 0; i < 8; i++) {
							if (player.item[i] == holdItem) {
								areThere = i;
							}
						}

						if(holdItem.getClass().getSuperclass().getSimpleName().equals("Potion")){
							for (int i = 0; i < 8; i++) {
								if (365 + i * 56 < inpX
										&& 365 + i * 56 + 50 > inpX
										&& 33 < inpY
										&& 33 + 50 > inpY) {
									if (areThere >= 0) {
										player.item[areThere] = null;
									}
									player.item[i] = holdItem;
									player.spell[i] = null;
								}
							}
						}else if(holdItem.getClass().getSuperclass().getSimpleName().equals("Equipment")){
							if(inpX >978 && inpX < 1034){
								if(inpY < 603 && inpY > 550){
									setEquip(0, 1);
								}else if(inpY < 536 && inpY > 483){
									setEquip(1, 2);
								}
							}else if(inpX > 1062 && inpX < 1118){
								if(inpY < 603 && inpY > 550){
									setEquip(2, 3);
								}else if(inpY < 536 && inpY > 483){
									setEquip(3, 4);
								}else if(inpY < 483 && inpY > 417){
									setEquip(4, 5);
								}else if(inpY < 406 && inpY > 353){
									setEquip(5, 6);
								}
							}else if(inpX > 1140 && inpX < 1200){
								if(inpY < 603 && inpY > 550){
									setEquip(6, 7);
								}else if(inpY < 536 && inpY > 483){
									setEquip(7, 7);
								}
							}
						}

						holdItem = null;
						break;
					}
			}
		}
	}

	private void setEquip(int i, int type) {
		if(((Equipment)holdItem).type == type){
			if(player.equipment[i] != null){
				player.equipment[i].remove(player);
				player.items.add(player.equipment[i]);
				player.equipment[i] = null;
			}

			player.equipment[i] = holdItem;
			player.items.remove(player.equipment[i]);
			player.equipment[i].use(player);
		}
	}
	private void removeEquip(int i) {
		if(player.equipment[i] != null){
			player.equipment[i].remove(player);
			player.items.add(player.equipment[i]);
			player.equipment[i] = null;
		}
	}

	public void deadScreen(SpriteBatch batch){
		if(player.dead){
			if(deadTime<=100){
				deadTime++;
			}
			dark.setColor(1, 1, 1, deadTime*0.005f);
			youAreDead.setColor(1, 1, 1, deadTime*0.01f);
			dark.draw(batch);
			youAreDead.draw(batch);
			if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
				reCreate();
			}
		}
	}

	public void ui(SpriteBatch batch){
		spellHolder.setOriginBasedPosition(640 + xPos, 75 + yPos);
		spellHolder.draw(batch);

		for (int i = 0; i < 8; i++) {
			if(player.spell[i] != null){
				if(i == 7 && player.spell[player.spellNum] != null){
					player.spell[player.spellNum].icon.setSize(32, 32);
					player.spell[player.spellNum].icon.setOriginCenter();
					player.spell[player.spellNum].icon.setOriginBasedPosition(432+i*45+51 + xPos, 75 + yPos);
					player.spell[player.spellNum].icon.draw(batch);
					if(player.spell[player.spellNum].reload > 0){
						float h = player.spell[player.spellNum].reload/player.spell[player.spellNum].reloadTime*32;
						if(h > 32){
							h = 32;
						}
						player.spell[player.spellNum].iconDark.setSize(32, h);
						player.spell[player.spellNum].iconDark.setOriginBasedPosition(432+i*45+51 + xPos, 75 + yPos);
						player.spell[player.spellNum].iconDark.draw(batch);
					}
				}else if (i == player.spellNum) {
					Sprite s = new Sprite(player.spell[i].icon);
					s.setSize(32, 32);
					s.setOriginCenter();
					s.setOriginBasedPosition(432+i*45 + xPos, 75 + yPos);
					s.draw(batch);
					if(player.spell[i].reload > 0){
						Sprite sd = new Sprite(player.spell[i].iconDark);
						float h = player.spell[i].reload/player.spell[i].reloadTime*32;
						if(h > 32){
							h = 32;
						}
						sd.setSize(32, h);
						sd.setOriginBasedPosition(432+i*45 + xPos, 75 + yPos);
						sd.draw(batch);
					}
				}else{
					player.spell[i].icon.setSize(32, 32);
					player.spell[i].icon.setOriginCenter();
					player.spell[i].icon.setOriginBasedPosition(432+i*45 + xPos, 75 + yPos);
					player.spell[i].icon.draw(batch);
					if(player.spell[i].reload > 0){
						float h = player.spell[i].reload/player.spell[i].reloadTime*32;
						if(h > 32){
							h = 32;
						}
						player.spell[i].iconDark.setSize(32, h);
						player.spell[i].iconDark.setOriginBasedPosition(432+i*45 + xPos, 75 + yPos);
						player.spell[i].iconDark.draw(batch);
					}
				}
			}
			//items
			if(player.item[i] != null){
				player.item[i].sprite.setSize(32, 32);
				player.item[i].sprite.setOriginCenter();
				player.item[i].sprite.setOriginBasedPosition(432+i*45 + xPos, 75 + yPos);
				player.item[i].sprite.draw(batch);
				if(player.item[i].quantity > 0){
					font.draw(batch, ""+player.item[i].quantity, 432+i*45 + xPos, 75 + yPos);
				}
			}
		}
		if(player.spell[7] != null){
			player.spell[7].icon.setSize(32, 32);
			player.spell[7].icon.setOriginCenter();
			player.spell[7].icon.setOriginBasedPosition(432+9*45+10 + xPos, 75 + yPos);
			player.spell[7].icon.draw(batch);
			if(player.spell[7].reload > 0){
				float h = player.spell[7].reload/player.spell[7].reloadTime*32;
				if(h > 32){
					h = 32;
				}
				player.spell[7].iconDark.setSize(32, h);
				player.spell[7].iconDark.setOriginBasedPosition(432+9*45+15 + xPos, 75 + yPos);
				player.spell[7].iconDark.draw(batch);
			}
		}
	}

	public void heroBar(SpriteBatch batch){
		hpBar.setPosition(10 + xPos, 680 + yPos);
		hpBar1.setPosition(9 + xPos, 679 + yPos);
		manaBar.setPosition(10 + xPos, 665 + yPos);
		manaBar1.setPosition(9 + xPos, 664 + yPos);


		hpBar1.draw(batch);
		manaBar1.draw(batch);

		float hp = (float)233*player.hp/player.maxHp;
		if(hp < 0){
			hp = 0;
		}
		hpBar.setSize(hp , hpBar.getHeight());

		float mp = (float)233*player.mp/player.maxMp;
		if(mp < 0){
			mp = 0;
		}
		manaBar.setSize(mp , manaBar.getHeight());

		hpBar.draw(batch);
		manaBar.draw(batch);
	}

	public void nextRoom(Player player){
		Room nextRoom = this.room.nextRoom(player);
		boolean isFind = false;
		if (nextRoom != null) {
			Dbullets.clear();
			bullets.clear();
			for (Room r: rooms) {
				if(r.getClass() == nextRoom.getClass()){
					r.spawnX = nextRoom.spawnX;
					r.spawnY = nextRoom.spawnY;
					room = r;
					isFind = true;
				}
			}
			if (!isFind){
				rooms.add(nextRoom);
				room = nextRoom;
			}
		}
	}

	public void bulletCollision(){
		for (Spell b: bullets){
			if (!b.enemy){
				for (Enemy e: room.enemies){
					if (!e.dead){
						if(b.laser || b.melee){
							float x;
							float y;
							for (int i = 0; i < b.sprites.get(0).sprite.getHeight()/10; i++) {
								x = b.x1 + i * b.deltaX*10;
								y = b.y1 + i * b.deltaY * 10;
								if(e.spellCollision(x, y)){
									b.hitEffect(e);
									e.playerIsHidden = true;
									e.lastPlayerX = player.x;
									e.lastPlayerY = player.y;
									e.playerFoundTime = 500;
									if(b.endSpell != null){
										if(Math.random() < 0.05 || !b.laser){
											Spell spell = b.endSpell.makeCopy();
											spell.damage *= 20;
											spell.x1 = e.x;
											spell.y1 = e.y;
											spell.deltaX = b.deltaX;
											spell.deltaY = b.deltaY;
											spell.ang = b.ang;
											addBullets.add(spell);
										}
									}
									break;
								}
							}
						}else {
							if(e.spellCollision(b.x1, b.y1)){
								b.hitEffect(e);
								e.playerIsHidden = true;
								e.lastPlayerX = player.x;
								e.lastPlayerY = player.y;
								e.playerFoundTime = 500;
							}
						}
					}
				}
			}else if(!player.invincible){
				if(player.spellCollision(b.x1, b.y1)){
					b.hitEffect(player);
				}
			}
		}

		for (Spell b: bullets){
			b.live(batch, room, addBullets);
			if(b.time > b.lifeTime){
				Dbullets.add(b);
			}
			if(b.destroyed){
				Dbullets.add(b);
			}
		}

		for (Spell b: Dbullets) {
			b.end(bullets);
		}
		bullets.removeAll(Dbullets);
		Dbullets.clear();

		bullets.addAll(addBullets);
		addBullets.clear();
	}


	@Override
	public void dispose () {
		batch.dispose();
	}
}
