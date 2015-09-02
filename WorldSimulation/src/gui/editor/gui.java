package gui.editor;

import java.awt.BorderLayout;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileFilter;

import gui.world.JWorld;
import world.Converter;
import world.World.Type;

public class gui {

	static JWorld map;
	
	public static ToolListener toolListner= new ToolListener();
	
	

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
		
		JMenuBar menu= new JMenuBar();
		JMenuItem generate =new JMenuItem("generate");
		generate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser=new JFileChooser();
				
				chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				chooser.setFileFilter(new FileFilter() {
					
					@Override
					public String getDescription() {
						// TODO Auto-generated method stub
						return "pddl";
					}
					
					@Override
					public boolean accept(File f) {
						if(f.getName().endsWith(".pddl") || f.isDirectory()){
							return true;
						}
						return false;
					}
				});
				int returnVal = chooser.showSaveDialog(frame);
				
				if(returnVal == JFileChooser.APPROVE_OPTION) {
					String path=chooser.getSelectedFile().getPath();
					System.out.println(path);
					if(!path.endsWith(".pddl")){
						path=path.concat(".pddl");
					}
					
					String[] tmp=path.split("(.pddl)|/");
					String name =tmp[tmp.length-1];
					try {
						
						Files.write(Paths.get(path), Converter.toPDDL(map.world, name));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					throw new UnsupportedOperationException();
				}else{
					System.out.println("no file saved");
					
				}
				
			}
		});
		menu.add(generate);
		
		frame.setJMenuBar(menu);

		BorderLayout border= new BorderLayout();
		frame.setLayout(border);

		JPanel tools= new JPanel();
		
		tools.setLayout(new GridLayout(6, 2));
		JButton start=new JButton(new ImageIcon(Type.player.toString()+".png"));
		start.setName("player");
		start.addMouseListener(toolListner);
		tools.add(start);
		JButton goal=new JButton(new ImageIcon("goal.png"));
		goal.setName("goal");
		goal.addMouseListener(toolListner);
		tools.add(goal);
		
		
		 Object[] items =
		        {
		            new ImageIcon(Type.ground.toString()+".png"),
		            new ImageIcon(Type.groundBlue.toString()+".png"),
		            new ImageIcon(Type.groundGreen.toString()+".png"),
		            new ImageIcon(Type.groundPurple.toString()+".png")
		        };
		JComboBox<Object> grounds= new JComboBox<>(items);
		grounds.setName("ground");
		grounds.addMouseListener(toolListner);
		tools.add(grounds);
		
		JButton ladder=new JButton(new ImageIcon(Type.ladder.toString()+".png"));
		ladder.setName("ladder");
		ladder.addMouseListener(toolListner);
		tools.add(ladder);
		JButton stone=new JButton(new ImageIcon(Type.stone.toString()+".png"));
		stone.setName("stone");
		stone.addMouseListener(toolListner);
		tools.add(stone);
		
		 Object[] items2 =
		        {
		            new ImageIcon(Type.robotLeft.toString()+".png"),
		            new ImageIcon(Type.robotRight.toString()+".png"),

		      
		        };
		JComboBox<Object> robots= new JComboBox<>(items2);
		robots.addMouseListener(toolListner);
		tools.add(robots);
		
		
		JButton gateRed=new JButton(new ImageIcon(Type.gateRed.toString()+".png"));
		gateRed.setName("gateRed");
		gateRed.addMouseListener(toolListner);
		tools.add(gateRed);
		JButton buttonRed=new JButton(new ImageIcon(Type.buttonRed.toString()+".png"));
		buttonRed.setName(Type.buttonRed.toString());
		buttonRed.addMouseListener(toolListner);
		tools.add(buttonRed);
		
		JButton teleport=new JButton(new ImageIcon("teleport.png"));
		teleport.setName("teleport");
		teleport.addMouseListener(toolListner);
		tools.add(teleport);
		
		frame.add(tools, BorderLayout.EAST);



		map = new JWorld();


		frame.add(map);
		frame.pack();
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int screenWidth = gd.getDisplayMode().getWidth();
		int screenHeight = gd.getDisplayMode().getHeight();
		frame.setLocation(screenWidth/2-frame.getWidth()/2, screenHeight/3-frame.getHeight()/2);
	
		frame.setVisible(true);
		
	}

}
