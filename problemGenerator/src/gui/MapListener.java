package gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.SwingUtilities;

public class MapListener implements MouseListener {

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println(((Tile)e.getSource()).getName());

		if(SwingUtilities.isLeftMouseButton(e)){
			((Tile)e.getSource()).setType(gui.toolListner.selected);
		}else if(SwingUtilities.isRightMouseButton(e)){
			((Tile)e.getSource()).setType("");
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
