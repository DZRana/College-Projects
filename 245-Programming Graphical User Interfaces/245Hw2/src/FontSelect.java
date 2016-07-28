// 
//	Name:		Danzel Rana
//	Homework:	2	
//	Due:		10-30-2015
//	Course:		cs-245-01-f15
//	Description:
//				Font Selection using JLists.
//
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
//////////////////////////////////////////////////////////////////////
public class FontSelect implements ActionListener
{
    JList fl;
    DefaultListModel lmf;
    JList sl;
    DefaultListModel lms;
//--------------------------------------------------------------------
    public static void main(String[] args) 
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                new FontSelect();
            }
        });
    }
//--------------------------------------------------------------------
    FontSelect()
    {
        JFrame frame = new JFrame ("Font Select");
        frame.setLayout(new GridLayout(1,3));
        frame.setSize(250,250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JScrollPane fontP;
        JScrollPane selectP;
        JPanel sfp = new JPanel(new GridLayout(2,1));
        JPanel arp = new JPanel(new GridLayout(2,1));
        JPanel selp = new JPanel(new GridLayout(2,1));

        JLabel listFonts = new JLabel("System Fonts:");
        listFonts.setHorizontalAlignment(SwingConstants.CENTER);
        
        GraphicsEnvironment e = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Font[] fonts = e.getAllFonts();
        lmf = new DefaultListModel();
        for(int i = 0; i < fonts.length; i++) lmf.addElement(fonts[i].getFontName());
        fl = new JList(lmf);
        fontP = new JScrollPane(fl);
        
        JButton add = new JButton("Add >>");
        add.setDisplayedMnemonicIndex(0);
        add.addActionListener(this);
           
        JButton remove = new JButton("Print");
        remove.setDisplayedMnemonicIndex(0);
        remove.addActionListener(this);
        
        JLabel listSelect = new JLabel("Selected:");
        listSelect.setHorizontalAlignment(SwingConstants.CENTER);
        
        lms = new DefaultListModel();
        sl = new JList(lms);
        selectP = new JScrollPane(sl);
        
        sfp.add(listFonts);
        sfp.add(fontP);
        frame.add(sfp);
        
        arp.add(add);
        arp.add(remove);
        frame.add(arp);
        
        selp.add(listSelect);
        selp.add(selectP);
        frame.add(selp);
        
        frame.pack();
        frame.setVisible(true);
    }
//--------------------------------------------------------------------
    public void actionPerformed(ActionEvent ae)
    {
    	if(ae.getActionCommand().equals("Add >>")) 
    	{
    		lms.addElement(fl.getSelectedValue());
    		lmf.removeElement(fl.getSelectedValue());
    	}
    	else
    	{
    		for(int i = 0; i < lms.getSize(); i++)
    		{
    			System.out.println(lms.getElementAt(i));
    		}
    	}
    }
//--------------------------------------------------------------------
} // end class FontSelect
//////////////////////////////////////////////////////////////////////
