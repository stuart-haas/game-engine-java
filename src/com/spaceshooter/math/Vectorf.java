package com.spaceshooter.math;

import com.spaceshooter.entity.Entity;
import com.spaceshooter.map.Layer;
import com.spaceshooter.map.Map;

public class Vectorf{
	
	public static double DEG_TO_RAD = Math.PI / 180;
	public static double RAD_TO_DEG = 180 / Math.PI;
	
	static Map map = Map.getInstance();
	
	public static Vector project(Vector v1, Vector v2) {
		
		double dp = dotProduct(v1, v2);
		double px = dp * v2.nx();
		double py = dp * v2.ny();
		return new Vector(px, py);
	}
	
	public static double dotProduct(Vector v1, Vector v2) {
		return v1.getX() * v2.nx() + v1.getY() * v2.ny();
	}
	
	public static double dotProduct2(Vector v1, Vector v2) {
		return v1.getX() * v2.getX() + v1.getY() * v2.getY();
	}
	
	public static double crossProduct(Vector v1, Vector v2) {
		return (v1.getX() * v2.getX()) - (v1.getY() * v2.getY());
	}
	
	public static double perpProduct(Vector v1, Vector v2) {
	
		double perpProduct = 1 / v1.getX() * v2.getY() - v1.getY() * v2.getX();
		
		if(perpProduct != 0){
			return perpProduct;
		} else {
			return 1;
		}
	}
	
	public static double angleBetween(Vector v1, Vector v2) {
		
		double dp = dotProduct(v1, v2);
		double mSq = v1.getDist() * v2.getDist();
		double angle = (Math.acos(dp / mSq)) * RAD_TO_DEG;
		return angle;
	}
	
	public static Vector bounce(Vector v1, Vector v2) {
		
		Vector p1 = Vectorf.project(v1, v2);
		Vector p2 = Vectorf.project(v1, v2.ln());
		
		double bx = p1.getX() + (p2.getX() * -1);
		double by = p1.getY() + (p2.getY() * -1);
		
		return new Vector(bx, by);
	}
	
	public static Vector bounceOnPlane(Entity a, Vector plane, double bounce, double friction) {
		
		Vector a1 = new Vector(a.position.getX(), a.position.getY());
		Vector a2 = new Vector(a.position.getX() + a.velocity.getX(), a.position.getY() + a.velocity.getY());
		Vector v1 = a2.subtract(a1);
		
		Vector p1 = Vectorf.project(v1, plane);
		Vector p2 = Vectorf.project(v1, plane.ln());
		
		double bx = p2.getX() * -1;
		double by = p2.getY() * -1;
		
		double fx = p1.getX();
		double fy = p1.getY();
		
		return new Vector((bx * bounce) + (fx * friction), (by * bounce) + (fy * friction));
	}
	
	public static Vector gravity(Vector vector, double mass1, double mass2) {
		
		double vx = vector.nx() * (mass1 * mass2) / vector.getDist();
		double vy = vector.ny() * (mass1 * mass2) / vector.getDist();
		
		return new Vector(vx, vy);
	}
	
	public static boolean lineOfSight(Layer layer, Vector source, Vector target, int steps, Vector offset) {
		Vector v1 = new Vector(source.getX() + offset.getX(), source.getY() + offset.getY());
		Vector v2 = new Vector(target.getX() + offset.getX(), target.getY() + offset.getY());
		Vector diff = v2.subtract(v1);
		int numberOfPoints = (int) (diff.getDist() / steps);
		
		for(int i = 0; i < numberOfPoints; i ++) {
			int length = steps * i;
			double px = target.getX() + diff.nx() * -length;
			double py = target.getY() + diff.ny() * -length;
			
			Entity node = map.nodeFromWorldPoint(new Vector(px, py), layer);
			if(node != null) {
				node.debug = true;
				return false;
			}
		}
		
		return true;
	}
}
