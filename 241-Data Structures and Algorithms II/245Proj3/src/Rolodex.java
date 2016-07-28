// 
//	Name:		Danzel Rana
//	Project:	3	
//	Due:		11-20-2015
//	Course:		cs-245-01-f15
//	Description:
//				Simple Rolodex.
//
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
//////////////////////////////////////////////////////////////////////
public class Rolodex implements ActionListener
{
	JTabbedPane tp;
	JFrame frame;
	ImageIcon aImg;
//--------------------------------------------------------------------
    public static void main(String[] args) 
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                new Rolodex();
            }
        });
    }
//--------------------------------------------------------------------
    Rolodex()
    {
        //FRAME
        frame = new JFrame("Rolodex");
        frame.setSize(700,300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        aImg = new ImageIcon("Rolodex.png");
        frame.setIconImage(aImg.getImage());
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
 
        //MENU BAR
        JMenuBar mb = new JMenuBar();
        
        //FILE
        JMenu file = new JMenu("File");
        file.setMnemonic(KeyEvent.VK_F);
        JMenuItem open = new JMenuItem("Open", KeyEvent.VK_O);
        open.setEnabled(false);
        file.add(open);
        file.addActionListener(this);
        file.addSeparator();
        JMenuItem exit = new JMenuItem("Exit", KeyEvent.VK_X);
        exit.addActionListener(this);
        file.add(exit);
        
        //TABS
        JMenu tabs = new JMenu("Tabs");
        tabs.setMnemonic(KeyEvent.VK_T);
        JMenu placement = new JMenu("Placement");
        placement.setMnemonic(KeyEvent.VK_P);
        JMenuItem top = new JMenuItem("Top", KeyEvent.VK_T);
        top.addActionListener(this);
        placement.add(top);
        JMenuItem right = new JMenuItem("Right", KeyEvent.VK_R);
        right.addActionListener(this);
        placement.add(right);
        JMenuItem bottom = new JMenuItem("Bottom", KeyEvent.VK_B);
        bottom.addActionListener(this);
        placement.add(bottom);
        JMenuItem left = new JMenuItem("Left", KeyEvent.VK_L);
        left.addActionListener(this);
        placement.add(left);
        JMenu layout = new JMenu("Layout");
        layout.setMnemonic(KeyEvent.VK_L);
        JMenuItem scroll = new JMenuItem("Scroll", KeyEvent.VK_S);
        scroll.addActionListener(this);
        layout.add(scroll);
        JMenuItem wrap = new JMenuItem("Wrap", KeyEvent.VK_W);
        wrap.addActionListener(this);
        layout.add(wrap);
        JMenuItem defaults = new JMenuItem("Defaults", KeyEvent.VK_D);
        defaults.setAccelerator(KeyStroke.getKeyStroke("control D"));
        defaults.addActionListener(this);
        
        tabs.add(placement);
        tabs.add(layout);
        tabs.addSeparator();
        tabs.add(defaults);
        
        //HELP
        JMenu help = new JMenu("Help");
        help.setMnemonic(KeyEvent.VK_H);
        JMenuItem about = new JMenuItem ("About...", KeyEvent.VK_A);
        about.addActionListener(this);
        help.add(about);
        
        //TABBED PANE
        tp = new JTabbedPane();
        tp.setTabPlacement(JTabbedPane.TOP);
		tp.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		
        //MY CONTACT
        ImageIcon myImg = new ImageIcon("nopic.jpg");
        JLabel myImgL = new JLabel(myImg); 
        JLabel myNameL = new JLabel("Name: ", SwingConstants.RIGHT);
        myNameL.setDisplayedMnemonic(KeyEvent.VK_N);
        JLabel myEmailL = new JLabel("Email: ", SwingConstants.RIGHT);
        myEmailL.setDisplayedMnemonic(KeyEvent.VK_E);
        JTextField myName = new JTextField("Danzel" + " " + "Rana", SwingConstants.LEFT);
        JTextField myEmail = new JTextField("danzelrana@gmail.com", SwingConstants.LEFT);
        JPanel myAllInfo = new JPanel(new GridLayout(1,2));
        JPanel myPic = new JPanel();
        myPic.add(myImgL);
        JPanel myCInfo = new JPanel(new GridLayout(2,2));
        myCInfo.add(myNameL);
        myCInfo.add(myName);
        myCInfo.add(myEmailL);
        myCInfo.add(myEmail);
        myAllInfo.add(myPic);
        myAllInfo.add(myCInfo); 
        tp.addTab("Rana" + ", " + "Danzel",myAllInfo);
        
        //OTHER CONTACTS
        File f = new File("contacts.txt");
        Scanner contacts = null; 
        try 
        {
      	 contacts = new Scanner(f);
        }
        catch (FileNotFoundException e) {} 
        if (!f.exists()) 
        {
      	  System.out.println("contacts.txt not found");
      	  System.exit(0);
        }
        contacts.useDelimiter(":|,|\\n");
        
        while (contacts.hasNextLine())
        {
        	String last = contacts.next();
        	String first = contacts.next();
        	first = first.trim();
        	String email = contacts.next();
        	String imgName = contacts.next();
        	imgName = imgName.trim();
        	File imgF = new File(imgName);
        	if (!(imgF.exists())) imgF = new File("nopic.jpg");
            ImageIcon img = new ImageIcon(imgF.getName());
            JLabel imgL = new JLabel(img); 
            JLabel nameL = new JLabel("Name: ", SwingConstants.RIGHT);
            nameL.setDisplayedMnemonic(KeyEvent.VK_N);
            JLabel emailL = new JLabel("Email: ", SwingConstants.RIGHT);
            emailL.setDisplayedMnemonic(KeyEvent.VK_E);
            JTextField name = new JTextField(first + " " + last, SwingConstants.LEFT);
            JTextField cEmail = new JTextField(email, SwingConstants.LEFT);
            JPanel allInfo = new JPanel(new GridLayout(1,2));
            JPanel pic = new JPanel();
            pic.add(imgL);
            JPanel cInfo = new JPanel(new GridLayout(2,2));
            cInfo.add(nameL);
            cInfo.add(name);
            cInfo.add(emailL);
            cInfo.add(cEmail);
            allInfo.add(pic);
            allInfo.add(cInfo); 
            tp.addTab(last + ", " + first,allInfo);
        }
        
        frame.add(tp);
        mb.add(file);
        mb.add(tabs);
        mb.add(help);
        frame.setJMenuBar(mb);
        frame.setVisible(true);
    }
//--------------------------------------------------------------------
    public void actionPerformed(ActionEvent ae)
    {
        switch(ae.getActionCommand())
        {
        	case "Exit":	System.exit(0);
        				 	break;
        	case "Top":		tp.setTabPlacement(JTabbedPane.TOP);
        					break;
        	case "Right":	tp.setTabPlacement(JTabbedPane.RIGHT);
        					break;
        	case "Bottom":	tp.setTabPlacement(JTabbedPane.BOTTOM);
							break;
        	case "Left":	tp.setTabPlacement(JTabbedPane.LEFT);
							break;
        	case "Scroll":	tp.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        					break;
        	case "Wrap":	tp.setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);
        					break;
        	case "Defaults":tp.setTabPlacement(JTabbedPane.TOP);
        					tp.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        					break;
        	case "About...":JOptionPane.showMessageDialog(frame, "<html>Rolodex version 0.1<br>Copyright (c) 2015 D. Rana",
        												  "About...", JOptionPane.INFORMATION_MESSAGE, aImg);
        					break;	
        }
    }
//--------------------------------------------------------------------
} // end class Rolodex
//////////////////////////////////////////////////////////////////////