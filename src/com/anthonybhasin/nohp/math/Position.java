package com.anthonybhasin.nohp.math;

public class Position extends Point2D {

	/**
	 * Refers to the z-ordering index for rendering.
	 */
	public int zIndex;

	public Position(float x, float y, int zIndex) {

		super(x, y);

		this.zIndex = zIndex;
	}

	public Position(float x, float y) {

		this(x, y, 0);
	}

	public Position() {

		this(0, 0, 0);
	}

	public void setZIndex(int zIndex) {

		this.zIndex = zIndex;
	}

	public void setPosition(float x, float y, int zIndex) {

		super.x = x;
		super.y = y;

		this.zIndex = zIndex;
	}
}
