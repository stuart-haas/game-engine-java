package com.spaceshooter.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Random;

import com.spaceshooter.behaviors.ABehavior;
import com.spaceshooter.core.Game;
import com.spaceshooter.core.Handler;
import com.spaceshooter.math.Vector2D;
import com.spaceshooter.sprite.Texture;
import com.spaceshooter.utils.ID;

public abstract class GameObject{

	protected Handler handler = Game.getHandler();
	public double rotation;
	public double angle;
	protected int width;
	protected int height;
	protected ID id1;
	protected ID id2;
	public boolean isMoving = false;

	protected Random r = new Random();
	protected DecimalFormat df = new DecimalFormat("#0.00");
	protected Texture texture = Texture.getInstance();

	public List<GameObject> nearbyTargets;
	public Vector2D position = new Vector2D();
	public Vector2D velocity = new Vector2D();
	public Vector2D steeringForce = new Vector2D();

	public int health = 50;
	public double friction = .95;
	public double maxSpeed = 5;
	public double minSpeed = 1;
	public double maxForce = 1;
	public double mass = 1;
	public double projectileSpeed = 0;
	public int fireRate = 0;
	public int fireRadius = 0;
	public int life = 100;
	public boolean expired = false;
	public double lastRotation;
	public double tempRotation;

	public GameObject(int x, int y, int width, int height, ID id1, ID id2){

		position.update(x, y);
		this.width = width;
		this.height = height;
		this.id1 = id1;
		this.id2 = id2;
	}
	public void addBehavior(ABehavior behavior) {
	}
	public void removeBehavior(ID id) {
	}
	public void tick() {
	}
	public void render(Graphics g) {
	}

	public Rectangle getRectBounds() {

		return new Rectangle((int) position.getX(), (int) position.getY(), width, height);
	}
	public Ellipse2D getEllipseBounds() {

		return new Ellipse2D.Double(position.getX(), position.getY(), width, height);
	}
	public void drawBounds(Graphics g) {

		g.setColor(Color.cyan);
		g.drawRect(getRectBounds().x, getRectBounds().y, getRectBounds().width, getRectBounds().height);
	}
	public void drawStats(Graphics g) {
		g.setColor(Color.cyan);
		g.drawString("[x: " + position.getX() + " , y: "
				+ df.format(position.getY()) + "]", (int) position.getX(), (int) position.getY() - 5);
		g.drawString("[vx: " + df.format(velocity.getX()) + " , vy: "
				+ df.format(velocity.getY()) + "]", (int) position.getX(), (int) position.getY() - 20);
		g.drawString("[ax: " + df.format(steeringForce.getX()) + " , ay: "
				+ df.format(steeringForce.getY()) + "]", (int) position.getX(), (int) position.getY() - 35);
		g.drawString("[angle: "
				+ df.format(Math.toDegrees(velocity.getAngle())) + "]", (int) position.getX(), (int) position.getY() - 50);
	}
	public BufferedImage getImage() {
		BufferedImage image = null;
		for (int i = 0; i < texture.imageArray.length; i++){
			image = texture.imageArray[i];
		}
		return image;
	}

	public ID getId1() {

		return id1;
	}
	public void setId1(ID id) {

		this.id1 = id;
	}
	public ID getId2() {

		return id2;
	}
	public void setId2(ID id) {

		this.id2 = id;
	}
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	public double getRotation(){
		return this.rotation;
	}
	public double getAngle(){
		return this.angle;
	}
}
