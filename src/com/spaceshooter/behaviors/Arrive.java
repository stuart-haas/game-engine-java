package com.spaceshooter.behaviors;

import com.spaceshooter.entity.Entity;
import com.spaceshooter.math.Vector2D;
import com.spaceshooter.utils.ID;

public class Arrive extends ABehavior {

	public Arrive(Entity target, double seekThreshold, double arriveThreshold) {
		super(target, ID.Arrive);
		this.seekThreshold = seekThreshold;
		this.arriveThreshold = arriveThreshold;
	}
	
	@Override
	public Vector2D calculate(Entity object) {
		return Arrive.calculate(object, target.position, arriveThreshold);
	}
	
	public static Vector2D calculate(Entity object, Vector2D target, double arriveThreshold) {
		Vector2D finalVel = target.subtract(object.position);
		finalVel.normalize();

		double distance = object.position.dist(target);
		if (distance > arriveThreshold) finalVel = finalVel.multiply(object.maxSpeed);
		else finalVel = finalVel.multiply(object.maxSpeed * distance / arriveThreshold);

		Vector2D force = finalVel.subtract(object.velocity);
		return force;
	}
}
