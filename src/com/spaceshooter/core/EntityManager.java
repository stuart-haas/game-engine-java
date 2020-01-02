package com.spaceshooter.core;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

import com.spaceshooter.entity.Entity;
import com.spaceshooter.fx.Emitter2D;
import com.spaceshooter.math.LineOfSight;
import com.spaceshooter.math.Vector2D;
import com.spaceshooter.utils.Colors;
import com.spaceshooter.utils.ID;

public class EntityManager{
	
	static EntityManager instance;
	
	public static EntityManager getInstance() {
		if(instance == null) {
			instance = new EntityManager();
			return instance;
		}
		return instance;
		
	}

	public List<Entity> entities = new ArrayList<Entity>();

	public EntityManager() {}
	
	public void addEntity(List<Entity> list, Entity object) {
		list.add(object);
	}
	
	public List<Entity> getNearbyEntities(ID id, Vector2D position, double radius) {
		List<Entity> list = new ArrayList<Entity>();

		for (Entity entity : entities){
			if (entity.getId1() == id || entity.getId2() == id){
				if (position.distSq(entity.position) < radius * radius){
					list.add(entity);
				}
				else{
					list.remove(entity);
				}
			}
		}
		return list;
	}
	
	public Entity getEntityById(ID id) {

		for (Entity entity : entities){
			if (entity.getId1() == id) return entity;
		}
		return null;
	}
	
	public void removeEntity(Entity entity) {
		entities.remove(entity);
	}
	
	public void render(Graphics g) {
		for (Entity entity: entities) {
			if(Camera.inViewPort(entity.position)) {
				entity.render(g);
			}
		}
	}
	
	public void update() {
		for (Entity entity: entities) {
			if(Camera.inViewPort(entity.position)) {
				entity.update();
			}
		}
	}
}
