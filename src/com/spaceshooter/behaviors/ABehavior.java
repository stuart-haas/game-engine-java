package com.spaceshooter.behaviors;

import java.util.ArrayList;

import com.spaceshooter.entity.Entity;
import com.spaceshooter.map.Id;
import com.spaceshooter.math.Vector;

public abstract class ABehavior {
		
	protected Id id;
	public Entity target;
	protected ArrayList<Vector> paths;
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

	public ABehavior(Entity target, Id id) {
		this.target = target;
		this.id = id;
	}
	
	public ABehavior(Id id) {
		this.id = id;
	}
	
	public Id getId() {
		return id;
	}
	
	public abstract Vector calculate(Entity object);
}
