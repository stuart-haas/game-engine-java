package com.spaceshooter.behaviors;

import java.util.ArrayList;

import com.spaceshooter.entity.Entity;
import com.spaceshooter.math.Vector2;
import com.spaceshooter.utils.ID;

public abstract class ABehavior {
		
	protected ID id;
	protected Entity target;
	protected ArrayList<Vector2> paths;
	protected boolean loop;
	protected int pathIndex = 0;
	protected double evadeSpeed;
	protected double fleeThreshold;
	protected double pathThreshold;
	protected double seekThreshold;
	protected double arriveThreshold;
	protected double wanderAngle;
	protected double wanderDistance;
	protected double wanderRadius;
	protected double wanderRange;
	protected double avoidBuffer;
	protected double avoidDist;

	public ABehavior(Entity target, ID id) {
		this.target = target;
		this.id = id;
	}
	
	public ABehavior(ID id) {
		this.id = id;
	}
	
	public ID getId() {
		return id;
	}
	
	public abstract Vector2 calculate(Entity object);
}
