package gui.editor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JComponent;

import world.World.Type;

public class ToolListener implements ActionListener{
	
	public Type selected =Type.player;
	
	@Override
	public void actionPerformed(ActionEvent e){
		// TODO Auto-generated method stub
		System.out.println(((JComponent)e.getSource()).getName());
		if(e.getSource() instanceof JComboBox<?>){
			JComboBox<?> combo=(JComboBox<?>) e.getSource();
			if(combo.getName().equals("robots")){
				switch (combo.getSelectedIndex()) {
				case 0:
					selected =Type.robotLeft;
					break;
				case 1:
					selected =Type.robotRight;
					break;
				default:
					System.err.println("fuck2");
					break;
				}
			}else if(combo.getName().equals("grounds")){
				switch (combo.getSelectedIndex()) {
				case 0:
					selected =Type.ground;
					break;
				case 1:
					selected =Type.groundBlue;
					break;
				case 2:
					selected =Type.groundGreen;
					break;
				case 3:
					selected =Type.groundPurple;
					break;
				default:
					System.err.println("fuck");
					break;
				}

			}
		}else{
			selected=Type.valueOf(((JComponent)e.getSource()).getName());
		}
		
	}


}
