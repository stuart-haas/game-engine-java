package com.spaceshooter.input;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

public class KeyInput extends KeyAdapter{
	
	static KeyInput instance;
	
	public static KeyInput getInstance() {
		if(instance == null) {
			instance = new KeyInput();
			return instance;
		}
		return instance;
		
	}

	Map<Integer, Boolean> pressed;

	public KeyInput() {
		pressed = new HashMap<Integer, Boolean>();
	}
	
	public boolean isKeyPressed(int keycode) {
		Boolean isKeyPressed = pressed.get(keycode);
		if(isKeyPressed != null) {
			return isKeyPressed;
		}
		return false;
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		pressed.put(key, true);
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		pressed.remove(key);
	}
}
