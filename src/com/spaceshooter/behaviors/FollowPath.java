package com.spaceshooter.behaviors;

import java.util.ArrayList;

import com.spaceshooter.entity.Entity;
import com.spaceshooter.map.Id;
import com.spaceshooter.math.Vector;

public class FollowPath extends ABehavior{

	public FollowPath(ArrayList<Vector> paths, double pathThreshold, double arriveThreshold, boolean loop){
		super(Id.FollowPath);
		this.paths = paths;
		this.pathThreshold = pathThreshold;
		this.arriveThreshold = arriveThreshold;
		this.loop = loop;
	}

	@Override
	public Vector calculate(Entity object) {
		
		Vector force = new Vector();
		Vector wayPoint = paths.get(pathIndex);
		if (wayPoint == null) return new Vector();
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
