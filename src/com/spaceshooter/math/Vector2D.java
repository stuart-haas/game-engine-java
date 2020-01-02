package com.spaceshooter.math;

import java.awt.Rectangle;

public class Vector2D{

	private double x, y = 0;
	
	public Vector2D(double x, double y) {
		set(x, y);
	}
	
	public Vector2D() {
		set(0, 0);
	}
	
	public double getX() {
		return fix(x, 2);
	}
	
	public double getY() {
		return fix(y, 2);
	}	
	
	public void setX(double x) {
		this.x = fix(x, 2);
	}
	
	public void setY(double y) {
		this.y = fix(y, 2);
	}
	
	public void set(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public void constrain(Rectangle bounds) {
		x = fix(Math.min(Math.max(bounds.getMinX(), x), bounds.getMaxX()), 2);
		y = fix(Math.min(Math.max(bounds.getMinY(), y), bounds.getMaxY()), 2);
	}
	
	public Vector2D normalize() {
		if (getDist() != 0) {
			fix(x /= getDist(), 2);
			fix(y /= getDist(), 2);
		}
		return this;
	}
	
	public Vector2D truncate(double max) {
		setDist(Math.min(max, getDist()));
		return this;
	}
	
	public Vector2D reverse() {
		x -= x;
		y -= y;
		return this;
	}
	
	public Vector2D zero() {
		x = 0;
		y = 0;
		return this;
	}
	
	public Vector2D clone() {
		return new Vector2D(x, y);
	}
	
	public boolean isEqualTo(Vector2D v1){
		return this.x == v1.x  && this.y == v1.y ? true : false;
	}
	
	public boolean isNormalTo(Vector2D v1){
		return this.nx() == v1.nx() && this.ny() == v1.ny() ? true : false;
	}
	
	public double fix(double value, int places){
		int temp = (int)(value * Math.pow(10 , places));  
		return ((double)temp)/Math.pow(10 , places); 
	}
	
	public String toString() {
		return "[Vector2D (x:" + x + ", y:" + y + ")]";
	}
	
	public int sign(Vector2D v1) {
		return rn().dotProduct(v1) < 0 ? -1 : 1;
	}
	
	public double dotProduct(Vector2D v1) {
		return fix(x * v1.nx() + y * v1.ny(), 2);
	}
	
	public double dotProduct2(Vector2D v1) {
		return fix(x * v1.x + y * v1.y, 2);
	}
	
	public double crossProduct(Vector2D v1) {
		return fix((x * v1.x) - (y * v1.y), 2);
	}
	
	public double perpProduct(Vector2D v1) {
		double perpProduct = fix(1 / x * v1.y - y * v1.x, 2);

		if (perpProduct != 0) {
			return perpProduct;
		}
		else{
			return 1;
		}
	}	
	
	public Vector2D project(Vector2D v1) {
		double dp = dotProduct(v1);

		double px = fix(dp * v1.nx(), 2);
		double py = fix(dp * v1.ny(), 2);

		return new Vector2D(px, py);
	}
	
	public double angleBetween(Vector2D v1) {
		return fix(Math.acos(dotProduct(v1) / (getDist() * v1.getDist())), 2);
	}
	
	public Vector2D add(Vector2D v1) {
		Vector2D v0 = new Vector2D();
		v0.x = x + v1.x;
		v0.y = y + v1.y;
		return v0;
	}
	
	public Vector2D add(double value) {
		Vector2D v0 = new Vector2D();
		v0.x = x + value;
		v0.y = y + value;
		return v0;
	}
	
	public Vector2D subtract(Vector2D v1) {
		Vector2D v0 = new Vector2D();
		v0.x = x - v1.x;
		v0.y = y - v1.y;
		return v0;
	}
	
	public Vector2D subtract(double value) {
		Vector2D v0 = new Vector2D();
		v0.x = x - value;
		v0.y = y - value;
		return v0;
	}
	
	public Vector2D multiply(Vector2D v1) {
		Vector2D v0 = new Vector2D();
		v0.x = x * v1.x;
		v0.y = y * v1.y;
		return v0;
	}
	
	public Vector2D multiply(double value) {
		Vector2D v0 = new Vector2D();
		v0.x = x * value;
		v0.y = y * value;
		return v0;
	}
	
	public Vector2D divide(Vector2D v1) {
		Vector2D v0 = new Vector2D();
		v0.x = x / v1.x;
		v0.y = y / v1.y;
		return v0;
	}
	
	public Vector2D divide(double value) {
		Vector2D v0 = new Vector2D();
		v0.x = x / value;
		v0.y = y / value;
		return v0;
	}
	
	public double dist(Vector2D v1) {
		return fix(Math.sqrt(distSq(v1)), 2);
	}
	
	public double distSq(Vector2D v1) {
		double dx = v1.x - x;
		double dy = v1.y - y;
		return fix(dx * dx + dy * dy, 2);
	}
	
	public double nx() {
		if (this.getDist() != 0){
			return fix(x / this.getDist(), 2);
		}
		else{
			return 0.001;
		}
	}
	
	public double ny() {
		if (this.getDist() != 0){
			return fix(y / this.getDist(), 2);
		}
		else{
			return 0.001;
		}
	}
	
	public Vector2D ln() {
		return new Vector2D(y, -x);
	}
	
	public Vector2D rn() {
		return new Vector2D(-y, x);
	}
	
	public double rx() {
		return -y;
	}
	
	public double ry() {
		return x;
	}
	
	public double lx() {
		return y;
	}
	
	public double ly() {
		return -x;
	}
	
	public void setAngle(double value) {
		double m = getDist();
		x = fix(Math.cos(value) * m, 2);
		y = fix(Math.sin(value) * m, 2);
	}
	
	public double getAngle() {
		return fix(Math.atan2(y, x), 2);
	}
	
	public double getDist() {
		return fix(Math.sqrt(getDistSq()), 2);
	}
	
	public double getDistSq() {
		return fix(x * x + y * y, 2);
	}
	
	public void setDist(double value) {
		double a = getAngle();
		x = fix(Math.cos(a) * value, 2);
		y = fix(Math.sin(a) * value, 2);
	}
	
	public Vector2D setHeading(double angle, double dist) {
		x = fix(Math.cos(angle) * dist, 2);
		y = fix(Math.sin(angle) * dist, 2);
		return this;
	}
}
