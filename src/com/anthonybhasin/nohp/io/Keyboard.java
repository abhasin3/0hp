package com.anthonybhasin.nohp.io;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;

public class Keyboard implements KeyListener {

	private static HashSet<Integer> activeKeys = new HashSet<Integer>();

	public static boolean getPressed(int keyCode) {

		return Keyboard.activeKeys.contains(keyCode);
	}

	/**
	 * Constructor for keyboard instantiated by the engine. Do not instantiate
	 * yourself.
	 */
	public Keyboard() {
	}

	@Override
	public void keyPressed(KeyEvent e) {

		Keyboard.activeKeys.add(e.getKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent e) {

		Keyboard.activeKeys.remove(e.getKeyCode());
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}
}
