package com.spaceshooter.input;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.spaceshooter.core.Camera;
import com.spaceshooter.core.EntityManager;

public class MouseInput extends MouseAdapter{

	public static int MOUSEX, MOUSEY;
	
	protected EntityManager handler;

	Camera camera;

	public MouseInput(EntityManager handler, Camera camera){
		this.handler = handler;
		this.camera = camera;
	}
	public void mousePressed(MouseEvent e) {
		MOUSEX = e.getX();
		MOUSEY = e.getY();
	}
}
