package com.anthonybhasin.nohp.math;

import java.util.Arrays;

/**
 * Stores 4 points representing a square boundary of any rotation. Provides
 * helper methods to easily access min-max.
 */
public class Bounds {

	public enum CameraView {

		UNROTATED_LAYER(), ROTATED_LAYER();

		private CameraView() {
		}
	}

	private CameraView cameraView;

	private Point2D[] points;

	private float minX, maxX, minY, maxY;

	public Bounds(CameraView cameraView, Point2D... points) {

		if (points.length != 4) {

			throw new IllegalArgumentException("Bounds constructor takes a points array of size 4 only!");
		}

		this.cameraView = cameraView;

		this.points = points;

		Point2D square0 = this.points[0], square1 = this.points[1], square2 = this.points[2], square3 = this.points[3];

		this.minX = Math.min(Math.min(Math.min(square0.x, square1.x), square2.x), square3.x);
		this.maxX = Math.max(Math.max(Math.max(square0.x, square1.x), square2.x), square3.x);

		this.minY = Math.min(Math.min(Math.min(square0.y, square1.y), square2.y), square3.y);
		this.maxY = Math.max(Math.max(Math.max(square0.y, square1.y), square2.y), square3.y);
	}

	public Bounds(CameraView cameraView, float[] knownMinMaxs, Point2D... points) {

		if (points.length != 4) {

			throw new IllegalArgumentException("Bounds constructor takes a points array of size 4 only!");
		}

		this.cameraView = cameraView;

		this.points = points;

		this.minX = knownMinMaxs[0];
		this.maxX = knownMinMaxs[1];

		this.minY = knownMinMaxs[2];
		this.maxY = knownMinMaxs[3];
	}

	@Override
	public String toString() {
//		TODO convert midX and midY to vars if actually kjeepying
		return "Bounds: {points=" + Arrays.toString(this.points) + ", minX=" + this.minX + ", midX=" + this.getMidX()
				+ ", maxX=" + this.maxX + ", minY=" + this.minY + ", midY=" + this.getMidY() + ", maxY=" + this.maxY
				+ "}";
	}

	Bounds alreadyCalculated = null;

	public Bounds toCameraView(CameraView view) {

		if (this.cameraView.equals(view)) {

			return this;
		}

		if (this.alreadyCalculated == null) {

			if (this.cameraView.equals(CameraView.ROTATED_LAYER) && view.equals(CameraView.UNROTATED_LAYER)) {

				Point2D square0 = RotationMath.getMainCameraRotatedCoordinate(this.getPoint(0)),
						square1 = RotationMath.getMainCameraRotatedCoordinate(this.getPoint(1)),
						square2 = RotationMath.getMainCameraRotatedCoordinate(this.getPoint(2)),
						square3 = RotationMath.getMainCameraRotatedCoordinate(this.getPoint(3));

				this.alreadyCalculated = new Bounds(view, square0, square1, square2, square3);
			} else if (this.cameraView.equals(CameraView.UNROTATED_LAYER) && view.equals(CameraView.ROTATED_LAYER)) {

//				TODO - this next!

				Point2D square0 = RotationMath.getMainCameraUnrotatedCoordinate(this.getPoint(0)),
						square1 = RotationMath.getMainCameraUnrotatedCoordinate(this.getPoint(1)),
						square2 = RotationMath.getMainCameraUnrotatedCoordinate(this.getPoint(2)),
						square3 = RotationMath.getMainCameraUnrotatedCoordinate(this.getPoint(3));

				this.alreadyCalculated = new Bounds(view, square0, square1, square2, square3);
			}
		}

		return this.alreadyCalculated;
	}

	public Bounds assertCameraView(CameraView view) {

		if (!this.cameraView.equals(view)) {

			return this.toCameraView(view);
		}

		return this;
	}

	/**
	 * Gets the {@link Point2D} at the specified index. Enforces read-only property
	 * of this class.
	 * 
	 * <pre>
	 * point0	point1
	 * point3	point2
	 * </pre>
	 * 
	 * Note that points can be rotated. For example, point0 always refers to the
	 * original top left point, but after rotation it may appear elsewhere!
	 */
	public Point2D getPoint(int index) {

		return new Point2D(this.points[index]);
	}

	public float getMinX() {

		return this.minX;
	}

	public float getMidX() {

		return this.minX + (this.maxX - this.minX) / 2;
	}

	public float getMaxX() {

		return this.maxX;
	}

	public float getMinY() {

		return this.minY;
	}

	public float getMidY() {

		return this.minY + (this.maxY - this.minY) / 2;
	}

	public float getMaxY() {

		return this.maxY;
	}

	public CameraView getCameraView() {

		return this.cameraView;
	}
}
