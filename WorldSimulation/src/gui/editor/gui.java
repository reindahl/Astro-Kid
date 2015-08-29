package gui.editor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileFilter;

import world.Point;
import world.World;

public class gui {

	public static Tile[][] tmap;
	public static World world;
	
	public static ToolListener toolListner= new ToolListener();
	
	public static MapListener mapListener= new MapListener();

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
					//FIXME:
//					Level level = new Level(tmap, Paths.get(path).getFileName().toString());
//					if(!path.endsWith(".pddl")){
//						path=path.concat(".pddl");
//					}
//					try {
//						Files.write(Paths.get(path), level.getText());
//					} catch (IOException e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					}
					
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

		JButton start=new JButton(new ImageIcon("start.png"));
		start.setName("start");
		start.addMouseListener(toolListner);
		tools.add(start);
		JButton goal=new JButton(new ImageIcon("goal.png"));
		goal.setName("goal");
		goal.addMouseListener(toolListner);
		tools.add(goal);
		JButton ground=new JButton(new ImageIcon("ground.png"));
		ground.setName("ground");
		ground.addMouseListener(toolListner);
		tools.add(ground);
		JButton groundPurple=new JButton(new ImageIcon("ground-purple.png"));
		groundPurple.setName("groundPurple");
		groundPurple.addMouseListener(toolListner);
		tools.add(groundPurple);
		JButton groundGreen=new JButton(new ImageIcon("ground-green.png"));
		groundGreen.setName("groundGreen");
		groundGreen.addMouseListener(toolListner);
		tools.add(groundGreen);
		JButton groundBlue=new JButton(new ImageIcon("ground-blue.png"));
		groundBlue.setName("groundBlue");
		groundBlue.addMouseListener(toolListner);
		tools.add(groundBlue);
		JButton ladder=new JButton(new ImageIcon("ladder.png"));
		ladder.setName("ladder");
		ladder.addMouseListener(toolListner);
		tools.add(ladder);
		JButton stone=new JButton(new ImageIcon("stone.png"));
		stone.setName("stone");
		stone.addMouseListener(toolListner);
		tools.add(stone);
		JButton robotRight=new JButton(new ImageIcon("robot-right.png"));
		robotRight.setName("robotRight");
		robotRight.addMouseListener(toolListner);
		tools.add(robotRight);
		JButton robotLeft=new JButton(new ImageIcon("robot-left.png"));
		robotLeft.setName("robotLeft");
		robotLeft.addMouseListener(toolListner);
		tools.add(robotLeft);
		JButton gateRed=new JButton(new ImageIcon("gate-red.png"));
		gateRed.setName("gateRed");
		gateRed.addMouseListener(toolListner);
		tools.add(gateRed);
		JButton buttonRed=new JButton(new ImageIcon("button-red.png"));
		buttonRed.setName("buttonRed");
		buttonRed.addMouseListener(toolListner);
		tools.add(buttonRed);
		frame.add(tools, BorderLayout.EAST);




		JPanel map = new JPanel();
		int width = 15;
		int height =15;
		tmap= new Tile[width][height];
		world = new World(width, height);
		map.setLayout(new GridLayout(width,height));
		for (int y = 0; y < width; y++) {
			for (int x = 0; x < height; x++) {

				Tile label = new Tile(new Point(x, y));
				label.setName(x+","+y);
				label.setPreferredSize(new Dimension(30, 30));
				label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				label.addMouseListener(mapListener);
				tmap[x][y]= label;
				map.add(label);
			}
		}


		frame.add(map);
		frame.pack();
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int screenWidth = gd.getDisplayMode().getWidth();
		int screenHeight = gd.getDisplayMode().getHeight();
		frame.setLocation(screenWidth/2-frame.getWidth()/2, screenHeight/3-frame.getHeight()/2);
		frame.setVisible(true);
	}

}
