package gui.editor;

import java.awt.Graphics;
import java.awt.Image;

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

	@Override
	public void paint(Graphics g){
		super.paint(g);

	}

	@Override
	protected void paintComponent(Graphics g){
		int x, y;
		if(typeBack!=null){
			Image image =parrent.getImageIcon(typeBack);
			x=(this.getWidth()-image.getWidth(null))/2;				
			y=(this.getHeight()-image.getHeight(null));
			g.drawImage(image, x, y, null);
		}
		if(typeMid!=null){
			Image image =parrent.getImageIcon(typeMid);
			x=(this.getWidth()-image.getWidth(null))/2;				
			y=(this.getHeight()-image.getHeight(null));
			g.drawImage(image, x, y, null);
		}
		if(typeFor!=null){
			Image image =parrent.getImageIcon(typeFor);
			x=(this.getWidth()-image.getWidth(null))/2;				
			y=(this.getHeight()-image.getHeight(null));
			g.drawImage(image, x, y, null);
		}
	}

	public void clear() {
		typeBack=null;
		typeMid=null;
		typeFor=null;
		repaint();

	}
}
