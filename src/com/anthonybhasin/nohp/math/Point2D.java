package com.anthonybhasin.nohp.math;

public class Point2D {

	public static Point2D at(int x, int y) {

		return new Point2D(x, y);
	}

	public static Point2D add(Point2D p1, Point2D p2) {

		return new Point2D(p1.x + p2.x, p1.y + p2.y);
	}

	public static Point2D subtract(Point2D p1, Point2D p2) {

		return new Point2D(p1.x - p2.x, p1.y - p2.y);
	}

	public float x, y;

	public Point2D(float x, float y) {

		this.x = x;
		this.y = y;
	}

	public Point2D() {

		this(0, 0);
	}

	public Point2D(Point2D point) {

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

	public void set(Point2D point) {

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

	public Point2D add(Point2D point) {

		this.translate(point.x, point.y);

		return this;
	}

	public Point2D subtract(Point2D point) {

		this.translate(-point.x, -point.y);

		return this;
	}

	public void move(Vector2D dir) {

		this.translate(dir.x, dir.y);
	}

	public float signedDistanceX(Point2D other) {

		return other.x - this.x;
	}

	public float signedDistanceY(Point2D other) {

		return other.y - this.y;
	}

	public float distanceSquared(Point2D other) {

		float distX = this.signedDistanceX(other), distY = this.signedDistanceY(other);

		return distX * distX + distY * distY;
	}

	/**
	 * Try comparing distanceSquared to your expected distance * distance instead to
	 * avoid the sqrt operation.
	 */
	public float distance(Point2D other) {

		return (float) Math.sqrt(this.distanceSquared(other));
	}
}