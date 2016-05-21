package com.spaceshooter.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;

import com.spaceshooter.core.Game;
import com.spaceshooter.core.Handler;
import com.spaceshooter.math.Vector2D;
import com.spaceshooter.sprite.Animation;
import com.spaceshooter.utils.Assets;
import com.spaceshooter.utils.ID;

public class Blackhole extends GameObject{

	Handler handler = Game.getHandler();
	Animation tile;

	public Blackhole(int x, int y, int width, int height, ID id1, ID id2){

		super(x, y, width, height, id1, id2);

		texture.loadImage(Assets.BLACKHOLE, 32, 32);
		tile = new Animation(1, true, texture.imageArray);
	}
	public void tick() {

		List<GameObject> missiles = handler.getNearByObjects(handler.missiles, ID.Missile, this.position, 250);

		for (GameObject tempObject : missiles){
			Vector2D force = tempObject.position.subtract(this.position);
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
