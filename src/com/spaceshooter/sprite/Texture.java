package com.spaceshooter.sprite;

import java.awt.image.BufferedImage;

public class Texture {
	
	public static Texture getInstance() {
		return new Texture();
	}

	SpriteSheet spriteSheet;
	BufferedImage[][] imageArray;
	BufferedImage image;
	int columns;
	int rows;

	public Texture() {}
	
	public void loadImage(String path, int width, int height) {
		
		ImageLoader loader = ImageLoader.getInstance();
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
				imageArray[x][y] = spriteSheet.getSprite(x + 1, y + 1, width, height);
			}
		}
	}
	
	public BufferedImage getSpriteById(int id) {
		return imageArray[id % columns][(int) Math.floor((float) id / columns)];
	}

    public SpriteSheet getSpriteSheet() {
        return spriteSheet;
    }

    public BufferedImage[][] getImageArray() {
        return imageArray;
    }
}
