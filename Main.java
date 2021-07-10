import java.awt.Color;
import javax.swing.*;
public class Main
{
	public static void main(String s[])
	{
		JFrame f=new JFrame();
		f.setTitle("SNACK GAME");
		f.setBounds(10,10,905,700);
		f.setResizable(false);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setBackground(Color.DARK_GRAY);
		
		GamePlay gp=new GamePlay();
		f.add(gp);
	}
}