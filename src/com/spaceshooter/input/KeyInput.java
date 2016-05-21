package com.spaceshooter.input;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.spaceshooter.core.Game;
import com.spaceshooter.core.Handler;
import com.spaceshooter.core.Map;
import com.spaceshooter.core.ObjectFactory;
import com.spaceshooter.math.Vector2D;
import com.spaceshooter.objects.GameObject;
import com.spaceshooter.utils.ID;

public class KeyInput extends KeyAdapter{

	Handler handler;
	Game game; 
	Map map;
	boolean[] keyDown = new boolean[4];
	GameObject player;

	public KeyInput(Handler handler, Game game, Map map){

		this.handler = handler;
		this.game = game;
		this.map = map;

		keyDown[0] = false;
		keyDown[1] = false;
		keyDown[2] = false;
		keyDown[3] = false;
	}
	public void keyPressed(KeyEvent e) {

		int key = e.getKeyCode();

		this.player = this.handler.getObjectById(handler.objects, ID.Player);

		if (key == KeyEvent.VK_W){
			player.steeringForce.setY(-player.maxForce);
			keyDown[0] = true;
		}
		if (key == KeyEvent.VK_S){
			player.steeringForce.setY(player.maxForce);
			keyDown[1] = true;
		}
		if (key == KeyEvent.VK_A){
			player.steeringForce.setX(-player.maxForce);
			keyDown[2] = true;
		}
		if (key == KeyEvent.VK_D){
			player.steeringForce.setX(player.maxForce);
			keyDown[3] = true;
		}
		if (key == KeyEvent.VK_SPACE){
			handler.addObject(handler.bullets, ObjectFactory.getBullet((int) ((int) player.position.getX() + 16 + Math.cos(player.getRotation())), (int) ((int) player.position.getY() + 16 + Math.sin(player.getRotation())), 16, 16, new Vector2D(Math.cos(player.velocity.getAngle()) * 8, Math.sin(player.velocity.getAngle()) * 8), ID.Bullet, ID.Projectile));
		}
		if (key == KeyEvent.VK_M){
			handler.addObject(handler.missiles, ObjectFactory.getMissile((int) ((int) player.position.getX() + 16 + Math.cos(player.getRotation())), (int) ((int) player.position.getY() + 16 + Math.sin(player.getRotation())), player.getRotation(), 24, 16, ID.Missile, ID.Projectile));
		}

		if (key == KeyEvent.VK_ESCAPE) System.exit(1);
		player.lastRotation = player.getRotation();
	}
	public void keyReleased(KeyEvent e) {

		int key = e.getKeyCode();
		this.player = this.handler.getObjectById(handler.objects, ID.Player);
		
		if (key == KeyEvent.VK_W){ keyDown[0] = false;}
		if (key == KeyEvent.VK_S){ keyDown[1] = false;}
		if (key == KeyEvent.VK_A){ keyDown[2] = false;}
		if (key == KeyEvent.VK_D){ keyDown[3] = false;}
		
		if (!keyDown[0] && !keyDown[1]){
			player.steeringForce.setY(0);
		}
		if (!keyDown[2] && !keyDown[3]){
			player.steeringForce.setX(0);
		}
	}
}
