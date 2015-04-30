package mainPackage;

import javax.swing.*;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Vector;
class ViewPanel extends JPanel
{
	//SIZE IS 900, 900
	//PARTICLE DENSITY (AMOUNT ON SCREEN)
	public static int fieldDensity = 200;
	public int threadCount;
	ArrayList<Particle> field, artificialParticles;
	ArrayList<EngineSession> sessions;
	VoidRectangle[] voidAreas;
	Particle A, B, mouseParticle;
	
	public ViewPanel()
	{
		System.out.println("VIEW FRAME!");
		mouseParticle = new Particle(0, 0, 0);
		field = new ArrayList<Particle>();
		artificialParticles = new ArrayList<Particle>();
		
		artificialParticles.add(new Particle(500, 500, (float)(Math.pow(10, 20))));
		artificialParticles.get(artificialParticles.size()-1).artificialParticle = true;
		artificialParticles.add(new Particle(1000, 500, (float)(Math.pow(10, 20))));
		artificialParticles.get(artificialParticles.size()-1).artificialParticle = true;
		
		voidAreas = new VoidRectangle[10];
		
		for (float q1 = 1; q1 < 1500.00; q1 += 1500.00/fieldDensity)
		{
			for (float q2 = 1; q2 < 1000.00; q2 += 1000.00/fieldDensity)
			{
				field.add(new Particle(q1, q2, 100));
				//System.out.println(q1 + ", " + q2);
			}
		}
		
		voidAreas[0] = new VoidRectangle(50, 150, 50, 400);
		voidAreas[1] = new VoidRectangle(100, 150, 150, 150);
		voidAreas[2] = new VoidRectangle(275, 150, 50, 400);
		voidAreas[3] = new VoidRectangle(325, 150, 150, 150);
		voidAreas[4] = new VoidRectangle(375, 300, 50, 250);
		voidAreas[5] = new VoidRectangle(500, 150, 200, 400);
		voidAreas[6] = new VoidRectangle(750, 150, 300, 50);
		voidAreas[7] = new VoidRectangle(750, 200, 50, 350);
		voidAreas[8] = new VoidRectangle(875, 200, 50, 350);
		voidAreas[9] = new VoidRectangle(1000, 200, 50, 350);
		
		threadCount = 1 + (int)(Math.ceil((double)field.size()/100000));
		setBackground(Color.BLACK);
	}
	
	public void startThreads()
	{
		sessions = new ArrayList<EngineSession>(0);
		  for (int q = 1; q < threadCount; q++)
		  {
		  	ArrayList<Particle> assignedParticles = new ArrayList<Particle>(0);
		  	for (int z = 1; z < field.size(); z += q)
		  	{
		  		assignedParticles.add(field.get(z));
		  	}
		  	System.out.println("Creating new thread: " + q);
		  	
		  	sessions.add(new EngineSession(assignedParticles));
		  	new Thread(sessions.get(sessions.size()-1)).start();
		  }
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		Graphics2D G = (Graphics2D) g;
		
		for (int q = 0; q < sessions.size(); q++)
		{
			sessions.get(q).paintEngineSession(G);
		}
		//for (int q = 0; q < artificialParticles.size(); q++)
		//{
			//artificialParticles.get(q).drawParticle(G);
		//}

		
		
		G.setFont(new Font("TimesRoman", Font.PLAIN, 300));
		G.drawString("?", 1100, 400);
		
		G.setColor(Color.GREEN);
		G.setFont(new Font("TimesNewRoman", Font.PLAIN, 12));
		G.drawString("Number of particles on-screen: " + (int)Math.pow(fieldDensity, 2), 5, 15);
		
	}
}
