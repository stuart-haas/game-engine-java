package com.spaceshooter.behaviors;

import com.spaceshooter.entity.Entity;
import com.spaceshooter.map.Id;
import com.spaceshooter.math.Vector;

public class Wander extends ABehavior {

	public Wander(double wanderAngle, double wanderDistance, double wanderRadius, double wanderRange) {
		super(Id.Wander);
		this.wanderAngle = wanderAngle;
		this.wanderDistance = wanderDistance;
		this.wanderRadius = wanderRadius;
		this.wanderRange = wanderRange;
	}

	@Override
	public Vector calculate(Entity object) {
		Vector center = object.velocity.clone().normalize().multiply(wanderDistance);
		Vector offset = new Vector();
		offset.setDirection(wanderRadius);
		offset.setAngle(wanderAngle);
		wanderAngle += Math.random() * wanderRange - wanderRange * .5;
		return center.add(offset);
	}
}
