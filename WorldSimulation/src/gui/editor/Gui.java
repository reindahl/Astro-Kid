package gui.editor;

import java.awt.BorderLayout;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import gui.world.JWorld;
import world.World;
import world.World.Type;

public class Gui {

	JWorld map;
	JFrame frame;
	
	public static ToolListener toolListner= new ToolListener();
	
	public Gui(){
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		frame=new JFrame("Editor");
		try {
			frame.setIconImage(ImageIO.read(new File("icon.png")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		MenuListener menuListener= new MenuListener(this);
		
		JMenuBar menu= new JMenuBar();
		JMenu file = new JMenu("File");
		menu.add(file);
		JMenuItem generate =new JMenuItem("generate pddl");
		generate.setName("pddl");
		generate.addActionListener(menuListener);
		file.add(generate);
		JMenuItem save =new JMenuItem("save");
		save.setName("save");
		save.addActionListener(menuListener);
		file.add(save);
		JMenuItem open =new JMenuItem("open");
		open.setName("open");
		open.addActionListener(menuListener);
		file.add(open);
		
		JMenuItem run =new JMenuItem("run");
		run.setName("run");
		run.addActionListener(menuListener);
		file.add(run);
		
		menu.add(new JSeparator(SwingConstants.VERTICAL));
		Integer[] numbers= new Integer[20];
		for (int i = 0; i < numbers.length; i++) {
			numbers[i]=i+1;
		}
		int height=8,width=15;
		JLabel heightLabel= new JLabel("height:");
		menu.add(heightLabel);
		JComboBox<Integer> heightCombo = new JComboBox<>(numbers);
		heightCombo.setSelectedIndex(height-1);
		heightCombo.setName("height");
		heightCombo.addActionListener(menuListener);
		heightCombo.setOpaque(false);
		menu.add(heightCombo);
		
		
		JLabel widthLabel= new JLabel("width:");
		menu.add(widthLabel);
		JComboBox<Integer> widthCombo = new JComboBox<>(numbers);
		widthCombo.setName("width");
		widthCombo.setSelectedIndex(width-1);
		widthCombo.addActionListener(menuListener);
		widthCombo.setOpaque(false);
		menu.add(widthCombo);
		frame.setJMenuBar(menu);

		BorderLayout border= new BorderLayout();
		frame.setLayout(border);

		
	
		
		frame.add(tools(), BorderLayout.EAST);



		map = new JWorld(width,height);


		frame.add(map);
		frame.pack();
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int screenWidth = gd.getDisplayMode().getWidth();
		int screenHeight = gd.getDisplayMode().getHeight();
		frame.setLocation(screenWidth/2-frame.getWidth()/2, screenHeight/3-frame.getHeight()/2);
	
		frame.setVisible(true);
	}

	public Gui(World world, Boolean Exit) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		JFrame frame=new JFrame("Astro Kid");
		if(Exit){
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}else{
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		}

		BorderLayout border= new BorderLayout();
		frame.setLayout(border);


		map = new JWorld(world);
		

		frame.add(map);
		frame.pack();
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int screenWidth = gd.getDisplayMode().getWidth();
		int screenHeight = gd.getDisplayMode().getHeight();
		frame.setLocation(screenWidth/2-frame.getWidth()/2, screenHeight/3-frame.getHeight()/2);
		map.update(world, null);
		frame.setVisible(true);
	}

	public void setWorld(World world){
		frame.remove(map);
		map= new JWorld(world);
		frame.add(map);
		frame.pack();
		map.update(world, null);

	}
	
	public JPanel tools(){
		JPanel tools= new JPanel();
		
		tools.setLayout(new GridLayout(6, 2));
		JButton start=new JButton(new ImageIcon(Type.player.toString()+".png"));
		start.setName("player");
		start.addActionListener(toolListner);
		tools.add(start);
		JButton goal=new JButton(new ImageIcon("goal.png"));
		goal.setName("goal");
		goal.addActionListener(toolListner);
		tools.add(goal);
		
		
		 Object[] items =
		        {
		            new ImageIcon(Type.ground.toString()+".png"),
		            new ImageIcon(Type.groundBlue.toString()+".png"),
		            new ImageIcon(Type.groundGreen.toString()+".png"),
		            new ImageIcon(Type.groundPurple.toString()+".png")
		        };
		JComboBox<Object> grounds= new JComboBox<>(items);
		grounds.setName("grounds");
		grounds.addActionListener(toolListner);
		tools.add(grounds);
		
		JButton ladder=new JButton(new ImageIcon(Type.ladder.toString()+".png"));
		ladder.setName("ladder");
		ladder.addActionListener(toolListner);
		tools.add(ladder);
		JButton stone=new JButton(new ImageIcon(Type.stone.toString()+".png"));
		stone.setName("stone");
		stone.addActionListener(toolListner);
		tools.add(stone);
		
		 Object[] items2 =
		        {
		            new ImageIcon(Type.robotLeft.toString()+".png"),
		            new ImageIcon(Type.robotRight.toString()+".png"),

		      
		        };
		JComboBox<Object> robots= new JComboBox<>(items2);
		robots.setName("robots");
		robots.addActionListener(toolListner);
		tools.add(robots);
		
		
		JButton gateRed=new JButton(new ImageIcon(Type.gateRed.toString()+".png"));
		gateRed.setName("gateRed");
		gateRed.addActionListener(toolListner);
		tools.add(gateRed);
		
		JButton buttonRed=new JButton(new ImageIcon(Type.buttonRed.toString()+".png"));
		buttonRed.setName(Type.buttonRed.toString());
		buttonRed.addActionListener(toolListner);
		tools.add(buttonRed);
		
		JButton teleport=new JButton(new ImageIcon(Type.teleport.toString()+".png"));
		teleport.setName(Type.teleport.toString());
		teleport.addActionListener(toolListner);
		tools.add(teleport);
		
		JButton remote=new JButton(new ImageIcon(Type.remote.toString()+".png"));
		remote.setName(Type.remote.toString());
		remote.addActionListener(toolListner);
		tools.add(remote);
		return tools;
	}
	
	public static void main(String[] args) {
		
		new Gui();
	}

}
