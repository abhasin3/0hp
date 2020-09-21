package com.anthonybhasin.nohp;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Stroke;

import com.anthonybhasin.nohp.level.entity.ImageEntity;
import com.anthonybhasin.nohp.math.Point;
import com.anthonybhasin.nohp.texture.Sprite;

public class Screen {

	public static Graphics2D g;

	public static void setGraphics(Graphics2D g) {

		Screen.g = g;
	}

	@SuppressWarnings("unchecked")
	public static abstract class RenderBuilder<T extends RenderBuilder<T>> {

		protected float x, y;

		protected abstract void draw_();

		public void draw() {

			Screen.g.translate(this.x, this.y);

			this.draw_();

			Screen.g.translate(-this.x, -this.y);
		}

		public T start(float x, float y) {

			this.x = x;
			this.y = y;

			return (T) this;
		}

		public T start(Point point) {

			return this.start(point.x, point.y);
		}

		public T color(Color color) {

			Screen.g.setColor(color);

			return (T) this;
		}

		public T stroke(Stroke stroke) {

			Screen.g.setStroke(stroke);

			return (T) this;
		}

		public T strokeWidth(int width) {

			return this.stroke(new BasicStroke(width));
		}

		public T font(Font font) {

			Screen.g.setFont(font);

			return (T) this;
		}
	}

	public interface RenderDimensions<T extends RenderBuilder<T>> {

		public T dimensions(int width, int height);
	}

	public interface RenderFillable<T extends RenderBuilder<T>> {

		public T fill();
	}

	public static class LineBuilder extends RenderBuilder<LineBuilder> {

		public float x2, y2;

		@Override
		protected void draw_() {

			Screen.g.drawLine(0, 0, (int) (this.x2 - super.x), (int) (this.y2 - super.y));
		}

		public LineBuilder end(float x2, float y2) {

			this.x2 = x2;
			this.y2 = y2;

			return this;
		}

		public LineBuilder end(Point point) {

			return this.end(point.x, point.y);
		}
	}

	public static LineBuilder line() {

		return new LineBuilder();
	}

	public static class RectBuilder extends RenderBuilder<RectBuilder>
			implements RenderDimensions<RectBuilder>, RenderFillable<RectBuilder> {

		public int width, height;

		public boolean fill = false;

		@Override
		public RectBuilder dimensions(int width, int height) {

			this.width = width;
			this.height = height;
			return this;
		}

		@Override
		public RectBuilder fill() {

			this.fill = true;
			return this;
		}

		@Override
		protected void draw_() {

			if (this.fill) {

				Screen.g.fillRect(0, 0, this.width, this.height);
			} else {

				Screen.g.drawRect(0, 0, this.width, this.height);
			}
		}
	}

	public static RectBuilder rect() {

		return new RectBuilder();
	}

	public static class TextBuilder extends RenderBuilder<TextBuilder> {

		public String text;

		public TextBuilder(String text) {

			this.text = text;
		}

		@Override
		protected void draw_() {

			Screen.g.drawString(this.text, 0, 0);
		}
	}

	public static TextBuilder text(String text) {

		return new TextBuilder(text);
	}

	public static class SpriteBuilder extends RenderBuilder<SpriteBuilder> implements RenderDimensions<SpriteBuilder> {

		private int width, height;

		private Sprite sprite;

		public SpriteBuilder(Sprite sprite) {

			this.sprite = sprite;
		}

		@Override
		protected void draw_() {

			Screen.g.drawImage(this.sprite.getImage(), 0, 0, this.width, this.height, null);
		}

		@Override
		public SpriteBuilder dimensions(int width, int height) {

			this.width = width;
			this.height = height;

			return this;
		}
	}

	public static SpriteBuilder sprite(Sprite sprite) {

		return new SpriteBuilder(sprite).dimensions(sprite.getWidth(), sprite.getHeight());
	}

	public static SpriteBuilder sprite(ImageEntity entity) {

		return new SpriteBuilder(entity.getSprite()).start(entity.renderPoint).dimensions(entity.getWidth(),
				entity.getHeight());
	}
}
