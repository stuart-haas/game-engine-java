package com.spaceshooter.entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.spaceshooter.utils.ID;

public class Node extends Entity {
	
	BufferedImage image;
	
	public Node(int x, int y, int width, int height, ID id,  BufferedImage image) {
		super(x, y, width, height, id);
		this.image = image;
	}

	public Node(int x, int y, int width, int height, ID id) {
		super(x, y, width, height, id);
	}
	
	public void render(Graphics2D g) {
		g.drawImage(image, (int) position.getX(), (int) position.getY(), null);
		
		if(drawBounds) {
			super.drawBounds(g);
		}
	}
}
