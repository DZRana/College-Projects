// 
//	Name:		Danzel Rana
//	Project:	4	
//	Due:		12-04-2015
//	Course:		cs-245-01-f15
//	Description:
//				Simple Notepad.
//
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.border.*;
import javax.swing.text.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.*;

public class JNotepad extends JFrame
{

	private JPanel contentPane;
	String cutOrCopyS = "";
	JTextArea jta;
	JCheckBoxMenuItem chckbxmntmStatusBar;
	JScrollPane scrollPane;
	int pos;
	String find;
	int findLength;
	static JNotepad frame;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					frame = new JNotepad();
					frame.setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public JNotepad()
	{
		File checkImg = new File("JNotepad.png");
		if (checkImg.exists()){
			ImageIcon img = new ImageIcon("JNotepad.png");
	      frame.setIconImage(img.getImage());
		}
		setLocationByPlatform(true);
		setTitle("Untitled - JNotepad");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		jta = new JTextArea();
		jta.setFont(new Font("Courier New", Font.PLAIN, 12));
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		mnFile.setMnemonic(KeyEvent.VK_F);
		menuBar.add(mnFile);
		
		JMenuItem mntmNew = new JMenuItem("New");
		mntmNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jta.setText("");
				frame.setTitle("Untitled - JNotepad");
			}
		});
		mntmNew.setMnemonic('N');
		mntmNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
		mnFile.add(mntmNew);
		
		JMenuItem mntmOpen = new JMenuItem("Open...");
		mntmOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc= new JFileChooser();
	        	if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
	        	{
	        		jta.setText("");
	        		try
	        		{
	        			Scanner scan = new Scanner(new FileReader(fc.getSelectedFile().getPath()));
	        			while(scan.hasNext()) jta.append(scan.nextLine() + "\n");
	        			frame.setTitle(fc.getSelectedFile().getName());
	        		}
	        		catch(Exception ex){
	        			ex.printStackTrace();
	        		}
	        	}
			}
		});
		mntmOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
		mnFile.add(mntmOpen);
		
		JMenuItem mntmSave = new JMenuItem("Save");
		mntmSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (frame.getTitle().equals("Untitled - JNotepad")){
					JFileChooser fc= new JFileChooser();
					fc.setDialogTitle("Save As");
					int selected = fc.showSaveDialog(frame);
					if (selected == JFileChooser.APPROVE_OPTION){
						try{
							File fileToSave = fc.getSelectedFile();
							FileWriter fw = new FileWriter(fileToSave);
							jta.write(fw);
							frame.setTitle(fileToSave.getName());
						}
						catch(Exception ex){
							ex.printStackTrace();
						}
					}
				}
				else{
					try{
						File currentFile = new File(frame.getTitle());
						FileWriter fw = new FileWriter(currentFile);
						jta.write(fw);
					}
					catch(Exception ex){
						ex.printStackTrace();
					}
				}
			}
		});
		mntmSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		mnFile.add(mntmSave);
		
		JMenuItem mntmSaveAs = new JMenuItem("Save As...");
		mntmSaveAs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc= new JFileChooser();
				fc.setDialogTitle("Save As");
				int selected = fc.showSaveDialog(frame);
				if (selected == JFileChooser.APPROVE_OPTION){
					try{
						File fileToSave = fc.getSelectedFile();
						FileWriter fw = new FileWriter(fileToSave);
						jta.write(fw);
						frame.setTitle(fileToSave.getName());
					}
					catch(Exception ex){
						ex.printStackTrace();
					}
				}
			}
		});
		mnFile.add(mntmSaveAs);
		
		JSeparator separator = new JSeparator();
		mnFile.add(separator);
		
		JMenuItem mntmPageSetup = new JMenuItem("Page Setup...");
		mntmPageSetup.setMnemonic('u');
		mnFile.add(mntmPageSetup);
		
		JMenuItem mntmPrint = new JMenuItem("Print...");
		mntmPrint.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_MASK));
		mnFile.add(mntmPrint);
		
		JSeparator separator_1 = new JSeparator();
		mnFile.add(separator_1);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.setMnemonic(KeyEvent.VK_X);
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		mnFile.add(mntmExit);
		
		JMenu mnEdit = new JMenu("Edit");
		mnEdit.setMnemonic(KeyEvent.VK_E);
		menuBar.add(mnEdit);
		
		JMenuItem mntmUndo = new JMenuItem("Undo");
		mnEdit.add(mntmUndo);
		
		JSeparator separator_2 = new JSeparator();
		mnEdit.add(separator_2);
		
		JMenuItem mntmCut = new JMenuItem("Cut");
		mntmCut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cutOrCopyS = jta.getSelectedText();
				jta.replaceRange("", jta.getSelectionStart(), jta.getSelectionEnd());
			}
		});
		mntmCut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_MASK));
		mnEdit.add(mntmCut);
		
		JMenuItem mntmCopy = new JMenuItem("Copy");
		mntmCopy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cutOrCopyS = jta.getSelectedText();
			}
		});
		mntmCopy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
		mnEdit.add(mntmCopy);
		
		JMenuItem mntmPaste = new JMenuItem("Paste");
		mntmPaste.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jta.replaceRange(cutOrCopyS, jta.getSelectionStart(), jta.getSelectionEnd());
			}
		});
		mntmPaste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_MASK));
		mnEdit.add(mntmPaste);
		
		JMenuItem mntmDelete = new JMenuItem("Delete");
		mntmDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jta.replaceRange("", jta.getSelectionStart(), jta.getSelectionEnd());
			}
		});
		mntmDelete.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0));
		mnEdit.add(mntmDelete);
		
		JSeparator separator_3 = new JSeparator();
		mnEdit.add(separator_3);
		
		JMenuItem mntmFind = new JMenuItem("Find...");
		mntmFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {		
				final JDialog dialog = new JDialog(frame, "Find");
				dialog.getContentPane().setLayout(new FlowLayout());
				dialog.setBounds(200,200,300,200);
				JLabel fText = new JLabel("Find what: ");
				dialog.getContentPane().add(fText);
				final JTextField jtf = new JTextField(10);
				dialog.getContentPane().add(jtf);
				JButton okay = new JButton("Okay");
				okay.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e){
						boolean found = false;
						find = jtf.getText().toLowerCase();
						findLength = find.length();
						Document doc = jta.getDocument();
						pos = 0;
						try{
							if(pos + findLength > doc.getLength()){
								pos = 0;
							}
							while (pos + findLength <= doc.getLength()){
								String match = doc.getText(pos, findLength).toLowerCase();
								if(match.equals(find)){
									found = true;
									break;
								}
								pos++;
							}
							if (found){
								Rectangle viewRect = jta.modelToView(pos);
								jta.scrollRectToVisible(viewRect);
								jta.setCaretPosition(pos + findLength);
								jta.moveCaretPosition(pos);
								pos += findLength;
							}
							dialog.dispose();
						}
						catch(Exception ex){
							ex.printStackTrace();
						}
					}
				});
				dialog.getContentPane().add(okay);
				dialog.setVisible(true);		
			}
		});
		mntmFind.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.CTRL_MASK));
		mnEdit.add(mntmFind);
		
		JMenuItem mntmFindNext = new JMenuItem("Find Next");
		mntmFindNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean found = false;
				Document doc = jta.getDocument();
				int findLength = find.length();
				try{
					if(pos +findLength > doc.getLength()){
						pos = 0;
					}
					while (pos + findLength <=doc.getLength()){
						String match = doc.getText(pos, findLength).toLowerCase();
						if(match.equals(find)){
							found = true;
							break;
						}
						pos++;
					}
					if (found){
						Rectangle viewRect = jta.modelToView(pos);
						jta.scrollRectToVisible(viewRect);
						jta.setCaretPosition(pos + findLength);
						jta.moveCaretPosition(pos);
						pos += findLength;
					}
				}
				catch(Exception ex){
					ex.printStackTrace();
				}
			}
		});
		mnEdit.add(mntmFindNext);
		
		JMenuItem mntmReplace = new JMenuItem("Replace...");
		mntmReplace.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, InputEvent.CTRL_MASK));
		mnEdit.add(mntmReplace);
		
		JMenuItem mntmGoTo = new JMenuItem("Go To...");
		mntmGoTo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, InputEvent.CTRL_MASK));
		mnEdit.add(mntmGoTo);
		
		JSeparator separator_4 = new JSeparator();
		mnEdit.add(separator_4);
		
		JMenuItem mntmSelectAll = new JMenuItem("Select All");
		mntmSelectAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jta.selectAll();
			}
		});
		mntmSelectAll.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK));
		mnEdit.add(mntmSelectAll);
		
		JMenuItem mntmTimedate = new JMenuItem("Time/Date");
		mntmTimedate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Date date = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("h:mm a MM/dd/yyyy");
				String dateF = sdf.format(date);
				jta.replaceRange(dateF, jta.getSelectionStart(), jta.getSelectionEnd());
			}
		});
		mntmTimedate.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0));
		mnEdit.add(mntmTimedate);
		
		JMenu mnFormat = new JMenu("Format");
		mnFormat.setMnemonic(KeyEvent.VK_O);
		menuBar.add(mnFormat);
		
		final JCheckBoxMenuItem chckbxmntmWordWrap = new JCheckBoxMenuItem("Word Wrap");
		chckbxmntmWordWrap.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e){	
				if(chckbxmntmWordWrap.isSelected()){
					chckbxmntmStatusBar.setSelected(false);
					chckbxmntmStatusBar.setEnabled(false);
					jta.setLineWrap(true);
					scrollPane.validate();
				}
				else{
					chckbxmntmStatusBar.setEnabled(true);
					jta.setLineWrap(false);
					scrollPane.validate();
				}
			}
		});
		chckbxmntmWordWrap.setMnemonic(KeyEvent.VK_W);
		mnFormat.add(chckbxmntmWordWrap);
		
		JMenuItem mntmFont = new JMenuItem("Font...");
		mntmFont.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				final JDialog dialog = new JDialog(frame, "Font");	
				dialog.getContentPane().setLayout(new GridLayout(1,4));
				dialog.setBounds(500,500,500,350);
				
				JPanel fontP = new JPanel();
				fontP.setLayout(new GridLayout(3,1));
				JLabel f = new JLabel("Font:");
				fontP.add(f);
				JTextField fSelected = new JTextField();
				fontP.add(fSelected);
				GraphicsEnvironment e = GraphicsEnvironment.getLocalGraphicsEnvironment();
				String[] fonts = e.getAvailableFontFamilyNames();
				DefaultListModel lmf = new DefaultListModel();
				for (int i = 0; i < fonts.length; i++) lmf.addElement(fonts[i]);
				JList fontList = new JList(lmf);
				JScrollPane fp = new JScrollPane(fontList);
				fontP.add(fp);
				dialog.add(fontP);
				
				JPanel fontStyleP = new JPanel();
				fontStyleP.setLayout(new GridLayout(3,1));
				JLabel fs = new JLabel("Font style:");
				fontStyleP.add(fs);
				JTextField fsSelected = new JTextField();
				fontStyleP.add(fsSelected);
				DefaultListModel lmfStyle = new DefaultListModel();
				lmfStyle.addElement("Regular");
				lmfStyle.addElement("Bold");
				lmfStyle.addElement("Italic");
				lmfStyle.addElement("Bold Italic");
				JList fontStyleList = new JList(lmfStyle);
				JScrollPane fsp = new JScrollPane(fontStyleList);
				fontStyleP.add(fsp);
				dialog.add(fontStyleP);
				
				JPanel fontSizeP = new JPanel();
				fontSizeP.setLayout(new GridLayout(3,1));
				JLabel fsz = new JLabel("Size:");
				fontSizeP.add(fsz);
				JTextField fszSelected = new JTextField();
				fontSizeP.add(fszSelected);
				DefaultListModel lmfSize = new DefaultListModel();
				for (int i = 8; i <= 11; i++) lmfSize.addElement((Integer) i);
				for (int i = 12; i <= 28; i +=2) lmfSize.addElement((Integer) i);
				lmfSize.addElement((Integer) 36);
				lmfSize.addElement((Integer) 48);
				lmfSize.addElement((Integer) 72);
				JList fontSizeList = new JList(lmfSize);
				JScrollPane fszp = new JScrollPane(fontSizeList);
				fontSizeP.add(fszp);
				dialog.add(fontSizeP);
				
				
				
				JPanel okc = new JPanel(new FlowLayout());
				JButton ok = new JButton("OK");
				ok.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e){
						dialog.dispose();
					}
				});
				okc.add(ok);
				JButton cancel = new JButton("Cancel");
				cancel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e){
						dialog.dispose();
					}
				});
				okc.add(cancel);
				dialog.add(okc);
				dialog.setVisible(true);
			}
		});
		mntmFont.setMnemonic(KeyEvent.VK_F);
		mnFormat.add(mntmFont);
		
		JMenu mnView = new JMenu("View");
		mnView.setMnemonic(KeyEvent.VK_V);
		menuBar.add(mnView);
		
		chckbxmntmStatusBar = new JCheckBoxMenuItem("Status Bar");
		chckbxmntmStatusBar.setMnemonic(KeyEvent.VK_S);
		mnView.add(chckbxmntmStatusBar);
		
		JMenu mnHelp = new JMenu("Help");
		mnHelp.setMnemonic(KeyEvent.VK_H);
		menuBar.add(mnHelp);
		
		JMenuItem mntmViewHelp = new JMenuItem("View Help");
		mntmViewHelp.setMnemonic(KeyEvent.VK_H);
		mnHelp.add(mntmViewHelp);
		
		JSeparator separator_5 = new JSeparator();
		mnHelp.add(separator_5);
		
		JMenuItem mntmAboutJnotepad = new JMenuItem("About JNotepad");
		mntmAboutJnotepad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(frame, "(c) Danzel Rana", "About JNotepad", JOptionPane.PLAIN_MESSAGE);
			}
		});
		mnHelp.add(mntmAboutJnotepad);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		scrollPane.setViewportView(jta);
		
		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(jta, popupMenu);
		
		JMenuItem mntmPUCut = new JMenuItem("Cut");
		mntmPUCut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cutOrCopyS = jta.getSelectedText();
				jta.replaceRange("", jta.getSelectionStart(), jta.getSelectionEnd());
			}
		});
		popupMenu.add(mntmPUCut);
		
		JMenuItem mntmPUCopy = new JMenuItem("Copy");
		mntmPUCopy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cutOrCopyS = jta.getSelectedText();
			}
		});
		popupMenu.add(mntmPUCopy);
		
		JMenuItem mntmPUPaste = new JMenuItem("Paste");
		mntmPUPaste.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jta.replaceRange(cutOrCopyS, jta.getSelectionStart(), jta.getSelectionEnd());
			}
		});
		popupMenu.add(mntmPUPaste);
	}

	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}