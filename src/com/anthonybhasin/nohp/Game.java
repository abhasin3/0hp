package com.anthonybhasin.nohp;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

import com.anthonybhasin.nohp.io.Mouse;
import com.anthonybhasin.nohp.level.Level;

public class Game {

	public static Game instance;

	public static void create() {

		Game.instance = new Game();
	}

	public Level level;

	private BufferStrategy bs;

	private boolean running;

	private int fps, ticks;

	private double nsPerTick;

	private Game() {

		this.running = false;

		Window.create(GameSettings.windowWidth, GameSettings.windowHeight, GameSettings.windowScale,
				GameSettings.windowTitle);

		this.updateMaxTPS();
	}

	public void updateMaxTPS() {

		this.nsPerTick = 1_000_000_000D / GameSettings.maxTPS;
	}

	public void setLevel(Level level) {

		this.level = level;
	}

	public void start() throws Exception {

//		if (this.level == null) {
//			
//			throw new Exception("A game cannot be started without a level!");
//		}
//		
		this.running = true;

		this.bs = Window.canvas.getBufferStrategy();

		long lastTime = System.nanoTime();

		long lastTimer = System.currentTimeMillis();
		double delta = 0D;

		int fps_ = 0;
		int ticks_ = 0;

		while (this.running) {

			long now = System.nanoTime();
			delta += (now - lastTime) / this.nsPerTick;
			lastTime = now;

			while (delta >= 1) {

				this.tick();

				ticks_++;
				delta--;
			}

			this.render();

			fps_++;

			if (System.currentTimeMillis() - lastTimer >= 1_000) {

				this.fps = fps_;
				this.ticks = ticks_;

				fps_ = ticks_ = 0;

				lastTimer += 1_000;
			}
		}
	}

	public void tick() {

		this.level.tick();

		Mouse.resetScroll();
	}

	public void render() {

		Graphics g = this.bs.getDrawGraphics();
		Graphics2D g2d = (Graphics2D) g;

		// Clear the screen.
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Window.canvas.getWidth(), Window.canvas.getHeight());

		// This clip prevents a Sprite from being rendered off the "display" screen
		// which is ratio-proportional to the width / height.
		g.clipRect(Window.displayX, Window.displayY, (int) (Window.width * Window.scale * Window.resizeScale),
				(int) (Window.height * Window.scale * Window.resizeScale));
		g.translate(Window.displayX, Window.displayY);
		g2d.scale(Window.scale * Window.resizeScale, Window.scale * Window.resizeScale);

		Screen.setGraphics(g2d);

		this.level.render();

		g.dispose();
		this.bs.show();
	}

	public int getFPS() {

		return this.fps;
	}

	public int getTicks() {

		return this.ticks;
	}
}
