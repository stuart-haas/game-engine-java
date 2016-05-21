package com.spaceshooter.behaviors;

import java.util.List;

import com.spaceshooter.math.Vector2D;
import com.spaceshooter.objects.GameObject;
import com.spaceshooter.utils.ID;

public class Avoid extends ABehavior{

	public Avoid(List<GameObject> group, ID id1, double avoidDist,
			double avoidBuffer){
		super(group, id1, null, ID.Avoid);
		this.avoidDist = avoidDist;
		this.avoidBuffer = avoidBuffer;
	}

	@Override
	public Vector2D calculate(GameObject object) {

		for (GameObject tempObject : group){
			if (tempObject.getId1() == id1){

				Vector2D heading = object.velocity.clone().normalize();

				Vector2D difference = tempObject.position.subtract(object.position);
				double dotProd = difference.dotProduct(heading);

				if (dotProd > 0){

					Vector2D feeler = heading.multiply(avoidDist);
					Vector2D proj = heading.multiply(dotProd);
					double dist = proj.subtract(difference).getDist();

					if (dist < object.getWidth() + avoidBuffer
							&& proj.getDist() < feeler.getDist()){

						Vector2D force = heading.multiply(object.maxSpeed);
						force.setAngle(force.getAngle()
								+ difference.sign(object.velocity) * Math.PI
								/ 2);

						force = force.multiply(1 - proj.getDist()
								/ feeler.getDist());

						object.steeringForce = object.steeringForce.add(force);
						object.velocity = object.velocity.multiply(proj.getDist()
								/ feeler.getDist());
					}
				}
			}
		}
		return new Vector2D();
	}
}