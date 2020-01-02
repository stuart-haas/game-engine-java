package com.spaceshooter.behaviors;

import com.spaceshooter.core.EntityManager;
import com.spaceshooter.entity.Entity;
import com.spaceshooter.math.Vector2D;
import com.spaceshooter.utils.ID;

public class Seek extends ABehavior {

	EntityManager handler = EntityManager.getInstance();

	public Seek(Entity target, double seekThreshold) {
		super( target, ID.Seek);
		this.seekThreshold = seekThreshold;
	}
	
	@Override
	public Vector2D calculate(Entity object) {
		return Seek.calculate(object, target.position);
	}
	
	public static Vector2D calculate(Entity object, Vector2D target, double seekThreshold) {
		
		Vector2D finalVel = target.subtract(object.position);
		if (finalVel.getDist() < seekThreshold){
			finalVel.normalize();
			finalVel = finalVel.multiply(object.maxSpeed);
			Vector2D force = finalVel.subtract(object.velocity);
			return force;
		}
		return new Vector2D();
	}
	
	public static Vector2D calculate(Entity object, Vector2D target) {
		
		Vector2D finalVel = target.subtract(object.position);
		finalVel.normalize();
		finalVel = finalVel.multiply(object.maxSpeed);
		Vector2D force = finalVel.subtract(object.velocity);
		return force;
	}
}
