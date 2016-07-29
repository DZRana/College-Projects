// 	
//	Written By: cs245f15 - NotepadMenu - tvnguyen7 
//	Edited by
//		Name:		Danzel Rana
//		Homework:	4	
//		Due:		11-13-2015
//		Course:		cs-245-01-f15
//		Description:
//					Edited NotpadMenu example to include Open
//					and Goto functionality.
//
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
//////////////////////////////////////////////////////////////////////
class NotepadMenu implements ActionListener 
{
	JLabel label;
	JFrame frame;
//--------------------------------------------------------------------
    NotepadMenu() 
    {
    	frame = new JFrame("Untitled - Notepad");
        frame.setSize(500, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        label = new JLabel("Select a menu");

        JMenuBar menubar = new JMenuBar();

        JMenu jmFile = new JMenu("File");
        jmFile.setMnemonic('F');

        JMenuItem jmiNew = new JMenuItem("New", 'N');
        jmiNew.setAccelerator(
                KeyStroke.getKeyStroke(KeyEvent.VK_N,
                        InputEvent.CTRL_MASK));
        jmFile.add(jmiNew);

        JMenuItem jmiOpen = addMenuItem(jmFile, "Open...", 'O');
        jmiOpen.setAccelerator(KeyStroke.getKeyStroke("control O"));
        jmiOpen.addActionListener(this);
        JMenuItem jmiSave = addMenuItem(jmFile, "Save", 'S');
        JMenuItem jmiSaveAs = addMenuItem(jmFile, "Save As..", 'A');
         
        jmFile.addSeparator();
        JMenuItem jmiPageSetup = addMenuItem(jmFile, "Page Setup...", 'u');
        JMenuItem jmiPrint = addMenuItem(jmFile, "Print...", 'P');
        jmFile.addSeparator();
        JMenuItem jmiExit = addMenuItem(jmFile, "Exit", 'x');
        jmiExit.setAccelerator(KeyStroke.getKeyStroke("control X"));
        jmiExit.addActionListener(ae -> {
            System.exit(0);
        });

        JMenu jmEdit = new JMenu("Edit");
        jmEdit.setMnemonic('E');
        JMenuItem jmiEdit = addMenuItem(jmEdit, "Goto...", 'G');
        jmiEdit.setAccelerator(KeyStroke.getKeyStroke("control G"));
        jmiEdit.addActionListener(this);
        
        JMenu jmFormat = new JMenu("Format");
        jmFormat.setMnemonic('o');

        JCheckBoxMenuItem jcbmiWordWrap = new JCheckBoxMenuItem("Word Wrap", false);
        jcbmiWordWrap.setMnemonic('W');
        jmFormat.add(jcbmiWordWrap);
        JMenuItem jmiFont = addMenuItem(jmFormat, "Font...", 'F');

        JMenu jmView = new JMenu("View");
        jmView.setMnemonic('V');

        JMenu jmHelp = new JMenu("Help");
        jmHelp.setMnemonic('H');

        // add menus to the main menubar
        menubar.add(jmFile);
        menubar.add(jmEdit);
        menubar.add(jmFormat);
        menubar.add(jmView);
        menubar.add(jmHelp);

        jmiNew.addActionListener(this);
        jmiExit.addActionListener(this);

        frame.add(label);

        frame.setJMenuBar(menubar);

        frame.setVisible(true);
    }
//--------------------------------------------------------------------
    private static JMenuItem addMenuItem(JMenu jm, String text, char mmemonic) 
    {
        JMenuItem jmi = new JMenuItem(text, mmemonic);
        jm.add(jmi);

        return jmi;
    }
//--------------------------------------------------------------------
    public void actionPerformed(ActionEvent ae) 
    {
        // Get the action command from the menu selection. 
        String comStr = ae.getActionCommand();

        // Display the selection. 
        label.setText(comStr + " Selected");
        
        // Open.
        if (comStr.equals("Open..."))
        {
        	JFileChooser fc= new JFileChooser();
        	if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
        	{
        		File selectedFile = fc.getSelectedFile();
        		label.setText(selectedFile.getName());
        	}
        }
        // Goto.
        if (comStr.equals("Goto..."))
        {
        	String lineNum = (String)JOptionPane.showInputDialog(frame, 
        														 "Line number:", 
        														 "Go To Line", 
        														 JOptionPane.QUESTION_MESSAGE);
        	label.setText(lineNum);
        }
    }
//--------------------------------------------------------------------
    public static void main(String args[]) 
    {
        // Create the frame on the event dispatching thread.   
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new NotepadMenu();
            }
        });
    }
//--------------------------------------------------------------------
} // end class NotepadMenu
//////////////////////////////////////////////////////////////////////
