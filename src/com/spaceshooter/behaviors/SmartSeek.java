package com.spaceshooter.behaviors;

import java.util.List;

import com.spaceshooter.core.Game;
import com.spaceshooter.entity.Entity;
import com.spaceshooter.core.EntityManager;
import com.spaceshooter.math.LineOfSight;
import com.spaceshooter.math.Vector2D;
import com.spaceshooter.utils.ID;

public class SmartSeek extends ABehavior{

	EntityManager handler = EntityManager.getInstance();

	public SmartSeek(List<Entity> group, ID id1,
			Entity target, int seekThreshold,
			List<Entity> obstacles, ID id2){
		super(group, id1, target, ID.SmartSeek);
		this.obstacles = obstacles;
		this.id2 = id2;

		this.seekThreshold = seekThreshold;
	}

	@Override
	public Vector2D calculate(Entity object) {

		Vector2D force = new Vector2D();
		if (group == null){
			if(target != null){
				if(id2 != null){
					if (LineOfSight.calculate(target, object, obstacles, id2)){
							return SmartSeek.calculate(object, target, seekThreshold);
					}
				}
				else{
					return SmartSeek.calculate(object, target, seekThreshold);
				}
			}
		}
		else{
			List<Entity> list = handler.getNearbyEntities(id1, object.position, seekThreshold);
			for (Entity tempObject : list){
				if (id2 != null){
					if (LineOfSight.calculate(tempObject, object, obstacles, id2)){
						return SmartSeek.calculate(object, tempObject);
					}
				}
				else{
					return SmartSeek.calculate(object, tempObject);
				}
			}
		}
		return force.setHeading(Math.toRadians(object.getAngle()), object.maxSpeed);
	}
	public static Vector2D calculate(Entity object, Entity target){
		Vector2D distance = target.position.subtract(object.position);
		Vector2D steeringForce = distance.multiply(object.maxForce).divide(distance.getDist());
		return steeringForce;
	}
	public static Vector2D calculate(Entity object, Entity target, double seekThreshold){
		Vector2D distance = target.position.subtract(object.position);
		if(distance.getDist() < seekThreshold){
			Vector2D steeringForce = distance.multiply(object.maxForce).divide(distance.getDist());
			return steeringForce;
		}
		return new Vector2D();
	}
}
