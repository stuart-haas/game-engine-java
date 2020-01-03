package com.spaceshooter.entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.spaceshooter.map.Id;
import com.spaceshooter.map.Layer;

public class Node extends Entity {
	
	BufferedImage image;
	
	public Node(BufferedImage image, int x, int y, int width, int height, Id id, Layer layer) {
		super(x, y, width, height, id, layer);
		this.image = image;
	}

	public Node(int x, int y, int width, int height, Id id, Layer layer) {
		super(x, y, width, height, id, layer);
	}
	
	public void render(Graphics2D g) {
		g.drawImage(image, (int) position.getX(), (int) position.getY(), null);
		
		if(debug) {
			super.debug(g);
		}
	}
}
