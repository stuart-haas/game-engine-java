package com.spaceshooter.core;

import java.util.ArrayList;

import com.spaceshooter.entity.Entity;
import com.spaceshooter.map.Layer;
import com.spaceshooter.map.Map;
import com.spaceshooter.math.Vector;

public class Collision {
	
	public static interface EventCallback {
		void success(Entity source, Entity target);
	}
	
	static Map map = Map.getInstance();
	
	public static void detect(Layer layer, Entity source, int distance, boolean debug, EventCallback callback) {
		ArrayList<Entity> neighbors = map.getNeighborsByPoint(layer, source.position, distance);
		for (Entity entity : neighbors) {
			if(entity != null) {
				if(debug) {
					entity.debug = true;
				}
				callback.success(source, entity);
			}
		}
	}

	public static boolean resolve(Entity aa, Entity bb) {
		
		boolean colliding = false;
		Vector v1 = new Vector(aa.position.getX(), aa.position.getY());
		Vector v2 = new Vector(bb.position.getX(), bb.position.getY());
		Vector diff = v2.subtract(v1);

		if (Math.abs(diff.getX()) < aa.getWidth() / 2 + bb.getWidth() / 2) {

			if (Math.abs(diff.getY()) < aa.getHeight() / 2 + bb.getHeight() / 2) {

				double overlapX = aa.getWidth() / 2 + bb.getWidth() / 2 - Math.abs(diff.getX());
				double overlapY = aa.getHeight() / 2 + bb.getHeight() / 2 - Math.abs(diff.getY());

				if (overlapX >= overlapY) {
					if (diff.getY() > 0) {
						aa.position.setY(aa.position.getY() - overlapY);
						colliding = true;
					}
					else {
						aa.position.setY(aa.position.getY() + overlapY);
						colliding = true;
					}
				} else {
					if (diff.getX() > 0) {
						aa.position.setX(aa.position.getX() - overlapX);
						colliding = true;
					}
					else {
						aa.position.setX(aa.position.getX() + overlapX);
						colliding = true;
					}
				}
			}
		}
		return colliding;
	}
}
