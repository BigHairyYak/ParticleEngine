package mainPackage;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class EngineSession implements Runnable
{
	int start, fin;
	ArrayList<Particle> particles;
	public EngineSession(int startIndex, int endIndex)
	{
		start = startIndex; fin = endIndex;
	}
	public EngineSession(ArrayList<Particle> assignedParticles)
	{
		particles = assignedParticles;
		assignedParticles = null;
	}
	
	public synchronized void paintEngineSession(Graphics2D G)
	{
		for (int q = 0 ; q < particles.size(); q++)
		{
			G.setColor(Color.PINK);
			particles.get(q).drawParticle(G);
		}
	}
	
	public void run()
	{
		while (true)
		{
			Particle A, B;
			for (int q = 0; q < particles.size(); q++)
			{
				//System.out.println(q);
				try
				{
					A = particles.get(q);

					for (int q2 = 0; q2 < Driver.viewFrame.VP.artificialParticles.size(); q2++)
					{
						B = Driver.viewFrame.VP.artificialParticles.get(q2);
						UniversalFunctions.determineGravitation(A, B);
					}
					
					Driver.viewFrame.VP.voidAreas[0].nullifySpeed(A);
					Driver.viewFrame.VP.voidAreas[1].nullifySpeed(A);
					Driver.viewFrame.VP.voidAreas[2].nullifySpeed(A);
					Driver.viewFrame.VP.voidAreas[3].nullifySpeed(A);
					Driver.viewFrame.VP.voidAreas[4].nullifySpeed(A);
					Driver.viewFrame.VP.voidAreas[5].nullifySpeed(A);
					Driver.viewFrame.VP.voidAreas[6].nullifySpeed(A);
					Driver.viewFrame.VP.voidAreas[7].nullifySpeed(A);
					Driver.viewFrame.VP.voidAreas[8].nullifySpeed(A);
					Driver.viewFrame.VP.voidAreas[9].nullifySpeed(A);
					
					
					A.move();
				}
				catch(Exception e){}
			}	
			try {Thread.sleep(100);}
			catch (InterruptedException e){e.printStackTrace();}
			
		}
	}
}
