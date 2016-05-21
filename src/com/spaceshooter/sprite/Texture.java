package com.spaceshooter.sprite;

import java.awt.image.BufferedImage;

public class Texture{

	SpriteSheet spriteSheet;
	BufferedImage image = null;
	int width;
	int height;
	int columns;
	int rows;
	int length;

	public BufferedImage[] imageArray;

	public Texture(){

	}
	public void loadImage(String path, int width, int height, int columns, int rows){
		
		this.width = width;
		this.height = height;
		this.columns = columns;
		this.rows = rows;
		
		ImageLoader loader = new ImageLoader();
		image = loader.loadImage(path);
		
		length = columns + rows;

		imageArray = new BufferedImage[length - 1];	             
		spriteSheet = new SpriteSheet(image);

		getTextures();
	}
	public void loadImage(String path, int width, int height){
		
		this.width = width;
		this.height = height;
		
		ImageLoader loader = new ImageLoader();
		image = loader.loadImage(path);

		columns = image.getWidth() / width;
		rows = image.getHeight() / height;
		length = columns + rows;

		imageArray = new BufferedImage[length - 1];	             
		spriteSheet = new SpriteSheet(image);

		getTextures();
	}
	private void getTextures() {

		for (int x = 0; x < columns; x ++){
			for(int y = 0; y < rows; y ++){
				imageArray[x + y] = spriteSheet.grabImage(x + 1, y + 1, width, height);
			}
		}
	}
	public static Texture getInstance() {
		return new Texture();
	}
}
