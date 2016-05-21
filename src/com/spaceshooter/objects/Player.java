package com.spaceshooter.objects;

import java.awt.Graphics;

import com.spaceshooter.core.Game;
import com.spaceshooter.sprite.Animation;
import com.spaceshooter.utils.Assets;
import com.spaceshooter.utils.ID;

public class Player extends GameObject{

	Animation animation;

	public Player(int x, int y, int width, int height, ID id1, ID id2
			){

		super(x, y, width, height, id1, id2);

		texture.loadImage(Assets.SHIP, width, height);
		animation = new Animation(1, true, texture.imageArray);
		animation.goToFrame(0);
	}
	public void tick() {
		velocity = velocity.multiply(friction);
		velocity = velocity.add(steeringForce);
		velocity.truncate(maxSpeed);
		position = position.add(velocity);
		
		if(velocity.getDistSq() > 2)
			rotation = Math.toDegrees(velocity.getAngle());

		position.setX((float) Game.clamp(position.getX(), Game.MAP_X, Game.MAP_WIDTH
				+ Game.IMAGE_WIDTH - (Game.IMAGE_WIDTH + width)
				+ (Game.WINDOW_HEIGHT - Game.CANVAS_HEIGHT)));
		position.setY((float) Game.clamp(position.getY(), Game.MAP_Y, Game.MAP_HEIGHT
				+ Game.IMAGE_HEIGHT - (Game.IMAGE_HEIGHT + height)
				+ (Game.WINDOW_HEIGHT - Game.CANVAS_HEIGHT)));
	}
	public void render(Graphics g) {

		animation.rotateAnimation(g, rotation, (int) position.getX(), (int) position.getY(), width, height);
	}
}