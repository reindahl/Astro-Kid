package gui.world;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.SwingUtilities;

import gui.editor.Tile;
import gui.editor.gui;
import world.Point;
import world.World.Color;
import world.objects.PhysObject.Direction;

public class MapListener implements MouseListener {
	
	JWorld map;
	
	Point teleport;

	public MapListener(JWorld JWorld) {
		map=JWorld;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println(((Tile)e.getSource()).getName());

		if(SwingUtilities.isLeftMouseButton(e)){
			//add
			Tile tile=((Tile)e.getSource());
			tile.setType(gui.toolListner.selected);
			switch (gui.toolListner.selected) {
			case player:
				map.world.addPlayer(tile.getPosition());
				break;
			case goal:
				map.world.addGoal(tile.getPosition());
				break;	
			case stone:
				map.world.addStone(tile.getPosition());
				break;	
			case ground:
				map.world.addGround(tile.getPosition());
				break;	
			case groundBlue:
				map.world.addGround(tile.getPosition(),Color.blue);
				break;	
			case groundGreen:
				map.world.addGround(tile.getPosition(),Color.green);
				break;	
			case groundPurple:
				map.world.addGround(tile.getPosition(),Color.purple);
				break;	
			case ladder:
				map.world.addLadder(tile.getPosition());
				break;	
			case robotLeft:
				map.world.addRobot(tile.getPosition(),Direction.left);
				break;	
			case robotRight:
				map.world.addRobot(tile.getPosition(),Direction.right);
				break;
			case gateRed:
				map.world.addGate(tile.getPosition(), Color.red);
				break;
			case buttonRed:
				map.world.addButton(tile.getPosition(), Color.red);
				break;
			case teleport:
				//TODO
				if(teleport!=null){
					map.world.addTeleport(teleport, tile.getPosition());
					teleport=null;
				}else{
					teleport=tile.getPosition();
				}
				break;
			default:
				System.err.println("unkown type: "+ gui.toolListner.selected);
				System.exit(-1);
				break;
			}
			
		}else if(SwingUtilities.isRightMouseButton(e)){
			((Tile)e.getSource()).clear();
			//remove
			map.world.clear(((Tile)e.getSource()).getPosition());
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
