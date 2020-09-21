package com.anthonybhasin.nohp.io;

import java.awt.MouseInfo;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.HashSet;
import java.util.Set;

import com.anthonybhasin.nohp.Window;
import com.anthonybhasin.nohp.math.Point;

public class Mouse implements MouseListener, MouseMotionListener, MouseWheelListener {

	private static Set<Integer> activeButtons = new HashSet<Integer>();

	public static int WHEEL_SCROLL_AMOUNT = 0, MOUSE_LEFT_BUTTON_CODE = 1, MOUSE_RIGHT_BUTTON_CODE = 3;

	public static Point position = new Point();

	public static boolean getPressed(int buttonCode) {

		return Mouse.activeButtons.contains(buttonCode);
	}

	public static void resetScroll() {

		Mouse.WHEEL_SCROLL_AMOUNT = 0;
	}

	public static int getNumberOfButtons() {

		return MouseInfo.getNumberOfButtons();
	}

	/**
	 * Constructor for mouse instantiated by the engine. Do not instantiate
	 * yourself.
	 */
	public Mouse() {
	}

//	Mouse

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {

		Mouse.activeButtons.add(e.getButton());
	}

	@Override
	public void mouseReleased(MouseEvent e) {

		Mouse.activeButtons.remove(e.getButton());
	}

//	Mouse Motion

	@Override
	public void mouseDragged(MouseEvent e) {

//		For proper position, dividing by the game scale as well as the resize scale.
//		Also, subtracting away the padding from the resize black bars (displayX and displayY) which also need to be divided by TotalScale to get a proper game (not screen) coordinate.
		Mouse.position.set((e.getX() - Window.displayX) / (Window.scale * Window.resizeScale),
				(e.getY() - Window.displayY) / (Window.scale * Window.resizeScale));
	}

	@Override
	public void mouseMoved(MouseEvent e) {

//		For proper position, dividing by the game scale as well as the resize scale.
//		Also, subtracting away the padding from the resize black bars (displayX and displayY) which also need to be divided by TotalScale to get a proper game (not screen) coordinate.
		Mouse.position.set((e.getX() - Window.displayX) / (Window.scale * Window.resizeScale),
				(e.getY() - Window.displayY) / (Window.scale * Window.resizeScale));
	}

//	Mouse Wheel

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {

		Mouse.WHEEL_SCROLL_AMOUNT = e.getWheelRotation();
	}
}
