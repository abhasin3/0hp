package com.anthonybhasin.nohp.demo;

import java.awt.event.KeyEvent;

import com.anthonybhasin.nohp.Game;
import com.anthonybhasin.nohp.GameSettings;
import com.anthonybhasin.nohp.io.Keyboard;
import com.anthonybhasin.nohp.level.Level;
import com.anthonybhasin.nohp.level.entity.Camera;
import com.anthonybhasin.nohp.level.entity.ImageEntity;
import com.anthonybhasin.nohp.math.Vector2D;
import com.anthonybhasin.nohp.texture.Sprite;

public class Main {

	public static void main(String[] args) {

//		Apply GameSettings
		GameSettings.engineDebugMode = true;

//		Create Game
		Game.create();

//		Example level
		Level level = new Level(new Camera() {

//			Add way to move Camera around for demo
			@Override
			public void tick() {

				super.tick();

				int xa = 0, ya = 0;

				if (Keyboard.getPressed(KeyEvent.VK_W)) {

					ya--;
				}
				if (Keyboard.getPressed(KeyEvent.VK_A)) {

					xa--;
				}
				if (Keyboard.getPressed(KeyEvent.VK_S)) {

					ya++;
				}
				if (Keyboard.getPressed(KeyEvent.VK_D)) {

					xa++;
				}

				super.position.translate(xa, ya);
			}
		});

//		Example Entity
		level.addEntity(new ImageEntity(new Sprite("/text8x8.png")) {

			private Vector2D moveDir = new Vector2D(1, 0);

			@Override
			public void tick() {

				System.out.println("ON SCREEN - this message will only print while the entity is on screen!");
				super.position.move(this.moveDir);
			}
		});

		Camera camera2 = new Camera();
		camera2.position.set(50, 25);
		level.addEntity(camera2);

//		Set Game Level
		Game.instance.setLevel(level);

		try {

//			Start Game
			Game.instance.start();
		} catch (Exception e) {

			e.printStackTrace();
		}
	}
}
