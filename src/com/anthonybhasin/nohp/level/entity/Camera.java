package com.anthonybhasin.nohp.level.entity;

import java.awt.BasicStroke;
import java.awt.Color;

import com.anthonybhasin.nohp.Game;
import com.anthonybhasin.nohp.GameSettings;
import com.anthonybhasin.nohp.Screen;
import com.anthonybhasin.nohp.Window;
import com.anthonybhasin.nohp.math.Point;

public class Camera extends Entity {

	/**
	 * The camera's rotation in degrees.
	 */
	protected int rotation;

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

			int crosshairLength = Window.width / 40;

			Camera levelCamera = Game.instance.level.camera;

			Point centerRenderPoint = new Point(super.position.x - levelCamera.getMinX(),
					super.position.y - levelCamera.getMinY());

//			Red line
			Screen.line().start(centerRenderPoint).end(centerRenderPoint.x, centerRenderPoint.y - crosshairLength)
					.color(Color.RED).strokeWidth(1).draw();
//			Blue line
			Screen.line().start(centerRenderPoint).end(centerRenderPoint.x + crosshairLength, centerRenderPoint.y)
					.color(Color.BLUE).strokeWidth(1).draw();

			Screen.text(super.position.toString()).start(centerRenderPoint.x, centerRenderPoint.y + 20)
					.font(GameSettings.engineFont).color(Color.WHITE).draw();

			if (!this.equals(levelCamera)) {

				Point topLeftRenderPoint = new Point(this.getMinX() - levelCamera.getMinX(),
						this.getMinY() - levelCamera.getMinY());

				BasicStroke dashed = new BasicStroke(1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 15.0f,
						new float[] { 15.0f }, 0.0f);

				Screen.rect().start(topLeftRenderPoint).dimensions(super.getWidth(), super.getHeight())
						.color(Color.YELLOW).stroke(dashed).draw();
			}
		}
	}

	public boolean canSee(Entity entity) {

		if (entity.getMaxX() < this.getMinX() || entity.getMinX() > this.getMaxX() || entity.getMaxY() < this.getMinY()
				|| entity.getMinY() > this.getMaxY()) {

			return false;
		}

		return true;
	}

	public void setRenderPoint(ImageEntity entity) {

		float renderX = 0, renderY = 0;

		boolean positionRelative = entity.positionRelative;

		renderX = entity.getMinX();
		renderY = entity.getMinY();

		if (positionRelative) {

			renderX -= this.getMinX();
			renderY -= this.getMinY();
		}

		entity.renderPoint.set(renderX, renderY);
	}

	@Override
	public float getMinX() {

		return super.getMidX() - Window.width / 2f;
	}

	@Override
	public float getMinY() {

		return super.getMidY() - Window.height / 2f;
	}

	@Override
	public float getMaxX() {

		return super.getMidX() + Window.width / 2f;
	}

	@Override
	public float getMaxY() {

		return super.getMidY() + Window.height / 2f;
	}

	public int getRotation() {

		return this.rotation;
	}
}
