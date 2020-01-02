package com.spaceshooter.core;

import java.awt.Color;

import com.spaceshooter.behaviors.Arrive;
import com.spaceshooter.behaviors.FollowPath;
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
import com.spaceshooter.math.Vector2;
import com.spaceshooter.objects.tiles.RoundTile;
import com.spaceshooter.objects.tiles.Tile;
import com.spaceshooter.objects.tiles.WallTile;
import com.spaceshooter.utils.ID;

public class EntityFactory {

	private static EntityManager handler = EntityManager.getInstance();
	private static Map map = Map.getInstance();

	public static Entity getPlayer(int x, int y, int width, int height, ID id) {
		Entity tempObject = new Player(x, y, width, height, id);
		tempObject.maxForce = 1;
		tempObject.maxSpeed = 2;
		tempObject.friction = .98;
		tempObject.projectileSpeed = 7;
		return tempObject;
	}
	
	public static Entity getBullet(int x, int y, int width, int height, Vector2 velocity, ID id) {

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
	
	public static Entity getDrone(int x, int y, int width, int height, ID id) {
		Entity tempObject = new Seeker(x, y, width, height, id);
		tempObject.addBehavior(new FollowPath(map.getPaths(), 20, 100, true));
		tempObject.maxSpeed = 3;
		tempObject.maxForce = .3;
		return tempObject;
	}
	
	public static Entity getSeeker(int x, int y, int width, int height, ID id) {
		Entity entity = new Seeker(x, y, width, height, id);
		entity.addBehavior(new Arrive(handler.getEntityById(ID.Player), 200));
		entity.maxSpeed = 2;
		entity.maxForce = .25;
		return entity;
	}
	
	public static Entity getRocket(int x, int y, int width, int height, ID id) {
		Entity tempObject = new Rocket(x, y, width, height, id);
		tempObject.maxSpeed = 3;
		tempObject.maxForce = .3;
		return tempObject;
	}
	
	public static Entity getTurret(int x, int y, int width, int height, ID id) {
		Entity tempObject = new Turret(x, y, width, height, id);
		tempObject.fireRate = 50;
		tempObject.projectileSpeed = 4;
		tempObject.fireRadius = 400;
		return tempObject;
	}
	
	public static Entity getBlackHole(int x, int y, int width, int height, ID id) {
		Entity tempObject = new Blackhole(x, y, width, height, id);
		tempObject.health = 5000;
		return tempObject;
	}
	
	public static Entity getTile(int x, int y, int width, int height, ID id) {
		Entity tempObject = new Tile(x, y, width, height, id);
		return tempObject;
	}
	
	public static Entity getWallTile(int x, int y, int width, int height, ID id) {
		Entity tempObject = new WallTile(x, y, width, height, id);
		return tempObject;
	}
	
	public static Entity getRoundTile(int x, int y, int width, int height, ID id) {
		Entity tempObject = new RoundTile(x, y, width, height, id);
		tempObject.mass = 10;
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
