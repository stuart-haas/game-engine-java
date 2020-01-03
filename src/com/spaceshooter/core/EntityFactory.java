package com.spaceshooter.core;

import java.awt.Color;

import com.spaceshooter.behaviors.Seek;
import com.spaceshooter.entity.Bullet;
import com.spaceshooter.entity.Entity;
import com.spaceshooter.entity.Missile;
import com.spaceshooter.entity.Node;
import com.spaceshooter.entity.Pickup;
import com.spaceshooter.entity.Player;
import com.spaceshooter.entity.Seeker;
import com.spaceshooter.gui.Marker;
import com.spaceshooter.math.Vector;
import com.spaceshooter.utils.ID;

public class EntityFactory {

	private static EntityManager entityManager = EntityManager.getInstance();

	public static Entity getPlayer(int x, int y, int width, int height, ID id) {
		Entity tempObject = new Player(x, y, width, height, id);
		tempObject.maxForce = 1;
		tempObject.maxSpeed = 3;
		tempObject.projectileSpeed = 7;
		return tempObject;
	}
	
	public static Entity getBullet(int x, int y, int width, int height, Vector velocity, ID id) {
		Entity tempObject = new Bullet(x, y, width, height, id);
		tempObject.velocity = velocity;
		return tempObject;
	}
	
	public static Entity getMissile(int x, int y, double angle, int width, int height, ID id) {
		Entity tempObject = new Missile(x, y, angle, width, height, id);
		tempObject.life = 300;
		tempObject.maxSpeed = 6;
		tempObject.maxForce = .3;
		tempObject.mass = 1;
		return tempObject;
	}
	
	public static Entity getSeeker(int x, int y, int width, int height, ID id) {
		Entity entity = new Seeker(x, y, width, height, id);
		entity.addBehavior(new Seek(entityManager.getEntityById(ID.Player), 200));
		entity.maxSpeed = 2;
		entity.maxForce = .25;
		return entity;
	}
	
	public static Entity getWallTile(int x, int y, int width, int height, ID id) {
		Entity tempObject = new Node(x, y, width, height, id);
		return tempObject;
	}
	
	public static Entity getPickup(int x, int y, int width, int height, ID id) {
		Entity tempObject = new Pickup(x, y, width, height, id);
		return tempObject;
	}
	
	public static Entity getMarker(int x, int y, int width, int height, Color color) {
		Entity tempObject = new Marker(x, y, width, height, color);
		return tempObject;
	}
}
