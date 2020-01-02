package com.spaceshooter.behaviors;

import com.spaceshooter.entity.Entity;
import com.spaceshooter.math.Vector2;
import com.spaceshooter.utils.ID;

public class Pursue extends ABehavior {

	public Pursue(Entity target, double seekThreshold) {
		super(target, ID.Pursue);
		this.seekThreshold = seekThreshold;
	}

	@Override
	public Vector2 calculate(Entity source) {
		return Pursue.calculate(source, target, seekThreshold);
	}
	
	public static Vector2 calculate(Entity source, Entity target, double seekThreshold) {
		double lookAheadTime = source.position.dist(target.position) / source.maxSpeed;
		Vector2 predictedTarget = target.position.add(target.velocity.multiply(lookAheadTime));
		return Seek.calculate(source, predictedTarget, seekThreshold);
	}
}
