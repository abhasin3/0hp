package com.anthonybhasin.nohp.level.entity;

public interface CameraRotatable {

	public enum RotationMode {
		/**
		 * {@link Camera} rotation is applied.
		 */
		NORMAL_ROTATION(),
		/**
		 * {@link Camera} rotation is applied, but the entity is always drawn in an
		 * upright position.
		 */
		BILLBOARD();

		private RotationMode() {
		}
	}

	public RotationMode getRotationMode();
}
