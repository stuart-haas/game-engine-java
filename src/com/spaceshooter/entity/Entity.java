package com.spaceshooter.entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.spaceshooter.behaviors.ABehavior;
import com.spaceshooter.map.Id;
import com.spaceshooter.map.Layer;
import com.spaceshooter.math.Vector;
import com.spaceshooter.sprite.Texture;

public abstract class Entity{
	
	public Vector position = new Vector();
	public Vector velocity = new Vector();
	public Vector steeringForce = new Vector();
	public double maxSpeed = 5;
	public double maxForce = 1;
	public double mass = 1;
	public double friction = .98;
	public boolean expired = false;
	public boolean debug = false;
	
	protected int width;
	protected int height;
	protected Id id;
	protected Layer layer;
	protected Texture texture;
	
	double rotation;
	double angle;

	public Entity(int x, int y, int width, int height, Id id, Layer layer){
		position.set(x, y);
		this.width = width;
		this.height = height;
		this.id = id;
		this.layer = layer;
	}
	
	public void addBehavior(ABehavior behavior) {}
	public void removeBehavior(Id id) {}
	public void update() {}
	public void render(Graphics2D g) {}

	public Rectangle getBounds() {
		return new Rectangle((int) position.getX(), (int) position.getY(), width, height);
	}
	
	public void debug(Graphics2D g) {
		g.setColor(Color.cyan);
		g.drawRect(getBounds().x, getBounds().y, getBounds().width, getBounds().height);
	}
	
	public void setId(Id id) {
		this.id = id;
	}

	public Id getId() {
		return id;
	}
	
	public void setLayer(Layer layer) {
		this.layer = layer;
	}
	
	public Layer getLayer() {
		return layer;
	}

	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public double getRotation(){
		return rotation;
	}
	
	public double getAngle(){
		return angle;
	}
}
