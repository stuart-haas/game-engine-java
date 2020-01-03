package com.spaceshooter.entity;

import java.util.HashMap;
import java.util.Map;

import com.spaceshooter.behaviors.ABehavior;
import com.spaceshooter.map.Id;
import com.spaceshooter.map.Layer;

public abstract class BehaviorEntity extends Entity{

	private Map<Id, ABehavior> behaviors = new HashMap<Id, ABehavior>();
	boolean lineOfSight = false;
	Entity target;
	
	public BehaviorEntity(int x, int y, int width, int height, Id id, Layer layer) {
		super(x, y, width, height, id, layer);
	}
	
	public void addBehavior(ABehavior behavior) {
		behaviors.put(behavior.getId(), behavior);
	}
	
	public ABehavior getBehavior(Id id) {
		return behaviors.get(id);
	}
	
	public void removeBehavior(Id id) {
		behaviors.remove(id);
	}
	
	public Map<Id, ABehavior> getBehaviorList() {
		return behaviors;
	}
	
	public void update() {

		for (Map.Entry<Id, ABehavior> entry : behaviors.entrySet()) {
			ABehavior behavior = entry.getValue();
			steeringForce = steeringForce.add(behavior.calculate(this)).divide(behaviors.size());
			steeringForce.truncate(maxForce);
			steeringForce = steeringForce.divide(mass);
			velocity = velocity.add(steeringForce);
		}
		
		velocity = velocity.add(steeringForce);
		velocity.truncate(maxSpeed);
		position = position.add(velocity);
	}
}
