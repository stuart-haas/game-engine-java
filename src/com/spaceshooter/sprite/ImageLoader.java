package com.spaceshooter.sprite;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageLoader {

    static ImageLoader instance;

    public static ImageLoader getInstance() {
        if(instance == null) {
            instance = new ImageLoader();
            return instance;
        }
        return instance;
    }
	
	BufferedImage image;

	private ImageLoader() {}

	public BufferedImage loadImage(String path) {
		try {
			image = ImageIO.read(getClass().getResource(path));
		} catch(IOException e) {
			e.printStackTrace();
		}
		return image;
	}

}
