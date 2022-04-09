import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

public class Demo {

	private static int xPosClicked;
	private static int yPosClicked;
	private static ArrayList<JLabel> pastMarks= new ArrayList<JLabel>();
	private static int clickCount=0;
	private static int score=0;
	private static int callibratedCoordinate=10;
	private static boolean hasClicked=false;
	//static JButton clickedPoint = new JButton();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//frame of the gui
		JFrame frame = new JFrame();
		//displeys last click
		JLabel currentClickedPoint = new JLabel();
		//grid/board of the game
		JLabel background = new JLabel();
		//labels the direction of button
		JLabel xCoordinateIndicator = new JLabel();
		JLabel yCoordinateIndicator = new JLabel();
		JLabel xCoordinateStrengthIndicator = new JLabel();
		JLabel yCoordinateStrengthIndicator = new JLabel();
		JLabel scoreboard = new JLabel();
		JLabel printingButtonCoordinates = new JLabel();
		JLabel printingMouseCoordinates = new JLabel();
		JLabel printingDiffInCoordinates = new JLabel();
		JLabel indicator = new JLabel();
		JLabel colorScheme = new JLabel();
		JLabel colorSchemeFarthestText = new JLabel();
		JLabel colorSchemeClosestText = new JLabel();
		JLabel colorSchemePerfectText = new JLabel();
		JButton revealButton = new JButton();
		JButton hideButton = new JButton();
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(0,0,1500,800);
		
		ImageIcon imageIcon = new ImageIcon("GridBackground.png");
		Image image = imageIcon.getImage(); // transform it 
		Image newimg = image.getScaledInstance(500, 500,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		imageIcon = new ImageIcon(newimg);  // transform it back
		
		JButton goalButton = new JButton();
		goalButton.setBounds(generateRandomNumber(460,30),generateRandomNumber(420,50),20,20);
		//System.out.println("X: "+goalButton.getX()+" Y: "+goalButton.getY());
		goalButton.setVisible(false);
		goalButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==goalButton) {
					goalButton.setLocation(generateRandomNumber(460,30),generateRandomNumber(420,50));
					goalButton.setVisible(false);
					
					clearLabels();
					score++;
		    		setScoreboard(scoreboard);
		    		
		    		//xCoordinateIndicator.setIcon(null);
		    		//yCoordinateIndicator.setIcon(null);
		    		JLabel lastClickedPoint = new JLabel();
		    		lastClickedPoint.setBounds(getXPos(),getYPos(),10,10);
		    		//lastClickedPoint.setVisible(true);
		    		lastClickedPoint.setOpaque(true);
		    		lastClickedPoint.setBackground(Color.blue);
		    		pastMarks.add(lastClickedPoint);
		    		layeredPane.add(pastMarks.get(pastMarks.size()-1),Integer.valueOf(2));
		    		
		    		
		    		if(Math.abs(getXPos()-(goalButton.getX()+callibratedCoordinate))<15) {
			    		xCoordinateStrengthIndicator.setBackground(Color.green);
			    	}
		    		else if(Math.abs(getXPos()-(goalButton.getX()+callibratedCoordinate))>200) {
			    		xCoordinateStrengthIndicator.setBackground(Color.MAGENTA);
			    	}
			    	else if(Math.abs(getXPos()-(goalButton.getX()+callibratedCoordinate))>100) {
			    		xCoordinateStrengthIndicator.setBackground(Color.red);
			    	}
			    	else if(Math.abs(getXPos()-(goalButton.getX()+callibratedCoordinate))>50) {
			    		xCoordinateStrengthIndicator.setBackground(Color.orange);
			    	}
			    	else if(Math.abs(getXPos()-(goalButton.getX()+callibratedCoordinate))>25) {
			    		xCoordinateStrengthIndicator.setBackground(Color.yellow);
			    	}
			    	
			    	if(Math.abs(getYPos()-(goalButton.getY()+callibratedCoordinate))<15) {
			    		yCoordinateStrengthIndicator.setBackground(Color.green);
			    	}
			    	else if(Math.abs(getYPos()-(goalButton.getY()+callibratedCoordinate))>200) {
			    		yCoordinateStrengthIndicator.setBackground(Color.magenta);
			    	}
			    	else if(Math.abs(getYPos()-(goalButton.getY()+callibratedCoordinate))>100) {
			    		yCoordinateStrengthIndicator.setBackground(Color.red);
			    	}
			    	else if(Math.abs(getYPos()-(goalButton.getY()+callibratedCoordinate))>50) {
			    		yCoordinateStrengthIndicator.setBackground(Color.orange);
			    	}
			    	else if(Math.abs(getYPos()-(goalButton.getY()+callibratedCoordinate))>25) {
			    		yCoordinateStrengthIndicator.setBackground(Color.yellow);
			    	}
		    		
		    		
		    		//changing x indicator
		    		if(isLeft(getXPos(),goalButton.getX())==1) {
			    		ImageIcon leftArrow = new ImageIcon("LeftArrow.png");
			    		Image image = leftArrow.getImage(); // transform it 
			    		Image newimg = image.getScaledInstance(150, 150,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
			    		leftArrow = new ImageIcon(newimg);  // transform it back
			    		xCoordinateIndicator.setIcon(leftArrow);
			    		
			    	}
			    	else if(isLeft(getXPos(),goalButton.getX())==0) {
			    		ImageIcon green = new ImageIcon("Green square.png");
			    		xCoordinateIndicator.setIcon(green);
			    	}
			    	else {
			    		ImageIcon rightArrow = new ImageIcon("RightArrow.png");
			    		Image image = rightArrow.getImage(); // transform it 
			    		Image newimg = image.getScaledInstance(150, 150,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
			    		rightArrow = new ImageIcon(newimg);  // transform it back
			    		xCoordinateIndicator.setIcon(rightArrow);
			    	}
		    		
		    		//changing y indicator
			    	if(isUp(getYPos(),goalButton.getY())==1) {
			    		ImageIcon upArrow = new ImageIcon("UpArrow.png");
			    		Image image = upArrow.getImage(); // transform it 
			    		Image newimg = image.getScaledInstance(150, 150,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
			    		upArrow = new ImageIcon(newimg);  // transform it back
			    		yCoordinateIndicator.setIcon(upArrow);
			    	}
			    	else if(isUp(getYPos(),goalButton.getY())==0) {
			    		ImageIcon green = new ImageIcon("Green square.png");
			    		yCoordinateIndicator.setIcon(green);
			    	}
			    	else {
			    		ImageIcon downArrow = new ImageIcon("DownArrow.png");
			    		Image image = downArrow.getImage(); // transform it 
			    		Image newimg = image.getScaledInstance(150, 150,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
			    		downArrow = new ImageIcon(newimg);  // transform it back
			    		yCoordinateIndicator.setIcon(downArrow);
			    	}
		    		
		    		indicator.setBackground(Color.red);
					printingButtonCoordinates.setText("Button coordinates: X: "+goalButton.getX()+" Y: "+goalButton.getY());
					//System.out.println("X: "+goalButton.getX()+" Y: "+goalButton.getY());
				}
			}
		}
	);
		
		//revealButton
		revealButton.setBounds(100,550,100,50);
		revealButton.setText("Reveal");
		revealButton.setVisible(true);
		revealButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==revealButton) {
					goalButton.setVisible(true);
					
				}
			}
		}
	);
		
		//hideButton
		hideButton.setBounds(250,550,100,50);
		hideButton.setText("hide");
		hideButton.setVisible(true);
		hideButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==hideButton) {
					goalButton.setVisible(false);
					
				}
			}
		}
	);
		
		//currentClickedPoint
		currentClickedPoint.setBounds(0,0,10,10);
		currentClickedPoint.setVisible(false);
		currentClickedPoint.setOpaque(true);
		currentClickedPoint.setBackground(Color.red);
		
		
		
		
		
		//background
//		background.setOpaque(true);
//		background.setVisible(true);
		//background.setBackground(Color.red);
		background.setIcon(imageIcon);
		background.setBounds(0,0,500,500);
		
		//xCoordinateIndicator
		xCoordinateIndicator.setOpaque(true);
		//xCoordinateIndicator.setVisible(true);
		//xCoordinateIndicator.setBackground(Color.green);
		xCoordinateIndicator.setBounds(650,200,150,150);
		
		//yCoordinateIndicator
		yCoordinateIndicator.setOpaque(true);
		//yCoordinateIndicator.setBackground(Color.blue);
		yCoordinateIndicator.setBounds(850,200,150,150);
		
		
		//xCoordinateStrengthIndicator
		xCoordinateStrengthIndicator.setOpaque(true);
		//xCoordinateStrengthIndicator.setVisible(true);
		//xCoordinateStrengthIndicator.setBackground(Color.green);
		xCoordinateStrengthIndicator.setBounds(650,400,150,150);
		
		//yCoordinateStrengthIndicator
		yCoordinateStrengthIndicator.setOpaque(true);
		//yCoordinateStrengthIndicator.setBackground(Color.blue);
		yCoordinateStrengthIndicator.setBounds(850,400,150,150);
		
		//scoreboard
		scoreboard.setOpaque(true);
		//scoreboard.setBackground(Color.yellow);
		setScoreboard(scoreboard);
		scoreboard.setBounds(650,10,250,150);
		
		//printingButtonCoordinates
		printingButtonCoordinates.setOpaque(true);
		printingButtonCoordinates.setBackground(Color.red);
		printingButtonCoordinates.setText("Button coordinates: X: "+goalButton.getX()+" Y: "+goalButton.getY());
		printingButtonCoordinates.setBounds(750,250,200,150);
		
		//printingMouseCoordinates
		printingMouseCoordinates.setOpaque(true);
		printingMouseCoordinates.setBackground(Color.pink);
		printingMouseCoordinates.setText("Mouse coordinates: X: "+getXPos()+" Y: "+getYPos());
		printingMouseCoordinates.setBounds(750,450,200,150);
		
		
		printingDiffInCoordinates.setOpaque(true);
		printingDiffInCoordinates.setBackground(Color.cyan);
		printingDiffInCoordinates.setText("diff in x: "+Math.abs(getXPos()-(goalButton.getX()+callibratedCoordinate))+ " diff in y: "+Math.abs(getYPos()-(goalButton.getY()+callibratedCoordinate)));
		printingDiffInCoordinates.setBounds(1000,250,200,150);
		
		indicator.setOpaque(true);
		indicator.setBackground(Color.red);
		indicator.setBounds(1000,450,200,150);
		
		
		ImageIcon colors = new ImageIcon("colorScheme.png");
		Image imageOfColors = colors.getImage(); // transform it 
		Image newimgOfColors = imageOfColors.getScaledInstance(150, 250,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		colors = new ImageIcon(newimgOfColors);  // transform it back
		
		//colorScheme
//		colorScheme.setOpaque(true);
		colorScheme.setIcon(colors);
		colorScheme.setBounds(1350,20,150,250);
		
		//colorSchemeFarthestText
		//colorSchemeFarthestText.setOpaque(true);
		colorSchemeFarthestText.setVisible(true);
		colorSchemeFarthestText.setFont(new Font("Comic-Sans", Font.PLAIN, 20));
		colorSchemeFarthestText.setText("Farthest");
		colorSchemeFarthestText.setBounds(1275,35,100,20);
		
		//colorSchemeClosestText
		//colorSchemeClosestText.setOpaque(true);
		colorSchemeClosestText.setVisible(true);
		//Font labelFont = s.getFont();
		colorSchemeClosestText.setFont(new Font("Comic-Sans", Font.PLAIN, 20));
		colorSchemeClosestText.setText("Closest");
		colorSchemeClosestText.setBounds(1275,180,100,20);
		
		//colorSchemePerfectText
		//colorSchemePerfectText.setOpaque(true);
		colorSchemePerfectText.setVisible(true);
		colorSchemePerfectText.setFont(new Font("Comic-Sans", Font.PLAIN, 20));
		colorSchemePerfectText.setText("Perfect");
		colorSchemePerfectText.setBounds(1275,235,100,20);
		
		
		layeredPane.add(background, Integer.valueOf(1));
		layeredPane.add(xCoordinateIndicator, Integer.valueOf(1));
		layeredPane.add(yCoordinateIndicator, Integer.valueOf(1));
		layeredPane.add(xCoordinateStrengthIndicator, Integer.valueOf(1));
		layeredPane.add(yCoordinateStrengthIndicator, Integer.valueOf(1));
		layeredPane.add(scoreboard, Integer.valueOf(1));
		layeredPane.add(goalButton, Integer.valueOf(2));
		layeredPane.add(currentClickedPoint,Integer.valueOf(2));
		layeredPane.add(colorScheme,Integer.valueOf(2));
		layeredPane.add(colorSchemeFarthestText,Integer.valueOf(2));
		layeredPane.add(colorSchemeClosestText,Integer.valueOf(2));
		layeredPane.add(colorSchemePerfectText,Integer.valueOf(2));
		//layeredPane.add(printingButtonCoordinates,Integer.valueOf(2));
		//layeredPane.add(printingMouseCoordinates,Integer.valueOf(2));
		//layeredPane.add(printingDiffInCoordinates,Integer.valueOf(2));
		//layeredPane.add(indicator,Integer.valueOf(2));
		layeredPane.add(revealButton,Integer.valueOf(1));
		layeredPane.add(hideButton,Integer.valueOf(1));
		
		
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500,500);
		frame.setLayout(null);
		frame.add(layeredPane);
		frame.setVisible(true);
		//frame.add(label1);
		
		
		
		
		frame.addMouseListener(new MouseListener() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		    	clickCount++;
		    	int x=e.getX();
		        int y=e.getY();
		        setCoordinates(x-10,y-30);
		    	if(clickedOnGoalButton(getXPos(),getYPos(),goalButton.getX(),goalButton.getY())) {
		    		indicator.setBackground(Color.green);
		    		
		    		yCoordinateStrengthIndicator.setBackground(Color.green);
		    		goalButton.setVisible(true);
		    		
		    	}
		    	if(Math.abs(getXPos()-(goalButton.getX()+callibratedCoordinate))<15) {
		    		xCoordinateStrengthIndicator.setBackground(Color.green);
		    	}
		    	else if(Math.abs(getXPos()-(goalButton.getX()+callibratedCoordinate))>200) {
		    		xCoordinateStrengthIndicator.setBackground(Color.MAGENTA);
		    	}
		    	else if(Math.abs(getXPos()-(goalButton.getX()+callibratedCoordinate))>100) {
		    		xCoordinateStrengthIndicator.setBackground(Color.red);
		    	}
		    	else if(Math.abs(getXPos()-(goalButton.getX()+callibratedCoordinate))>50) {
		    		xCoordinateStrengthIndicator.setBackground(Color.orange);
		    	}
		    	else if(Math.abs(getXPos()-(goalButton.getX()+callibratedCoordinate))>25) {
		    		xCoordinateStrengthIndicator.setBackground(Color.yellow);
		    	}
		    	
		    	if(Math.abs(getYPos()-(goalButton.getY()+callibratedCoordinate))<15) {
		    		yCoordinateStrengthIndicator.setBackground(Color.green);
		    	}
		    	else if(Math.abs(getYPos()-(goalButton.getY()+callibratedCoordinate))>200) {
		    		yCoordinateStrengthIndicator.setBackground(Color.magenta);
		    	}
		    	else if(Math.abs(getYPos()-(goalButton.getY()+callibratedCoordinate))>100) {
		    		yCoordinateStrengthIndicator.setBackground(Color.red);
		    	}
		    	else if(Math.abs(getYPos()-(goalButton.getY()+callibratedCoordinate))>50) {
		    		yCoordinateStrengthIndicator.setBackground(Color.orange);
		    	}
		    	else if(Math.abs(getYPos()-(goalButton.getY()+callibratedCoordinate))>25) {
		    		yCoordinateStrengthIndicator.setBackground(Color.yellow);
		    	}
		    	
		    	
		    	//changing x indicator
		    	if(isLeft(getXPos(),goalButton.getX())==1) {
		    		ImageIcon leftArrow = new ImageIcon("LeftArrow.png");
		    		Image image = leftArrow.getImage(); // transform it 
		    		Image newimg = image.getScaledInstance(150, 150,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		    		leftArrow = new ImageIcon(newimg);  // transform it back
		    		xCoordinateIndicator.setIcon(leftArrow);
		    		
		    	}
		    	else if(isLeft(getXPos(),goalButton.getX())==0) {
		    		ImageIcon green = new ImageIcon("Green square.png");
		    		xCoordinateIndicator.setIcon(green);
		    	}
		    	else {
		    		ImageIcon rightArrow = new ImageIcon("RightArrow.png");
		    		Image image = rightArrow.getImage(); // transform it 
		    		Image newimg = image.getScaledInstance(150, 150,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		    		rightArrow = new ImageIcon(newimg);  // transform it back
		    		xCoordinateIndicator.setIcon(rightArrow);
		    	}
		    	
		    	//changing y indicator
		    	if(isUp(getYPos(),goalButton.getY())==1) {
		    		ImageIcon upArrow = new ImageIcon("UpArrow.png");
		    		Image image = upArrow.getImage(); // transform it 
		    		Image newimg = image.getScaledInstance(150, 150,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		    		upArrow = new ImageIcon(newimg);  // transform it back
		    		yCoordinateIndicator.setIcon(upArrow);
		    	}
		    	else if(isUp(getYPos(),goalButton.getY())==0) {
		    		ImageIcon green = new ImageIcon("Green square.png");
		    		yCoordinateIndicator.setIcon(green);
		    	}
		    	else {
		    		ImageIcon downArrow = new ImageIcon("DownArrow.png");
		    		Image image = downArrow.getImage(); // transform it 
		    		Image newimg = image.getScaledInstance(150, 150,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		    		downArrow = new ImageIcon(newimg);  // transform it back
		    		yCoordinateIndicator.setIcon(downArrow);
		    	}
		    	
		    		JLabel lastClickedPoint = new JLabel();
		    		lastClickedPoint.setBounds(getXPos(),getYPos(),10,10);
		    		//lastClickedPoint.setVisible(true);
		    		lastClickedPoint.setOpaque(true);
		    		lastClickedPoint.setBackground(Color.blue);
		    		pastMarks.add(lastClickedPoint);
		    		layeredPane.add(pastMarks.get(pastMarks.size()-1),Integer.valueOf(2));
		    		/*
		    		if(hasClicked) {	
		    			
		    		}
		    	else {
		    		hasClicked=true;
		    	}
		    	*/
		    	
		    	
		        currentClickedPoint.setVisible(true);
		        printingMouseCoordinates.setText("Mouse coordinates: X: "+getXPos()+" Y: "+getYPos());
		        //System.out.println("Mouse coordinates: X: "+getXPos()+" Y: "+getYPos());
		        printingDiffInCoordinates.setText("diff in x: "+Math.abs(getXPos()-(goalButton.getX()+callibratedCoordinate))+ " diff in y: "+Math.abs(getYPos()-(goalButton.getY()+callibratedCoordinate)));
		        
		        currentClickedPoint.setLocation(getXPos(),getYPos());
		        
		        //System.out.println((x-10)+","+(y-30));//these co-ords are relative to the component
		        
		        
		        
		    }

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}

	public static void setXPos(int x) {
		xPosClicked=x;
	}
	public static void setYPos(int y) {
		yPosClicked=y;
	}
	public static void setCoordinates(int x, int y) {
		xPosClicked=x;
		yPosClicked=y;
	}
	
	public static int getXPos() {
		return xPosClicked;
	}
	public static int getYPos() {
		return yPosClicked;
	}
	
	public static int generateRandomNumber(int a, int b) {
		int randomNumber = (int) (Math.random()*a)+b;
		return randomNumber;
	}
	
	public static boolean clickedOnGoalButton(int mouseXPos, int mouseYPos, int buttonXPos, int buttonYPos) {
		//System.out.println("x:"+mouseXPos+ "y: "+mouseYPos);
		
		//System.out.println("diff in x: "+Math.abs(mouseXPos-buttonXPos)+ " diff in y: "+Math.abs(mouseYPos-buttonYPos));
		int diffInX=(Math.abs(mouseXPos-(buttonXPos+callibratedCoordinate)));
		int diffInY=(Math.abs(mouseYPos-(buttonYPos+callibratedCoordinate)));
		if(diffInX<=20&&diffInY<=20) {
			//System.out.println("true");
			return true;
		}
		else {
			return false;
		}
	}
	
	public static void clearLabels() {
		for(int i=pastMarks.size()-1;i>=0;i--) {
			pastMarks.get(i).setVisible(false);
			pastMarks.remove(i);
		}
	}
	
	public static void setScoreboard(JLabel s) {
		//Font labelFont = s.getFont();
		s.setFont(new Font("Arial", Font.PLAIN, 50));
		s.setText("Score: "+score);
	}
	
	public static int isLeft(int mouseXPos, int buttonXPos) {
		if((mouseXPos-(buttonXPos+callibratedCoordinate))>7) {
			return 1;
		}
		else if(((mouseXPos-(buttonXPos+callibratedCoordinate))>-15)){
			return 0;
		}
		else {
			return -1;
		}
	}
	
	public static int isUp(int mouseYPos, int buttonYPos) {
		if((mouseYPos-(buttonYPos+callibratedCoordinate))>7) {
			return 1;
		}
		else if((mouseYPos-(buttonYPos+callibratedCoordinate))>-15) {
			return 0;
		}
		else {
			return -1;
		}
	}
	

}


