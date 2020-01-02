package com.spaceshooter.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import com.spaceshooter.sprite.Animation;
import com.spaceshooter.utils.Assets;
import com.spaceshooter.utils.ID;

public class Pickup extends Entity {
	
	Animation animation;

	public Pickup(int x, int y, int width, int height, ID id) {
		super(x, y, width, height, id);
		texture.loadImage(Assets.COIN_SPIN, width, height);
		animation = new Animation(6, true, texture.imageArray);
	}
		
	@Override
	public void update() {
		animation.runAnimation();
	}

	@Override
	public void render(Graphics2D g) {
		animation.drawAnimation(g, (int) position.getX(), (int) position.getY());
	}
	
	public Ellipse2D getEllipseBounds() {
		return new Ellipse2D.Double(position.getX() + 1, position.getY() + 1, width - 6, height - 3);
	}
	public void drawBounds(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setPaint(Color.cyan);
		g2d.draw(getEllipseBounds());
	}
}
