package com.spaceshooter.objects;

import java.awt.Graphics;

import com.spaceshooter.core.Handler;
import com.spaceshooter.sprite.Animation;
import com.spaceshooter.utils.Assets;
import com.spaceshooter.utils.ID;

public class Rocket extends MovingObject{
	
	Animation animation;
	Handler handler;

	public Rocket(int x, int y, int width, int height, ID id1, ID id2){
		super(x, y, width, height, id1, id2);

		texture.loadImage(Assets.ROCKET, width, height);
		animation = new Animation(5, true, texture.imageArray);
	}
	public void tick() {
		super.tick();
		animation.runAnimation();
	}
	public void render(Graphics g) {
		animation.drawAnimation(g, (int) position.getX(), (int) position.getY());
	}
}
