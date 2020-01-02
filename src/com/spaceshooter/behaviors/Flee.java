package com.spaceshooter.behaviors;

import com.spaceshooter.entity.Entity;
import com.spaceshooter.math.Vector2D;
import com.spaceshooter.utils.ID;

public class Flee extends ABehavior{

	public Flee(Entity target, double fleeThreshold){
		super(target, ID.Flee);
		this.fleeThreshold = fleeThreshold;
	}

	@Override
	public Vector2D calculate(Entity object) {
		return Flee.calculate(object, target.position); 
	}
	
	public static Vector2D calculate(Entity object, Vector2D predictedTarget, double fleeThreshold) {

		Vector2D finalVel = object.position.subtract(predictedTarget);

		if (finalVel.getDist() < fleeThreshold){
			finalVel.normalize();
			finalVel = finalVel.multiply(object.maxSpeed);
			Vector2D force = finalVel.add(object.velocity);
			return force;
		}
		return new Vector2D();
	}
	
	public static Vector2D calculate(Entity object, Vector2D predictedTarget) {

		Vector2D finalVel = object.position.subtract(predictedTarget);
		finalVel.normalize();
		finalVel = finalVel.multiply(object.maxSpeed);
		Vector2D force = finalVel.add(object.velocity);
		return force;
	}
}
