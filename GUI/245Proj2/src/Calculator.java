// 
//	Name:		Danzel Rana
//	Project:	2	
//	Due:		10-30-2015
//	Course:	cs-245-01-f15
//	Description:
//				A simple integer calculator. 
//
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
//////////////////////////////////////////////////////////////////////
public class Calculator implements ActionListener
{
//--------------------------------------------------------------------
	JLabel displayLabel;
   int firstNum = 0;
   boolean flag = false;
   String firstOp = "";
   public static void main(String[] args) 
   {
   	SwingUtilities.invokeLater(new Runnable()
      {
   		public void run()
         {
   			new Calculator();
         }
      });
   }
//--------------------------------------------------------------------   
   Calculator()
   {
   	JFrame frame = new JFrame("Calculator");
      frame.setLayout(new GridLayout(2,1));
      frame.setSize(300,400);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
      Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
      frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
      
      ImageIcon img = new ImageIcon("Calculator.png");
      frame.setIconImage(img.getImage());
      
      JRootPane rp = frame.getRootPane();
        
      JPanel display = new JPanel(new BorderLayout());
      display.setBorder(BorderFactory.createLineBorder(Color.red,4));
        
      displayLabel = new JLabel("0");
      displayLabel.setHorizontalAlignment(SwingConstants.RIGHT);
      display.add(displayLabel);
               
      JPanel keyPanel = new JPanel(new GridLayout(4,4));
      String[] keyNums = {"7","8","9","/","4","5","6","*",
                          "1","2","3","-","0","c","=","+"};
        
      int keyNDX = 0;
      for (int i = 0; i < 4; i++)
      {
      	for (int j = 0; j < 4; j++)
         {
      		JButton key = new JButton(keyNums[keyNDX]);
            key.addActionListener(this);
            if(keyNums[keyNDX].equals("=")) rp.setDefaultButton(key);
            if(keyNums[keyNDX].equals("c")) key.setDisplayedMnemonicIndex(0);
            keyPanel.add(key);
            keyNDX++;
         }
      }
        
      frame.add(display);
      frame.add(keyPanel);

      frame.setVisible(true);
   }
//--------------------------------------------------------------------
   public void actionPerformed(ActionEvent ae)
   {
   	if(displayLabel.getText().equals("(c) 2015 Danzel Rana") && ae.getActionCommand().equals("c"))
   		displayLabel.setText("0");
   	if (((ae.getModifiers() & InputEvent.CTRL_MASK) != 0) && ae.getActionCommand().equals("c")) 
   		displayLabel.setText("(c) 2015 Danzel Rana");
   	else 
   	{
   		if(flag == false)
   		{
   			if (!(ae.getActionCommand().equals("+")) &&
                !(ae.getActionCommand().equals("-")) &&
                !(ae.getActionCommand().equals("*")) &&
                !(ae.getActionCommand().equals("/")) &&
                !(ae.getActionCommand().equals("=")) &&
                !(ae.getActionCommand().equals("c")))
   			{
   				displayLabel.setText(ae.getActionCommand());
   				flag = true;
   			}
   		}
   		else 
   		{
   			switch(ae.getActionCommand())
   			{
            	case "+":	firstNum = Integer.parseInt(displayLabel.getText());
              					firstOp = "+";
              					flag = false;
              					break;
              	case "-":   firstNum = Integer.parseInt(displayLabel.getText());
                				firstOp = "-";
                				flag = false;
                				break;
              	case "*":   firstNum = Integer.parseInt(displayLabel.getText());
    				 				firstOp = "*";
    				 				flag = false;
    				 				break;
              	case "/":   firstNum = Integer.parseInt(displayLabel.getText());
    				 				firstOp = "/";
    				 				flag = false;
    				 				break;
              	case "=":	try
              		         {
              						int secNum = Integer.parseInt(displayLabel.getText());
              						displayLabel.setText("");
              						switch(firstOp)
              						{
               	 			 		case "+":	displayLabel.setText(String.valueOf(firstNum + secNum));
               	 			 						break;
               	 			 		case "-":	displayLabel.setText(String.valueOf(firstNum - secNum));
               	 			 	 					break;
               	 			 		case "*":	displayLabel.setText(String.valueOf(firstNum * secNum));
               	 			 	 					break;
               	 			 		case "/":	displayLabel.setText(String.valueOf(firstNum / secNum));
               	 			 	 					break;
               	 			 		default:	break;
              						}
              		         }
              					catch (Exception e) 
              					{
              						displayLabel.setText(e.toString());
              					}
               	 			break;
               	 			 	
              	case "c":	displayLabel.setText("0");
              					flag = false;
              					break;
              	default:    displayLabel.setText(displayLabel.getText() 
                                                + ae.getActionCommand());
                           break;
   			}
   		}
   	}
   }
//--------------------------------------------------------------------
} // end class Calculator
//////////////////////////////////////////////////////////////////////