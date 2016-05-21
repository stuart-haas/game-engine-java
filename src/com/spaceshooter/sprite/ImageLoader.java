package com.spaceshooter.sprite;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageLoader{
	
	BufferedImage image;

	public ImageLoader(){
		
	}
	public BufferedImage loadImage(String path){
		try{
			image = ImageIO.read(getClass().getResource(path));
		}
		catch(IOException e){
			e.printStackTrace();
		}
		return image;
	}

}
