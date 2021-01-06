package com.anthonybhasin.nohp.math;

public class Vector2D extends Point2D {

	public static float dot(Vector2D v1, Vector2D v2) {

		return v1.x * v2.x + v1.y * v2.y;
	}

	public static Vector2D invert(Vector2D v) {

		return new Vector2D(-v.x, -v.y);
	}

	public static Vector2D perpendicular(Vector2D v) {

		return new Vector2D(-v.y, v.x);
	}

	public static Vector2D perpendicular2(Vector2D v) {

		return new Vector2D(v.y, -v.x);
	}

	public static Vector2D normal(Vector2D v) {

		float length = v.length();

		return new Vector2D(v.x / length, v.y / length);
	}

	public static Vector2D add(Vector2D v1, Vector2D v2) {

		return new Vector2D(v1.x + v2.x, v1.y + v2.y);
	}

	public static Vector2D subtract(Vector2D v1, Vector2D v2) {

		return new Vector2D(v1.x - v2.x, v1.y - v2.y);
	}

	public static Vector2D multiply(Vector2D v, float mult) {

		return new Vector2D(v.x * mult, v.y * mult);
	}

	public Vector2D() {

		super();
	}

	public Vector2D(float x, float y) {

		super.x = x;
		super.y = y;
	}

	public Vector2D(Point2D point) {

		this(point.x, point.y);
	}

	@Override
	public String toString() {

		return "<" + this.x + ", " + this.y + ">";
	}

	public float length() {

		return (float) Math.sqrt(super.x * super.x + super.y * super.y);
	}

	public Vector2D normalize() {

		float length = this.length();

		if (length != 0) {

			super.set(super.x / length, super.y / length);
		}

		return this;
	}

	public Vector2D add(Vector2D v) {

		super.translate(v.x, v.y);

		return this;
	}

	public Vector2D subtract(Vector2D v) {

		super.translate(-v.x, -v.y);

		return this;
	}

	public Vector2D multiply(float mult) {

		super.set(super.x * mult, super.y * mult);

		return this;
	}
}