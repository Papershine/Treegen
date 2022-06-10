package com.hilarylau.treegen;

import java.awt.Graphics;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.JTextField;

public class DrawPanel extends JPanel {
	
	private static final long serialVersionUID = 908925503915443172L;
	double prob = 0.8;
	int maxS = 700;
	int minS = 500;
	JTextField branchProb;

	@Override
	public void paint(Graphics g) {
		// clear the whole panel
		super.paint(g);
		// begin recursive drawing
		this.drawVector(g, new LineVector(375, 750, 375, 525));
	}
	
	// creates a vector extending from a previous vector with anticlockwise rotation
	public LineVector transformCoordinatesPos(LineVector vector) {
		// randomize angle with positive value
		Random r = new Random();
		int angle = r.nextInt(45 - 5 + 1) + 5;
		return transformVector(vector, angle);
	}
	
	// creates a vector extending from a previous vector with clockwise rotation
	public LineVector transformCoordinatesNeg(LineVector vector) {
		// randomize angle with negative value
		Random r = new Random();
		int angle = (r.nextInt(45 - 5 + 1) + 5) * (-1);
		return transformVector(vector, angle);
	}
	
	// creates a vector extending from a previous vector, shrunk and rotated at an angle
	public LineVector transformVector(LineVector vector, int angleDeg) {
		double length = vector.distance();
		double angle = Math.toRadians(angleDeg);
		// set initial point to the previous final point
		int x1 = vector.x2;
		int y1 = vector.y2;
		// randomize scale factor
		Random r = new Random();
		int n = r.nextInt(maxS - minS + 1) + minS;
		double scaleFactor = n / 1000.0;
		// extend vector to find final point
		double tempX2 = x1 + (x1 - vector.x1) / length * (length * scaleFactor);
		double tempY2 = y1 + (y1 - vector.y1) / length * (length * scaleFactor);
		// rotate vector around point
		int x2 = (int)(x1 + (tempX2-x1)*Math.cos(angle) - (tempY2-y1)*Math.sin(angle));
		int y2 = (int)(y1 + (tempX2-x1)*Math.sin(angle) + (tempY2-y1)*Math.cos(angle));
		
		return new LineVector(x1, y1, x2, y2);
	}
	
	// recursive line drawing function
	public void drawVector(Graphics g, LineVector vector) {
		// draw the vector provided
		g.drawLine(vector.x1, vector.y1, vector.x2, vector.y2);
		// generate child vectors
		LineVector posVector = transformCoordinatesPos(vector);
		LineVector posVector2 = transformCoordinatesPos(vector);
		LineVector negVector = transformCoordinatesNeg(vector);
		LineVector negVector2 = transformCoordinatesNeg(vector);
		if (vector.distance() > 5) { // End Condition when branch length is shorter than 4 pixels
			// call function again to recurse
			this.drawVector(g, posVector);
			this.drawVector(g, negVector);
			if (this.branchAppearing()) { // randomize if branch appears or not
				this.drawVector(g, posVector2);
			}
			if (this.branchAppearing()) { // randomize if branch appears or not
				this.drawVector(g, negVector2);
			}
		}
	}
	
	public boolean branchAppearing(){
		System.out.println(prob);
	    return Math.random() < prob; // probability of branch appearing is 0.8
	}
	
}
