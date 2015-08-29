package gui.editor;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import world.Point;
import world.World.Type;

public class Tile extends JLabel{
	
	Point position;

	/**
	 * 
	 */
	private static final long serialVersionUID = -8347905137731878617L;


	
	Type type = Type.nothing;
	
	public Tile(ImageIcon imageIcon) {
		super(imageIcon);
	}

	public Tile(Point position) {
		super();
		this.position=position;
	}

	public void setType(String type){
		
		switch (type) {
		case "start":
			this.setIcon(new ImageIcon("start.png"));
			this.type=Type.player;
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
	public Point getPosition(){
		return new Point(position);
	}
	public Type getType(){
		
		return type;
	}
}
