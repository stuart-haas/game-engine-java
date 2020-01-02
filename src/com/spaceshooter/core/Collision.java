package com.spaceshooter.core;

import com.spaceshooter.entity.Entity;
import com.spaceshooter.math.Vector2;

public class Collision{

	public static boolean rectangle(Entity aa, Entity bb) {
		
		boolean colliding = false;
		Vector2 v1 = new Vector2(aa.position.getX(), aa.position.getY());
		Vector2 v2 = new Vector2(bb.position.getX(), bb.position.getY());
		Vector2 diff = v2.subtract(v1);

		if (Math.abs(diff.getX()) < aa.getWidth() * 0.5 + bb.getWidth() * 0.5){

			if (Math.abs(diff.getY()) < aa.getHeight() * 0.5 + bb.getHeight() * 0.5){

				double overlapX = aa.getWidth() * 0.5 + bb.getWidth() * 0.5 - Math.abs(diff.getX());

				double overlapY = aa.getHeight() * 0.5 + bb.getHeight() * 0.5 - Math.abs(diff.getY());

				if (overlapX >= overlapY){
					if (diff.getY() > 0){
						aa.position.setY(aa.position.getY() - overlapY);
						colliding = true;
					}
					else{
						aa.position.setY(aa.position.getY() + overlapY);
						colliding = true;
					}
				}
				else{
					if (diff.getX() > 0){
						aa.position.setX(aa.position.getX() - overlapX);
						colliding = true;
					}
					else{
						aa.position.setX(aa.position.getX() + overlapX);
						colliding = true;
					}

				}
			}
			else{
				colliding = false;
			}
		}
		else{
			colliding = false;
		}
		return colliding;
	}
}
