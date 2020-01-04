package com.spaceshooter.core;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import com.spaceshooter.entity.Entity;
import com.spaceshooter.entity.Node;
import com.spaceshooter.map.Grid;
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

	static Grid grid = Grid.getInstance();

	private EntityManager() {}
	
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
		for (Entity entity: entities) {
			if(Camera.inViewPort(entity.position)) {
				entity.render(g);
				entity.update();
                if(grid.path != null) {
                    if(grid.path.contains(entity)) {
                        entity.debug = true;
                    } else {
                        entity.debug = false;
                    }
                }
			}
		}
	}
}
