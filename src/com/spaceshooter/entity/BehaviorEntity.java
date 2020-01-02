package com.spaceshooter.entity;

import java.util.HashMap;
import java.util.Map;

import com.spaceshooter.behaviors.ABehavior;
import com.spaceshooter.utils.ID;

public abstract class BehaviorEntity extends Entity{

	private Map<ID, ABehavior> behaviors = new HashMap<ID, ABehavior>();
	
	public BehaviorEntity(int x, int y, int width, int height, ID id) {
		super(x, y, width, height, id);
	}
	
	public void addBehavior(ABehavior behavior) {
		behaviors.put(behavior.getId(), behavior);
	}
	
	public ABehavior getBehavior(ID id) {
		return behaviors.get(id);
	}
	
	public void removeBehavior(ID id) {
		behaviors.remove(id);
	}
	
	public Map<ID, ABehavior> getBehaviorList() {
		return behaviors;
	}
	
	public void update() {

		for (Map.Entry<ID, ABehavior> entry : behaviors.entrySet()) {
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
