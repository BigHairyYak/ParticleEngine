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
	public static int fieldDensity = 200;
	ArrayList<Particle> field, artificialParticles;
	//Vector<Particle> particleVector;
	Particle A, B, mouseParticle;
	public ViewPanel()
	{
		System.out.println("VIEW FRAME!");
		mouseParticle = new Particle(0, 0, 0);
		field = new ArrayList<Particle>();
		artificialParticles = new ArrayList<Particle>();
		//particleVector = new Vector<Particle>();
		for (float q1 = 1; q1 < 900.00; q1 += 900.00/fieldDensity)
		{
			for (float q2 = 1; q2 < 900.00; q2 += 900.00/fieldDensity)
			{
				//particleVector.addElement(new Particle(q1+20, q2+20, 50));
				field.add(new Particle(q1+10, q2+10, 100));
			}
		}
		//UniversalFunctions.getSystemMass(particleVector);
		setBackground(Color.BLACK);
	}
	public void paintComponent(Graphics G)
	{
		super.paintComponent(G);
		/*for (Enumeration<Particle> e = particleVector.elements(); e.hasMoreElements();)
		{
			((Particle) e.nextElement()).drawParticle(G);
		}*/	
		for (int q = 0; q < field.size(); q++)
		{
			((Particle) field.get(q)).drawParticle(G);
			//if (q > 2)
				//G.fillRect(((int)field.get(q).X), (int)field.get(q).Y, (int)field.get(q-1).X, (int)field.get(q-1).Y);
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
		//if (ViewFrame.mousePressed)
			//mouseParticle.drawParticle(G);
	}
}
