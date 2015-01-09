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
	
	public void velocityColor(int colorMode)
	{
		int newColorValue = 0;
		//System.out.println(vel);
		newColorValue = (int)(Math.abs(vel)/1000.00 * 255);
		if (newColorValue > 255)
			newColorValue = 255;
		if (newColorValue < 0)
			newColorValue = 0;
		//System.out.println(newColorValue + ", " + (255-newColorValue));
		if (colorMode == 1)
			color = new Color(255-newColorValue, 0, newColorValue);
		else if (colorMode == 2)
			color = new Color(newColorValue, 255-newColorValue, 0);
		else if (colorMode == 3)
			color = new Color(newColorValue, newColorValue, newColorValue);
		else if (colorMode == 4)
			color = new Color(255-newColorValue, 255-newColorValue, 255-newColorValue);
		else if (colorMode == 5)
			color = new Color(Math.abs(128-newColorValue), Math.abs(128-newColorValue), Math.abs(128-newColorValue));
		else if (colorMode == 6)
			color = new Color(newColorValue, 0, 0);
		else if (colorMode == 7)
			color = new Color(0, newColorValue, 0);
		else if (colorMode == 8)
			color = new Color(0, 0, newColorValue);
	}
	public void velocityColorByDistance(int Distance)
	{
		int newColorValue = (int)(Math.abs(Distance)/450.00 * 255);
		if (newColorValue > 255)
			newColorValue = 255;
		if (newColorValue < 0)
			newColorValue = 0;
	    color = new Color(255-newColorValue, 0, 255-newColorValue);
	}
	public void move()
	{
		X += velocityX; Y += velocityY;
		//System.out.println(vel);
	}
	
	public void drawParticle(Graphics G)
	{
		G.setColor(color);
		if (artificialParticle == false)
			G.fillOval((int)(X-1), (int)(Y-1), 2, 2);
		else 
			G.fillOval((int)(X-2), (int)(Y-2),  4,  4);
		//X+=velocityX; Y+= velocityY;
	}
}
