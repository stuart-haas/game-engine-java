package com.spaceshooter.objects.tiles;

import java.awt.Graphics;

import com.spaceshooter.sprite.Animation;
import com.spaceshooter.utils.Assets;
import com.spaceshooter.utils.ID;

public class WallTile extends Tile{
	
	Animation tile;

	public WallTile(int x, int y, int width, int height, ID id1, ID id2){

		super(x, y, width, height, id1, id2);
		
		texture.loadImage(Assets.COLLISION_TILE, width, height);
		tile = new Animation(1, true, texture.imageArray);
	}
	public void render(Graphics g) {
		tile.drawAnimation(g, (int) position.getX(), (int) position.getY());
		tile.goToFrame(0);
	}
}
