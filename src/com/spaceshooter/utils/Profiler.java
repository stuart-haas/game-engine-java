package com.spaceshooter.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

import com.spaceshooter.core.Camera;
import com.spaceshooter.core.Game;
import com.spaceshooter.entity.Entity;
import com.spaceshooter.core.EntityManager;
import com.spaceshooter.input.MouseInput;

public class Profiler {
	
	ArrayList<Info> info = new ArrayList<Info>();
	EntityManager entityManager;
	Entity target;
	int x, y;
	
	class Info {
		
		public String label;
		public int value;
		
		private Info(String label, int value) {
			this.label = label;
			this.value = value;
		}
	}

	public Profiler(int x, int y, ID id) {
		this.x = x;
		this.y = y;
		entityManager = EntityManager.getInstance();
		target = entityManager.getEntityById(id);
	}
	
	public void render(Graphics g) {

		double px = target.position.getX();
		double py = target.position.getY();
		double rotation = Math.round(target.getRotation());
		
		g.setColor(Color.blue);
		g.fillRect(x, y, 150, 300);
		
		Font f = new Font("arial", 1, 12);		
		g.setColor(Color.white);
		g.setFont(f);
		g.drawString("Global X: " + px, x + 4, y + 14);
		g.drawString("Global Y: " + py, x + 4, y + 28);
		g.drawString("Camera X: " + Camera.X, x + 4, y + 42);
		g.drawString("Camera Y: " + Camera.Y, x + 4, y + 56);
		g.drawString("Offset X: " + Camera.OFFSET_X, x + 4, y + 70);
		g.drawString("Offset Y: " + Camera.OFFSET_Y, x + 4, y + 84);
		g.drawString("Mouse X: " + MouseInput.MOUSE_X, x + 4, y + 98);
		g.drawString("Mouse Y: " + MouseInput.MOUSE_Y, x + 4, y + 112);
		g.drawString("Image Width: " + Game.IMAGE_WIDTH, x + 4, y + 126);
		g.drawString("Image Height: " + Game.IMAGE_HEIGHT, x + 4, y + 140);
		g.drawString("Map Width: " + Game.MAP_WIDTH, x + 4, y + 154);
		g.drawString("Map Height: " + Game.MAP_HEIGHT, x + 4, y + 168);
		g.drawString("Window Width: " + Game.WINDOW_WIDTH, x + 4, y + 182);
		g.drawString("Window Height: " + Game.WINDOW_HEIGHT, x + 4, y + 196);
		g.drawString("Canvas Width: " + Game.CANVAS_WIDTH, x + 4, y + 210);
		g.drawString("Canvas Height: " + Game.CANVAS_HEIGHT, x + 4, y + 224);
		g.drawString("FPS: " + Game.FPS, x + 4, y + 238);
		g.drawString("Memory: " + getMemory() + " MB", x + 4, y + 252);
		g.drawString("Rotation: " + rotation, x + 4, y + 268);
		g.drawString("Visible Entities: " + entityManager.visibleEntities.size(), x + 4, y + 284);
	}
	
	public void addInfo(String label, int value) {
		info.add(new Info(label, value));
	}
	
	public static double getMemory() {

		double mb = Runtime.getRuntime().totalMemory() / 1024 / 1024;

		double memory = Math.round(mb * 100) / 100;

		return memory;
	}
}
