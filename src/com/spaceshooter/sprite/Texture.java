package com.spaceshooter.sprite;

import java.awt.image.BufferedImage;

public class Texture {
	
	public static Texture getInstance() {
		return new Texture();
	}

	BufferedImage image;
	int width;
	int height;
	int columns;
	int rows;
	int length;
	
	public SpriteSheet spriteSheet;
	public BufferedImage[][] imageArray;

	public Texture() {}
	
	public void loadImage(String path, int width, int height, int columns, int rows) {
		
		this.width = width;
		this.height = height;
		this.columns = columns;
		this.rows = rows;
		
		ImageLoader loader = new ImageLoader();
		image = loader.loadImage(path);

		imageArray = new BufferedImage[columns][rows];	
		spriteSheet = new SpriteSheet(image);

		getTextures(width, height);
	}
	
	public void loadImage(String path, int width, int height) {
		
		this.width = width;
		this.height = height;
		
		ImageLoader loader = new ImageLoader();
		image = loader.loadImage(path);

		columns = image.getWidth() / width;
		rows = image.getHeight() / height;

		imageArray = new BufferedImage[columns][rows];
		spriteSheet = new SpriteSheet(image);

		getTextures(width, height);
	}
	
	private void getTextures(int width, int height) {
		
		for(int x = 0; x < imageArray.length; x ++) {
			for(int y = 0; y < imageArray[x].length; y ++) {
				imageArray[x][y] = spriteSheet.getTile(x + 1, y + 1, width, height);
			}
		}
	}
	
	public BufferedImage getTileById(int id) {
		return imageArray[id % columns][(int) Math.floor(id / columns)];
	}
}
