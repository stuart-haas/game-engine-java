package com.spaceshooter.sprite;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Animation{

	int speed;
	int frames;

	int index = 0;
	int count = 0;

	boolean loop;

	BufferedImage[][] images;
	BufferedImage currentImage;

	public Animation(int speed, boolean loop, BufferedImage[][] args){
		this.speed = speed;
		this.loop = loop;
		images = new BufferedImage[args.length][0];
		for (int i = 0; i < args.length; i++){
			images[i] = args[i];
		}
		frames = args.length;
		
		currentImage = images[0][0];
	}
	public void runAnimation() {
		index++;
		if (index > speed){
			index = 0;
			nextFrame();
		}
	}
	public void drawAnimation(Graphics g, int x, int y) {
		g.drawImage(currentImage, x, y, null);
	}
	public void drawAnimation(Graphics g, int x, int y, int scaleX, int scaleY) {
		g.drawImage(currentImage, x, y, scaleX, scaleY, null);
	}
	public void rotateAnimation(Graphics g, double rotation, int x, int y, int width,
			int height) {
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.rotate(Math.toRadians((int)rotation), x + currentImage.getWidth() / 2, y + currentImage.getHeight() / 2);
		g2d.drawImage(currentImage, x, y, null);
		g2d.dispose();
	}
	public void goToFrame(int index) {
		currentImage = images[index][0];
	}
	private void nextFrame() {

		for (int i = 0; i < frames; i++){
			if (count == i){
				currentImage = images[i][0];
			}
		}
		count++;

		if (count > frames && loop == true) count = 0;
	}
}
