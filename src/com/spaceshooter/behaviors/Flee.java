package com.spaceshooter.behaviors;

import com.spaceshooter.entity.Entity;
import com.spaceshooter.math.Vector2;
import com.spaceshooter.utils.ID;

public class Flee extends ABehavior{

	public Flee(Entity target, double fleeThreshold){
		super(target, ID.Flee);
		this.fleeThreshold = fleeThreshold;
	}

	@Override
	public Vector2 calculate(Entity object) {
		return Flee.calculate(object, target.position); 
	}
	
	public static Vector2 calculate(Entity object, Vector2 predictedTarget, double fleeThreshold) {

		Vector2 finalVel = object.position.subtract(predictedTarget);

		if (finalVel.getDist() < fleeThreshold){
			finalVel.normalize();
			finalVel = finalVel.multiply(object.maxSpeed);
			Vector2 force = finalVel.add(object.velocity);
			return force;
		}
		return new Vector2();
	}
	
	public static Vector2 calculate(Entity object, Vector2 predictedTarget) {

		Vector2 finalVel = object.position.subtract(predictedTarget);
		finalVel.normalize();
		finalVel = finalVel.multiply(object.maxSpeed);
		Vector2 force = finalVel.add(object.velocity);
		return force;
	}
}
