package com.spaceshooter.objects.tiles;

import java.awt.Graphics;

import com.spaceshooter.entity.Entity;
import com.spaceshooter.sprite.Animation;
import com.spaceshooter.utils.Assets;
import com.spaceshooter.utils.ID;

public class Tile extends Entity{
	
	Animation tile;

	public Tile(int x, int y, int width, int height, ID id){

		super(x, y, width, height, id);
		
		texture.loadImage(Assets.METAL_TILE, width, height);
		tile = new Animation(1, true, texture.imageArray);
	}
	public void render(Graphics g) {
		tile.drawAnimation(g, (int) position.getX(), (int) position.getY());
		tile.goToFrame(0);
	}
}
