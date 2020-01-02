package com.spaceshooter.input;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseInput extends MouseAdapter{

	public static int MOUSE_X, MOUSE_Y;
	
	static MouseInput instance;
	
	public static MouseInput getInstance() {
		if(instance == null) {
			instance = new MouseInput();
			return instance;
		}
		return instance;
	}

	public MouseInput() {}
	
	public void mousePressed(MouseEvent e) {
		MOUSE_X = e.getX();
		MOUSE_Y = e.getY();
	}
}
