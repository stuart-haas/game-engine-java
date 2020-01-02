package com.spaceshooter.behaviors;

import com.spaceshooter.entity.Entity;
import com.spaceshooter.math.Vector2D;
import com.spaceshooter.utils.ID;

public class Pursue extends ABehavior {

	public Pursue(Entity target, double seekThreshold) {
		super(target, ID.Pursue);
		this.seekThreshold = seekThreshold;
	}

	@Override
	public Vector2D calculate(Entity source) {
		return Pursue.calculate(source, target, seekThreshold);
	}
	
	public static Vector2D calculate(Entity object, Entity target, double seekThreshold) {
		double lookAheadTime = object.position.dist(target.position) / object.maxSpeed;
		Vector2D predictedTarget = target.position.add(target.velocity.multiply(lookAheadTime));
		return Seek.calculate(object, predictedTarget, seekThreshold);
	}
}
