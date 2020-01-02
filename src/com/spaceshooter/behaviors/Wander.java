package com.spaceshooter.behaviors;

import com.spaceshooter.entity.Entity;
import com.spaceshooter.math.Vector2D;
import com.spaceshooter.utils.ID;

public class Wander extends ABehavior{

	public Wander(double wanderAngle, double wanderDistance,
			double wanderRadius, double wanderRange){
		super(null, null, null, ID.Wander);
		
		this.wanderAngle = wanderAngle;
		this.wanderDistance = wanderDistance;
		this.wanderRadius = wanderRadius;
		this.wanderRange = wanderRange;
	}

	@Override
	public Vector2D calculate(Entity object) {
		Vector2D center = object.velocity.clone().normalize().multiply(wanderDistance);
		Vector2D offset = new Vector2D();
		offset.setDist(wanderRadius);
		offset.setAngle(wanderAngle);
		wanderAngle += Math.random() * wanderRange - wanderRange * .5;
		return center.add(offset);
	}
}
