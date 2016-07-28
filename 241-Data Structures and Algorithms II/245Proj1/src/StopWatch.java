import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class StopWatch implements ActionListener
{
	JLabel dynamicText;
	
	public static void main (String[] args)
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			public void run()
			{
				new StopWatch();
			}
		});
	}
	
	StopWatch()
	{
		JFrame frame = new JFrame("Title OF the FRAMEEEEE");
		frame.setLayout(new FlowLayout());
		frame.setSize(230,90);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel info1 = new JLabel("testingggg");
		frame.add(info1);
		
		JLabel info2 = new JLabel("12345");
		frame.add(info2);
		
		JButton b1 = new JButton("b1");
		b1.addActionListener(this);
		frame.add(b1);
		
		JButton b2 = new JButton("b2");
		b2.addActionListener(this);
		frame.add(b2);
		
		dynamicText = new JLabel("waiting for button press");
		frame.add(dynamicText);
		
		frame.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		if (ae.getActionCommand().equals("b1")) dynamicText.setText("b1 pressed");
		else dynamicText.setText("b2 pressed");
	}
}
