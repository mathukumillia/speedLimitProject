import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.JLayeredPane;
import java.applet.*;
import java.net.*;


/**
* Display
*	Initializes the gui, performs comparison of speeds
*
*
**/
public class Display extends JFrame{

	private JLabel speed;	//speed limit label on gui
	private String roadName;   //name of the street in string form
	private readFromFile reader; //the reader object that gets speed limit from the file
	private int speedLimit;  //the speed limit obtained from the file
	private int currentSpeed = 0; //the current speed of the vehicle
	private JLayeredPane p;  // the layered pane that holds all the other graphical objects
 	private JLabel currSpeed;  //the label on the gui that displays the current speed
	private JButton up;  //the button that increases the current speed on the gui
	private JButton down;  //the button that decreases the current speed on the gui
	private Sound beep = new Sound("Beep.wav");  //the sound file played by excessive current speed
	private String type; //the type of speedometer displayed on the gui-analog vs digital
	private SpeedometerLabel speedometer; //the speedometer image
	private int speedX = 70;
	private int speedY = 240;


	/**
	* Constructor
	*	Initializes the window with all jlabels, takes in a street name through a popup window
	*
	**/
	public Display() throws FileNotFoundException{
		super("Speed Warning");
		this.setSize(600,600);									//default JFram init commands
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		reader = new readFromFile();   							//initializes reader

	    p = getLayeredPane();									//initializes layeredPane

		JLabel sign = new JLabel(new ImageIcon("speedlimitsign.png"));
		sign.setBounds(50, 50, 300, 300);								//paints on speed limit image

		speed = new JLabel();
		speed.setFont(new Font("Arial", Font.BOLD, 70));		//sets font of speed limit label
		speed.setBounds(160, 190, 100, 100);

		currSpeed = new JLabel(Integer.toString(currentSpeed));		//initializes current speed JLabel

		final String[] options = {"Analog", "Digital"};

		JFrame frame = new JFrame();
		type = (String)JOptionPane.showInputDialog(frame, "Choose the display type you want to use: ", "Display Options", JOptionPane.QUESTION_MESSAGE, null, options, options[1]);  // input dialog to choose between digital and analog display

		if(type.equals("Analog")){
			speedometer = new SpeedometerLabel(new ImageIcon("speedometer.jpg"), 157,162, speedX, speedY); //paints speedometer to screen
			speedometer.setBounds(325, 50, 300, 300);
			p.add(speedometer, new Integer(4));
		}else {															//sets gui to digital mode
			currSpeed.setFont(new Font("Arial", Font.BOLD, 130));		//sets font of current speed label
			currSpeed.setBounds(350,100,300,200);
			p.add(currSpeed, new Integer(3));
		}

		theHandler handler = new theHandler();				//creates a handler(action listener) object

		up = new JButton("increase");
		up.setBounds(150,400,100,100);			
		up.addActionListener(handler);
														//creates buttons and adds to them the action listener
		down = new JButton("decrease");
		down.setBounds(350,400,100,100);
		down.addActionListener(handler);


		p.add(sign, new Integer(1));
		p.add(speed, new Integer(2));			//adds everything to the layered pane
		p.add(up, new Integer(3));
		p.add(down, new Integer(3));


		roadName = JOptionPane.showInputDialog("Enter street");
		speedLimit = Integer.parseInt(reader.getSpeedLimit(roadName));		//displayes popup and takes in street name
		speed.setText("" + speedLimit);
		compareSpeeds();


	}

	/**
	*  compareSpeeds
	*	   compares current speed to speedLimit, reacts accordingly
	*
	**/
	public void compareSpeeds(){
		if(type.equals("Digital")){
			if(speedLimit>=currentSpeed){
				currSpeed.setForeground(Color.green);
				beep.stopSound();
			}else if(speedLimit < currentSpeed){
				currSpeed.setForeground(Color.red);
				beep.playSound();
			}
		}else if(type.equals("Analog")){
			if(speedLimit>=currentSpeed){
				beep.stopSound();
			}else if(speedLimit < currentSpeed){
				beep.playSound();
			}
		}	

 	}

 	/**
 	*  theHandler
 	*	  action listener
 	*	
 	**/
	private class theHandler implements ActionListener{


		/**
		*  actionPerformed
		*		adjusts current speed when buttons are pressed
		*
		**/
		public void actionPerformed(ActionEvent event){
			if(type.equals("Digital")){
				if(event.getSource() == up){
					currentSpeed++;										
					currSpeed.setText(Integer.toString(currentSpeed));
					compareSpeeds();
				}else if(event.getSource() == down){							// processes button events when format is digital
					currentSpeed--;
					if(currentSpeed < 0){
						currentSpeed = 0;
					}
					currSpeed.setText(Integer.toString(currentSpeed));
					compareSpeeds();
				}
			}else{
				if(event.getSource() == up){
					currentSpeed++;	
					//speedX-=1;
					//speedY-=3;
					//speedometer.refresh(speedX, speedY);									
					compareSpeeds();
				}else if(event.getSource() == down){							// processes button events when format is analog
					currentSpeed--;
					compareSpeeds();
				}
			}	
		}

	}



}