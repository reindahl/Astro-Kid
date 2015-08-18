package gui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class gui extends JFrame {


	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JFrame frame=new JFrame("Progress");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		
		BorderLayout border= new BorderLayout();
		frame.setLayout(border);
		
		JPanel tools= new JPanel();
		JButton start=new JButton(new ImageIcon("start.png"));
		tools.add(start);
		JButton goal=new JButton(new ImageIcon("goal.png"));
		tools.add(goal);
		frame.add(tools, BorderLayout.EAST);
		
		
		frame.pack();
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int width = gd.getDisplayMode().getWidth();
		int height = gd.getDisplayMode().getHeight();
		frame.setLocation(width/2-frame.getWidth()/2, height/3-frame.getHeight()/2);
		frame.setVisible(true);
	}
	
}
