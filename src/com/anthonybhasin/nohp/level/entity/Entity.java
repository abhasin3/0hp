package com.anthonybhasin.nohp.level.entity;

import com.anthonybhasin.nohp.math.Point;
import com.anthonybhasin.nohp.math.Position;

public abstract class Entity {

	/**
	 * Stores the positional data of the Entity.
	 * 
	 * Refers to the <b>center point</b>.
	 */
	public Position position;

	protected boolean persistent;

	public Entity() {

		this.position = new Position(0, 0);

		this.persistent = false;
	}

	/**
	 * Use the tick method to update information.
	 * 
	 * There is no guarantee the information will stay updated.
	 * 
	 * For example, an updated position may be reset during a collision.
	 */
	public abstract void tick();

	public void render() {
	}

	public int signedDistanceX(Point point) {

		return (int) (point.x - this.getMidX());
	}

	public int signedDistanceY(Point point) {

		return (int) (point.y - this.getMidY());
	}

	public int distanceSquared(Point point) {

		int distX = this.signedDistanceX(point), distY = this.signedDistanceY(point);

		return distX * distX + distY * distY;
	}

	/**
	 * Try comparing distanceSquared to your expected distance * distance instead to
	 * prevent the sqrt operation.
	 */
	public int distance(Point point) {

		return (int) Math.sqrt(this.distanceSquared(point));
	}

	public int signedDistanceX(Entity other) {

		return this.signedDistanceX(other.position);
	}

	public int signedDistanceY(Entity other) {

		return this.signedDistanceY(other.position);
	}

	public int distanceSquared(Entity other) {

		return this.distanceSquared(other.position);
	}

	/**
	 * Try comparing distanceSquared to your expected distance * distance instead to
	 * prevent the sqrt operation.
	 */
	public int distance(Entity other) {

		return this.distance(other.position);
	}

	public boolean isPersistent() {

		return this.persistent;
	}

	public float getMidX() {

		return this.position.x;
	}

	public float getMidY() {

		return this.position.y;
	}

	public float getMinX() {

		return this.getMidX();
	}

	public float getMinY() {

		return this.getMidY();
	}

	public float getMaxX() {

		return this.getMidX();
	}

	public float getMaxY() {

		return this.getMidY();
	}

	public int getMidXi() {

		return (int) this.getMidX();
	}

	public int getMidYi() {

		return (int) this.getMidY();
	}

	public int getMinXi() {

		return (int) this.getMinX();
	}

	public int getMinYi() {

		return (int) this.getMinY();
	}

	public int getMaxXi() {

		return (int) this.getMaxX();
	}

	public int getMaxYi() {

		return (int) this.getMaxY();
	}

	public int getWidth() {

		return this.getMaxXi() - this.getMinXi();
	}

	public int getHeight() {

		return this.getMaxYi() - this.getMinYi();
	}
}
