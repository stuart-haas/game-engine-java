package com.spaceshooter.core;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.List;

import com.spaceshooter.objects.GameObject;
import com.spaceshooter.utils.ID;

public class MiniMap{

	Map map;
	BufferedImage level;
	Handler handler;
	int x, y;
	int width, height;
	double scaleX, scaleY;
	int colWidth, colHeight;
	int pixel, red, green, blue = 0;

	public MiniMap(int x, int y, int colWidth, int colHeight, Map map,
			Handler handler){
		this.map = map;
		this.level = map.getLevel();
		this.handler = handler;
		this.x = x;
		this.y = y;
		this.colWidth = colWidth;
		this.colHeight = colHeight;

		width = Game.IMAGE_WIDTH * colWidth;
		height = Game.IMAGE_HEIGHT * colHeight;

		scaleX = (double) width / (double) Game.MAP_WIDTH;
		scaleY = (double) height / (double) Game.MAP_HEIGHT;
	}
	public void render(Graphics g) {

		Graphics2D g2d = (Graphics2D) g;

		g2d.setPaint(Color.black);
		g2d.fillRect(x, y, width + 4, height + 4);

		drawTile(g, handler.objects, ID.Player, Color.blue);
		drawTile(g, handler.objects, ID.Enemy, Color.red);
		drawTile(g, handler.pickups, ID.Pickup, Color.yellow);
		drawTile(g, handler.objects, ID.InteriorTile, Color.white);
		drawTile(g, handler.collisionObjects, ID.CollisionTile, Color.gray);
		drawTile(g, handler.missiles, ID.Missile, Color.cyan);
		drawTile(g, handler.objects, ID.Blackhole, Color.pink);
		drawTile(g, handler.objects, ID.Bullet, Color.cyan);
	}
	private void drawTile(Graphics g, List<GameObject> list, ID id,
			Color color) {

		for (int i = 0; i < list.size(); i++){

			GameObject tempObject = list.get(i);
			if (tempObject.getId1() == id || tempObject.getId2() == id){

				double px = (-width + tempObject.position.getX()) * scaleX;
				double py = (-height + tempObject.position.getY()) * scaleY;
				ObjectFactory.getMarker(((int) px) + x + 18, ((int) py) + y
						+ 18, colWidth, colHeight, color).render(g);
			}
		}
	}
}
