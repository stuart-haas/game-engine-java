package com.spaceshooter.behaviors;

import java.util.List;

import com.spaceshooter.math.LineOfSight;
import com.spaceshooter.math.Vector2D;
import com.spaceshooter.objects.GameObject;
import com.spaceshooter.utils.ID;

public class Arrive extends ABehavior{

	public Arrive(List<GameObject> group, ID id1, GameObject target,
			double seekThreshold, double arriveThreshold, List<GameObject> obstacles, ID id2){
		super(group, id1, target, ID.Arrive);

		this.obstacles = obstacles;
		this.id2 = id2;

		this.seekThreshold = seekThreshold;
		this.arriveThreshold = arriveThreshold;
	}
	@Override
	public Vector2D calculate(GameObject object) {

		Vector2D finalVel;
		Vector2D force;


		if (group == null){
			if(target != null){
				finalVel = target.position.subtract(object.position);
				if (finalVel.getDist() < seekThreshold){
					if (id2 != null){
						if (LineOfSight.calculate(target, object, obstacles, id2)){
							finalVel.normalize();

							double distance = object.position.dist(target.position);
							if (distance > arriveThreshold) finalVel = finalVel.multiply(object.maxSpeed);
							else finalVel = finalVel.multiply(object.maxSpeed
									* distance / arriveThreshold);

							force = finalVel.subtract(object.velocity);
							return force;
						}
					}
					else{
						return Arrive.calculate(object, target.position, arriveThreshold);
					}
				}
			}
		}
		else for (GameObject tempObject : group){

			if (tempObject.getId1() == id1){
				finalVel = tempObject.position.subtract(object.position);
				if (finalVel.getDist() < seekThreshold){
					if (id2 != null){
						if (LineOfSight.calculate(target, object, obstacles, id2)){
							finalVel.normalize();

							double distance = object.position.dist(tempObject.position);
							if (distance > arriveThreshold) finalVel = finalVel.multiply(object.maxSpeed);
							else finalVel = finalVel.multiply(object.maxSpeed
									* distance / arriveThreshold);

							force = finalVel.subtract(object.velocity);
							return force;
						}
					}
					else{
						return Arrive.calculate(object, target.position, arriveThreshold);
					}
				}
			}
		}
		return new Vector2D();
	}
	public static Vector2D calculate(GameObject object, Vector2D target,
			double arriveThreshold) {
		Vector2D finalVel = target.subtract(object.position);
		finalVel.normalize();

		double distance = object.position.dist(target);
		if (distance > arriveThreshold) finalVel = finalVel.multiply(object.maxSpeed);
		else finalVel = finalVel.multiply(object.maxSpeed * distance
				/ arriveThreshold);

		Vector2D force = finalVel.subtract(object.velocity);
		return force;
	}
}
