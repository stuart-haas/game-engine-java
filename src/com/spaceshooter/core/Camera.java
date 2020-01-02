package com.spaceshooter.core;

import com.spaceshooter.math.Math2;
import com.spaceshooter.math.Vector2;

public class Camera {

	public static int X, Y, OFFSET_X, OFFSET_Y;
	
	static Camera instance;
	
	public static Camera getInstance(float x, float y, float scrollSpeed) {
		if(instance == null) {
			instance = new Camera(x, y, scrollSpeed);
			return instance;
		}
		return instance;
		
	}
	
	public Vector2 position = new Vector2();
	
	float scrollSpeed;

	public Camera(float x, float y, float scrollSpeed) {
		position.set(x, y);
		this.scrollSpeed = scrollSpeed;
	}
	
	public void update(Vector2 target) {
		
		OFFSET_X = (int) (target.getX() - Camera.X);
		OFFSET_Y = (int) (target.getY() - Camera.Y);

		position.setX(position.getX() + ((target.getX() - Game.CANVAS_WIDTH / 2) - position.getX()) * scrollSpeed);
		position.setY(position.getY() + ((target.getY() - Game.CANVAS_HEIGHT / 2) - position.getY()) * scrollSpeed);
		
		position.setX(Math2.clamp(position.getX(), Game.MAP_X, Game.MAP_WIDTH - Game.WINDOW_WIDTH));
		position.setY(Math2.clamp(position.getY(), Game.MAP_Y, ((Game.MAP_HEIGHT - Game.WINDOW_HEIGHT) + (Game.WINDOW_HEIGHT - Game.CANVAS_HEIGHT))));
		
		X = (int) position.getX();
		Y = (int) position.getY();
	}
	
	public static boolean inViewPort(Vector2 position) {
		if(
			position.getX() < Camera.OFFSET_X - Game.CANVAS_WIDTH / 2 ||
			position.getX() > Camera.X + Game.CANVAS_WIDTH ||
			position.getY() < Camera.OFFSET_Y - Game.CANVAS_HEIGHT / 2 ||
			position.getY() > Camera.Y + Game.CANVAS_HEIGHT
		) {
			return false;
		}
		return true;	
	}
}
