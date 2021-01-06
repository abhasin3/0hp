package com.anthonybhasin.nohp.level.entity;

import com.anthonybhasin.nohp.GameSettings;

public interface DebugRenderable {

	/**
	 * Implement to draw debug information about entities.
	 * 
	 * This method will only be called if {@link GameSettings#debugMode} is active
	 */
	public abstract void debugRender();
}
