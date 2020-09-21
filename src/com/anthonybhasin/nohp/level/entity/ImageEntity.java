package com.anthonybhasin.nohp.level.entity;

import com.anthonybhasin.nohp.Screen;
import com.anthonybhasin.nohp.math.Point;
import com.anthonybhasin.nohp.texture.Sprite;

public abstract class ImageEntity extends Entity {

	/**
	 * Stores the render point of the ImageEntity.
	 * 
	 * <p>
	 * Refers to the <b>top left</b> point in the game's coordinate system (not the
	 * screen location) when being rendered (may be impacted by special effects such
	 * as rotation).
	 * 
	 * <p>
	 * Note: the render point is updated after calculations have been completed
	 * (after {@link Entity#tick()} has been called from
	 * {@link com.anthonybhasin.nohp.level.Level#tick()}. Any use prior would refer
	 * to the last snapshot of the render point (based on the previous tick's
	 * positional data).
	 */
	public Point renderPoint;

	protected int width, height;

	/**
	 * Determines whether the image is rendered absolutely on the screen or relative
	 * to the main camera.
	 */
	protected boolean positionRelative;

	protected Sprite sprite;

	public ImageEntity(Sprite sprite) {

		super();

		this.renderPoint = new Point();

		this.positionRelative = true;

		this.sprite = sprite;

		this.width = this.sprite.getWidth();
		this.height = this.sprite.getHeight();
	}

	@Override
	public void render() {

		Screen.sprite(this).draw();
	}

	public boolean isPositionRelative() {

		return this.positionRelative;
	}

	public float getMidX() {

		return super.position.x;
	}

	public float getMidY() {

		return super.position.y;
	}

	public float getMinX() {

		return super.position.x - this.width / 2f;
	}

	public float getMinY() {

		return super.position.y - this.height / 2f;
	}

	public float getMaxX() {

		return super.position.x + this.width / 2f;
	}

	public float getMaxY() {

		return super.position.y + this.height / 2f;
	}

	public int getMidXi() {

		return (int) this.getMidX();
	}

	public int getMidYi() {

		return (int) this.getMidY();
	}

	public int getMinXi() {

		return this.getMidXi() - this.width / 2;
	}

	public int getMinYi() {

		return this.getMidYi() - this.height / 2;
	}

	public int getMaxXi() {

		return this.getMidXi() + this.width / 2;
	}

	public int getMaxYi() {

		return this.getMidYi() + this.height / 2;
	}

	@Override
	public int getWidth() {

		return this.width;
	}

	@Override
	public int getHeight() {

		return this.height;
	}

	public Sprite getSprite() {

		return this.sprite;
	}
}
