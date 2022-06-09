package com.hilarylau.treegen;

public class LineVector {
	
	// initial point
	int x1;
	int y1;
	// final point
	int x2;
	int y2;
	
	public LineVector(int x1, int y1, int x2, int y2) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}
	
	// distance between the two points
	public double distance() {
		return Math.sqrt(Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2));
	}
	
}
