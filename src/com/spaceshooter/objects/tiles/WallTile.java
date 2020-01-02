package com.spaceshooter.objects.tiles;

import java.awt.Graphics2D;

import com.spaceshooter.sprite.Animation;
import com.spaceshooter.utils.Assets;
import com.spaceshooter.utils.ID;

public class WallTile extends Tile {
	
	Animation tile;

	public WallTile(int x, int y, int width, int height, ID id) {
		super(x, y, width, height, id);
		texture.loadImage(Assets.COLLISION_TILE, width, height);
		tile = new Animation(1, true, texture.imageArray);
	}
	
	public void render(Graphics2D g) {
		tile.drawAnimation(g, (int) position.getX(), (int) position.getY());
		tile.goToFrame(0);
	}
}
