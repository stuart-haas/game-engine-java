package com.spaceshooter.core;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import com.spaceshooter.entity.Entity;
import com.spaceshooter.math.Vector2D;
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
			if (entity.getId() == id || entity.getId() == id){
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
		
		for (Entity entity : entities) {
			if (entity.getId() == id) return entity;
		}
		return null;
	}
	
	public void removeEntity(Entity entity) {
		entities.remove(entity);
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
