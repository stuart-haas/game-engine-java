package com.spaceshooter.map;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

	public List<Node> path;
	
	BufferedReader br = null;
	String line =  "";
	String splitBy = ",";
	String[] tokens = null;
	EntityManager entityManager;
	Texture texture;
	int[][] grid;
	Map<Layer, Node[][]> layers;
	
	private Grid(){
		Game.GRID_X = 0;
		Game.GRID_Y = 0;
		Game.IMAGE_WIDTH = rows;
		Game.IMAGE_HEIGHT = columns;
		Game.GRID_WIDTH = Game.IMAGE_WIDTH * nodeSize;
		Game.GRID_HEIGHT = Game.IMAGE_HEIGHT * nodeSize;
		
		entityManager = EntityManager.getInstance();
		layers = new HashMap<>();
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
		Node[][] nodes = new Node[rows][columns];
		texture = new Texture(texturePath, nodeSize, nodeSize);
		for(int x = 0; x < map.length; x ++){
			for(int y = 0; y < map[x].length; y ++){
				if(map[y][x] != -1) {
					nodes[y][x] = (Node) entityManager.addEntity(new Node(texture.getSpriteById(map[y][x]), x, y, x * nodeSize, y * nodeSize, nodeSize, nodeSize, id, layer));
					layers.put(layer, nodes);

				}
			}
		}
	}

	public ArrayList<Node> getNeighborsByNode(Layer layer, Node node) {
		ArrayList<Node> neighbors = new ArrayList<Node>();
        for(int x = -1; x <= 1; x ++) {
            for(int y = -1; y <= 1; y ++) {
                if(x == 0 && y == 0) {
                    continue;
                }

                int cx = node.gridX + x;
                int cy = node.gridY + y;

                neighbors.add(nodeFromIndex(cx, cy, layer));
            }
        }
		return neighbors;
	}
	
	public ArrayList<Node> getNeighborsByPoint(Layer layer, Vector source, int distance) {
		ArrayList<Node> neighbors = new ArrayList<Node>();
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
                Node node = nodeFromIndex(i, j, layer);
				neighbors.add(node);
			}
		}
		return neighbors;
	}
	
	public Node nodeFromWorldPoint(Vector point, Layer layer) {
		int x = (int) point.getX() / nodeSize;
		int y = (int) point.getY() / nodeSize;
		return nodeFromIndex(x, y, layer);
	}
	
	public Node nodeFromIndex(int x, int y, Layer layer) {
		if (x < 0 && x > Game.IMAGE_WIDTH && y < 0 && y > Game.IMAGE_HEIGHT) return null;
		return layers.get(layer)[y][x];
	}
	
	public int[][] getGrid() {
		return grid;
	}
	
	public Map<Layer, Node[][]> getLayers() {
		return layers;
	}
	
	public Entity[][] getNodes(Layer layer) {
		return layers.get(layer);
	}
}
