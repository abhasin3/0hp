package com.anthonybhasin.nohp.math;

public class Point {

	public static Point add(Point p1, Point p2) {

		return new Point(p1.x + p2.x, p1.y + p2.y);
	}

	public static Point subtract(Point p1, Point p2) {

		return new Point(p1.x - p2.x, p1.y - p2.y);
	}

	public float x, y;

	public Point(float x, float y) {

		this.x = x;
		this.y = y;
	}

	public Point() {

		this(0, 0);
	}

	public Point(Point point) {

		this(point.x, point.y);
	}

	@Override
	public String toString() {

		return "(" + this.x + ", " + this.y + ")";
	}

	public void set(float x, float y) {

		this.x = x;
		this.y = y;
	}

	public void set(Point point) {

		this.x = point.x;
		this.y = point.y;
	}

	public void translateX(float dx) {

		this.x += dx;
	}

	public void translateY(float dy) {

		this.y += dy;
	}

	/**
	 * 
	 * @param dx delta x
	 * @param dy delta y
	 */
	public void translate(float dx, float dy) {

		this.x += dx;
		this.y += dy;
	}

	public void move(Vector2D dir) {

		this.translate(dir.x, dir.y);
	}

	public float signedDistanceX(Point other) {

		return other.x - this.x;
	}

	public float signedDistanceY(Point other) {

		return other.y - this.y;
	}

	public float distanceSquared(Point other) {

		float distX = this.signedDistanceX(other), distY = this.signedDistanceY(other);

		return distX * distX + distY * distY;
	}

	/**
	 * Try comparing distanceSquared to your expected distance * distance instead to
	 * avoid the sqrt operation.
	 */
	public float distance(Point other) {

		return (float) Math.sqrt(this.distanceSquared(other));
	}
}