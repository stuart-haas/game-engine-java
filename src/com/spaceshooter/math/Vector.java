package com.spaceshooter.math;

import java.awt.Rectangle;

public class Vector{

	private double x, y = 0;
	
	public Vector(double x, double y) {
		set(x, y);
	}
	
	public Vector() {
		set(0, 0);
	}
	
	public void set(double x, double y) {
		setX(x);
		setY(y);
	}
	
	public void setX(double x) {
		this.x = Mathf.fix(x, 2);
	}
	
	public double getX() {
		return Mathf.fix(x, 2);
	}
	
	public void setY(double y) {
		this.y = Mathf.fix(y, 2);
	}
	
	public double getY() {
		return Mathf.fix(y, 2);
	}	
	
	public Vector setDirection(double value) {
		double a = getAngle();
		x = Mathf.fix(Math.cos(a) * value, 2);
		y = Mathf.fix(Math.sin(a) * value, 2);
		return this;
	}
	
	public Vector setDirection(double angle, double dist) {
		x = Mathf.fix(Math.cos(angle) * dist, 2);
		y = Mathf.fix(Math.sin(angle) * dist, 2);
		return this;
	}
	
	public Vector setAngle(double value) {
		double m = getDist();
		x = Mathf.fix(Math.cos(value) * m, 2);
		y = Mathf.fix(Math.sin(value) * m, 2);
		return this;
	}
	
	public Vector add(Vector v1) {
		Vector v0 = new Vector();
		v0.x = x + v1.x;
		v0.y = y + v1.y;
		return v0;
	}
	
	public Vector add(double value) {
		Vector v0 = new Vector();
		v0.x = x + value;
		v0.y = y + value;
		return v0;
	}
	
	public Vector subtract(Vector v1) {
		Vector v0 = new Vector();
		v0.x = x - v1.x;
		v0.y = y - v1.y;
		return v0;
	}
	
	public Vector subtract(double value) {
		Vector v0 = new Vector();
		v0.x = x - value;
		v0.y = y - value;
		return v0;
	}
	
	public Vector multiply(Vector v1) {
		Vector v0 = new Vector();
		v0.x = x * v1.x;
		v0.y = y * v1.y;
		return v0;
	}
	
	public Vector multiply(double value) {
		Vector v0 = new Vector();
		v0.x = x * value;
		v0.y = y * value;
		return v0;
	}
	
	public Vector divide(Vector v1) {
		Vector v0 = new Vector();
		v0.x = x / v1.x;
		v0.y = y / v1.y;
		return v0;
	}
	
	public Vector divide(double value) {
		Vector v0 = new Vector();
		v0.x = x / value;
		v0.y = y / value;
		return v0;
	}
	
	public double dist(Vector v1) {
		return Mathf.fix(Math.sqrt(distSq(v1)), 2);
	}
	
	public double distSq(Vector v1) {
		double dx = v1.x - x;
		double dy = v1.y - y;
		return Mathf.fix(dx * dx + dy * dy, 2);
	}
	
	public double angleBetween(Vector v1) {
		return Mathf.fix(Math.acos(dotProduct(v1) / (getDist() * v1.getDist())), 2);
	}
	
	public double nx() {
		if (this.getDist() != 0){
			return Mathf.fix(x / this.getDist(), 2);
		}
		else{
			return 0.001;
		}
	}
	
	public double ny() {
		if (this.getDist() != 0){
			return Mathf.fix(y / this.getDist(), 2);
		}
		else{
			return 0.001;
		}
	}
	
	public Vector ln() {
		return new Vector(y, -x);
	}
	
	public Vector rn() {
		return new Vector(-y, x);
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
	
	public int sign(Vector v1) {
		return rn().dotProduct(v1) < 0 ? -1 : 1;
	}
	
	public double dotProduct(Vector v1) {
		return Mathf.fix(x * v1.nx() + y * v1.ny(), 2);
	}
	
	public double dotProduct2(Vector v1) {
		return Mathf.fix(x * v1.x + y * v1.y, 2);
	}
	
	public double crossProduct(Vector v1) {
		return Mathf.fix((x * v1.x) - (y * v1.y), 2);
	}
	
	public double perpProduct(Vector v1) {
		double perpProduct = Mathf.fix(1 / x * v1.y - y * v1.x, 2);

		if (perpProduct != 0) {
			return perpProduct;
		}
		else{
			return 1;
		}
	}	
	
	public Vector project(Vector v1) {
		double dp = dotProduct(v1);

		double px = Mathf.fix(dp * v1.nx(), 2);
		double py = Mathf.fix(dp * v1.ny(), 2);

		return new Vector(px, py);
	}
	
	public double getAngle() {
		return Mathf.fix(Math.atan2(y, x), 2);
	}
	
	public double getDist() {
		return Mathf.fix(Math.sqrt(getDistSq()), 2);
	}
	
	public double getDistSq() {
		return Mathf.fix(x * x + y * y, 2);
	}
	
	public Vector clamp(Rectangle bounds) {
		x = Mathf.fix(Math.min(Math.max(bounds.getMinX(), x), bounds.getMaxX()), 2);
		y = Mathf.fix(Math.min(Math.max(bounds.getMinY(), y), bounds.getMaxY()), 2);
		return this;
	}
	
	public Vector truncate(double max) {
		setDirection(Math.min(max, getDist()));
		return this;
	}
	
	public Vector normalize() {
		if (getDist() != 0) {
			Mathf.fix(x /= getDist(), 2);
			Mathf.fix(y /= getDist(), 2);
		}
		return this;
	}
	
	public Vector reverse() {
		x -= x;
		y -= y;
		return this;
	}
	
	public Vector zero() {
		x = 0;
		y = 0;
		return this;
	}
	
	public Vector clone() {
		return new Vector(x, y);
	}
	
	public boolean isEqualTo(Vector v1){
		return this.x == v1.x  && this.y == v1.y ? true : false;
	}
	
	public boolean isNormalTo(Vector v1){
		return this.nx() == v1.nx() && this.ny() == v1.ny() ? true : false;
	}
	
	public String toString() {
		return "[Vector2D (x:" + x + ", y:" + y + ")]";
	}
}
