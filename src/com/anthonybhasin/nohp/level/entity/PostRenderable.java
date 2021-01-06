package com.anthonybhasin.nohp.level.entity;

public interface PostRenderable {

	/**
	 * Implement to draw to the screen at the end of
	 * {@link com.anthonybhasin.nohp.level.Level#render()}.
	 * 
	 * Useful for drawing overlays after a level's environment and entities have
	 * drawn themselves to the {@link com.anthonybhasin.nohp.Screen}.
	 */
	public abstract void postRender();
}
