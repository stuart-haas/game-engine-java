package com.spaceshooter.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

import com.spaceshooter.core.EntityManager;
import com.spaceshooter.input.MouseInput;
import com.spaceshooter.map.Id;

public class Button extends MouseInput{

	String text;
	int x, y, width, height, fontSize;
	Id id;

	public Button(String text, int fontSize, int x, int y, int width, int height, Id id, EntityManager handler) {
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

	}
	
	public void mouseRelease(MouseEvent e) {

	}
	
	public void render(Graphics2D g) {

		Font font = new Font("arial", 1, fontSize);
		g.setPaint(Color.blue);
		g.fillRect(x, y, width, height);
		g.setFont(font);
		g.setColor(Color.white);
		g.drawString(text, x + 5, y + height / 2 + 5);
	}
	

	/*private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
		if (mx > x && mx < x + width){
			if (my > y && my < y + height){
				return true;
			}
			return false;
		}
		return false;
	}*/
}
