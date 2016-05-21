package com.spaceshooter.behaviors;

import java.util.List;

import com.spaceshooter.math.LineOfSight;
import com.spaceshooter.math.Vector2D;
import com.spaceshooter.objects.GameObject;
import com.spaceshooter.utils.ID;

public class Flee extends ABehavior{

	public Flee(List<GameObject> group, ID id1, GameObject target,
			double fleeThreshold, List<GameObject> obstacles, ID id2){
		super(group, id1, target, ID.Flee);
		this.fleeThreshold = fleeThreshold;
		this.obstacles = obstacles;
		this.id2 = id2;
	}

	@Override
	public Vector2D calculate(GameObject object) {

		Vector2D distance;
		if (group == null){
			distance = target.position.subtract(object.position);
			if (distance.getDist() < fleeThreshold){
				if (id2 != null){
					if (LineOfSight.calculate(target, object, obstacles, id2)){ 
						return Flee.calculate(object, target.position); 
					}
				}
				else{
					return Flee.calculate(object, target.position);
				}
			}
		}
		else{
			for (GameObject tempObject : group){
				if (tempObject.getId1() == id1 || tempObject.getId2() == id1){
					distance = tempObject.position.subtract(object.position);
					if (distance.getDist() < fleeThreshold){
						if (id2 != null){
							if (LineOfSight.calculate(target, object, obstacles, id2)){ 
								return Flee.calculate(object, tempObject.position);
							}
						}
						else{
							return Flee.calculate(object, tempObject.position);
						}
					}
				}
			}
		}
		return new Vector2D();

	}
	public static Vector2D calculate(GameObject object,
			Vector2D predictedTarget, double fleeThreshold) {

		Vector2D finalVel = object.position.subtract(predictedTarget);

		if (finalVel.getDist() < fleeThreshold){
			finalVel.normalize();
			finalVel = finalVel.multiply(object.maxSpeed);
			Vector2D force = finalVel.add(object.velocity);
			return force;
		}
		return new Vector2D();
	}
	public static Vector2D calculate(GameObject object,
			Vector2D predictedTarget) {

		Vector2D finalVel = object.position.subtract(predictedTarget);
		finalVel.normalize();
		finalVel = finalVel.multiply(object.maxSpeed);
		Vector2D force = finalVel.add(object.velocity);
		return force;
	}
}
