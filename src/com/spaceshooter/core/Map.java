package com.spaceshooter.core;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.spaceshooter.entity.Entity;
import com.spaceshooter.math.Vector2;
import com.spaceshooter.utils.ID;

public class Map {
	
	static Map instance;
	
	public static Map getInstance() {
		if(instance == null) {
			instance = new Map();
			return instance;
		}
		return instance;
	}
	
	BufferedReader br = null;
	String line =  "";
	String splitBy = ",";
	String[] tokens = null;
	EntityManager entityManager;
	int[][] map;
	Entity[][] nodes;
	int nodeSize = 32;
	
	public Map(){
		this.entityManager = EntityManager.getInstance();
	}
	
	public void loadMap(String path, int rows, int columns) {
		
		map = new int[rows][columns];
		nodes = new Entity[rows][columns];
		
		try {
			InputStream is = Map.class.getResourceAsStream(path);
			br = new BufferedReader(new InputStreamReader(is));
			int y = 0; 
			while((line = br.readLine()) != null){
				tokens = line.split(splitBy);
				for(int x = 0; x < tokens.length; x ++){
					map[y][x] = Integer.parseInt(tokens[x]);
				}
				y ++;
			}	
		} catch(Exception e) {
			System.out.println(e);
		}
		
		createMap(rows, columns);
	}
	
	private void createMap(int rows, int columns) {
		Game.MAP_X = 0;
		Game.MAP_Y = 0;
		Game.IMAGE_WIDTH = rows;
		Game.IMAGE_HEIGHT = columns;
		Game.MAP_WIDTH = Game.IMAGE_WIDTH * nodeSize;
		Game.MAP_HEIGHT = Game.IMAGE_HEIGHT * nodeSize;
		
		for(int x = 0; x < map.length; x ++){
			for(int y = 0; y < map[x].length; y ++){
				if(map[y][x] == 0)
					nodes[y][x] = entityManager.addEntity(EntityFactory.getPlayer(x * nodeSize, y * nodeSize, nodeSize, nodeSize, ID.Player));
				if(map[y][x] == 1)
					nodes[y][x] = entityManager.addEntity(EntityFactory.getWallTile(x * nodeSize, y * nodeSize, nodeSize, nodeSize, ID.WallTile));
				if(map[y][x] == 3)
					nodes[y][x] = entityManager.addEntity(EntityFactory.getPickup(x * nodeSize, y * nodeSize, nodeSize, nodeSize, ID.Coin));
				if(map[y][x] == 5)
					nodes[y][x] = entityManager.addEntity(EntityFactory.getSeeker(x * nodeSize, y * nodeSize, nodeSize, nodeSize, ID.Seeker));
			}
		}
	}
	
	public ArrayList<Entity> getNeighborsByPoint(Vector2 source, int distance) {
		ArrayList<Entity> neighbors = new ArrayList<Entity>();
		int left = (int) source.getX() / nodeSize - distance;
		int right = (int) (source.getX() + nodeSize) / nodeSize + distance;
		int top = (int) source.getY() / nodeSize - distance;
		int bottom = (int) (source.getY() + nodeSize) / nodeSize + distance;
		
		for(int i = left; i <= right; i ++) {
			for(int j = top; j <= bottom; j ++) {
				Entity node = nodefromIndex(i, j);
				neighbors.add(node);
			}
		}
		return neighbors;
	}
	
	public Entity nodefromIndex(int x, int y) {
		return nodes[y][x];
	}
	
	public int[][] getMap() {
		return map;
	}
}
