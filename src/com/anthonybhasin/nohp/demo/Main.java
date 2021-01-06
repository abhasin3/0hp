package com.anthonybhasin.nohp.demo;

import java.awt.Color;
import java.awt.event.KeyEvent;

import com.anthonybhasin.nohp.Game;
import com.anthonybhasin.nohp.GameSettings;
import com.anthonybhasin.nohp.Screen.LineBuilder;
import com.anthonybhasin.nohp.Screen.TextBuilder;
import com.anthonybhasin.nohp.io.Keyboard;
import com.anthonybhasin.nohp.io.Log;
import com.anthonybhasin.nohp.level.Level;
import com.anthonybhasin.nohp.level.entity.Camera;
import com.anthonybhasin.nohp.level.entity.ImageEntity;
import com.anthonybhasin.nohp.math.Bounds;
import com.anthonybhasin.nohp.math.Bounds.CameraView;
import com.anthonybhasin.nohp.math.Point2D;
import com.anthonybhasin.nohp.math.RotationMath;
import com.anthonybhasin.nohp.math.Vector2D;
import com.anthonybhasin.nohp.texture.Sprite;

public class Main {

	public static void main(String[] args) {

//		Apply GameSettings
		GameSettings.windowTitle = "Demo";
		GameSettings.scale = 2;
		GameSettings.engineDebugMode = true;

//		Create Game
		Game.create();

		Camera camera2 = new Camera();
		camera2.position.set(50, 25);

//		Example level
		Level level = new Level(new Camera() {

			int speed = 15, rotationSpeed = 3;

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

				if (Keyboard.getPressed(KeyEvent.VK_LEFT)) {

					super.rotation += this.rotationSpeed;

					super.rotation = super.rotation % 360;
				}

				if (Keyboard.getPressed(KeyEvent.VK_RIGHT)) {

					super.rotation -= this.rotationSpeed;

					if (super.rotation < 0) {

						super.rotation = 360 + super.rotation;
					}
				}

				if (Keyboard.getPressed(KeyEvent.VK_2)) {

					Game.instance.level.camera = camera2;
				}

				Vector2D moveDir = RotationMath.getRotatedDirection(
						new Vector2D(xa, ya).normalize().multiply(this.speed), Game.instance.level.camera);

				super.position.move(moveDir);
			}
		});

//		Example Entity
		level.addEntity(new ImageEntity(new Sprite("/text8x8.png")) {

			private Vector2D moveDir = new Vector2D(1, 0);

			@Override
			public void tick() {

				Log.print("Text8x8 is ON SCREEN");

				super.position.move(this.moveDir);
			}

			@Override
			public void render() {

				super.render();

				Bounds bounds = super.bounds.toCameraView(CameraView.UNROTATED_LAYER);
				new TextBuilder("Text8x8 Entity").start(new Point2D(bounds.getMidX() - 30, bounds.getMinY()))
						.cameraView(CameraView.UNROTATED_LAYER).color(Color.WHITE).draw();

				Point2D topLeftPoint = bounds.getPoint(0);
				Point2D midPoint = new Point2D(bounds.getMidX(), bounds.getMinY() - 2);

				new LineBuilder().start(topLeftPoint).end(midPoint).color(Color.PINK).strokeWidth(2)
						.cameraView(CameraView.UNROTATED_LAYER).draw();
			}
		});

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
