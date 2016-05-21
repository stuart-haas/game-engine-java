package com.spaceshooter.objects;

import java.util.ArrayList;
import java.util.List;

import com.spaceshooter.behaviors.ABehavior;
import com.spaceshooter.math.Vector2D;
import com.spaceshooter.utils.ID;

public abstract class MovingObject extends GameObject{

	private List<ABehavior> behaviors = new ArrayList<ABehavior>();
	
	public MovingObject(int x, int y, int width, int height, ID id1, ID id2){
		super(x, y, width, height, id1, id2);

	}
	public void addBehavior(ABehavior behavior) {
		this.behaviors.add(behavior);
	}
	public ABehavior getBehavior(ID id){
		for(ABehavior behavior : behaviors){
			if(behavior.getId() == id){
				return behavior;
			}
		}
		return null;
	}
	public void removeBehavior(ID id){
		for(ABehavior behavior : behaviors){
			if(behavior.getId() == id){
				behaviors.remove(behavior);
			}
		}
	}
	public List<ABehavior> getBehaviorList(){
		return behaviors;
	}
	public void tick() {

		for (int i = 0; i < behaviors.size(); i++){
			steeringForce = steeringForce.add(behaviors.get(i).calculate(this)).divide(behaviors.size());

			steeringForce.truncate(maxForce);
			steeringForce = steeringForce.divide(mass);
			velocity = velocity.add(steeringForce);
			steeringForce = new Vector2D();
		}
		velocity = velocity.add(steeringForce);
		velocity.truncate(maxSpeed);

		position = position.add(velocity);
	}
}
