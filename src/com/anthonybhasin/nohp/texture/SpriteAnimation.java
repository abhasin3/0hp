package com.anthonybhasin.nohp.texture;

import java.awt.image.BufferedImage;

public class SpriteAnimation extends Sprite {

	private Sprite[] sprites;

	private boolean pingPong, rising;

	private int animRate, timer, spriteIdx;

	/**
	 * Create a sprite animation object.
	 * 
	 * @param animRate - the amount of ticks between each frame.
	 * @param pingPong - whether to ping-pong the animation or not.
	 * @param sprites  - sprites to include in the animation (<b>must have length >=
	 *                 1</b>)
	 */
	public SpriteAnimation(int animRate, boolean pingPong, Sprite... sprites) {

		super(sprites[0]);

		this.pingPong = pingPong;
		this.rising = true;

		this.animRate = animRate;
		this.timer = 0;
		this.spriteIdx = 0;

		this.sprites = sprites;
	}

	public void tick() {

		this.timer++;

		if (this.timer >= this.animRate) {

			if (this.pingPong) {

				if (this.rising) {

					this.spriteIdx++;

					if (this.spriteIdx == this.sprites.length - 1) {

						this.rising = false;
					}
				} else {

					this.spriteIdx--;

					if (this.spriteIdx == 0) {

						this.rising = true;
					}
				}
			} else {

				this.spriteIdx = (this.spriteIdx + 1) % this.sprites.length;
			}

			this.timer = 0;
		}
	}

	public BufferedImage getImage() {

		return this.sprites[this.spriteIdx].getImage();
	}
}