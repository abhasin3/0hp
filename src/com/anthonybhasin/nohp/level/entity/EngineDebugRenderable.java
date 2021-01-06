package com.anthonybhasin.nohp.level.entity;

import com.anthonybhasin.nohp.GameSettings;

public interface EngineDebugRenderable {

	/**
	 * Engine will implement to draw debug information about entities.
	 * 
	 * This method will only be called if {@link GameSettings#engineDebugMode} is
	 * active
	 */
	public abstract void engineDebugRender();
}
