package com.spaceshooter.behaviors;

import java.util.List;

import com.spaceshooter.entity.Entity;
import com.spaceshooter.math.LineOfSight;
import com.spaceshooter.math.Vector2D;
import com.spaceshooter.utils.ID;

public class Evade extends ABehavior{

	public Evade(List<Entity> group, ID id1, Entity target,
			double evadeSpeed, double fleeThreshold,
			List<Entity> obstacles, ID id2){
		super(group, id1, target, ID.Evade);
		this.evadeSpeed = evadeSpeed;
		this.fleeThreshold = fleeThreshold;
		this.obstacles = obstacles;
		this.id2 = id2;
	}

	@Override
	public Vector2D calculate(Entity object) {

		if (group == null){
			if (id2 != null){
				if (LineOfSight.calculate(target, object, obstacles, id2)){
					return Evade.calculate(object, target, evadeSpeed, fleeThreshold);
				}
			}
			else{
				return Evade.calculate(object, target, evadeSpeed, fleeThreshold);
			}
		}
		else{
			for (Entity tempObject : group){
				if (tempObject.getId1() == id1 || tempObject.getId2() == id1){
					if (id2 != null){
						if (LineOfSight.calculate(target, object, obstacles, id2)){
							return Evade.calculate(object, tempObject, evadeSpeed, fleeThreshold);
						}
					}
					else{
						return Evade.calculate(object, tempObject, evadeSpeed, fleeThreshold);
					}
				}
			}
		}
		return new Vector2D();
	}
	public static Vector2D calculate(Entity object, Entity target, double evadeSpeed, double fleeThreshold){
		double lookAheadTime = object.position.dist(target.position)
				/ evadeSpeed;

		Vector2D predictedTarget = target.position.add(target.velocity.multiply(lookAheadTime));
		return Flee.calculate(object, predictedTarget, fleeThreshold);
	}

}
