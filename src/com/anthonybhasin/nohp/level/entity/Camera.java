package com.anthonybhasin.nohp.level.entity;

import java.awt.BasicStroke;
import java.awt.Color;

import com.anthonybhasin.nohp.Game;
import com.anthonybhasin.nohp.GameSettings;
import com.anthonybhasin.nohp.Screen;
import com.anthonybhasin.nohp.level.entity.CameraRotatable.RotationMode;
import com.anthonybhasin.nohp.level.entity.error.UnsupportedRotationModeException;
import com.anthonybhasin.nohp.math.Bounds;
import com.anthonybhasin.nohp.math.Bounds.CameraView;
import com.anthonybhasin.nohp.math.Point2D;
import com.anthonybhasin.nohp.math.RotationMath;

public class Camera extends Entity {

	/**
	 * The camera's rotation in degrees [0, 360).
	 */
	public int rotation;

	/**
	 * Create a Camera object.
	 */
	public Camera() {

		super();

		this.rotation = 0;
	}

	@Override
	public void tick() {
	}

	@Override
	public void render() {

		if (GameSettings.engineDebugMode) {

			int crosshairLength = GameSettings.width / 40;

			Camera levelCamera = Game.instance.level.camera;

			boolean equalsLevelCamera = this.equals(levelCamera);

			if (equalsLevelCamera) {

				Screen.unrotate(levelCamera);
			}

			Point2D centerRenderPoint = new Point2D(super.position.x - levelCamera.getMinX(),
					super.position.y - levelCamera.getMinY());

//			Red line
			Screen.line().start(centerRenderPoint).end(centerRenderPoint.x, centerRenderPoint.y - crosshairLength)
					.color(Color.RED).strokeWidth(1).draw();
//			Blue line
			Screen.line().start(centerRenderPoint).end(centerRenderPoint.x + crosshairLength, centerRenderPoint.y)
					.color(Color.BLUE).strokeWidth(1).draw();

			Screen.text(super.position.toString()).start(centerRenderPoint.x, centerRenderPoint.y + 20)
					.font(GameSettings.engineFont).color(Color.WHITE).draw();

			if (!equalsLevelCamera) {

				Point2D topLeftRenderPoint = new Point2D(this.getMinX() - levelCamera.getMinX(),
						this.getMinY() - levelCamera.getMinY());

				BasicStroke dashed = new BasicStroke(1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 15.0f,
						new float[] { 15.0f }, 0.0f);

				Screen.rect().start(topLeftRenderPoint).dimensions(super.getWidth(), super.getHeight())
						.color(Color.YELLOW).stroke(dashed).draw();
			} else {

				Screen.rotate(levelCamera);
			}
		}
	}

	/**
	 * Returns the {@link Bounds} of the {@link Entity} in the camera coordinate
	 * system (post rotation and translation).
	 */
	public Bounds getCameraCoordinates(Entity entity) {

		float entityMinX, entityMaxX, entityMinY, entityMaxY;

		if (entity instanceof CameraRotatable) {

			CameraRotatable rotatable = (CameraRotatable) entity;

			RotationMode rm = rotatable.getRotationMode();

			switch (rm) {
			case NORMAL_ROTATION:

				entityMinX = entity.getMinX();
				entityMaxX = entity.getMaxX();
				entityMinY = entity.getMinY();
				entityMaxY = entity.getMaxY();

				if (entity.isPositionRelative()) {

					entityMinX -= this.getMinX();
					entityMaxX -= this.getMinX();
					entityMinY -= this.getMinY();
					entityMaxY -= this.getMinY();
				}

				return new Bounds(CameraView.ROTATED_LAYER,
						new float[] { entityMinX, entityMaxX, entityMinY, entityMaxY },
						new Point2D(entityMinX, entityMinY), new Point2D(entityMaxX, entityMinY),
						new Point2D(entityMaxX, entityMaxY), new Point2D(entityMinX, entityMaxY));
			case BILLBOARD:

				int width = entity.getWidth(), height = entity.getHeight();

				Point2D centerPoint = new Point2D(entity.position);

				if (entity.isPositionRelative()) {

					centerPoint.translate(-this.getMinX(), -this.getMinY());
				}

				centerPoint = RotationMath.getMainCameraRotatedCoordinate(centerPoint);

				Point2D square0BB = Point2D.add(centerPoint, new Point2D(-width / 2, -height / 2)),
						square1BB = Point2D.add(centerPoint, new Point2D(width / 2, -height / 2)),
						square2BB = Point2D.add(centerPoint, new Point2D(width / 2, height / 2)),
						square3BB = Point2D.add(centerPoint, new Point2D(-width / 2, height / 2));

				return new Bounds(CameraView.UNROTATED_LAYER,
						new float[] { square0BB.x, square2BB.x, square0BB.y, square2BB.y }, square0BB, square1BB,
						square2BB, square3BB);
			default:
				throw new UnsupportedRotationModeException(rm);
			}
		} else {

			entityMinX = entity.getMinX();
			entityMaxX = entity.getMaxX();
			entityMinY = entity.getMinY();
			entityMaxY = entity.getMaxY();
		}

		if (entity.isPositionRelative()) {

			entityMinX -= this.getMinX();
			entityMaxX -= this.getMinX();
			entityMinY -= this.getMinY();
			entityMaxY -= this.getMinY();
		}

		return new Bounds(CameraView.UNROTATED_LAYER, new float[] { entityMinX, entityMaxX, entityMinY, entityMaxY },
				new Point2D(entityMinX, entityMinY), new Point2D(entityMaxX, entityMinY),
				new Point2D(entityMaxX, entityMaxY), new Point2D(entityMinX, entityMaxY));
	}

	public boolean canSee(Entity entity) {

		Bounds bounds = entity.bounds;

		bounds = bounds.assertCameraView(CameraView.UNROTATED_LAYER);

		float entityMinX = bounds.getMinX(), entityMinY = bounds.getMinY(), entityMaxX = bounds.getMaxX(),
				entityMaxY = bounds.getMaxY();

		return !(entityMaxX < 0 || entityMinX > GameSettings.width || entityMaxY < 0
				|| entityMinY > GameSettings.height);
	}

	@Override
	public float getMinX() {

		return super.getMidX() - GameSettings.width / 2f;
	}

	@Override
	public float getMinY() {

		return super.getMidY() - GameSettings.height / 2f;
	}

	@Override
	public float getMaxX() {

		return super.getMidX() + GameSettings.width / 2f;
	}

	@Override
	public float getMaxY() {

		return super.getMidY() + GameSettings.height / 2f;
	}
}
