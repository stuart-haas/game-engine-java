package com.spaceshooter.behaviors;

import com.spaceshooter.entity.Entity;
import com.spaceshooter.map.Id;
import com.spaceshooter.math.Vector;

public class Arrive extends ABehavior {

	public Arrive(Entity target, double arriveThreshold) {
		super(target, Id.Arrive);
		this.arriveThreshold = arriveThreshold;
	}
	
	@Override
	public Vector calculate(Entity source) {
		return Arrive.calculate(source, target.position, arriveThreshold);
	}
	
	public static Vector calculate(Entity source, Vector target, double arriveThreshold) {
		
		Vector force = new Vector();
		Vector diff = target.subtract(source.position);
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
