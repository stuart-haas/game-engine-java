package com.spaceshooter.fx;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import com.spaceshooter.objects.GameObject;
import com.spaceshooter.utils.ID;

public class Emitter2D{

	List<GameObject> particles = new ArrayList<GameObject>();
	
	private ID id;

	public Emitter2D(ID id){
		this.id = id;
	}
	public void create(int amount, int x, int y, double angle, double speed,
			double friction, double size, double maxSize, double growth,
			double life, double lifeRate, float alpha, Color[] colors) {

		for (int i = 0; i < amount; i++){

			Particle particle = new Particle(x, y, angle, speed, friction, size, maxSize, growth, life, lifeRate, alpha, colors);
			particles.add(particle);
		}
	}
	public void tick() {

		ListIterator<GameObject> particleIterator = particles.listIterator();
		while (particleIterator.hasNext()){
			GameObject p = particleIterator.next();
			p.tick();
			if (p.expired){
				particleIterator.remove();
			}
		}
	}
	public void render(Graphics g) {

		for (GameObject p : particles){
			p.render(g);
		}
	}
	public ID getId() {
		return id;
	}
	public void setId(ID id) {
		this.id = id;
	}
}
