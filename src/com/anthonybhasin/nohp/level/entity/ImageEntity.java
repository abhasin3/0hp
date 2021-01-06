package com.anthonybhasin.nohp.level.entity;

import java.awt.Color;

import com.anthonybhasin.nohp.Screen;
import com.anthonybhasin.nohp.math.Bounds;
import com.anthonybhasin.nohp.math.Bounds.CameraView;
import com.anthonybhasin.nohp.texture.Sprite;

public abstract class ImageEntity extends Entity implements CameraRotatable, EngineDebugRenderable {

	protected int width, height;

	protected Sprite sprite;

	protected RotationMode rotationMode;

	public ImageEntity(Sprite sprite) {

		super();

		this.sprite = sprite;

		this.width = this.sprite.getWidth();
		this.height = this.sprite.getHeight();

		this.rotationMode = RotationMode.NORMAL_ROTATION;
	}

	@Override
	public void render() {

		Screen.sprite(this).draw();
	}

	@Override
	public void engineDebugRender() {

		Bounds boundsUnrot = this.bounds.toCameraView(CameraView.UNROTATED_LAYER);

		Screen.rect().start(boundsUnrot.getMinX(), boundsUnrot.getMinY())
				.dimensions((int) (boundsUnrot.getMaxX() - boundsUnrot.getMinX()),
						(int) (boundsUnrot.getMaxY() - boundsUnrot.getMinY()))
				.color(Color.ORANGE).cameraView(boundsUnrot.getCameraView()).draw();

		Bounds boundsRot = this.bounds.toCameraView(CameraView.ROTATED_LAYER);

		Screen.rect().start(boundsRot.getMinX(), boundsRot.getMinY())
				.dimensions((int) (boundsRot.getMaxX() - boundsRot.getMinX()),
						(int) (boundsRot.getMaxY() - boundsRot.getMinY()))
				.color(Color.YELLOW).cameraView(boundsRot.getCameraView()).draw();
	}

	@Override
	public RotationMode getRotationMode() {

		return rotationMode;
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
