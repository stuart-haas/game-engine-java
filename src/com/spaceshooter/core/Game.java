package com.spaceshooter.core;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

import com.spaceshooter.gui.Button;
import com.spaceshooter.input.KeyInput;
import com.spaceshooter.input.MouseInput;
import com.spaceshooter.utils.Assets;
import com.spaceshooter.utils.ID;
import com.spaceshooter.utils.Profiler;

public class Game extends Canvas implements Runnable{

	public static int CANVAS_WIDTH, CANVAS_HEIGHT;
	public static int IMAGE_WIDTH, IMAGE_HEIGHT;
	public static int MAP_WIDTH, MAP_HEIGHT;
	public static int MAP_X, MAP_Y;
	public static boolean DISPLAY_COLLISION_OBJECT_BOUNDS = false;
	public static boolean DISPLAY_OBJECT_BOUNDS = false;
	public static boolean DISPLAY_STATS = false;
	public static int FPS = 0;
	public static String GAME_TITLE = "Space Shooter!";
	public static int WINDOW_WIDTH = 800,
			WINDOW_HEIGHT = WINDOW_WIDTH / 12 * 9;
	private static final long serialVersionUID = 4378882451804347022L;

	public STATE state = STATE.Running;

	public enum STATE {
		Paused(), Running();
	}

	public static Handler getHandler() {

		return handler;
	}
	public static Map getMap() {

		return map;
	}
	public static double clamp(double var, double min, double max) {

		if (var >= max) return var = max;
		else if (var <= min) return var = min;
		else return var;
	}
	public static void main(String[] args) {
		new Window(WINDOW_WIDTH, WINDOW_HEIGHT, GAME_TITLE, new Game());
	}

	Button button1;
	Button button2;
	Button button3;
	Camera camera;
	static Handler handler;
	static Map map;
	Graphics g;
	MiniMap mapView;
	Profiler profile;
	boolean running = false;
	Thread thread;
	BufferStrategy bs;

	public Game(){

	}
	public void run() {

		init();
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while (running){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1){
				tick();
				delta--;
			}
			if (running) render();

			frames++;

			if (System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				FPS = frames;
				frames = 0;
			}
		}
		stop();
	}
	public synchronized void start() {

		if (running) return;

		thread = new Thread(this);
		thread.start();
		running = true;
	}
	public synchronized void stop() {

		try{
			thread.join();
			running = false;
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	private void init() {

		CANVAS_WIDTH = getWidth();
		CANVAS_HEIGHT = getHeight();

		handler = new Handler();
		camera = new Camera(0, 0, 0.05f);

		map = new Map(handler);
		map.loadMapFromFile("/levels/TileMap.csv", 40, 40);
		//map.loadMapFromImage(Assets.LEVEL_THREE);

		mapView = new MiniMap(0, 0, 3, 3, map, handler);
		profile = new Profiler(650, 0, ID.Player, handler);

		this.addKeyListener(new KeyInput(handler, this, map));
		this.addMouseListener(new MouseInput(handler, camera));
	}
	private void render() {

		bs = this.getBufferStrategy();
		if (bs == null){
			this.createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		Graphics2D g2d = (Graphics2D) g;

		g.setColor(Color.black);
		g.fillRect(0, 0, CANVAS_WIDTH, CANVAS_HEIGHT);

		g2d.translate(-camera.position.getX(), -camera.position.getY());

		handler.render(g);

		g2d.translate(camera.position.getX(), camera.position.getY());

		mapView.render(g);
	    profile.render(g);

		g.dispose();
		bs.show();
	}
	private void tick() {

		handler.tick();
		camera.tick(handler.getObjectById(handler.objects, ID.Player));
	}
}
