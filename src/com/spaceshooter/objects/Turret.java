package com.spaceshooter.objects;

import java.awt.Graphics;

import com.spaceshooter.core.Game;
import com.spaceshooter.core.Handler;
import com.spaceshooter.math.LineOfSight;
import com.spaceshooter.math.Vector2D;
import com.spaceshooter.sprite.Animation;
import com.spaceshooter.utils.Assets;
import com.spaceshooter.utils.ID;

public class Turret extends MovingObject{

	Animation animation;
	Handler handler;
	int fireTimer = 0;

	public Turret(int x, int y, int width, int height, ID id1, ID id2){
		super(x, y, width, height, id1, id2);

		handler = Game.getHandler();
		texture.loadImage(Assets.TURRET, width, height);
		animation = new Animation(1, false, texture.imageArray);
		animation.goToFrame(0);
	}
	public void tick() {

		GameObject player = handler.getObjectById(handler.objects, ID.Player);
		if (LineOfSight.calculate(player, this, handler.collisionObjects, ID.CollisionTile)){
			Vector2D diff = player.position.subtract(position);
			if(diff.getDist() < fireRadius){
				fireTimer ++;
				if(fireTimer >= fireRate){
					GameObject p = new Bullet((int)position.getX() + 8, (int)position.getY(), 16, 16, ID.Bullet, ID.Projectile);
					p.velocity.setHeading(diff.getAngle(), projectileSpeed);
					handler.addObject(handler.bullets, p);
					fireTimer = 0;
				}
			}
		}
	}
	public void render(Graphics g) {
		
		animation.drawAnimation(g, (int) position.getX(), (int) position.getY());
	}
}
