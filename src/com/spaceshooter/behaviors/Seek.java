package com.spaceshooter.behaviors;

import java.util.List;

import com.spaceshooter.core.Game;
import com.spaceshooter.core.Handler;
import com.spaceshooter.math.LineOfSight;
import com.spaceshooter.math.Vector2D;
import com.spaceshooter.objects.GameObject;
import com.spaceshooter.utils.ID;

public class Seek extends ABehavior{

	Handler handler = Game.getHandler();

	public Seek(List<GameObject> group, ID id1, GameObject target,
			double seekThreshold, List<GameObject> obstacles, ID id2){
		super(group, id1, target, ID.Seek);

		this.seekThreshold = seekThreshold;
		this.obstacles = obstacles;
		this.id2 = id2;
	}
	@Override
	public Vector2D calculate(GameObject object) {

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
			for (GameObject tempObject : group){
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
	public static Vector2D calculate(GameObject object, Vector2D target,
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
	public static Vector2D calculate(GameObject object, Vector2D target) {
		Vector2D finalVel = target.subtract(object.position);
		finalVel.normalize();
		finalVel = finalVel.multiply(object.maxSpeed);
		Vector2D force = finalVel.subtract(object.velocity);
		return force;
	}
}
