package com.spaceshooter.core;

import java.awt.Color;

import com.spaceshooter.behaviors.Avoid;
import com.spaceshooter.behaviors.Flee;
import com.spaceshooter.behaviors.FollowPath;
import com.spaceshooter.behaviors.Pursue;
import com.spaceshooter.behaviors.SmartSeek;
import com.spaceshooter.behaviors.Wander;
import com.spaceshooter.entity.Blackhole;
import com.spaceshooter.entity.Bullet;
import com.spaceshooter.entity.Entity;
import com.spaceshooter.entity.Missile;
import com.spaceshooter.entity.Pickup;
import com.spaceshooter.entity.Player;
import com.spaceshooter.entity.Rocket;
import com.spaceshooter.entity.Seeker;
import com.spaceshooter.entity.Turret;
import com.spaceshooter.gui.Marker;
import com.spaceshooter.math.Vector2D;
import com.spaceshooter.objects.tiles.RoundTile;
import com.spaceshooter.objects.tiles.Tile;
import com.spaceshooter.objects.tiles.WallTile;
import com.spaceshooter.utils.ID;

public class ObjectFactory{

	private static EntityManager handler = EntityManager.getInstance();
	private static Map map = Map.getInstance();

	public static Entity getPlayer(int x, int y, int width, int height,
			ID id1, ID id2) {

		Entity tempObject = new Player(x, y, width, height, id1, id2);
		tempObject.maxForce = 1;
		tempObject.maxSpeed = 2;
		tempObject.friction = .98;
		tempObject.projectileSpeed = 7;
		return tempObject;
	}
	public static Entity getBullet(int x, int y, int width, int height,
			Vector2D velocity, ID id1, ID id2) {

		Entity tempObject = new Bullet(x, y, width, height, id1, id2);
		tempObject.velocity = velocity;
		return tempObject;
	}
	public static Entity getMissile(int x, int y, double angle, int width, int height, ID id1, ID id2) {

		Entity tempObject = new Missile(x, y, angle, width, height, id1, id2);
		tempObject.life = 300;
		tempObject.maxSpeed = 6;
		tempObject.maxForce = .3;
		tempObject.mass = 1;
		tempObject.addBehavior(new SmartSeek(handler.entities, ID.Enemy, null, 400, handler.entities, ID.CollisionTile));
		tempObject.addBehavior(new Flee(handler.entities, ID.CollisionTile, null, 50, null, null));
		return tempObject;
	}
	public static Entity getDrone(int x, int y, int width, int height, ID id1, ID id2) {

		Entity tempObject = new Seeker(x, y, width, height, id1, id2);
		tempObject.addBehavior(new FollowPath(map.getPaths(), 20, 100, true));
		tempObject.maxSpeed = 3;
		tempObject.maxForce = .3;
		return tempObject;
	}
	public static Entity getSeeker(int x, int y, int width, int height,
			ID id1, ID id2) {

		Entity tempObject = new Seeker(x, y, width, height, id1, id2);
		//tempObject.addBehavior(new Wander(2, 2, 2, 2));
		tempObject.addBehavior(new Pursue(null, null, handler.getEntityById(ID.Player), 400, handler.entities, ID.CollisionTile));
		//tempObject.addBehavior(new Avoid(handler.entities, ID.CollisionTile, 100, 50));
		tempObject.maxSpeed = 1.5;
		tempObject.maxForce = .2;
		return tempObject;
	}
	public static Entity getRocket(int x, int y, int width, int height, ID id1, ID id2) {

		Entity tempObject = new Rocket(x, y, width, height, id1, id2);
		tempObject.maxSpeed = 3;
		tempObject.maxForce = .3;
		return tempObject;
	}
	public static Entity getTurret(int x, int y, int width, int height, ID id1, ID id2) {

		Entity tempObject = new Turret(x, y, width, height, id1, id2);
		tempObject.fireRate = 50;
		tempObject.projectileSpeed = 4;
		tempObject.fireRadius = 400;
		return tempObject;
	}
	public static Entity getBlackHole(int x, int y, int width, int height, ID id1, ID id2) {

		Entity tempObject = new Blackhole(x, y, width, height, id1, id2);
		tempObject.health = 5000;
		return tempObject;
	}
	public static Entity getTile(int x, int y, int width, int height, ID id1, ID id2) {

		Entity tempObject = new Tile(x, y, width, height, id1, id2);
		return tempObject;
	}
	public static Entity getWallTile(int x, int y, int width,
			int height, ID id1, ID id2) {

		Entity tempObject = new WallTile(x, y, width, height, id1, id2);
		return tempObject;
	}
	public static Entity getRoundTile(int x, int y, int width, int height,
			ID id1, ID id2) {

		Entity tempObject = new RoundTile(x, y, width, height, id1, id2);
		tempObject.mass = 10;
		return tempObject;
	}
	public static Entity getPickup(int x, int y, int width, int height,
			ID id1, ID id2) {

		Entity tempObject = new Pickup(x, y, width, height, id1, id2);
		return tempObject;
	}
	public static Entity getMarker(int x, int y, int width, int height,
			Color color) {

		Entity tempObject = new Marker(x, y, width, height, color);
		return tempObject;
	}
}
