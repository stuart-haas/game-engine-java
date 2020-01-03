package com.spaceshooter.core;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.spaceshooter.entity.Entity;
import com.spaceshooter.entity.Node;
import com.spaceshooter.math.Vector;
import com.spaceshooter.sprite.Texture;
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
	Texture texture;
	int[][] map;
	Entity[][] nodes;
	int nodeSize = 32;
	
	public Map(){
		entityManager = EntityManager.getInstance();
		texture = Texture.getInstance();
		texture.loadImage("/sprite_sheets/tallgrass.png", nodeSize, nodeSize, 3, 6);
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
				if(map[y][x] != -1)
					nodes[y][x] = entityManager.addEntity(new Node(x * nodeSize, y * nodeSize, nodeSize, nodeSize, ID.Tile, texture.getTileById(map[y][x])));
			}
		}
	}
	
	public ArrayList<Entity> getNeighborsByPoint(Vector source, int distance) {
		ArrayList<Entity> neighbors = new ArrayList<Entity>();
		int left = (int) source.getX() / nodeSize - distance;
		int right = (int) (source.getX() + nodeSize) / nodeSize + distance;
		int top = (int) source.getY() / nodeSize - distance;
		int bottom = (int) (source.getY() + nodeSize) / nodeSize + distance;
		
	    if(left < 0) left = 0;
	    if(right > Game.IMAGE_WIDTH) right = Game.IMAGE_WIDTH;
	    if(top < 0) top = 0;
	    if(bottom > Game.IMAGE_HEIGHT) bottom = Game.IMAGE_HEIGHT;
		
		for(int i = left; i <= right; i ++) {
			for(int j = top; j <= bottom; j ++) {
				Entity node = nodeFromIndex(i, j);
				neighbors.add(node);
			}
		}
		return neighbors;
	}
	
	public Entity nodeFromWorldPoint(Vector point) {
		int x = (int) point.getX() / nodeSize;
		int y = (int) point.getY() / nodeSize;
		return nodeFromIndex(x, y);
	}
	
	public Entity nodeFromIndex(int x, int y) {
		if (x < 0 && x > Game.IMAGE_WIDTH && y < 0 && y > Game.IMAGE_HEIGHT) return null;
		return nodes[y][x];
	}
	
	public int[][] getMap() {
		return map;
	}
}
