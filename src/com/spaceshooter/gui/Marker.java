package com.spaceshooter.gui;

import java.awt.Color;
import java.awt.Graphics;

import com.spaceshooter.entity.Entity;

public class Marker extends Entity{

	Color color;

	public Marker(int x, int y, int width, int height, Color color){
		super(x, y, width, height, null, null);
		this.color = color;
	}
	public void render(Graphics g) {
		g.setColor(color);
		g.fillRect((int) position.getX(), (int) position.getY(), width, height);
	}
}
