package mainPackage;

import javax.swing.*;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Vector;
class ViewPanel extends JPanel
{
	//SIZE IS 900, 900
	//PARTICLE DENSITY (AMOUNT ON SCREEN)
	public static int fieldDensity = 100;
	ArrayList<Particle> field, artificialParticles;
	Particle A, B, mouseParticle;
	public ViewPanel()
	{
		System.out.println("VIEW FRAME!");
		mouseParticle = new Particle(0, 0, 0);
		field = new ArrayList<Particle>();
		artificialParticles = new ArrayList<Particle>();
		for (float q1 = 1; q1 < 950.00; q1 += 950.00/fieldDensity)
		{
			for (float q2 = 1; q2 < 950.00; q2 += 950.00/fieldDensity)
			{
				field.add(new Particle(q1+10, q2+10, 100));
			}
		}
	
		setBackground(Color.BLACK);
	}
	
	public void startThreads()
	{
		for (int q = 1; q < field.size();)
		{
			int lastQ;
			if ((q+100000) < field.size())
			{
				lastQ = q; q+= 100000;
			}
			else
			{
				lastQ = q;
				q = field.size();	
			}
			new Thread(new EngineSession(lastQ, q-1)).start();
		}
	}
	
	public void paintComponent(Graphics G)
	{
		super.paintComponent(G);

		for (int q = 0; q < field.size(); q++)
		{
			field.get(q).drawParticle(G);
		}
		for (int q = 0; q < artificialParticles.size(); q++)
		{
			artificialParticles.get(q).drawParticle(G);
		}
		G.setColor(Color.GREEN);
		G.drawString("Number of particles on-screen: " + field.size(), 5, 15);
		G.drawString("Physics Mode: " + UniversalFunctions.PHYSICS_MODE + " - Press keys 1 to 4 to switch (1: Gravity, 2: Orbiting, 3: Oscillating, 4: Event Horizon)", 5, 30);
		G.drawString("Force Factor: " + UniversalFunctions.FORCEFACTOR + " - Press Q to modify and S to switch signs", 5, 45);
		G.drawString("Jittering: " + UniversalFunctions.JITTER + " - Press J to switch", 5, 60);
		G.drawString("AUTOPILOT: " + Driver.viewFrame.autoPilot +" - PRESS A TO SWITCH", 5, 75);
		G.drawString("Press SPACE or move the mouse off-window to pause! Press SPACE again or return the mouse to resume!", 5, 90);
	}
}
