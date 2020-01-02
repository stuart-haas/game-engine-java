package com.spaceshooter.math;

import com.spaceshooter.entity.Entity;

public class VectorMath{
	
	public static double DEG_TO_RAD = Math.PI / 180;
	public static double RAD_TO_DEG = 180 / Math.PI;
	
	public static Vector2 project(Vector2 v1, Vector2 v2) {
		double dp = dotProduct(v1, v2);

		double px = dp * v2.nx();
		double py = dp * v2.ny();

		return new Vector2(px, py);
	}
	public static double dotProduct(Vector2 v1, Vector2 v2) {
		return v1.getX() * v2.nx() + v1.getY() * v2.ny();
	}
	public static double dotProduct2(Vector2 v1, Vector2 v2){
		
		return v1.getX() * v2.getX() + v1.getY() * v2.getY();
	}
	public static double crossProduct(Vector2 v1, Vector2 v2){
		
		return (v1.getX() * v2.getX()) - (v1.getY() * v2.getY());
	}
	public static double perpProduct(Vector2 v1, Vector2 v2){
		
		double perpProduct = 1 / v1.getX() * v2.getY() - v1.getY() * v2.getX();
		
		if(perpProduct != 0){
			
			return perpProduct;
		}
		else{
			
			return 1;
		}
	}
	public static double angleBetween(Vector2 v1, Vector2 v2){
		
		double dp = dotProduct(v1, v2);
		
		double mSq = v1.getDist() * v2.getDist();
			
		double angle = (Math.acos(dp / mSq)) * RAD_TO_DEG;
		
		return angle;
		
	}
	public static Vector2 bounce(Vector2 v1, Vector2 v2){
		
		Vector2 p1 = VectorMath.project(v1, v2);
		
		Vector2 p2 = VectorMath.project(v1, v2.ln());
		
		double bx = p1.getX() + (p2.getX() * -1);
		double by = p1.getY() + (p2.getY() * -1);
		
		return new Vector2(bx, by);
	}
	public static void bounceOnPlane(Entity a, Vector2 plane, double bounce, double friction){
		
		Vector2 a1 = new Vector2(a.position.getX(), a.position.getY());
		Vector2 a2 = new Vector2(a.position.getX() + a.velocity.getX(), a.position.getY() + a.velocity.getY());
		Vector2 v1 = a2.subtract(a1);
		
		Vector2 p1 = VectorMath.project(v1, plane);
		Vector2 p2 = VectorMath.project(v1, plane.ln());
		
		double bx = p2.getX() * -1;
		double by = p2.getY() * -1;
		
		double fx = p1.getX();
		double fy = p1.getY();
		
		a.velocity.setX((bx * bounce) + (fx * friction));
		a.velocity.setY((by * bounce) + (fy * friction));
	}
	public static Vector2 gravity(Vector2 vector, double mass1, double mass2){
		
		double gx = vector.nx() * (mass1 * mass2) / vector.getDist();
		double gy = vector.ny() * (mass1 * mass2) / vector.getDist();
		
		return new Vector2(gx, gy);
	}
}
