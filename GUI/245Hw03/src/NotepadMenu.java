// 
//	Name:			Danzel Rana
//	Homework:	3	
//	Due:			11-06-2015
//	Course:		cs-245-01-f15
//	Description:
//					JMenuBar w/ JMenuItem's (Time works) 
//
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Date;
//////////////////////////////////////////////////////////////////////
public class NotepadMenu implements ActionListener
{
	JLabel label;
//--------------------------------------------------------------------
	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			public void run()
			{
				new NotepadMenu();
			}
		});
	}
//--------------------------------------------------------------------	
	NotepadMenu()
	{
		JFrame frame = new JFrame("Untitled - Notepad");
		frame.setSize(300,300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar mb = new JMenuBar();
		
		JMenu menuF = new JMenu("File");
		menuF.setMnemonic(KeyEvent.VK_F);
		JMenu menuE = new JMenu("Edit");
		menuE.setMnemonic(KeyEvent.VK_E);
		JMenu menuFmat = new JMenu("Format");
		menuFmat.setMnemonic(KeyEvent.VK_O);
		JMenu menuV = new JMenu("View");
		menuV.setMnemonic(KeyEvent.VK_V);
		JMenu menuH = new JMenu("Help");
		menuH.setMnemonic(KeyEvent.VK_H);
		
		JMenuItem undo = new JMenuItem("Undo", KeyEvent.VK_U);
		undo.setAccelerator(KeyStroke.getKeyStroke("control Z"));
		JMenuItem cut = new JMenuItem("Cut", KeyEvent.VK_T);
		cut.setAccelerator(KeyStroke.getKeyStroke("control X"));
		menuE.add(undo);
		menuE.addSeparator();
		menuE.add(cut);
		menuE.addSeparator();
		JMenu subMenu = new JMenu("Clock");
		subMenu.setMnemonic(KeyEvent.VK_K);
		JMenuItem date = new JMenuItem("Date", KeyEvent.VK_D);
		JMenuItem time = new JMenuItem("Time", KeyEvent.VK_T);
		time.setAccelerator(KeyStroke.getKeyStroke("control T"));
		time.addActionListener(this);
		subMenu.add(date);
		subMenu.add(time);
		menuE.add(subMenu);
		mb.add(menuF);
		mb.add(menuE);
		mb.add(menuFmat);
		mb.add(menuV);
		mb.add(menuH);
		
		frame.setJMenuBar(mb);
		
		label = new JLabel("Select option");
		frame.add(label);
		
		frame.setVisible(true);	
	}
//--------------------------------------------------------------------
	public void actionPerformed(ActionEvent ae)
	{
		label.setText(new Date().toString());
	}
//--------------------------------------------------------------------
} // end class NotepadMenu
//////////////////////////////////////////////////////////////////////