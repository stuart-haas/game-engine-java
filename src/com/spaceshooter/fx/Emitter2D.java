package com.spaceshooter.fx;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import com.spaceshooter.entity.Entity;
import com.spaceshooter.map.Id;

public class Emitter2D{

	List<Entity> particles = new ArrayList<Entity>();
	
	private Id id;

	public Emitter2D(Id id){
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
	public void update() {

		ListIterator<Entity> particleIterator = particles.listIterator();
		while (particleIterator.hasNext()){
			Entity p = particleIterator.next();
			p.update();
			if (p.expired){
				particleIterator.remove();
			}
		}
	}
	public void render(Graphics2D g) {

		for (Entity p : particles){
			p.render(g);
		}
	}
	public Id getId() {
		return id;
	}
	public void setId(Id id) {
		this.id = id;
	}
}
