package com.spaceshooter.entity;

import java.awt.Graphics;

import com.spaceshooter.sprite.Animation;
import com.spaceshooter.utils.Assets;
import com.spaceshooter.utils.ID;

public class Rocket extends MovingObject {
	
	Animation animation;

	public Rocket(int x, int y, int width, int height, ID id){
		super(x, y, width, height, id);
		texture.loadImage(Assets.ROCKET, width, height);
		animation = new Animation(5, true, texture.imageArray);
	}
	
	public void update() {
		super.update();
		animation.runAnimation();
	}
	
	public void render(Graphics g) {
		animation.drawAnimation(g, (int) position.getX(), (int) position.getY());
	}
}
