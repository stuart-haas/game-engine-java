package com.spaceshooter.behaviors;

import com.spaceshooter.entity.Entity;
import com.spaceshooter.map.Id;
import com.spaceshooter.math.Vector;

public class Seek extends ABehavior {

	public Seek(Entity target, double seekThreshold) {
		super(target, Id.Seek);
		this.seekThreshold = seekThreshold;
	}
	
	@Override
	public Vector calculate(Entity source) {
		return Seek.calculate(source, target.position);
	}
	
	public static Vector calculate(Entity source, Vector target, double seekThreshold) {
		
		Vector finalVel = target.subtract(source.position);
		if (finalVel.getDist() < seekThreshold){
			finalVel.normalize();
			finalVel = finalVel.multiply(source.maxSpeed);
			Vector force = finalVel.subtract(source.velocity);
			return force;
		}
		return new Vector();
	}
	
	public static Vector calculate(Entity object, Vector target) {
		
		Vector finalVel = target.subtract(object.position);
		finalVel.normalize();
		finalVel = finalVel.multiply(object.maxSpeed);
		Vector force = finalVel.subtract(object.velocity);
		return force;
	}
}
