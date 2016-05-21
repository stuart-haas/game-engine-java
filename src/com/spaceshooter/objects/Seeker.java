package com.spaceshooter.objects;

import java.awt.Graphics;

import com.spaceshooter.core.Game;
import com.spaceshooter.core.Handler;
import com.spaceshooter.sprite.Animation;
import com.spaceshooter.utils.Assets;
import com.spaceshooter.utils.ID;

public class Seeker extends MovingObject{
	
	Animation animation;
	Handler handler;

	public Seeker(int x, int y, int width, int height, ID id1, ID id2){
		super(x, y, width, height, id1, id2);

		handler = Game.getHandler();
		texture.loadImage(Assets.SEEKER, width, height);
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
