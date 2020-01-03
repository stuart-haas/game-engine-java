package com.spaceshooter.behaviors;

import com.spaceshooter.entity.Entity;
import com.spaceshooter.map.Id;
import com.spaceshooter.math.Vector;

public class Flee extends ABehavior{

	public Flee(Entity target, double fleeThreshold){
		super(target, Id.Flee);
		this.fleeThreshold = fleeThreshold;
	}

	@Override
	public Vector calculate(Entity object) {
		return Flee.calculate(object, target.position); 
	}
	
	public static Vector calculate(Entity object, Vector predictedTarget, double fleeThreshold) {

		Vector finalVel = object.position.subtract(predictedTarget);

		if (finalVel.getDist() < fleeThreshold){
			finalVel.normalize();
			finalVel = finalVel.multiply(object.maxSpeed);
			Vector force = finalVel.add(object.velocity);
			return force;
		}
		return new Vector();
	}
	
	public static Vector calculate(Entity object, Vector predictedTarget) {

		Vector finalVel = object.position.subtract(predictedTarget);
		finalVel.normalize();
		finalVel = finalVel.multiply(object.maxSpeed);
		Vector force = finalVel.add(object.velocity);
		return force;
	}
}
