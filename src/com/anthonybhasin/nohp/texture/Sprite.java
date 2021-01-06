package com.anthonybhasin.nohp.texture;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Sprite {

	private int width, height;

	private String path = "null";

	private BufferedImage image;

	public Sprite() {

		this.width = 1;
		this.height = 1;
		this.image = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
	}

	public Sprite(int width, int height, int col) {

		this.width = width;
		this.height = height;
		this.image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

		for (int i = 0; i < width; i++) {

			for (int j = 0; j < height; j++) {

				this.image.setRGB(i, j, col);
			}
		}
	}

	public Sprite(BufferedImage image) {

		this.width = image.getWidth();
		this.height = image.getHeight();

		this.image = image;
	}

	public Sprite(String path) {

		this.path = path;

		try {

			this.image = ImageIO.read(Sprite.class.getResource(this.path));

			this.width = this.image.getWidth();
			this.height = this.image.getHeight();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Sprite(int x, int y, int width, int height, Sprite spriteSheet) {

		this.width = width;
		this.height = height;

		this.path = spriteSheet.path;

		this.image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

		BufferedImage sheetImg = spriteSheet.getImage();

		for (int yy = y; yy < y + height; yy++) {

			if (yy < 0) {

				yy = 0;
				continue;
			}

			if (yy >= sheetImg.getHeight()) {

				break;
			}

			for (int xx = x; xx < x + width; xx++) {

				if (xx < 0) {

					xx = 0;
					continue;
				}

				if (xx >= sheetImg.getWidth()) {

					break;
				}

				int color = sheetImg.getRGB(xx, yy);

				this.image.setRGB(xx - x, yy - y, color);
			}
		}
	}

	public Sprite(Sprite spriteToClone) {

		this.width = spriteToClone.getWidth();
		this.height = spriteToClone.getHeight();

		this.path = spriteToClone.path;

		BufferedImage imageCopy = new BufferedImage(this.width, this.height, BufferedImage.TYPE_INT_ARGB);
		Graphics g = imageCopy.createGraphics();
		g.drawImage(spriteToClone.getImage(), 0, 0, null);
		g.dispose();
		this.image = imageCopy;
	}

	@Override
	public Sprite clone() {

		return new Sprite(this);
	}

	public void resize(int width, int height) {

		this.width = width;
		this.height = height;

		BufferedImage img = new BufferedImage(this.width, this.height, BufferedImage.TYPE_INT_ARGB);

		Graphics g = img.createGraphics();
		g.drawImage(this.image, 0, 0, this.width, this.height, null);
		g.dispose();

		this.image = img;
	}

	public void scale(float scaleWidth, float scaleHeight) {

		this.resize((int) (this.width * scaleWidth), (int) (this.height * scaleHeight));
	}

	public int getWidth() {

		return this.width;
	}

	public int getHeight() {

		return this.height;
	}

	public String getPath() {

		return this.path;
	}

	public BufferedImage getImage() {

		return this.image;
	}

	public Sprite cloneResized(int width, int height) {

		Sprite ret = this.clone();

		ret.resize(width, height);

		return ret;
	}
}