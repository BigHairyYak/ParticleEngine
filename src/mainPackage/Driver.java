package mainPackage;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import java.util.TimerTask;
import java.util.Vector;
import java.util.Timer;

import javax.swing.JFrame;

public class Driver 
{
	static ViewFrame viewFrame;
	public static void main(String[] Args)
	{
		viewFrame = new ViewFrame();
		viewFrame.setVisible(true);
	}
}
class ViewFrame extends JFrame //implements KeyListener, MouseListener
{
	public Timer gameTimer = new Timer();
	ViewPanel VP;
	Scanner INPUT = new Scanner(System.in);
	int mouseX, mouseY, pmouseX, pmouseY, ticksSinceLastSwitch, CoMx, CoMy = 0;
	public float ADJUSTMENT = (float) -1;
	public boolean autoPilot;
	Random PhysModeSwitcher = new Random();
	static boolean mousePressed = false;
	public ViewFrame()
	{
		VP = new ViewPanel();
		add(VP);
		setSize(1500, 1000);
		setUndecorated(true);
		
		TimerTask Q = new TimerTask()
		{
			public void run()
			{
				repaint();
			}			
		};	
		gameTimer.schedule(Q, (long)0, (long)250);
		VP.startThreads();
	}
}
