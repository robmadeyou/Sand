package com.gmail.robmadeyou;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.nio.FloatBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.LWJGLUtil;
import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GLContext;

import static org.lwjgl.opengl.ARBTransposeMatrix.*;
import static org.lwjgl.opengl.GL11.*;

public class JApplet extends Applet {

	/** The Canvas where the LWJGL Display is added */
	Canvas display_parent;

	/** Thread which runs the main game loop */
	Thread gameThread;

	/** is the game loop running */
	boolean running;

	/**
	 * Once the Canvas is created its add notify method will call this method to
	 * start the LWJGL Display and game loop in another thread.
	 */
	public void startLWJGL() {
		gameThread = new Thread() {
			public void run() {
				running = true;
				Main.applet = true;
				try {
					Display.setParent(display_parent);
					Display.create();
				} catch (LWJGLException e) {
					e.printStackTrace();
				}
				gameLoop();
			}
		};
		gameThread.start();
	}


	/**
	 * Tell game loop to stop running, after which the LWJGL Display will be destoryed.
	 * The main thread will wait for the Display.destroy() to complete
	 */
	private void stopLWJGL() {
		running = false;
		try {
			gameThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void start() {

	}

	public void stop() {

	}

	/**
	 * Applet Destroy method will remove the canvas, before canvas is destroyed it will notify
	 * stopLWJGL() to stop main game loop and to destroy the Display
	 */
	public void destroy() {
		remove(display_parent);
		super.destroy();
		System.out.println("Clear up");
	}

	public void init() {
		setLayout(new BorderLayout());
		try {
			display_parent = new Canvas() {
				public void addNotify() {
					super.addNotify();
					startLWJGL();
				}
				public void removeNotify() {
					stopLWJGL();
					super.removeNotify();
				}
			};
			display_parent.setSize(getWidth(),getHeight());
			add(display_parent);
			display_parent.setFocusable(true);
			display_parent.requestFocus();
			display_parent.setIgnoreRepaint(true);
			//setResizable(true);
			setVisible(true);
		} catch (Exception e) {
			System.err.println(e);
			throw new RuntimeException("Unable to create display");
		}
	}

	public void gameLoop() {
			Main.main(null);
	}

	public void drawLoop() {
		
	}

	protected void initGL() {
		
	}
}
