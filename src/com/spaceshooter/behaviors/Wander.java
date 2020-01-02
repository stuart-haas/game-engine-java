package com.spaceshooter.behaviors;

import com.spaceshooter.entity.Entity;
import com.spaceshooter.math.Vector2;
import com.spaceshooter.utils.ID;

public class Wander extends ABehavior {

	public Wander(double wanderAngle, double wanderDistance, double wanderRadius, double wanderRange) {
		super(ID.Wander);
		this.wanderAngle = wanderAngle;
		this.wanderDistance = wanderDistance;
		this.wanderRadius = wanderRadius;
		this.wanderRange = wanderRange;
	}

	@Override
	public Vector2 calculate(Entity object) {
		Vector2 center = object.velocity.clone().normalize().multiply(wanderDistance);
		Vector2 offset = new Vector2();
		offset.setDist(wanderRadius);
		offset.setAngle(wanderAngle);
		wanderAngle += Math.random() * wanderRange - wanderRange * .5;
		return center.add(offset);
	}
}
