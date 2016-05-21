package com.spaceshooter.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.util.Random;

import com.spaceshooter.sprite.Animation;
import com.spaceshooter.utils.Assets;
import com.spaceshooter.utils.ID;

public class Missile extends MovingObject{

	Animation animation;
	Random r = new Random();
	
	public Missile(int x, int y, double angle, int width, int height, ID id1, ID id2){
		super(x, y, width, height, id1, id2);
		
		this.angle = angle;
		texture.loadImage(Assets.MISSILE, width, height);
		animation = new Animation(5, true, texture.imageArray);
		animation.goToFrame(0);
	}
	public void tick() {
		
		life --;
		if(life <= 0)
			expired = true;

		super.tick();

		rotation = Math.toDegrees(velocity.getAngle());	
	}
	public void render(Graphics g) {

		animation.rotateAnimation(g, rotation, (int) position.getX(), (int) position.getY(), width, height);
	}
	public Ellipse2D getEllipseBounds(){
		
		return new Ellipse2D.Double(position.getX(), position.getY(), width, height);
	}
	public void drawBounds(Graphics g){
		
		Graphics2D g2d = (Graphics2D) g;
		g2d.setPaint(Color.white);
		
		g2d.draw(getEllipseBounds());
	}
}
