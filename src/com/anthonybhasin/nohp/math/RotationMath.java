package com.anthonybhasin.nohp.math;

import com.anthonybhasin.nohp.Game;
import com.anthonybhasin.nohp.GameSettings;
import com.anthonybhasin.nohp.level.entity.Camera;

public class RotationMath {

	/**
	 * Pre-calculated values for each integer degree.
	 */
	public static final float[] SIN = new float[360], COS = new float[360], ROT_ANGLE = new float[360];

	static {

		for (int i = 0; i < 360; i++) {

			double rotAngle = Math.toRadians(i);

			RotationMath.ROT_ANGLE[i] = (float) rotAngle;

			RotationMath.SIN[i] = (float) Math.sin(rotAngle);

			RotationMath.COS[i] = (float) Math.cos(rotAngle);
		}
	}

	public static Point2D getUnrotatedCoordinate(float x, float y, Point2D anchor, Camera camera) {

		x -= anchor.x;
		y -= anchor.y;

		float sinRot = RotationMath.SIN[camera.rotation], cosRot = RotationMath.COS[camera.rotation],
				xUnrot = cosRot * x + sinRot * y, yUnrot = -sinRot * x + cosRot * y;

		return new Point2D(xUnrot, yUnrot).add(anchor);
	}

	public static Point2D getUnrotatedCoordinate(Point2D point, Point2D anchor, Camera camera) {

		return RotationMath.getUnrotatedCoordinate(point.x, point.y, anchor, camera);
	}

	public static Point2D getMainCameraUnrotatedCoordinate(float x, float y) {

//		Anchor is the screen center point.
		Point2D anchor = new Point2D(GameSettings.width / 2f, GameSettings.height / 2f);

		return RotationMath.getUnrotatedCoordinate(x, y, anchor, Game.instance.level.camera);
	}

	public static Point2D getMainCameraUnrotatedCoordinate(Point2D point) {

		return RotationMath.getMainCameraUnrotatedCoordinate(point.x, point.y);
	}

	public static Point2D getRotatedCoordinate(float x, float y, Point2D anchor, Camera camera) {

		x -= anchor.x;
		y -= anchor.y;

		float sinRot = RotationMath.SIN[camera.rotation], cosRot = RotationMath.COS[camera.rotation],
				xRot = cosRot * x - sinRot * y, yRot = sinRot * x + cosRot * y;

		return new Point2D(xRot, yRot).add(anchor);
	}

	public static Point2D getRotatedCoordinate(Point2D point, Point2D anchor, Camera camera) {

		return RotationMath.getRotatedCoordinate(point.x, point.y, anchor, camera);
	}

	public static Point2D getMainCameraRotatedCoordinate(float x, float y) {

//		Anchor is the screen center point.
		Point2D anchor = new Point2D(GameSettings.width / 2f, GameSettings.height / 2f);

		return RotationMath.getRotatedCoordinate(x, y, anchor, Game.instance.level.camera);
	}

	public static Point2D getMainCameraRotatedCoordinate(Point2D point) {

		return RotationMath.getMainCameraRotatedCoordinate(point.x, point.y);
	}

	public static float getRotatedXDirection(float x, float y, Camera camera) {

		return x * RotationMath.COS[camera.rotation] + y * RotationMath.SIN[camera.rotation];
	}

	public static float getRotatedYDirection(float x, float y, Camera camera) {

		return y * RotationMath.COS[camera.rotation] - x * RotationMath.SIN[camera.rotation];
	}

	public static Vector2D getRotatedDirection(Vector2D direction, Camera camera) {

		return new Vector2D(RotationMath.getRotatedXDirection(direction.x, direction.y, camera),
				RotationMath.getRotatedYDirection(direction.x, direction.y, camera));
	}
}
