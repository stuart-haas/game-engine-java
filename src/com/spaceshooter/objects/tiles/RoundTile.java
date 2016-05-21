package com.spaceshooter.objects.tiles;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import com.spaceshooter.sprite.Animation;
import com.spaceshooter.utils.Assets;
import com.spaceshooter.utils.ID;

public class RoundTile extends Tile{
	
	Animation tile;

	public RoundTile(int x, int y, int width, int height, ID id1, ID id2){

		super(x, y, width, height, id1, id2);
		
		texture.loadImage(Assets.BOUNCE_TILE, 32, 32);
		tile = new Animation(1, true, texture.imageArray);
	}
	public void render(Graphics g) {
		tile.drawAnimation(g, (int) position.getX(), (int) position.getY());
		tile.goToFrame(0);
	}
	public void drawBounds(Graphics g){
		
		Graphics2D g2d = (Graphics2D) g;
		g2d.setPaint(Color.cyan);
		
		g2d.draw(getEllipseBounds());
	}
}
