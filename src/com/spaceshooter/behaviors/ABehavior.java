package com.spaceshooter.behaviors;

import java.util.ArrayList;
import java.util.List;

import com.spaceshooter.entity.Entity;
import com.spaceshooter.math.Vector2D;
import com.spaceshooter.utils.ID;

public abstract class ABehavior{
	
	private ID id;
	
	protected double arriveThreshold;
	protected double avoidBuffer;
	protected double avoidDist;

	protected double evadeSpeed;
	protected double fleeThreshold;
	protected List<Entity> group;
	protected List<Entity> obstacles;
	protected ID id1;
	protected ID id2;
	protected boolean loop;
	protected int pathIndex = 0;
	protected ArrayList<Vector2D> paths;
	protected double pathThreshold;
	protected double seekThreshold;
	protected Entity target;
	protected double wanderAngle;
	protected double wanderDistance;
	protected double wanderRadius;
	protected double wanderRange;

	public ABehavior(List<Entity> group, ID id1, Entity target, ID id){
		this.target = target;
		this.group = group;
		this.id1 = id1;
		this.id = id;
	}
	public abstract Vector2D calculate(Entity object);
	public ID getId(){
		return id;
	}
}
