package com.spaceshooter.behaviors;

import java.util.List;

import com.spaceshooter.core.Game;
import com.spaceshooter.entity.Entity;
import com.spaceshooter.core.EntityManager;
import com.spaceshooter.math.LineOfSight;
import com.spaceshooter.math.Vector2D;
import com.spaceshooter.utils.ID;

public class Seek extends ABehavior{

	EntityManager handler = EntityManager.getInstance();

	public Seek(List<Entity> group, ID id1, Entity target,
			double seekThreshold, List<Entity> obstacles, ID id2){
		super(group, id1, target, ID.Seek);

		this.seekThreshold = seekThreshold;
		this.obstacles = obstacles;
		this.id2 = id2;
	}
	@Override
	public Vector2D calculate(Entity object) {

		Vector2D distance;
		if (group == null){

			distance = target.position.subtract(object.position);
			if (distance.getDist() < seekThreshold){
				if (id2 != null){
					if (LineOfSight.calculate(target, object, obstacles, id2)){
						Seek.calculate(object, target.position);
					}
				}
				else{
					Seek.calculate(object, target.position);
				}
			}
		}
		else{
			for (Entity tempObject : group){
				if (tempObject.getId1() == id1){
					distance = tempObject.position.subtract(object.position);
					if (distance.getDist() < seekThreshold){
						if (id2 != null){
							if (LineOfSight.calculate(target, object, obstacles, id2)){
								Seek.calculate(object, tempObject.position);
							}
						}
						else{
							Seek.calculate(object, tempObject.position);
						}
					}
				}
			}
		}
		return new Vector2D();
	}
	public static Vector2D calculate(Entity object, Vector2D target,
			double seekThreshold) {
		Vector2D finalVel = target.subtract(object.position);
		if (finalVel.getDist() < seekThreshold){
			finalVel.normalize();
			finalVel = finalVel.multiply(object.maxSpeed);
			Vector2D force = finalVel.subtract(object.velocity);
			return force;
		}
		return new Vector2D();
	}
	public static Vector2D calculate(Entity object, Vector2D target) {
		Vector2D finalVel = target.subtract(object.position);
		finalVel.normalize();
		finalVel = finalVel.multiply(object.maxSpeed);
		Vector2D force = finalVel.subtract(object.velocity);
		return force;
	}
}
