package com.spaceshooter.map;


public enum Layer {
	
	Collidable(),
	Controllable();

	int value;
	
	public int value() {
		return value;
	}
	 
    private Layer(int value) {
        this.value = value;
    }
    
    private Layer() {}
}
