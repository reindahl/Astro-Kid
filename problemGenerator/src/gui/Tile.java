package gui;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import problemGenerator.Level.Type;

public class Tile extends JLabel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8347905137731878617L;


	
	Type type = Type.nothing;
	
	public Tile(ImageIcon imageIcon) {
		super(imageIcon);
	}

	public Tile() {
		super();
	}

	public void setType(String type){
		
		switch (type) {
		case "start":
			this.setIcon(new ImageIcon("start.png"));
			this.type=Type.start;
			break;
		case "goal":
			this.setIcon(new ImageIcon("goal.png"));
			this.type=Type.goal;
			break;
		case "ground":
			this.setIcon(new ImageIcon("ground.png"));
			this.type=Type.ground;
			break;
		case "groundGreen":
			this.setIcon(new ImageIcon("ground-green.png"));
			this.type=Type.groundGreen;
			break;
		case "groundBlue":
			this.setIcon(new ImageIcon("ground.png"));
			this.type=Type.groundBlue;
			break;
		case "groundPurple":
			this.setIcon(new ImageIcon("ground.png"));
			this.type=Type.groundPurple;
			break;
		case "stone":
			this.setIcon(new ImageIcon("stone.png"));
			this.type=Type.stone;
			break;
		case "robotRight":
			this.setIcon(new ImageIcon("robot-right.png"));
			this.type=Type.robotRight;
			break;
		case "robotLeft":
			this.setIcon(new ImageIcon("robot-left.png"));
			this.type=Type.robotLeft;
			break;
		case "ladder":
			this.setIcon(new ImageIcon("ladder.png"));
			this.type=Type.ladder;
			break;
		case "buttonRed":
			this.setIcon(new ImageIcon("ladder.png"));
			this.type=Type.buttonRed;
			break;
		case "gateRed":
			this.setIcon(new ImageIcon("ladder.png"));
			this.type=Type.gateRed;
			break;
		default:
			this.setIcon(null);
			System.out.println("unown type "+type);
			break;
		}
	}
	
	public Type getType(){
		
		return type;
	}
}
