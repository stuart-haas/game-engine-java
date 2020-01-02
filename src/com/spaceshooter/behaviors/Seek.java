package com.spaceshooter.behaviors;

import com.spaceshooter.entity.Entity;
import com.spaceshooter.math.Vector2;
import com.spaceshooter.utils.ID;

public class Seek extends ABehavior {

	public Seek(Entity target, double seekThreshold) {
		super(target, ID.Seek);
		this.seekThreshold = seekThreshold;
	}
	
	@Override
	public Vector2 calculate(Entity source) {
		return Seek.calculate(source, target.position);
	}
	
	public static Vector2 calculate(Entity source, Vector2 target, double seekThreshold) {
		
		Vector2 finalVel = target.subtract(source.position);
		if (finalVel.getDist() < seekThreshold){
			finalVel.normalize();
			finalVel = finalVel.multiply(source.maxSpeed);
			Vector2 force = finalVel.subtract(source.velocity);
			return force;
		}
		return new Vector2();
	}
	
	public static Vector2 calculate(Entity object, Vector2 target) {
		
		Vector2 finalVel = target.subtract(object.position);
		finalVel.normalize();
		finalVel = finalVel.multiply(object.maxSpeed);
		Vector2 force = finalVel.subtract(object.velocity);
		return force;
	}
}
