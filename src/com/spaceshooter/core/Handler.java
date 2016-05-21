package com.spaceshooter.core;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

import com.spaceshooter.fx.Emitter2D;
import com.spaceshooter.math.LineOfSight;
import com.spaceshooter.math.Vector2D;
import com.spaceshooter.objects.GameObject;
import com.spaceshooter.utils.Colors;
import com.spaceshooter.utils.ID;

public class Handler{

	public List<GameObject> bullets = Collections.synchronizedList(new ArrayList<GameObject>());
	public Emitter2D bulletTrailFx;
	public List<GameObject> collisionObjects = new ArrayList<GameObject>();
	public Emitter2D explosionFx;
	public ArrayList<GameObject> explosions = new ArrayList<GameObject>();
	public Emitter2D fireTrailFx;
	public List<GameObject> missiles = Collections.synchronizedList(new ArrayList<GameObject>());
	public List<GameObject> objects = new ArrayList<GameObject>();
	public List<GameObject> backgroundObjects = new ArrayList<GameObject>();
	public List<GameObject> pickups = new ArrayList<GameObject>();
	public Emitter2D smokeTrailFx;
	public Emitter2D thrustTrailFx;
	Random r = new Random();

	public Handler(){

		fireTrailFx = new Emitter2D(ID.MissileTrail);
		smokeTrailFx = new Emitter2D(ID.MissileTrail);
		explosionFx = new Emitter2D(ID.Explosion);
		bulletTrailFx = new Emitter2D(ID.BulletTrail);
		thrustTrailFx = new Emitter2D(ID.ThrustTrail);
	}
	public void addObject(List<GameObject> list, GameObject object) {

		list.add(object);
	}
	public void drawCollisionObjectBounds(Graphics g) {

		for (GameObject tempObject : collisionObjects){
			tempObject.drawBounds(g);
		}
	}

	public void drawObjectBounds(Graphics g) {

		for (GameObject tempObject : objects){
			tempObject.drawBounds(g);
		}
		for (GameObject tempObject : pickups){
			tempObject.drawBounds(g);
		}
	}
	public void drawProjectileObjectBounds(Graphics g) {

		for (GameObject tempObject : missiles){
			tempObject.drawBounds(g);
		}
		for (GameObject tempObject : bullets){
			tempObject.drawBounds(g);
		}
	}
	public void drawStats(Graphics g) {

		for (GameObject tempObject : objects){
			if (tempObject.getId1() == ID.Player
					|| tempObject.getId2() == ID.Enemy){
				tempObject.drawStats(g);
			}
		}
		for (GameObject tempObject : missiles){
			if (tempObject.getId1() == ID.Missile){
				tempObject.drawStats(g);
			}
		}
		for (GameObject tempObject : bullets){
			if (tempObject.getId1() == ID.Bullet){
				tempObject.drawStats(g);
			}
		}
	}
	public int getIndexById(ArrayList<GameObject> list, ID id) {

		for (GameObject tempObject : list){
			if (tempObject.getId1() == id) return objects.indexOf(tempObject);
		}
		return -1;
	}
	public List<GameObject> getNearByObjects(List<GameObject> objects, ID id,
			Vector2D position, double radius) {
		List<GameObject> list = new ArrayList<GameObject>();

		for (GameObject tempObject : objects){
			if (tempObject.getId1() == id || tempObject.getId2() == id){
				if (position.distSq(tempObject.position) < radius * radius){
					list.add(tempObject);
				}
				else{
					list.remove(tempObject);
				}
			}
		}
		return list;
	}
	public GameObject getObjectById(List<GameObject> objects, ID id) {

		for (GameObject tempObject : objects){
			if (tempObject.getId1() == id) return tempObject;
		}
		return null;
	}
	public void removeObject(List<GameObject> list, GameObject object) {

		list.remove(object);
	}
	public void render(Graphics g) {
		
		for (GameObject tempObject : backgroundObjects){
			tempObject.render(g);
		}

		for (GameObject tempObject : collisionObjects){
			tempObject.render(g);
		}

		for (GameObject tempObject : pickups){
			tempObject.render(g);
		}
		
		for (GameObject tempObject : explosions){
			tempObject.render(g);
		}
		
		smokeTrailFx.render(g);
		fireTrailFx.render(g);
		
		synchronized (missiles){
			Iterator<GameObject> missilesIterator = missiles.iterator();
			while (missilesIterator.hasNext()){
				GameObject tempObject = missilesIterator.next();
				tempObject.render(g);
			}
		}
		synchronized (bullets){
			Iterator<GameObject> bulletsIterator = bullets.iterator();
			while (bulletsIterator.hasNext()){
				GameObject tempObject = bulletsIterator.next();
				tempObject.render(g);
			}
		}
		
		for (GameObject tempObject : objects){
			tempObject.render(g);
		}
		
		//LineOfSight.render(g, 400, this.getObjectById(objects, ID.Player), objects, ID.Enemy, collisionObjects, ID.CollisionTile);

		explosionFx.render(g);
		bulletTrailFx.render(g);
		thrustTrailFx.render(g);
	}
	
	public void tick() {

		for (GameObject tempObject : objects){
			tempObject.tick();
		}
		for (GameObject tempObject : collisionObjects){
			tempObject.tick();
		}
		for (GameObject tempObject : pickups){
			tempObject.tick();
		}
		for (GameObject tempObject : explosions){
			tempObject.tick();
		}
		synchronized (missiles){
			Iterator<GameObject> missilesIterator = missiles.iterator();
			while (missilesIterator.hasNext()){
				GameObject tempObject = missilesIterator.next();

				tempObject.tick();

				if (tempObject.getId1() == ID.Missile){
					int x = (int) tempObject.position.getX();
					int y = (int) tempObject.position.getY();
					int offsetX = x + (tempObject.getWidth() / 2)
							+ (int) Math.cos(tempObject.getRotation());
					int offsetY = y + (tempObject.getHeight() / 2)
							+ (int) Math.sin(tempObject.getRotation());
					fireTrailFx.create(2, offsetX, offsetY, tempObject.getRotation(), r.nextDouble() * -1, .9, 1, 2, .5, .05, .001, 1, Colors.fire);
					smokeTrailFx.create(6, offsetX, offsetY, tempObject.getRotation(), r.nextDouble() * -1, .95, 8, 12, .5, .004, .0001, .5f, Colors.smoke);

					if (tempObject.expired){
						explosionFx.create(70, (int) tempObject.position.getX(), (int) tempObject.position.getY(), 180, -4, .95, 6, 6, -.5, .01, .008, 1f, Colors.smoke);
						explosionFx.create(70, (int) tempObject.position.getX(), (int) tempObject.position.getY(), 180, -4, .9, 4, 4, -.5, .01, .008, .8f, Colors.fire);
						missilesIterator.remove();
						break;
					}
				}
			}
		}
		synchronized (bullets){
			Iterator<GameObject> bulletsIterator = bullets.iterator();
			while (bulletsIterator.hasNext()){
				GameObject tempObject = bulletsIterator.next();
				tempObject.tick();
			}
		}

		fireTrailFx.tick();
		smokeTrailFx.tick();
		explosionFx.tick();
		bulletTrailFx.tick();
		thrustTrailFx.tick();

		collision();
	}
	private void collision() {

		for (GameObject a : collisionObjects){
			for (GameObject b : objects){
				if (a.getId2() == ID.CollisionTile && (b.getId1() == ID.Player || b.getId2() == ID.Enemy)){
					Collision.rectangle(b, a, 1, 1);
				}
			}
		}

		for (GameObject a : objects){
			if (a.getId1() == ID.Player){
				ListIterator<GameObject> pickupsIterator = pickups.listIterator();
				while (pickupsIterator.hasNext()){
					GameObject b = pickupsIterator.next();
					if (Collision.rectangle(b, a, 1, 1)){
						pickupsIterator.remove();
						explosionFx.create(10, (int) b.position.getX() + 16, (int) b.position.getY() + 16, 15, 2.2, .95, 4, 4, -.2, .01, .008, 1, Colors.fire);
						break;
					}
				}
			}
		}
		synchronized (bullets){
			Iterator<GameObject> bulletsIterator = bullets.iterator();
			while (bulletsIterator.hasNext()){
				GameObject a = bulletsIterator.next();
				if (a.getId1() == ID.Bullet){
					for (GameObject b : collisionObjects){
						if (b.getId2() == ID.CollisionTile){
							if (Collision.rectangle(a, b, 1, .98)){
								explosionFx.create(30, (int) a.position.getX() + 12, (int) a.position.getY(), 180, -2, .95, 8, 8, -.5, .01, .008, 1f, Colors.plasma);
								explosionFx.create(30, (int) a.position.getX() + 12, (int) a.position.getY(), 180, -4, .9, 4, 4, -.5, .01, .008, .8f, Colors.plasma);
								bulletsIterator.remove();
								break;
							}
						}
					}
					for (GameObject b : objects){
						if (b.getId2() == ID.Enemy){
							if (Collision.rectangle(a, b, 1, .98)){
								explosionFx.create(30, (int) a.position.getX() + 12, (int) a.position.getY(), 180, -2, .95, 8, 8, -.5, .01, .008, 1f, Colors.plasma);
								explosionFx.create(30, (int) a.position.getX() + 12, (int) a.position.getY(), 180, -4, .9, 4, 4, -.5, .01, .008, .8f, Colors.plasma);
								bulletsIterator.remove();
								break;
							}
						}
					}
				}
			}
			synchronized (missiles){
				Iterator<GameObject> missilesIterator = missiles.iterator();
				while (missilesIterator.hasNext()){
					GameObject a = missilesIterator.next();
					if (a.getId1() == ID.Missile){
						for (GameObject b : collisionObjects){
							if (b.getId2() == ID.CollisionTile){
								if (Collision.rectangle(a, b, 1, .98)){
									missilesIterator.remove();
									explosionFx.create(30, (int) a.position.getX() + 12, (int) a.position.getY(), 180, -4, .95, 6, 6, -.5, .01, .008, 1f, Colors.smoke);
									explosionFx.create(30, (int) a.position.getX() + 12, (int) a.position.getY(), 180, -4, .9, 4, 4, -.5, .01, .008, .8f, Colors.fire);
									break;
								}

							}
						}
					}
				}
				synchronized (missiles){
					missilesIterator = missiles.iterator();
					while (missilesIterator.hasNext()){
						GameObject a = missilesIterator.next();
						if (a.getId1() == ID.Missile){
							ListIterator<GameObject> objectIterator = objects.listIterator();
							while (objectIterator.hasNext()){
								GameObject b = objectIterator.next();
								if (b.getId2() == ID.Enemy){
									if (Collision.rectangle(a, b, 1, .98)){
										b.health -= 10;
										if (b.health <= 0){
											explosionFx.create(30, (int) b.position.getX() + 12, (int) b.position.getY(), 45, -4, .95, 6, 6, -.2, .01, .008, 1f, Colors.smoke);
											explosionFx.create(30, (int) b.position.getX() + 12, (int) b.position.getY(), 45, -4, .9, 6, 6, -.4, .01, .008, 1f, Colors.fire);
											explosionFx.create(30, (int) b.position.getX() + 12, (int) b.position.getY(), 45, -4, .9, 6, 6, -.4, .002, .0008, 1f, Colors.debris);
											objectIterator.remove();
										}
										explosionFx.create(30, (int) a.position.getX() + 12, (int) a.position.getY(), 45, -4, .95, 4, 4, -.2, .01, .008, 1f, Colors.smoke);
										explosionFx.create(30, (int) a.position.getX() + 12, (int) a.position.getY(), 45, -4, .9, 4, 4, -.4, .01, .008, 1f, Colors.fire);
										missilesIterator.remove();
										break;
									}
								}
							}
						}
					}
				}
			}
		}
	}
}
