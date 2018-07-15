// Michael Adams
// TuTh 1pm

// IMPORT PACKAGES FOR GUI COMPONENTS -------------------------------------------------------------------------------------------------------------
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;
import java.util.*;
import java.io.*;
import javax.imageio.*;
import java.awt.image.*;
import java.awt.print.*;
import java.lang.*;
import javax.swing.event.*;
import javax.swing.colorchooser.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.io.File;
import java.text.SimpleDateFormat;
import javax.swing.JFileChooser;

//ASSIG5 CLASS -------------------------------------------------------------------------------------------------------------
public class Assig5
{
	public static String software = "Mosaic Art 1.0";
	private Mosaic m, m1;
	private DrawPanel thePanel; 
	private JPanel buttonPanel;
	private JFrame theWindow;
	private JButton paintIt, eraseIt, editButton;
	private JPopupMenu popup;
	private JMenuItem popItem1, popItem2, item1, item2; 
	
	// ArrayList of Mosaic to store the individual shapes.  Note that
	// since Mosaic is the superclass of both MCircle and MSquare, both
	// shapes can be stored in this ArrayList
	private ArrayList<Mosaic> chunks;
	public double X, Y, C = 0, D = 0, newSize, setNewSize;
	private Color newColor, secondColor, thirdColor;
	private int selected;
	private boolean painting, erasing, editing, s, shapes_off, color_off, c = true, shape_default = true;
	private String currFile, userFile, name;
	private JMenuBar theBar;
	private JMenu fileMenu, effectsMenu,defaultsMenu, setShape;
	private JMenuItem endProgram, saveAs, printScene, square, circle, setSize, setColor;
	private JMenuItem New, Open,Save, twistingColor, twistingShape, stoptwistingShape;
	public int count = 0, loc;
	public boolean fileChanged;
	private boolean t1 = false;
	
	//CONSTRUCTOR -------------------------------------------------------------------------------------------------------------
	public Assig5()
	{
		theWindow = new JFrame(software);
		theWindow.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		//creates a default JPanel 
		thePanel = new DrawPanel(600, 600);
		newSize = 15;
		newColor = Color.RED;

		selected = -1;
		painting = false;
		erasing = false;
		paintIt = new JButton("Paint");
		eraseIt = new JButton("Erase");
		editButton = new JButton("Edit");
		
		//add ActionListener to buttons, add JPanel to bottom JFrame
		ActionListener bListen = new ButtonListener();
		paintIt.addActionListener(bListen);
		eraseIt.addActionListener(bListen);
		editButton.addActionListener(bListen); 
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(1,2));
		buttonPanel.add(paintIt);
		buttonPanel.add(editButton);
		buttonPanel.add(eraseIt);
		
		//add button JPanels to JFrame
		theWindow.add(buttonPanel, BorderLayout.SOUTH);
		theWindow.add(thePanel, BorderLayout.NORTH);
		
		//add menu to file JButton
		theBar = new JMenuBar();
		theWindow.setJMenuBar(theBar);
		fileMenu = new JMenu("File");
		theBar.add(fileMenu);
		New = new JMenuItem("New");
		Open = new JMenuItem("Open");
		Save = new JMenuItem("Save");
		saveAs = new JMenuItem("Save As");
		printScene = new JMenuItem("Print");
		endProgram = new JMenuItem("Exit");
		fileMenu.add(New);
		fileMenu.add(Open);
		fileMenu.add(Save);
		fileMenu.add(saveAs);
		fileMenu.add(printScene);
		fileMenu.add(endProgram);
		saveAs.addActionListener(bListen);
		New.addActionListener(bListen);
		Open.addActionListener(bListen);
		Save.addActionListener(bListen);
		printScene.addActionListener(bListen);
		endProgram.addActionListener(bListen);
		
		//add menu to defaults JButton
		defaultsMenu = new JMenu("Defaults");
		theBar.add(defaultsMenu);
		setColor = new JMenuItem("Set Color"); 
		setSize = new JMenuItem("Set Size"); 
		defaultsMenu.add(setColor);
		defaultsMenu.add(setSize);
		setColor.addActionListener(bListen);
		setSize.addActionListener(bListen);
		setShape = new JMenu("Set Shape");
		circle = new JMenuItem("Circle");
		square = new JMenuItem("Square");
		setShape.add(circle);
		setShape.add(square);
		defaultsMenu.add(setShape); 
		square.addActionListener(bListen);
		circle.addActionListener(bListen);
		
		//add menu to effects JButton
		effectsMenu = new JMenu("Effects");
		theBar.add(effectsMenu);
		twistingShape = new JMenuItem("Start Twisting Shapes");
		twistingColor = new JMenuItem("Start Twisting Colors");
		effectsMenu.add(twistingShape);
		effectsMenu.add(twistingColor);
		twistingColor.addActionListener(bListen);
		twistingShape.addActionListener(bListen);
		stoptwistingShape=new JMenuItem("Stop Twisting Shapes");	
		stoptwistingShape.addActionListener(bListen);
		
		//add popupmenu		
		popup = new JPopupMenu();
		JMenuItem item1 = new JMenuItem("Recolor");
		item1.addActionListener(new MenuActionListener());
		popup.add(item1);
		JMenuItem item2 = new JMenuItem("Resize");
		item2.addActionListener(new MenuActionListener());
		popup.add(item2);
		
		//size JFrame appropriately
		theWindow.pack();
		theWindow.setVisible(true);
	}
	
	//MENUAL CLASS -------------------------------------------------------------------------------------------------------------
	class MenuActionListener implements ActionListener 
	{
		public void actionPerformed(ActionEvent e) 
		{
			if (e.getActionCommand().equals("Recolor"))
			{
				thirdColor = JColorChooser.showDialog(null, "Select Color", thirdColor);
				chunks.get(loc).setColor(thirdColor);
				boolean b = false;
				chunks.get(loc).highlight(b);
				theWindow.repaint();
			}
			else if (e.getActionCommand().equals("Resize"))
			{
				String inter = JOptionPane.showInputDialog(theWindow,"Enter New Size");
				double setNewSize = Double.parseDouble(inter);
				chunks.get(loc).setSize(setNewSize);
				boolean b = false;
				chunks.get(loc).highlight(b);
				theWindow.repaint();
			}
		}
	}	
	
	//DRAWPANEL CLASS -------------------------------------------------------------------------------------------------------------
	private class DrawPanel extends JPanel
	{
		private int prefwid, prefht;
		
		// Initialize the DrawPanel by creating a new ArrayList for the images and a MouseListener to respond to clicks in the panel
		public DrawPanel(int wid, int ht)
		{
			prefwid = wid;
			prefht = ht;
			
			chunks = new ArrayList<Mosaic>();
			
			// Add MouseListener to this JPanel
			// also need a MouseMotionListener to respond to the user dragging the mouse
			addMouseListener(new MListen());
			addMouseMotionListener(new MyMotionListener());
		}
		
		// This method allows a window that encloses this panel to determine
		// how much space the panel needs.  In particular, when the "pack()"
		// method is called from an outer JFrame, this method is called
		// implicitly and the result determines how much space is needed for
		// the JPanel
		public Dimension getPreferredSize()
		{
			return new Dimension(prefwid, prefht);
		}
		
		// This method is responsible for rendering the images within the
		// JPanel.  You should not have to change this code.
		public void paintComponent (Graphics g)       
		{
			super.paintComponent(g);
			Graphics2D g2d = (Graphics2D) g;
			for (int i = 0; i < chunks.size(); i++)
			{
				chunks.get(i).draw(g2d);
			}
		}
		
		// Add a new Mosaic and repaint.  The repaint() method call requests
		// that the panel be redrawn.  Make sure that you call repaint()
		// after changes to your scenes so that the changes are actually
		// exhibited in the display.
		public void add(Mosaic m)
		{
			chunks.add(m);
			repaint();
		}
		
		// Remove the Mosaic at index i and repaint
		public void remove(int i)
		{
			if (chunks.size() > i)
				chunks.remove(i);
			repaint();
		}
		
		// Select a Mosaic that contains the point (x, y).  Note that this
		// is using the contains() method of the Mosaic class, which in turn
		// is checking within the underlying RectangularShape of the object.
		public int select(double x, double y)
		{
			for (int i = 0; i < chunks.size(); i++)
			{
				if (chunks.get(i).contains(x, y))
				{
					return i;
				}
			}
			return -1;
		}
		
		public void resize(double X, double Y)
		{
			System.out.println("now now we here");
			loc = select(X,Y);
			String inter = JOptionPane.showInputDialog(theWindow,"Enter New Size");
			setNewSize = Double.parseDouble(inter); //take size input from user, parse string input into double
			chunks.get(loc).setSize(setNewSize);
			t1 = false;
		}
	}// DrawPanel class
	
	// Save the images within the window to a file.  Run this program to see the 
	// format of the saved file.
	
	//SAVEIMAGES METHOD -------------------------------------------------------------------------------------------------------------
	public void saveImages()
	{
		try
		{
			PrintWriter P = new PrintWriter(new File(currFile));
			P.println(chunks.size());
			for (int i = 0; i < chunks.size(); i++)
			{
				P.println(chunks.get(i).saveFile());
			}
			P.close();
		}
		catch (IOException e)
		{
			JOptionPane.showMessageDialog(theWindow, "I/O Problem - File not Saved");
		}
	}
	
	
	// Listener for some buttons.  Note that the JMenuItems are also listened
	// for here.  Like JButtons, JMenuItems also generate ActionEvents when
	// they are clicked on.  You will need to add more JButtons and JMenuItems
	// to your program and the logic of handling them will also be more
	// complex.  See details in the Assignment 5 specifications.
	
	//BUTTON LISTENER CLASS -------------------------------------------------------------------------------------------------------------
	private class ButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if (e.getSource() == paintIt) //PAINT
			{
				painting = true;
				paintIt.setForeground(Color.RED);
				erasing = false;
				eraseIt.setForeground(Color.BLACK);
				editButton.setForeground(Color.BLACK);
				editing = false;
			}
			else if (e.getSource() == eraseIt) //ERASE
			{
				painting = false;
				paintIt.setForeground(Color.BLACK);
				erasing = true;
				eraseIt.setForeground(Color.RED);
				editButton.setForeground(Color.BLACK);
				editing = false;
			}
			else if (e.getSource() == saveAs) //SAVEAS
			{
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setDialogTitle("Specify a file to save");   
 				int userSelection = fileChooser.showSaveDialog(theWindow);
 
				if (userSelection == JFileChooser.APPROVE_OPTION) 
				{
					File selectedFile = fileChooser.getSelectedFile();
					
					if ((selectedFile != null) && selectedFile.exists()) 
					{
						String[] buttons = { "Yes", "No"};
						int rc = JOptionPane.showOptionDialog(null, "File already exists, overwrite?", "Confirmation",
						JOptionPane.WARNING_MESSAGE, 0, null, buttons, buttons[1]);
					
						if (rc==0)
						{
						currFile=selectedFile.getName();
						saveImages();
						theWindow.setTitle(software + " - " + currFile);
						}
					}
					else
					{
						currFile=selectedFile.getName();
						saveImages();
						theWindow.setTitle(software + " - " + currFile);
					}
				}
				fileChanged = false;
			}
			else if (e.getSource() == Save) //SAVE
			{
				if (currFile == null)
				{ 
					JFileChooser fileChooser = new JFileChooser();
					fileChooser.setDialogTitle("Specify a file to save");   
 					int userSelection = fileChooser.showSaveDialog(theWindow);
 
					if (userSelection == JFileChooser.APPROVE_OPTION) 
					{
						File fileToSave = fileChooser.getSelectedFile();
						System.out.println("Save as file: " + fileToSave.getAbsolutePath());
						currFile=fileToSave.getName();
					}
					saveImages();
					theWindow.setTitle(software + " - " + currFile);
				}
				else
				{
					saveImages();
					theWindow.setTitle(software + " - " + currFile);
				}
				fileChanged = false;
			}
			else if (e.getSource() == Open) //OPEN
			{
				if (currFile == null)
				{
					String[] buttons = { "Yes", "No"};
					int rc = JOptionPane.showOptionDialog(null, "Save File?", "Confirmation",
					JOptionPane.WARNING_MESSAGE, 0, null, buttons, buttons[1]);
					
					if (rc == 0)
					{
						currFile = JOptionPane.showInputDialog(theWindow,"Enter new file name");
						saveImages();
						chunks.clear();
						theWindow.setTitle(software);
						theWindow.repaint();
						
						JFileChooser fileChooser = new JFileChooser();
						int returnValue = fileChooser.showOpenDialog(null);
						if (returnValue == JFileChooser.APPROVE_OPTION) 
						{
							File selectedFile = fileChooser.getSelectedFile();
							userFile=selectedFile.getName();
							theWindow.setTitle(software + " - " + userFile);
							loadFile();
						}						
						fileChanged = false;
					}
					else
					{
						chunks.clear();
						theWindow.setTitle(software);
						theWindow.repaint();
						JFileChooser fileChooser = new JFileChooser();
						int returnValue = fileChooser.showOpenDialog(null);
						if (returnValue == JFileChooser.APPROVE_OPTION) 
						{
							File selectedFile = fileChooser.getSelectedFile();
							userFile=selectedFile.getName();
							theWindow.setTitle(software + " - " + userFile);
							loadFile();
						}
					}
				}
				else if (currFile != null)
				{
					if (fileChanged == false)
					{
						chunks.clear();
						theWindow.setTitle(software);
						theWindow.repaint();
						JFileChooser fileChooser = new JFileChooser();
						int returnValue = fileChooser.showOpenDialog(null);
						if (returnValue == JFileChooser.APPROVE_OPTION) 
						{
							File selectedFile = fileChooser.getSelectedFile();
							userFile=selectedFile.getName();
							theWindow.setTitle(software + " - " + userFile);
							loadFile();
						}
					}
					else if (fileChanged)
					{
						String[] buttons = { "Yes", "No"};
						int rc = JOptionPane.showOptionDialog(null, "Save File?", "Confirmation",
						JOptionPane.WARNING_MESSAGE, 0, null, buttons, buttons[1]);
					
						if (rc == 0)
						{
							currFile = JOptionPane.showInputDialog(theWindow,"Enter new file name");
							saveImages();
							chunks.clear();
							theWindow.setTitle(software);
							theWindow.repaint();
							JFileChooser fileChooser = new JFileChooser();
							int returnValue = fileChooser.showOpenDialog(null);
							if (returnValue == JFileChooser.APPROVE_OPTION) 
							{
								File selectedFile = fileChooser.getSelectedFile();
								userFile=selectedFile.getName();
								theWindow.setTitle(software + " - " + userFile);
								loadFile();
							}
						}
						else
						{
							chunks.clear();
							theWindow.setTitle(software);
							theWindow.repaint();
							JFileChooser fileChooser = new JFileChooser();
							int returnValue = fileChooser.showOpenDialog(null);
							if (returnValue == JFileChooser.APPROVE_OPTION) 
							{
								File selectedFile = fileChooser.getSelectedFile();
								userFile=selectedFile.getName();
								theWindow.setTitle(software + " - " + userFile);
								loadFile();
							}
						}
						fileChanged = false;
					}
				}
				fileChanged = false;
				currFile = null;
			}
			else if (e.getSource() == New) //NEW
			{
				if (currFile == null)
				{
					String[] buttons = { "Yes", "No"};
					int rc = JOptionPane.showOptionDialog(null, "Save File?", "Confirmation",
					JOptionPane.WARNING_MESSAGE, 0, null, buttons, buttons[1]);
					
					if (rc == 0)
					{
						currFile = JOptionPane.showInputDialog(theWindow,"Enter new file name");
						saveImages();
						chunks.clear();
						theWindow.setTitle(software);
						theWindow.repaint();
						fileChanged = false;
					}
					else
					{
						chunks.clear();
						theWindow.repaint();
					}
				}
				else if (currFile != null)
				{
					if (fileChanged == false)
					{
						chunks.clear();
						theWindow.repaint();
						theWindow.setTitle(software);
					}
					else if (fileChanged)
					{
						String[] buttons = { "Yes", "No"};
						int rc = JOptionPane.showOptionDialog(null, "Save File?", "Confirmation",
						JOptionPane.WARNING_MESSAGE, 0, null, buttons, buttons[1]);
					
						if (rc == 0)
						{
							currFile = JOptionPane.showInputDialog(theWindow,"Enter new file name");
							saveImages();
							chunks.clear();
							theWindow.setTitle(software);
							theWindow.repaint();
						}
						else
						{
							chunks.clear();
							theWindow.setTitle(software);
							theWindow.repaint();
						}
						fileChanged = false;
					}
				}
				currFile = null;
			}
			else if (e.getSource() == endProgram) //ENDPROGRAM
			{
				if (currFile == null && fileChanged)
				{
					String[] buttons = { "Yes", "No"};
					int rc = JOptionPane.showOptionDialog(null, "Save File?", "Confirmation",
					JOptionPane.WARNING_MESSAGE, 0, null, buttons, buttons[1]);
					
					if (rc == 0)
					{
						currFile = JOptionPane.showInputDialog(theWindow,"Enter new file name");
						saveImages();
					}
				}
				else if (currFile != null)
				{
					if (fileChanged == false)
					{
					}
					else if (fileChanged)
					{
						String[] buttons = { "Yes", "No"};
						int rc = JOptionPane.showOptionDialog(null, "Save File?", "Confirmation",
						JOptionPane.WARNING_MESSAGE, 0, null, buttons, buttons[1]);
					
						if (rc == 0)
						{
							currFile = JOptionPane.showInputDialog(theWindow,"Enter new file name");
							saveImages();							
						}
						else
						{
						}
					}
				}
				System.exit(0);
			}
			else if (e.getSource() == printScene) //PRINTSCENE
			{
				 Printable thePPanel = new thePrintPanel(thePanel); 
			     PrinterJob job = PrinterJob.getPrinterJob();
         		 job.setPrintable(thePPanel);
         		 boolean ok = job.printDialog();
         		 if (ok) 
         		 {
             	 	try {
                  		job.print();
             		} 
             		catch (PrinterException ex) {
              		// The job did not successfully complete
             		}
             	 }
        	}
			else if(e.getSource() == square) //SQUARE
			{
				s = true; //square
				c = false; //circle
				shape_default = false;
				c = false; 
				s = false;
				if (shapes_off)				
				{
					twistingShape.setText("Start Twisting Shape");
					shapes_off = false;
					JOptionPane.showMessageDialog(theWindow, "Twisting Shapes was turned off");
				}
			}
			else if (e.getSource() == circle) //CIRCLE
			{
				s = false;
				c = true;
				shape_default = true;
				c = false; 
				s = false;
				if (shapes_off)				
				{
					twistingShape.setText("Start Twisting Shape");
					shapes_off=false;
					JOptionPane.showMessageDialog(theWindow, "Twisting Shapes was turned off");
				}				
			}
			else if (e.getSource() == setColor) //SETCOLOR
			{
				newColor = JColorChooser.showDialog(null, "Select Color", newColor);
				if (color_off)				
				{
					color_off=false;
					twistingColor.setText("Start Twisting Color");
					JOptionPane.showMessageDialog(theWindow, "Twisting Colors was turned off");
				}
			}
			else if (e.getSource() == setSize) //SETSIZE
			{
				String inter = JOptionPane.showInputDialog(theWindow,"Enter New Default Size");
				newSize = Double.parseDouble(inter);
			}
			else if (e.getSource() == editButton) //EDIT
			{
				editing = true;
				painting = false;
				editButton.setForeground(Color.RED);
				erasing = false;
				eraseIt.setForeground(Color.BLACK);
				paintIt.setForeground(Color.BLACK);
			}
			else if (e.getSource() == twistingShape && shapes_off == false) //START COLOR TWIST
			{
				c = true; 
				s = true;
				twistingShape.setText("Stop Twisting Shape");
				shapes_off = true;
			}
			else if (e.getSource() == twistingShape && shapes_off == true) //STOP SHAPE TWIST
			{
				c = false; 
				s = false;
				twistingShape.setText("Start Twisting Shape");
				shapes_off = false;
				
			}
			else if (e.getSource() == stoptwistingShape) //STOP SHAPE TWIST
			{
				s = false;
			}
			else if (e.getSource() == twistingColor && color_off == false) //START SHAPE TWIST
			{
				
				twistingColor.setText("Stop Twisting Colors");
				color_off = true;
				secondColor = JColorChooser.showDialog(null, "Select Second Color",secondColor);	
			}
			else if (e.getSource() == twistingColor && color_off == true)//STOP COLOR TWIST
			{
				twistingColor.setText("Start Twisting Colors");
				color_off = false;
			}
		}
	}
	
	//LOAD FILE METHOD -------------------------------------------------------------------------------------------------------------
	public void loadFile()
	{
		int [] xpts, ypts;
		int comma;
		int r, g, b;
		try
		{
			Scanner inFile = new Scanner(new File(userFile));
			int numShape = Integer.parseInt(inFile.nextLine());
			for (int i = 0; i < numShape; i++)//read in
			{
				String [] big = inFile.nextLine().split(",");
				String SH=big[0];
				double SIZE=Double.parseDouble(big[1]);
				double x_location=Double.parseDouble(big[2]);
				double y_location=Double.parseDouble(big[3]);
				String [] small= big[4].split(":");
				 r=Integer.parseInt(small[0]);
				 g=Integer.parseInt(small[1]);
				 b=Integer.parseInt(small[2]);				
			
				Color tempColor = new Color(r,g,b);
				if (SH.equals("Circle"))//if circle
				{
					m = new MCircle(SIZE, x_location, y_location, tempColor);
				}
				else if (SH.equals("Square"))//if square
				{
					m = new MSquare(SIZE, x_location, y_location, tempColor);
				}
				thePanel.add(m);
				theWindow.repaint();
			}
		
		inFile.close();
		}
		catch (IOException e)
		{
			JOptionPane.showMessageDialog(theWindow, "I/O Problem - File Doesn't Exist");
		}
	}
	
	// Simple mouse event handling to allow a mousePressed to add or remove
	// a Mosaic from the display.  You will need to enhance this
	// MouseAdapter and you will also need to add a MouseMotionListener to
	// your program.  In this simple program all of the Mosaics drawn are
	// MCircles and they all have the same size and color.  You must add in
	// your program the ability to change all of these attributes.
	//MLISTEN CLASS -------------------------------------------------------------------------------------------------------------
	private class MListen extends MouseAdapter
	{
		public void mousePressed(MouseEvent e)
		{
			X = e.getX();  //mouse was pressed location
			Y = e.getY();
			
			if (painting) //add Mosaic
			{
				// create new MCircle and add it to the ArrayList
				if (c && s && !color_off) //alternating shapes, same color
				{
					if (count%2 == 0)//if divisible by 2 (every other shape)
					{
						m = new MCircle(newSize, X, Y, newColor);
						thePanel.add(m);
					}
					else
					{	
						m = new MSquare(newSize,X,Y,newColor);
						thePanel.add(m);
					}
					count++;//track number of shapes
				}
				else if (!shape_default && !color_off) //square, same color
				{
					m = new MSquare(newSize,X,Y,newColor);
					thePanel.add(m);
				}
				else if (shape_default && !color_off) //circle, same color
				{
					m = new MCircle(newSize, X, Y, newColor);
					thePanel.add(m);
				}
				else if (c && s && color_off) //alternating shapes, alternating color
				{
					if (count%2 == 0)
					{
						m = new MCircle(newSize, X, Y, newColor);
						thePanel.add(m);
					}
					else
					{	
						m = new MSquare(newSize,X,Y,secondColor);
						thePanel.add(m);
					}
					count++;
				}
				else if (shape_default && color_off) //circle, alternating color
				{
					if (count%2 == 0)
					{
						m = new MCircle(newSize, X, Y, newColor);
					}
					else 
					{
						m = new MCircle(newSize, X, Y, secondColor);
					}
					thePanel.add(m);
					count++;
				}
				else if (!shape_default && color_off)//square, alternating color
				{
					if (count%2 == 0)
					{
						m = new MSquare(newSize, X, Y, newColor);
					}
					else 
					{
						m = new MSquare(newSize, X, Y, secondColor);
					}
					thePanel.add(m);
					count++;
				}
				
				fileChanged = true;
			}
			else if (erasing) //delete Mosaic
			{
				// check point for shape, then delete if present
				int loc = thePanel.select(X, Y);
				if (loc > -1)
				{
					thePanel.remove(loc);
				}
				
				fileChanged = true;
			}	
			
			
		}
		
		public void mouseClicked(MouseEvent e)
		{
			if (editing && SwingUtilities.isRightMouseButton(e))
			{
				X = e.getX();  // Get mouse pressed location
				Y = e.getY();
				
				loc = thePanel.select(X, Y);
				if (loc > -1)
				{
					boolean b = true;
					chunks.get(loc).highlight(b);
					theWindow.repaint();
					popup.show(e.getComponent(), e.getX(), e.getY());
					
					if (t1)
					{
						thePanel.resize(X, Y);
					}
				}
			}
		}
	}	

	//MYMOTIONLISTENER CLASS -------------------------------------------------------------------------------------------------------------
	private class MyMotionListener implements MouseMotionListener
    {
		public void mouseMoved(MouseEvent e) //gets location when mouseMoved
        {
            double X = e.getX();
            double Y = e.getY();
        }	  
		
		public void mouseDragged(MouseEvent e)
        {			
			if (painting)
			{
				double A = e.getX();  // Get the location where mouse was pressed
				double B = e.getY();
			
				if (Math.sqrt(Math.pow(C-A,2)+Math.pow(D-B,2))>=newSize)//distance formula
				{
					if (c && s && !color_off)//alternating shapes not color
					{
						if (count%2==0)
						{
							m = new MCircle(newSize, A, B, newColor);
							thePanel.add(m);
						}
						else
						{	
							m=new MSquare(newSize,A,B,newColor);
							thePanel.add(m);
						}
					count++;
					}
					else if (!shape_default && !color_off)// square no color twisting
					{
						m=new MSquare(newSize,A,B,newColor);
						thePanel.add(m);
					}
					else if (shape_default && !color_off)//circle no color twisting
					{
						m = new MCircle(newSize, A, B, newColor);
						thePanel.add(m);
					}
					else if (c && s && color_off)//alternating shapes and color
					{
						if (count%2==0)
						{
							m = new MCircle(newSize, A, B, newColor);
							thePanel.add(m);
						}
						else
						{	
						m=new MSquare(newSize,A,B,secondColor);
						thePanel.add(m);
						}
					count++;
					}
					else if (shape_default && color_off)//circle alternating color
					{
						if (count%2==0)
						{
						m = new MCircle(newSize, A, B, newColor);
						}
						else 
						{
						m=new MCircle(newSize, A, B, secondColor);
						}
						thePanel.add(m);
						count++;
					}
					else if (!shape_default && color_off)//square alternating color
					{
						if (count%2==0)
						{
						m = new MSquare(newSize, A, B, newColor);
						}
						else 
						{
						m=new MSquare(newSize, A, B, secondColor);
						}
						thePanel.add(m);
						count++;
					}		
					
					C = A;//keep last mouse position to compare to new mouse position 
					D = B;
				}
				
				fileChanged = true;
			} //if painting
			
			if (erasing)
			{
				double A = e.getX();  // Get the location where mouse was pressed
				double B = e.getY();
				
				int loc = thePanel.select(A, B);
				if (loc > -1)
				{
					thePanel.remove(loc);
				}	
				
				fileChanged = true;
			}
			
			if (editing)
			{
				double A1 = e.getX();  // Get the location where mouse was pressed
				double B1 = e.getY();
				
				int loc = thePanel.select(X, Y);
				if (loc > -1)
				{
					m1 = chunks.get(loc);
					m1.move(A1-X,B1-Y);
					thePanel.add(m1);
				}
			
				X = A1;
				Y = B1;
				
				fileChanged = true;
			}
		}
	}
	
    //MAIN METHOD -------------------------------------------------------------------------------------------------------------
	public static void main(String [] args)
	{
		new Assig5();
	}
}

// This class is taken from the Web and is somewhat buggy but it does a basic print of the panel
class thePrintPanel implements Printable
{
	JPanel panelToPrint;
	
	public int print(Graphics g, PageFormat pf, int page) throws
                                                        PrinterException
    {
        if (page > 0) { /* We have only one page, and 'page' is zero-based */
            return NO_SUCH_PAGE;
        }

        /* User (0,0) is typically outside the imageable area, so we must
         * translate by the X and Y values in the PageFormat to avoid clipping
         */
        Graphics2D g2d = (Graphics2D)g;
        AffineTransform t = new AffineTransform();
        t.scale(0.9, 0.9);
        g2d.transform(t);
        g2d.translate(pf.getImageableX(), pf.getImageableY());
		//pf.setOrientation(PageFormat.REVERSE_LANDSCAPE);
        /* Now print the window and its visible contents */
        panelToPrint.printAll(g);

        /* tell the caller that this page is part of the printed document */
        return PAGE_EXISTS;
    }
    
    public thePrintPanel(JPanel p)
    {
    	panelToPrint = p;
    }
}

 

 