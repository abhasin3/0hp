package com.anthonybhasin.nohp.level.entity.error;

import com.anthonybhasin.nohp.level.entity.CameraRotatable.RotationMode;

public class UnsupportedRotationModeException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UnsupportedRotationModeException(RotationMode rm) {

		super("The rotation mode " + rm.toString() + " (" + rm.name() + ") is unsupported for this operation!");
	}
}
