package com.anthonybhasin.nohp;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JFrame;

import com.anthonybhasin.nohp.io.Keyboard;
import com.anthonybhasin.nohp.io.Mouse;

public class Window {

	public static int displayX, displayY;

	public static float resizeScale;

	public static String title;

	public static JFrame frame;
	public static Canvas canvas;

	public static void create(int width, int height, int scale, String title) {

		Window.title = title;

		Dimension scaledSize = new Dimension((int) (width * scale), (int) (height * scale));

		Window.frame = new JFrame();

		Window.frame.setTitle(Window.title);
		Window.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Window.frame.setVisible(true);

		Window.frame.requestFocus();

		Window.canvas = new Canvas();

		Window.canvas.setSize(scaledSize);

		Window.canvas.addKeyListener(new Keyboard());

		Mouse mouse = new Mouse();

		Window.canvas.addMouseListener(mouse);
		Window.canvas.addMouseMotionListener(mouse);
		Window.canvas.addMouseWheelListener(mouse);

		Window.frame.add(Window.canvas);
		Window.frame.pack();

		Window.frame.setMinimumSize(Window.frame.getSize());

		Window.frame.setLocationRelativeTo(null);

		Window.frame.addComponentListener(new ComponentAdapter() {

			public void componentResized(ComponentEvent e) {

				Window.updateDisplayBounds();
			}
		});

		Window.canvas.createBufferStrategy(3);
	}

	public static void updateDisplayBounds() {

		double fullWidth = GameSettings.width * GameSettings.scale,
				fullHeight = GameSettings.height * GameSettings.scale;

		int canvasWidth = Window.canvas.getWidth(), canvasHeight = Window.canvas.getHeight();

		if (canvasHeight / (float) canvasWidth < GameSettings.height / (float) GameSettings.width) {
//			Use height as scale
			Window.resizeScale = canvasHeight / (float) fullHeight;
			int scaledWidth = (int) (fullWidth * Window.resizeScale);
			Window.displayX = (canvasWidth - scaledWidth) / 2;
			Window.displayY = 0;
		} else {
//			Use width as scale
			Window.resizeScale = canvasWidth / (float) fullWidth;
			Window.displayX = 0;
			int scaledHeight = (int) (fullHeight * Window.resizeScale);
			Window.displayY = (canvasHeight - scaledHeight) / 2;
		}
	}
}
