package com.spaceshooter.math;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Line2D;
import java.util.List;
import java.util.ListIterator;

import com.spaceshooter.objects.GameObject;
import com.spaceshooter.utils.ID;
import com.spaceshooter.utils.Profiler;

public class LineOfSight{

	public static boolean calculate(GameObject target, GameObject object,
			List<GameObject> obstacles, ID id) {

		if (obstacles == null || id == null) return false;

		boolean visible = false;

		Vector2D objectVector = new Vector2D(object.position.getX()
				+ object.getWidth() * 0.5, object.position.getY()
				+ object.getWidth() * 0.5);
		Vector2D targetVector = new Vector2D(target.position.getX()
			+ target.getWidth() * 0.5, target.position.getY()
				+ target.getWidth() * 0.5);

		Vector2D distance = targetVector.subtract(objectVector);

		int segment = target.getWidth();

		int numberOfPoints = (int) distance.getDist() / segment;

		int counter = 0;

		while (counter++ != numberOfPoints){
			int vectorLength = segment * counter;

			Vector2D point = new Vector2D(object.position.getX()
				+ (distance.nx() * vectorLength), object.position.getY()
					+ (distance.ny() * vectorLength));

			Line2D line = new Line2D.Double(object.position.getX()
			, object.position.getY(), point.getX()
			, point.getY());

			ListIterator<GameObject> obstaclesIterator = obstacles.listIterator();
			while (obstaclesIterator.hasNext()){
				GameObject obstacle = obstaclesIterator.next();
				if (obstacle.getId2() == id || obstacle.getId1() == id){
					if (obstacle.getRectBounds().intersectsLine(line)){
						visible = false;
						break;
					}
					else{
						visible = true;
					}
				}
			}
		}
		return visible;
	}
	public static void render(Graphics g, int drawDistance, GameObject target,
			List<GameObject> objects, ID id1, List<GameObject> obstacles,
			ID id2) {

		ListIterator<GameObject> objectsIterator = objects.listIterator();
		while (objectsIterator.hasNext()){
			GameObject object = objectsIterator.next();
			if (object.getId1() == id1 || object.getId2() == id1){

				Vector2D objectVector = new Vector2D(object.position.getX()
			
						+ object.getWidth() * 0.5, object.position.getY()
						+ object.getWidth() * 0.5);
				Vector2D targetVector = new Vector2D(target.position.getX()
			
						+ target.getWidth() * 0.5, target.position.getY()
						+ target.getWidth() * 0.5);

				Vector2D distance = targetVector.subtract(objectVector);
				if (distance.getDist() < drawDistance){

					int segment = target.getWidth();

					int numberOfPoints = (int) distance.getDist() / segment;

					int counter = 0;

					while (counter++ != numberOfPoints){
						int vectorLength = segment * counter;

						Vector2D point = new Vector2D(object.position.getX()
			
								+ (distance.nx() * vectorLength), object.position.getY()
								+ (distance.ny() * vectorLength));

						Line2D line = new Line2D.Double(object.position.getX()
			, object.position.getY(), point.getX()
			, point.getY());

						ListIterator<GameObject> obstaclesIterator = obstacles.listIterator();
						while (obstaclesIterator.hasNext()){
							GameObject obstacle = obstaclesIterator.next();
							if (obstacle.getId2() == id2
									|| obstacle.getId1() == id2){
								if (obstacle.getRectBounds().intersectsLine(line)){
									obstacle.drawBounds(g);
									object.drawBounds(g);
									target.drawBounds(g);
									g.setColor(Color.cyan);
									g.drawLine((int) object.position.getX()
			
											+ (int) (object.getWidth() * 0.5), (int) object.position.getY()
											+ (int) (object.getWidth() * 0.5), (int) point.getX()
			
											+ (int) (object.getWidth() * 0.5), (int) point.getY()
											+ (int) (object.getWidth() * 0.5));

									Profiler.IN_SIGHT = "FALSE";
									break;
								}
								else{
									Profiler.IN_SIGHT = "TRUE";
								}
							}
						}
					}
				}
			}
		}
	}
}
