package com.spaceshooter.physics;

import java.util.ArrayList;

import com.spaceshooter.entity.Entity;
import com.spaceshooter.entity.Node;
import com.spaceshooter.map.Layer;
import com.spaceshooter.map.Grid;
import com.spaceshooter.math.Vector;

public class Collision {
	
	public interface EventCallback {
		void success(Entity source, Entity target);
	}
	
	static Grid map = Grid.getInstance();
	
	public static void detect(Layer layer, Entity source, int distance, boolean debug, EventCallback callback) {
		ArrayList<Node> neighbors = map.getNeighborsByPoint(layer, source.position, distance);
		for (Node node : neighbors) {
			if(node != null) {
				if(debug) {
					node.debug = true;
				}
				callback.success(source, node);
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

				double overlapX = aa.getWidth() * 0.5 + bb.getWidth() * 0.5 - Math.abs(diff.getX());
				double overlapY = aa.getHeight() * 0.5 + bb.getHeight() * 0.5 - Math.abs(diff.getY());

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
