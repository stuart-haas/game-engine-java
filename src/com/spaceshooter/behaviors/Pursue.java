package com.spaceshooter.behaviors;

import java.util.List;

import com.spaceshooter.entity.Entity;
import com.spaceshooter.math.LineOfSight;
import com.spaceshooter.math.Vector2D;
import com.spaceshooter.utils.ID;

public class Pursue extends ABehavior{

	public Pursue(List<Entity> group, ID id1, Entity target,
			double seekThreshold, List<Entity> obstacles, ID id2){
		super(group, id1, target, ID.Pursue);
		this.seekThreshold = seekThreshold;
		this.obstacles = obstacles;
		this.id2 = id2;
	}

	@Override
	public Vector2D calculate(Entity object) {
		
		if (group == null){
			if(target != null){
				if (id2 != null){
					if (LineOfSight.calculate(target, object, obstacles, id2)){
						return Pursue.calculate(object, target, seekThreshold);
					}
				}
				else{
					return Pursue.calculate(object, target, seekThreshold);
				}
			}
		}
		else{
			for (Entity tempObject : group){
				if (tempObject.getId1() == id1){
					if (id2 != null){
						if (LineOfSight.calculate(target, object, obstacles, id2)){
							return Pursue.calculate(object, tempObject, seekThreshold);
						}
					}
					else{
						return Pursue.calculate(object, tempObject, seekThreshold);
					}
				}
			}
		}
		return new Vector2D();
	}
	public static Vector2D calculate(Entity object, Entity target, double seekThreshold){
		
		double lookAheadTime = object.position.dist(target.position)
				/ object.maxSpeed;

		Vector2D predictedTarget = target.position.add(target.velocity.multiply(lookAheadTime));
		return Seek.calculate(object, predictedTarget, seekThreshold);
	}
}
