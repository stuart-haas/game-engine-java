package com.spaceshooter.entity;

import java.awt.Graphics2D;

import com.spaceshooter.core.Collision;
import com.spaceshooter.core.EntityManager;
import com.spaceshooter.map.Id;
import com.spaceshooter.map.Layer;
import com.spaceshooter.math.Vector;
import com.spaceshooter.sprite.Animation;
import com.spaceshooter.utils.Assets;

public class Seeker extends BehaviorEntity {
	
	Animation animation;
	EntityManager handler;
	Vector lastPosition = new Vector();

	public Seeker(int x, int y, int width, int height, Id id, Layer layer){
		super(x, y, width, height, id, layer);

		handler = EntityManager.getInstance();
		texture.loadImage(Assets.SEEKER, width, height);
		animation = new Animation(5, true, texture.getImageArray());
	}
	
	public void update() {

		lastPosition = position.clone();
		
		super.update();
		
		if(lastPosition.getX() != position.getX() || lastPosition.getY() != position.getY()) {
			Collision.detect(Layer.Collidable, this, 0, true, new Collision.EventCallback() {

				@Override
				public void success(Entity source, Entity target) {
					Collision.resolve(source, target);
				}
			});
		}
		
		animation.runAnimation();
	}
	 
	public void render(Graphics2D g) {
		animation.drawAnimation(g, (int) position.getX(), (int) position.getY());
	}
}
