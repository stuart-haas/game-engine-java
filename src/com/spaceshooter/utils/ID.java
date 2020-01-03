package com.spaceshooter.utils;

public enum ID {

	Arrive(), 
	Avoid(), 
	Bullet(),
	Coin(3), 
	CollisionTile(),
	Cursor(), 
	Evade(), 
	Explosion(), 
	Flee(), 
	FollowPath(),
	Missile(), 
	MissileTrail(),
	Particle(),
	Pickup(), 
	Player(0), 
	Powerup(), 
	Projectile(), 
	Pursue(), 
	Seek(),
	Seeker(5), 
	ThrustTrail(), 
	Tile(),
	Turret(),
	WallTile(1), 
	Wander(), 
	Empty();
	
	int value;
	
	public int value() {
		return value;
	}
	 
    private ID(int value) {
        this.value = value;
    }
    
    private ID() {}
}
