package com.anthonybhasin.nohp;

import java.awt.Font;

import com.anthonybhasin.nohp.texture.Sprite;

public class GameSettings {

	/**
	 * If enabled, the engine itself will perform debug rendering.
	 */
	public static boolean engineDebugMode = false;

	/**
	 * If enabled, the engine will tell the game to perform debug rendering.
	 */
	public static boolean debugMode = false;

	public static int windowWidth = 800, windowHeight = 450, windowScale = 1;

	/**
	 * If maxTPS is updated after {@link Game#create()} has been called,
	 * {@link Game#updateMaxTPS()} should be called.
	 */
	public static int maxTPS = 25;

	public static String windowTitle = "0HP Game Engine";

	public static Sprite nullSprite = new Sprite(), nullTextured1x1Sprite = new Sprite(1, 1, 0xFFFF00FE);

	/**
	 * The standard font used throughout the engine (most likely for debug displays
	 * when {@link GameSettings#engineDebugMode} is true}.
	 */
	public static Font engineFont = new Font("Verdana", 0, 12);
}
