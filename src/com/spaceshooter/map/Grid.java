package com.spaceshooter.map;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.spaceshooter.core.EntityManager;
import com.spaceshooter.core.Game;
import com.spaceshooter.entity.Entity;
import com.spaceshooter.entity.Node;
import com.spaceshooter.math.Vector;
import com.spaceshooter.sprite.Texture;

public class Grid {
	
	public static int rows = 40;
	public static int columns = 40;
	public static int nodeSize = 32;
	
	static Grid instance;
	
	public static Grid getInstance() {
		if(instance == null) {
			instance = new Grid();
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
	int[][] grid;
	Map<Layer, Entity[][]> layers;
	
	public Grid(){
		Game.GRID_X = 0;
		Game.GRID_Y = 0;
		Game.IMAGE_WIDTH = rows;
		Game.IMAGE_HEIGHT = columns;
		Game.GRID_WIDTH = Game.IMAGE_WIDTH * nodeSize;
		Game.GRID_HEIGHT = Game.IMAGE_HEIGHT * nodeSize;
		
		entityManager = EntityManager.getInstance();
		texture = Texture.getInstance();
		layers = new HashMap<Layer, Entity[][]>();
	}
	
	public void load(String path) {
		try {
			grid = new int[rows][columns];
			InputStream is = Grid.class.getResourceAsStream(path);
			br = new BufferedReader(new InputStreamReader(is));
			int y = 0; 
			while((line = br.readLine()) != null){
				tokens = line.split(splitBy);
				for(int x = 0; x < tokens.length; x ++){
					grid[y][x] = Integer.parseInt(tokens[x]);
				}
				y ++;
			}	
		} catch(Exception e) {
			System.out.println(e);
		}
	}
	
	public void addNodes(int[][] map, String texturePath, Id id, Layer layer) {
		Entity[][] nodes = new Entity[rows][columns];
		texture.loadImage(texturePath, nodeSize, nodeSize);
		for(int x = 0; x < map.length; x ++){
			for(int y = 0; y < map[x].length; y ++){
				if(map[y][x] != -1) {
					nodes[y][x] = entityManager.addEntity(new Node(texture.getSpriteById(map[y][x]), x * nodeSize, y * nodeSize, nodeSize, nodeSize, id, layer));
					layers.put(layer, nodes);

				}
			}
		}
	}
	
	public ArrayList<Entity> getNeighborsByPoint(Layer layer, Vector source, int distance) {
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
				Entity node = nodeFromIndex(i, j, layer);
				neighbors.add(node);
			}
		}
		return neighbors;
	}
	
	public Entity nodeFromWorldPoint(Vector point, Layer layer) {
		int x = (int) point.getX() / nodeSize;
		int y = (int) point.getY() / nodeSize;
		return nodeFromIndex(x, y, layer);
	}
	
	public Entity nodeFromIndex(int x, int y, Layer layer) {
		if (x < 0 && x > Game.IMAGE_WIDTH && y < 0 && y > Game.IMAGE_HEIGHT) return null;
		return layers.get(layer)[y][x];
	}
	
	public int[][] getGrid() {
		return grid;
	}
	
	public Map<Layer, Entity[][]> getLayers() {
		return layers;
	}
	
	public Entity[][] getNodes(Layer layer) {
		return layers.get(layer);
	}
}
