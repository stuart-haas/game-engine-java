package com.spaceshooter.behaviors;

import com.spaceshooter.entity.Entity;
import com.spaceshooter.math.Vector2;
import com.spaceshooter.utils.ID;

public class Arrive extends ABehavior {

	public Arrive(Entity target, double arriveThreshold) {
		super(target, ID.Arrive);
		this.arriveThreshold = arriveThreshold;
	}
	
	@Override
	public Vector2 calculate(Entity source) {
		return Arrive.calculate(source, target.position, arriveThreshold);
	}
	
	public static Vector2 calculate(Entity source, Vector2 target, double arriveThreshold) {
		
		Vector2 force = new Vector2();
		Vector2 diff = target.subtract(source.position);
		diff.normalize();

		double distance = source.position.dist(target);
		if (distance > arriveThreshold) {
			diff = diff.multiply(source.maxSpeed);
			force = diff.subtract(source.velocity);
		}
		else {
			diff = diff.multiply(source.maxSpeed * distance / arriveThreshold);
		}

		return force;
	}
}
