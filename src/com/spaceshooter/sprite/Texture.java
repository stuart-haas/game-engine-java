package com.spaceshooter.sprite;

import java.awt.image.BufferedImage;

public class Texture {

	SpriteSheet spriteSheet;
	BufferedImage[][] spriteArray;
	BufferedImage image;
	int columns;
	int rows;

	public Texture(String path, int width, int height) {
	    load(path, width, height);
    }
	
	private void load(String path, int width, int height) {
		
		ImageLoader loader = ImageLoader.getInstance();
		image = loader.loadImage(path);

		columns = image.getWidth() / width;
		rows = image.getHeight() / height;

		spriteArray = new BufferedImage[columns][rows];
		spriteSheet = new SpriteSheet(image);

		buildSpriteArray(width, height);
	}
	
	private void buildSpriteArray(int width, int height) {
		
		for(int x = 0; x < spriteArray.length; x ++) {
			for(int y = 0; y < spriteArray[x].length; y ++) {
				spriteArray[x][y] = spriteSheet.getSprite(x + 1, y + 1, width, height);
			}
		}
	}
	
	public BufferedImage getSpriteById(int id) {
		return spriteArray[id % columns][(int) Math.floor((float) id / columns)];
	}

    public SpriteSheet getSpriteSheet() {
        return spriteSheet;
    }

    public BufferedImage[][] getSpriteArray() {
        return spriteArray;
    }
}
