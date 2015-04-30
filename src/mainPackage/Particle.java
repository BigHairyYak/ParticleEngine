package mainPackage;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.Random;

public class Particle
{
	float X, Y, mass, velocityX, velocityY, vel;
	boolean artificialParticle = false;
	boolean stopped = false;
	Color color;
	//Random Q = new Random();
	public Particle(float x, float y, float Mass)
	{
		X = x; Y = y; mass = Mass;
		velocityX = velocityY = 0;

		color = Color.PINK; 
	}
	public Particle(Particle artificial)
	{
		//X = artificial.X; Y = artificial.Y;
		mass = artificial.mass;
		//color = artificial.color;
		artificialParticle = true;
	}
	
	public Color velocityColor(int colorMode)
	{
		int newColorValue = 0;
		newColorValue = (int)(Math.abs(vel)/(50) * 255);
		if (newColorValue > 255)
			newColorValue = 255;
		if (newColorValue < 0)
			newColorValue = 0;
		//System.out.println(newColorValue + ", " + (255-newColorValue));
		if (colorMode == 1)
			return new Color(255-newColorValue, 0, newColorValue);
		else if (colorMode == 2)
			return new Color(newColorValue, 255-newColorValue, 0);
		else if (colorMode == 3)
			return new Color(newColorValue, newColorValue, newColorValue);
		else if (colorMode == 4)
			return new Color(255-newColorValue, 255-newColorValue, 255-newColorValue);
		else if (colorMode == 5)
			return new Color(Math.abs(128-newColorValue), Math.abs(128-newColorValue), Math.abs(128-newColorValue));
		else if (colorMode == 6)
			return new Color(newColorValue, 0, 0);
		else if (colorMode == 7)
			return new Color(0, newColorValue, 0);
		else if (colorMode == 8)
			return new Color(0, 0, newColorValue);
		else
			return Color.PINK;
	}
	public void move()
	{
		//float lastX = X; float lastY = Y;
		if (stopped == false)
		{
			vel = (float)Math.sqrt((velocityX*velocityX)+(velocityY*velocityY));
		
			X += velocityX; Y += velocityY;
			//velocityColor(1);
		}
		//System.out.println(velocityX);
	}
	
	public void stop()
	{
		velocityX -= 10;
		velocityY -= 10;
		//vel = 0;
		if (Math.abs(velocityX) < 5 || Math.abs(velocityY) < 5)
			stopped = true;
		//stopped = true;
	}
	
	public void drawParticle(Graphics2D G)
	{
		if (X > 0 && X < 1500 && Y > 0 && Y < 1000)
		{
			//G.setColor(velocityColor(/*UniversalFunctions.COLOR_MODE*/1));
			//if (artificialParticle == false)			
				G.drawLine((int)(X), (int)(Y), (int)X, (int)Y);
			//else 
				//G.fillOval((int)(X-2), (int)(Y-2),  4,  4);
		}
	}
	public Point getLocation() 
	{
		return new Point((int)X, (int)Y);
	}
}
