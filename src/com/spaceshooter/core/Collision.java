package com.spaceshooter.core;

import java.awt.image.BufferedImage;

import com.spaceshooter.math.Vector2D;
import com.spaceshooter.math.VectorMath;
import com.spaceshooter.objects.GameObject;

public class Collision{

	static String collisionSide = "No collision";
	static boolean colliding = false;

	public static boolean rectangle(GameObject r1, GameObject r2,
			double bounceForce, double friction) {

		Vector2D rv1 = new Vector2D(r1.position.getX(), r1.position.getY());
		Vector2D rv2 = new Vector2D(r2.position.getX(), r2.position.getY());
		Vector2D v0 = rv2.subtract(rv1);

		if (Math.abs(v0.getX()) < r1.getWidth() * 0.5 + r2.getWidth() * 0.5){

			if (Math.abs(v0.getY()) < r1.getHeight() * 0.5 + r2.getHeight() * 0.5){

				double overlapX = r1.getWidth() * 0.5 + r2.getWidth() * 0.5
						- Math.abs(v0.getX());

				double overlapY = r1.getHeight() * 0.5 + r2.getHeight() * 0.5
						- Math.abs(v0.getY());

				if (overlapX >= overlapY){
					if (v0.getY() > 0){
						collisionSide = "Top";
						r1.position.setY(r1.position.getY() - overlapY);
						colliding = true;
					}
					else{
						collisionSide = "Bottom";
						r1.position.setY(r1.position.getY() + overlapY);
						colliding = true;
					}

					/*Vector2D rx1 = new Vector2D(r1.position.getX() - r2.getWidth()
							* 0.5, r1.position.getY());
					Vector2D rx2 = new Vector2D(r1.position.getX() + r2.getHeight()
							* 0.5, r1.position.getY());
					Vector2D xAxis = rx2.subtract(rx1);
					
					VectorMath.bounceOnPlane(r1, xAxis, bounceForce, friction);*/
				}
				else{
					if (v0.getX() > 0){
						collisionSide = "Left";
						r1.position.setX(r1.position.getX() - overlapX);
						colliding = true;
					}
					else{
						collisionSide = "Right";
						r1.position.setX(r1.position.getX() + overlapX);
						colliding = true;
					}

					/*Vector2D ry1 = new Vector2D(r1.position.getX(), r1.position.getY()
							- r2.getHeight() * 0.5);
					Vector2D ry2 = new Vector2D(r1.position.getY(), r1.position.getY()
							+ r2.getWidth() * 0.5);
					Vector2D yAxis = ry2.subtract(ry1);

					VectorMath.bounceOnPlane(r1, yAxis, bounceForce, friction);*/
				}
			}
			else{
				collisionSide = "No collision";
				colliding = false;
			}
		}
		else{
			collisionSide = "No collision";
			colliding = false;
		}
		return colliding;
	}
	public static boolean ellipse(GameObject a, GameObject b, double bounceForce) {
		Vector2D diff = a.position.subtract(b.position);

		double totalRadii = (a.getWidth() * .5) + (b.getWidth() * .5);

		if (diff.getDist() < totalRadii){
			colliding = true;
			double overlap = totalRadii - diff.getDist();

			double colX = Math.abs(diff.nx() * overlap * .5);
			double colY = Math.abs(diff.ny() * overlap * .5);

			int xSide;
			int ySide;

			if (a.position.getX() > b.position.getX()) xSide = 1;
			else xSide = -1;
			if (a.position.getY() > b.position.getY()) ySide = 1;
			else ySide = -1;

			a.position.setX(a.position.getX() + (colX * xSide));
			a.position.setY(a.position.getY() + (colY * ySide));

			Vector2D v1 = b.velocity.subtract(a.velocity);
			Vector2D b1 = VectorMath.project(v1, diff);

			a.velocity.setX(a.velocity.getX() + b1.getX() / b.mass * bounceForce);
			a.velocity.setY(a.velocity.getY() + b1.getY() / b.mass * bounceForce);
		}
		return colliding;
	}
	public static boolean pixel(double x1, double y1,
			BufferedImage image1, double x2, double y2, BufferedImage image2) {

		double width1 = x1 + image1.getWidth() - 1, height1 = y1
				+ image1.getHeight() - 1, width2 = x2 + image2.getWidth() - 1, height2 = y2
				+ image2.getHeight() - 1;

		int xstart = (int) Math.max(x1, x2), ystart = (int) Math.max(y1, y2), xend = (int) Math.min(width1, width2), yend = (int) Math.min(height1, height2);

		int toty = Math.abs(yend - ystart);
		int totx = Math.abs(xend - xstart);

		for (int y = 1; y < toty - 1; y++){
			int ny = Math.abs(ystart - (int) y1) + y;
			int ny1 = Math.abs(ystart - (int) y2) + y;

			for (int x = 1; x < totx - 1; x++){
				int nx = Math.abs(xstart - (int) x1) + x;
				int nx1 = Math.abs(xstart - (int) x2) + x;
				try{
					if (((image1.getRGB(nx, ny) & 0xFF000000) != 0x00)
							&& ((image2.getRGB(nx1, ny1) & 0xFF000000) != 0x00)){

					return true; }
				}
				catch (Exception e){
					/*
					 * System.out.println("s1 = " + nx + "," + ny + "  -  s2 = "
					 * + nx1 + "," + ny1);
					 */
				}
			}
		}
		return false;
	}
}
