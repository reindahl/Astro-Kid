package gui.editor;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import gui.world.JWorld;
import world.Point;
import world.World.Type;

public class Tile extends JLabel{
	
	Point position;

	/**
	 * 
	 */
	private static final long serialVersionUID = -8347905137731878617L;

	Type type = null;
	
	Type typeBack = null;
	Type typeMid=null;
	Type typeFor=null;
	
	 JWorld parrent;
	
	public Tile(ImageIcon imageIcon, JWorld parrent) {
		super(imageIcon);
		this.setOpaque(false);
		this.parrent=parrent;
	}

	public Tile(Point position, JWorld parrent) {
		super();
		this.position=position;
		this.setOpaque(false);
		this.parrent=parrent;
	}

	public void setType(Type type){
		this.type=type;
//		this.setIcon(new ImageIcon(type.toString()+".png"));
		
		switch (type) {
		case player:
		case goal:
		case ground:	
		case groundBlue:
		case groundGreen:
		case groundPurple:	
		case teleport:
			typeBack=type;
			break;
		
		case ladder:
			typeMid=type;
			break;
		
		case gateRed:
		case stone:	
		case robotLeft:
		case robotRight:	
		case buttonRed:
		case bootBlue:
		case bootGreen:
		case bootPurple:
		case remote:
			typeFor=type;
			break;
		default:
			System.err.println("unknown type "+type);
			System.exit(-3);
			break;
			
		}
		this.repaint();
	}
	
	public void setTypeFor(Type type){
		this.typeFor=type;

	}
	public void setTypeBack(Type type){
		this.typeBack=type;
	}
	
	public Point getPosition(){
		return new Point(position);
	}
	
	public void paint(Graphics g){
		super.paint(g);
		if(typeBack!=null){
			g.drawImage(parrent.getImageIcon(typeBack), 0, 0, null);
		}
		if(typeMid!=null){
			g.drawImage(parrent.getImageIcon(typeMid), 0, 0, null);
		}
		if(typeFor!=null){
			g.drawImage(parrent.getImageIcon(typeFor), 0, 0, null);
		}
	}
	
	public Type getType(){
		
		return type;
	}

	public void clear() {
		typeBack=null;
		typeMid=null;
		typeFor=null;
		this.repaint();
		
	}
}
