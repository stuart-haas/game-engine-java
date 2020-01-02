package com.spaceshooter.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

import com.spaceshooter.core.Game;
import com.spaceshooter.core.EntityManager;
import com.spaceshooter.input.MouseInput;
import com.spaceshooter.utils.ID;

public class Button extends MouseInput{

	String text;
	int x, y, width, height, fontSize;
	ID id;

	public Button(String text, int fontSize, int x, int y, int width, int height, ID id, EntityManager handler) {
		super();
		this.text = text;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.fontSize = fontSize;
		this.id = id;
	}
	
	public void mousePressed(MouseEvent e) {

		int mx = e.getX();
		int my = e.getY();

		if (id == ID.Button1){
			if (mouseOver(mx, my, x, y, width, height)){
				if (!Game.DISPLAY_OBJECT_BOUNDS) Game.DISPLAY_OBJECT_BOUNDS = true;
				else Game.DISPLAY_OBJECT_BOUNDS = false;
			}
		}
		if (id == ID.Button2){
			if (mouseOver(mx, my, x, y, width, height)){
				if (!Game.DISPLAY_COLLISION_OBJECT_BOUNDS) Game.DISPLAY_COLLISION_OBJECT_BOUNDS = true;
				else Game.DISPLAY_COLLISION_OBJECT_BOUNDS = false;
			}
		}
		if (id == ID.Button3){
			if (mouseOver(mx, my, x, y, width, height)){
				if (!Game.DISPLAY_STATS) Game.DISPLAY_STATS = true;
				else Game.DISPLAY_STATS = false;
			}
		}
	}
	
	public void mouseRelease(MouseEvent e) {

	}
	
	public void render(Graphics g) {

		Font font = new Font("arial", 1, fontSize);

		Graphics2D g2d = (Graphics2D) g;
		g2d.setPaint(Color.blue);
		g2d.fillRect(x, y, width, height);

		g.setFont(font);
		g.setColor(Color.white);
		g.drawString(text, x + 5, y + height / 2 + 5);
	}
	
	private boolean mouseOver(int mx, int my, int x, int y, int width,
			int height) {
		if (mx > x && mx < x + width){
			if (my > y && my < y + height){
				return true;
			}
			return false;
		}
		return false;
	}
}
