package com.spaceshooter.entity;

import java.awt.Graphics;

import com.spaceshooter.core.EntityManager;
import com.spaceshooter.math.LineOfSight;
import com.spaceshooter.math.Vector2D;
import com.spaceshooter.sprite.Animation;
import com.spaceshooter.utils.Assets;
import com.spaceshooter.utils.ID;

public class Turret extends MovingObject{

	Animation animation;
	EntityManager handler;
	int fireTimer = 0;

	public Turret(int x, int y, int width, int height, ID id){
		super(x, y, width, height, id);
		handler = EntityManager.getInstance();
		texture.loadImage(Assets.TURRET, width, height);
		animation = new Animation(1, false, texture.imageArray);
		animation.goToFrame(0);
	}
	public void update() {

		Entity player = handler.getEntityById(ID.Player);
		if (LineOfSight.calculate(player, this, handler.entities, ID.CollisionTile)){
			Vector2D diff = player.position.subtract(position);
			if(diff.getDist() < fireRadius){
				fireTimer ++;
				if(fireTimer >= fireRate){
					Entity p = new Bullet((int)position.getX() + 8, (int)position.getY(), 16, 16, ID.Bullet);
					p.velocity.setHeading(diff.getAngle(), projectileSpeed);
					handler.addEntity(handler.entities, p);
					fireTimer = 0;
				}
			}
		}
	}
	public void render(Graphics g) {
		animation.drawAnimation(g, (int) position.getX(), (int) position.getY());
	}
}
