package com.spaceshooter.behaviors;

import com.spaceshooter.entity.Entity;
import com.spaceshooter.map.Id;
import com.spaceshooter.math.Vector;

public class Pursue extends ABehavior {

	public Pursue(Entity target, double seekThreshold) {
		super(target, Id.Pursue);
		this.seekThreshold = seekThreshold;
	}

	@Override
	public Vector calculate(Entity source) {
		return Pursue.calculate(source, target, seekThreshold);
	}
	
	public static Vector calculate(Entity source, Entity target, double seekThreshold) {
		double lookAheadTime = source.position.dist(target.position) / source.maxSpeed;
		Vector predictedTarget = target.position.add(target.velocity.multiply(lookAheadTime));
		return Seek.calculate(source, predictedTarget, seekThreshold);
	}
}
