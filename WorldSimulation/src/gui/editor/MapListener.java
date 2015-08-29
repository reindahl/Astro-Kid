package gui.editor;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.SwingUtilities;

import world.World.Color;
import world.objects.PhysObject.Direction;

public class MapListener implements MouseListener {
	

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println(((Tile)e.getSource()).getName());

		if(SwingUtilities.isLeftMouseButton(e)){
			//add
			Tile tile=((Tile)e.getSource());
			tile.setType(gui.toolListner.selected);
			
			switch (tile.getType()) {
			case player:
				gui.world.addPlayer(tile.getPosition());
				break;
			case goal:
				gui.world.addGoal(tile.getPosition());
				break;	
			case stone:
				gui.world.addStone(tile.getPosition());
				break;	
			case ground:
				gui.world.addGround(tile.getPosition());
				break;	
			case groundBlue:
				gui.world.addGround(tile.getPosition(),Color.blue);
				break;	
			case groundGreen:
				gui.world.addGround(tile.getPosition(),Color.green);
				break;	
			case groundPurple:
				gui.world.addGround(tile.getPosition(),Color.purple);
				break;	
			case ladder:
				gui.world.addLadder(tile.getPosition());
				break;	
			case robotLeft:
				gui.world.addRobot(tile.getPosition(),Direction.left);
				break;	
			case robotRight:
				gui.world.addRobot(tile.getPosition(),Direction.right);
				break;
				
			default:
				System.err.println("unkown type: "+ tile.getType());
				System.exit(-1);
				break;
			}
			
		}else if(SwingUtilities.isRightMouseButton(e)){
			((Tile)e.getSource()).setType("");
			//remove
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
