package com.spaceshooter.behaviors;

import java.util.ArrayList;

import com.spaceshooter.entity.Entity;
import com.spaceshooter.math.Vector2;
import com.spaceshooter.utils.ID;

public class FollowPath extends ABehavior{

	public FollowPath(ArrayList<Vector2> paths, double pathThreshold, double arriveThreshold, boolean loop){
		super(ID.FollowPath);
		this.paths = paths;
		this.pathThreshold = pathThreshold;
		this.arriveThreshold = arriveThreshold;
		this.loop = loop;
	}

	@Override
	public Vector2 calculate(Entity object) {
		
		Vector2 force = new Vector2();
		Vector2 wayPoint = paths.get(pathIndex);
		if (wayPoint == null) return new Vector2();
		if (object.position.dist(wayPoint) < pathThreshold){
			if (pathIndex >= paths.size() - 1){
				if (loop){
			 		pathIndex = 0;
				}
			}
			else{
				pathIndex++;
			}
		}
		if (pathIndex >= paths.size() - 1 && !loop){
			force = force.add(Arrive.calculate(object, wayPoint, arriveThreshold));	
			return force;
		}
		else{
			force = force.add(Seek.calculate(object, wayPoint, 2000));
			return force;
		}
	}
}
