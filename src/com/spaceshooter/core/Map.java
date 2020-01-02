package com.spaceshooter.core;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.spaceshooter.math.Vector2D;
import com.spaceshooter.sprite.ImageLoader;
import com.spaceshooter.utils.Colors;
import com.spaceshooter.utils.ID;

public class Map{
	
	static Map instance;
	
	public static Map getInstance() {
		if(instance == null) {
			instance = new Map();
			return instance;
		}
		return instance;
		
	}

	ArrayList<Vector2D> paths;
	BufferedImage level;
	ImageLoader loader;
	EntityManager handler;
	int[][] map;
	
	public Map(){
		this.handler = EntityManager.getInstance();
	}
	
	public void loadMapFromImage(String path) {
		paths = new ArrayList<Vector2D>();
		loader = new ImageLoader();
		level = loader.loadImage(path);
		createMap(level);
	}
	public void loadMapFromFile(String path, int rows, int columns){
		BufferedReader br = null;
		String line =  "";
		String splitBy = ",";
		String[] tokens = null;
		int[][] map = new int[rows][columns];
		int y = 0; 
		try{
			InputStream is = Map.class.getResourceAsStream(path);
			br = new BufferedReader(new InputStreamReader(is));
			while((line = br.readLine()) != null){
				tokens = line.split(splitBy);
				for(int x = 0; x < tokens.length; x ++){
					map[y][x] = Integer.parseInt(tokens[x]);
				}
				y ++;
			}	
		}
		catch(Exception e){
			System.out.println(e);
		}
		createMap(map, rows, columns);
	}
	private void createMap(int[][] map, int rows, int columns){
		Game.MAP_X = 0;
		Game.MAP_Y = 0;
		Game.IMAGE_WIDTH = rows;
		Game.IMAGE_HEIGHT = columns;
		Game.MAP_WIDTH = Game.IMAGE_WIDTH * 32;
		Game.MAP_HEIGHT = Game.IMAGE_HEIGHT * 32;
		
		for(int x = 0; x < map.length; x ++){
			for(int y = 0; y < map[x].length; y ++){
				if(map[y][x] == 0)
					handler.addEntity(handler.entities, ObjectFactory.getPlayer(x * 32, y * 32, 32, 32, ID.Player));
				if(map[y][x] == 1)
					handler.addEntity(handler.entities, ObjectFactory.getWallTile(x * 32, y * 32, 32, 32, ID.WallTile));
				if(map[y][x] == 2)
					handler.addEntity(handler.entities, ObjectFactory.getRoundTile(x * 32, y * 32, 32, 32, ID.BounceTile));
				if(map[y][x] == 3)
					handler.addEntity(handler.entities, ObjectFactory.getPickup(x * 32, y * 32, 32, 32, ID.Coin));
				if(map[y][x] == 4)
					handler.addEntity(handler.entities, ObjectFactory.getTile(x * 32, y * 32, 32, 32, ID.InteriorTile));
				if(map[y][x] == 5)
					handler.addEntity(handler.entities, ObjectFactory.getSeeker(x * 32, y * 32, 32, 32, ID.Seeker));
			}
		}
	}
	
	private void createMap(BufferedImage level) {
		Game.MAP_X = level.getMinX();
		Game.MAP_Y = level.getMinY();
		Game.IMAGE_WIDTH = level.getWidth();
		Game.IMAGE_HEIGHT = level.getHeight();

		Game.MAP_WIDTH = Game.IMAGE_WIDTH * 32;
		Game.MAP_HEIGHT = Game.IMAGE_HEIGHT * 32;

		for (int x = 0; x < Game.IMAGE_WIDTH; x++){
			for (int y = 0; y < Game.IMAGE_HEIGHT; y++){
				int pixel = level.getRGB(x, y);
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel >> 0) & 0xff;
				Color color = new Color(red, green, blue);

				if (color.equals(Colors.Player)){
					handler.addEntity(handler.entities, ObjectFactory.getPlayer(x * 32, y * 32, 32, 32, ID.Player));
				}
				if (red == 0 && green == 255 && blue == 0)
					handler.addEntity(handler.entities, ObjectFactory.getSeeker(x * 32, y * 32, 32, 32, ID.Seeker));
				if (red == 255 && green == 0 && blue == 0)
					handler.addEntity(handler.entities, ObjectFactory.getDrone(x * 32, y * 32, 32, 32, ID.Drone));
				if (red == 0 && green == 255 && blue == 255)
					handler.addEntity(handler.entities, ObjectFactory.getTurret(x * 32, y * 32, 32, 32, ID.Turret));
				if (red == 255 && green == 150 && blue == 255)
					handler.addEntity(handler.entities, ObjectFactory.getBlackHole(x * 32, y * 32, 32, 32, ID.Blackhole));
				if (red == 255 && green == 255 && blue == 255)
					handler.addEntity(handler.entities, ObjectFactory.getTile(x * 32, y * 32, 32, 32, ID.InteriorTile));
				if (red == 150 && green == 255 && blue == 150)
					handler.addEntity(handler.entities, ObjectFactory.getWallTile(x * 32, y * 32, 32, 32, ID.HiddenWallTile));
				if (red == 130 && green == 130 && blue == 130)
					handler.addEntity(handler.entities, ObjectFactory.getWallTile(x * 32, y * 32, 32, 32, ID.WallTile));
				if (red == 150 && green == 0 && blue == 255)
					handler.addEntity(handler.entities, ObjectFactory.getRoundTile(x * 32, y * 32, 32, 32, ID.BounceTile));
				if (red == 255 && green == 255 && blue == 0)
					handler.addEntity(handler.entities, ObjectFactory.getPickup(x * 32, y * 32, 32, 32, ID.Coin));
				if (red == 255 && green == 150 && blue == 0)
					paths.add(new Vector2D(x * 32, y * 32));
				if (red == 255 && green == 100 && blue == 0)
					paths.add(new Vector2D(x * 32, y * 32));
				if (red == 255 && green == 50 && blue == 0)
					paths.add(new Vector2D(x * 32, y * 32));
			}
		}
	}
	public int[][] getMap(){
		return map;
	}
	public BufferedImage getLevel() {
		return level;
	}
	public ArrayList<Vector2D> getPaths(){
		return paths;
	}
}
