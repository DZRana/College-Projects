// 
//	Name:		Danzel Rana
//	Project:	1	
//	Due:		10-14-2015
//	Course:		cs-245-01-f15
//	Description:
//				A simple stopwatch.
//
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
//////////////////////////////////////////////////////////////////////
public class StopWatch implements ActionListener
{
	JLabel jLab;
	long start;
//--------------------------------------------------------------------
	public static void main(String[] args) 
    {
		javax.swing.SwingUtilities.invokeLater(new Runnable()
        {
			public void run()
            {
				new StopWatch();
            }
        });
    }
//--------------------------------------------------------------------    
    StopWatch()
    {
    	JFrame frame = new JFrame("D. Rana's Stopwatch");
        frame.setLayout(new FlowLayout());
        frame.setSize(230,90);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JButton startB = new JButton("Start");
        startB.addActionListener(this);
        frame.add(startB);
     
        JButton stopB = new JButton("Stop");
        stopB.addActionListener(this);
        frame.add(stopB);
        
        jLab = new JLabel("Press Start to begin timing.");
        frame.add(jLab);

        frame.setVisible(true);
    }
//--------------------------------------------------------------------      
    public void actionPerformed(ActionEvent ae)
    {
    	if (ae.getActionCommand().equals("Start")) 
    	{
    		start = ae.getWhen();
    		jLab.setText("Stopwatch is Running...");
    	}
    	else jLab.setText("Elapsed time is " + (ae.getWhen() - start) 
    					  + "ms");
    } 
//--------------------------------------------------------------------
} // end class StopWatch
//////////////////////////////////////////////////////////////////////
