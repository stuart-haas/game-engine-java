package com.spaceshooter.behaviors;

import java.util.List;

import com.spaceshooter.core.Game;
import com.spaceshooter.core.Handler;
import com.spaceshooter.math.LineOfSight;
import com.spaceshooter.math.Vector2D;
import com.spaceshooter.objects.GameObject;
import com.spaceshooter.utils.ID;

public class SmartFlee extends ABehavior{

	Handler handler = Game.getHandler();
	
	public SmartFlee(List<GameObject> group, ID id1, GameObject target,
			double fleeThreshold, List<GameObject> obstacles, ID id2){
		super(group, id1, target, ID.SmartFlee);
		this.fleeThreshold = fleeThreshold;
		this.obstacles = obstacles;
		this.id2 = id2;
	}
	@Override
	public Vector2D calculate(GameObject object) {
		Vector2D force = new Vector2D();
		if (group == null){
			if (id2 != null){
				if (LineOfSight.calculate(target, object, obstacles, id2)){
					SmartFlee.calculate(object, target, fleeThreshold);
				}
			}
			else{
				SmartFlee.calculate(object, target, fleeThreshold);
			}
		}
		else{
			List<GameObject> list = handler.getNearByObjects(group, id1, object.position, fleeThreshold);
			for (GameObject tempObject : list){
				if (tempObject.getId1() == id1 || tempObject.getId2() == id1){
					if (id2 != null){
						if (LineOfSight.calculate(tempObject, object, obstacles, id2)){
							SmartFlee.calculate(object, tempObject);
						}
					}
					else{
						SmartFlee.calculate(object, tempObject);
					}
				}
			}
		}
		return force.setHeading(Math.toRadians(object.getAngle()), object.maxSpeed);
	}
	public static Vector2D calculate(GameObject object, GameObject target){
		Vector2D distance = object.position.subtract(target.position);
		Vector2D steeringForce = distance.multiply(object.maxForce).divide(distance.getDist());
		return steeringForce;
	}
	public static Vector2D calculate(GameObject object, GameObject target, double fleeThreshold){
		Vector2D distance = object.position.subtract(target.position);
		if(distance.getDist() < fleeThreshold){
			Vector2D steeringForce = distance.multiply(object.maxForce).divide(distance.getDist());
			return steeringForce;
		}
		return new Vector2D();
	}
}
