package com.spaceshooter.core;

import com.spaceshooter.behaviors.Seek;
import com.spaceshooter.entity.Entity;
import com.spaceshooter.entity.Node;
import com.spaceshooter.entity.Player;
import com.spaceshooter.entity.Seeker;
import com.spaceshooter.map.Id;
import com.spaceshooter.map.Layer;

public class EntityFactory {

	private static EntityManager entityManager = EntityManager.getInstance();

	public static Entity playerInstance(int x, int y, int width, int height, Id id, Layer layer) {
		Entity tempObject = new Player(x, y, width, height, id, layer);
		tempObject.maxForce = 1;
		tempObject.maxSpeed = 3;
		return tempObject;
	}
	
	public static Entity seekerInstance(int x, int y, int width, int height, Id id, Layer layer) {
		Entity entity = new Seeker(x, y, width, height, id, layer);
		entity.addBehavior(new Seek(entityManager.getEntityById(Id.Player), 200));
		entity.maxSpeed = 2;
		entity.maxForce = .25;
		return entity;
	}
	
	public static Entity nodeInstance(int x, int y, int width, int height, Id id, Layer layer) {
		Entity tempObject = new Node(x, y, width, height, id, layer);
		return tempObject;
	}
}
