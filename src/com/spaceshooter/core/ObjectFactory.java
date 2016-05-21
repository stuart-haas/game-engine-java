package com.spaceshooter.core;

import java.awt.Color;

import com.spaceshooter.behaviors.Avoid;
import com.spaceshooter.behaviors.Flee;
import com.spaceshooter.behaviors.FollowPath;
import com.spaceshooter.behaviors.Pursue;
import com.spaceshooter.behaviors.SmartSeek;
import com.spaceshooter.behaviors.Wander;
import com.spaceshooter.gui.Marker;
import com.spaceshooter.math.Vector2D;
import com.spaceshooter.objects.Blackhole;
import com.spaceshooter.objects.Bullet;
import com.spaceshooter.objects.GameObject;
import com.spaceshooter.objects.Missile;
import com.spaceshooter.objects.Pickup;
import com.spaceshooter.objects.Player;
import com.spaceshooter.objects.Rocket;
import com.spaceshooter.objects.Seeker;
import com.spaceshooter.objects.Turret;
import com.spaceshooter.objects.tiles.RoundTile;
import com.spaceshooter.objects.tiles.Tile;
import com.spaceshooter.objects.tiles.WallTile;
import com.spaceshooter.utils.ID;

public class ObjectFactory{

	private static Handler handler = Game.getHandler();
	private static Map map = Game.getMap();

	public static GameObject getPlayer(int x, int y, int width, int height,
			ID id1, ID id2) {

		GameObject tempObject = new Player(x, y, width, height, id1, id2);
		tempObject.maxForce = .5;
		tempObject.maxSpeed = 5;
		tempObject.friction = .95;
		tempObject.projectileSpeed = 7;
		return tempObject;
	}
	public static GameObject getBullet(int x, int y, int width, int height,
			Vector2D velocity, ID id1, ID id2) {

		GameObject tempObject = new Bullet(x, y, width, height, id1, id2);
		tempObject.velocity = velocity;
		return tempObject;
	}
	public static GameObject getMissile(int x, int y, double angle, int width, int height, ID id1, ID id2) {

		GameObject tempObject = new Missile(x, y, angle, width, height, id1, id2);
		tempObject.life = 300;
		tempObject.maxSpeed = 6;
		tempObject.maxForce = .3;
		tempObject.mass = 1;
		tempObject.addBehavior(new SmartSeek(handler.objects, ID.Enemy, null, 400, handler.collisionObjects, ID.CollisionTile));
		tempObject.addBehavior(new Flee(handler.collisionObjects, ID.CollisionTile, null, 50, null, null));
		return tempObject;
	}
	public static GameObject getDrone(int x, int y, int width, int height, ID id1, ID id2) {

		GameObject tempObject = new Seeker(x, y, width, height, id1, id2);
		tempObject.addBehavior(new FollowPath(map.getPaths(), 20, 100, true));
		tempObject.maxSpeed = 3;
		tempObject.maxForce = .3;
		return tempObject;
	}
	public static GameObject getSeeker(int x, int y, int width, int height,
			ID id1, ID id2) {

		GameObject tempObject = new Seeker(x, y, width, height, id1, id2);
		tempObject.addBehavior(new Wander(2, 2, 2, 2));
		tempObject.addBehavior(new Pursue(null, null, handler.getObjectById(handler.objects, ID.Player), 400, handler.collisionObjects, ID.CollisionTile));
		tempObject.addBehavior(new Avoid(handler.collisionObjects, ID.CollisionTile, 100, 50));
		tempObject.maxSpeed = 2;
		tempObject.maxForce = .2;
		return tempObject;
	}
	public static GameObject getRocket(int x, int y, int width, int height, ID id1, ID id2) {

		GameObject tempObject = new Rocket(x, y, width, height, id1, id2);
		tempObject.maxSpeed = 3;
		tempObject.maxForce = .3;
		return tempObject;
	}
	public static GameObject getTurret(int x, int y, int width, int height, ID id1, ID id2) {

		GameObject tempObject = new Turret(x, y, width, height, id1, id2);
		tempObject.fireRate = 50;
		tempObject.projectileSpeed = 4;
		tempObject.fireRadius = 400;
		return tempObject;
	}
	public static GameObject getBlackHole(int x, int y, int width, int height, ID id1, ID id2) {

		GameObject tempObject = new Blackhole(x, y, width, height, id1, id2);
		tempObject.health = 5000;
		return tempObject;
	}
	public static GameObject getTile(int x, int y, int width, int height, ID id1, ID id2) {

		GameObject tempObject = new Tile(x, y, width, height, id1, id2);
		return tempObject;
	}
	public static GameObject getWallTile(int x, int y, int width,
			int height, ID id1, ID id2) {

		GameObject tempObject = new WallTile(x, y, width, height, id1, id2);
		return tempObject;
	}
	public static GameObject getRoundTile(int x, int y, int width, int height,
			ID id1, ID id2) {

		GameObject tempObject = new RoundTile(x, y, width, height, id1, id2);
		tempObject.mass = 10;
		return tempObject;
	}
	public static GameObject getPickup(int x, int y, int width, int height,
			ID id1, ID id2) {

		GameObject tempObject = new Pickup(x, y, width, height, id1, id2);
		return tempObject;
	}
	public static GameObject getMarker(int x, int y, int width, int height,
			Color color) {

		GameObject tempObject = new Marker(x, y, width, height, color);
		return tempObject;
	}
}
