package com.spaceshooter.math;

public class Mathf {

	public static double clamp(double value, double min, double max) {
		if (value >= max) return value = max;
		else if (value <= min) return value = min;
		else return value;
	}
	
	public static double fix(double value, int places) {
		int temp = (int)(value * Math.pow(10 , places));  
		return ((double)temp)/Math.pow(10 , places); 
	}
}
