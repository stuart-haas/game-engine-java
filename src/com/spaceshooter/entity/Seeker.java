package com.spaceshooter.entity;

import java.awt.Graphics2D;

import com.spaceshooter.core.EntityManager;
import com.spaceshooter.sprite.Animation;
import com.spaceshooter.utils.Assets;
import com.spaceshooter.utils.ID;

public class Seeker extends MovingObject {
	
	Animation animation;
	EntityManager handler;

	public Seeker(int x, int y, int width, int height, ID id){
		super(x, y, width, height, id);

		handler = EntityManager.getInstance();
		texture.loadImage(Assets.SEEKER, width, height);
		animation = new Animation(5, true, texture.imageArray);
	}
	
	public void update() {
		super.update();
		animation.runAnimation();
	}
	 
	public void render(Graphics2D g) {
		animation.drawAnimation(g, (int) position.getX(), (int) position.getY());
	}
}
