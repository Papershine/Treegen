package com.hilarylau.treegen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Treegen implements Runnable, ActionListener {
	
	DrawPanel panel;
	JTextField branchProb; // branching probability
	JTextField minScale; // minimum scale factor
	JTextField maxScale; // maximum scale factor
	
	@Override
	public void run() {
		JFrame f = new JFrame("Treegen");
		panel = new DrawPanel();

		// add button
		JButton regenerateButton = new JButton("Regenerate");
		regenerateButton.setActionCommand("regenerate");
		regenerateButton.addActionListener(this);
		panel.add(regenerateButton);
		// add textfields
		JLabel l = new JLabel("Branching Probability");
		branchProb = new JTextField("0.8", 5);
		JLabel minS = new JLabel("Minimum Scale");
		minScale = new JTextField("0.5", 5);
		JLabel maxS = new JLabel("Maximum Scale");
		maxScale = new JTextField("0.7", 5);
		
		panel.add(l);
		panel.add(branchProb);
		panel.add(minS);
		panel.add(minScale);
		panel.add(maxS);
		panel.add(maxScale);
		
		f.getContentPane().add(panel);
		f.setSize(750, 750); // size of window
		f.setVisible(true);
		f.setResizable(false);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	// regenerate tree when the button is clicked
	@Override
	public void actionPerformed(ActionEvent e) {
		// pass down values to the panel class
		panel.prob = Double.parseDouble(branchProb.getText());
		panel.minS = (int)(Double.parseDouble(minScale.getText()) * 1000);
		panel.maxS = (int)(Double.parseDouble(maxScale.getText()) * 1000);
		// repaint the panel to get a new tree
		panel.repaint();
	} 
	
    public static void main(String[] args) {
    	// start program
        SwingUtilities.invokeLater(new Treegen());
    }

}
