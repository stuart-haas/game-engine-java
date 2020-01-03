package com.spaceshooter.core;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import com.spaceshooter.entity.Entity;
import com.spaceshooter.map.Id;

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
	public List<Entity> visibleEntities;

	public EntityManager() {}
	
	public Entity addEntity(Entity entity) {
		entities.add(entity);
		return entity;
	}
	
	public Entity getEntityById(Id id) {
		
		for (Entity entity : entities) {
			if (entity.getId() == id) return entity;
		}
		return null;
	}
	
	public void removeEntity(Entity entity) {
		entities.remove(entity);
	}
	
	public void render(Graphics2D g) {
		visibleEntities  = new ArrayList<Entity>();
		for (Entity entity: entities) {
			if(Camera.inViewPort(entity.position)) {
				visibleEntities.add(entity);
				entity.render(g);
				entity.update();
			} else {
				visibleEntities.remove(entity);
			}
		}
	}
}
