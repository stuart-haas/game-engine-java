package com.spaceshooter.core;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import com.spaceshooter.entity.Entity;
import com.spaceshooter.math.Vector2;
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
	
	public Entity addEntity(Entity entity) {
		entities.add(entity);
		return entity;
	}
	
	public Entity getEntityById(ID id) {
		
		for (Entity entity : entities) {
			if (entity.getId() == id) return entity;
		}
		return null;
	}
	
	public void removeEntity(Entity entity) {
		entities.remove(entity);
	}
	
	public List<Entity> getNearbyEntities(ID id, Vector2 position, double radius) {
		List<Entity> nearby = new ArrayList<Entity>();

		for (Entity entity : entities){
			if (entity.getId() == id || entity.getId() == id){
				if (position.distSq(entity.position) < radius * radius){
					nearby.add(entity);
				}
				else{
					nearby.remove(entity);
				}
			}
		}
		return nearby;
	}
	
	public void render(Graphics2D g) {
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
