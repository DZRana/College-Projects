// 
//	Name:		Danzel Rana
//	Homework:	1	
//	Due:		10-12-2015
//	Course:		cs-245-01-f15
//	Description:
//				An image viewer that displays GIF, JPG, or PNG 
//				files.
//
import javax.swing.*;
import java.awt.*;
//////////////////////////////////////////////////////////////////////
public class ImageViewer 
{
//--------------------------------------------------------------------
	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			public void run()
			{
				new ImageViewer(args[0]);
			}
		});
	}
//--------------------------------------------------------------------
	ImageViewer(String image)
	{
		JFrame frame = new JFrame("D. Rana's Image Viewer");
		frame.setLayout(new BorderLayout());
		frame.setSize(800,600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		ImageIcon icon = new ImageIcon(image);
		JLabel labelIcon = new JLabel(icon);
		frame.add(labelIcon, BorderLayout.CENTER);
		
		JLabel labelTxt = new JLabel(image);
		labelTxt.setHorizontalAlignment(SwingConstants.CENTER);
		frame.add(labelTxt, BorderLayout.SOUTH);
		
		frame.setVisible(true);
	}
//--------------------------------------------------------------------
} // end class ImageViewer
//////////////////////////////////////////////////////////////////////