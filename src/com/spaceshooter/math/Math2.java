package com.spaceshooter.math;

public class Math2{

	public static double clamp(double value, double min, double max) {
		if (value >= max) return value = max;
		else if (value <= min) return value = min;
		else return value;
	}
}
