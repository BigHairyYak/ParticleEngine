package mainPackage;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Particle
{
	float X, Y, mass, velocityX, velocityY, vel;
	boolean artificialParticle = false;
	Color color;
	Random Q = new Random();
	public Particle(float x, float y, float Mass)
	{
		X = x; Y = y; mass = Mass;
		velocityX = velocityY = 0;

		//color = new Color(Q.nextInt(256), Q.nextInt(256), Q.nextInt(256));
	}
	public Particle(Particle artificial)
	{
		//X = artificial.X; Y = artificial.Y;
		mass = artificial.mass;
		//color = artificial.color;
		artificialParticle = true;
	}
	
	public void velocityColor()
	{
		double newColorValue = 0;
		//System.out.println(vel);
		newColorValue = Math.abs(vel)/1000.00 * 255;
		if (newColorValue > 255)
		{
			newColorValue = 255;
		}
		//System.out.println(newColorValue + ", " + (255-newColorValue));
		color = new Color(255-(int)newColorValue, 0, (int)newColorValue);
	}
	public void move()
	{
		X += velocityX; Y += velocityY;
		//System.out.println(vel);
		velocityColor();
	}
	
	public void drawParticle(Graphics G)
	{
		G.setColor(color);
		if (artificialParticle == false)
			G.fillOval((int)(X-2), (int)(Y-2), 4, 4);
		else 
			G.fillRect((int)(X-5), (int)(Y-5),  10,  10);
		//X+=velocityX; Y+= velocityY;
	}
}
