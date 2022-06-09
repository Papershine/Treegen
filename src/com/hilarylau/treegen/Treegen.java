package com.hilarylau.treegen;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Treegen implements Runnable {
	
	@Override
	public void run() {
		JFrame f = new JFrame("Treegen");
		f.getContentPane().add(new DrawPanel());
		f.setSize(750, 750);
		f.setVisible(true);
		f.setResizable(false);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Treegen());
    }

}
