package com.spaceshooter.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;

import com.spaceshooter.core.EntityManager;
import com.spaceshooter.math.Vector2;
import com.spaceshooter.sprite.Animation;
import com.spaceshooter.utils.Assets;
import com.spaceshooter.utils.ID;

public class Blackhole extends Entity{

	EntityManager handler = EntityManager.getInstance();
	Animation tile;

	public Blackhole(int x, int y, int width, int height, ID id){
		super(x, y, width, height, id);
		texture.loadImage(Assets.BLACKHOLE, 32, 32);
		tile = new Animation(1, true, texture.imageArray);
	}
	
	public void update() {

		List<Entity> missiles = handler.getNearbyEntities(ID.Missile, this.position, 250);

		for (Entity tempObject : missiles){
			Vector2 force = tempObject.position.subtract(this.position);
			tempObject.velocity = tempObject.velocity.subtract(force.normalize().multiply(.3));
		}
	}
	
	public void render(Graphics g) {
		tile.drawAnimation(g, (int) position.getX(), (int) position.getY());
		tile.goToFrame(0);
	}
	
	public void drawBounds(Graphics g) {

		Graphics2D g2d = (Graphics2D) g;
		g2d.setPaint(Color.cyan);

		g2d.draw(getEllipseBounds());
	}
}
