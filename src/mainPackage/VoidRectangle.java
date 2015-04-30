package mainPackage;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

//A rectangle in which particles' speeds will always slow down
public class VoidRectangle extends Rectangle
{
	int X, Y, W, H;
	public VoidRectangle(int x, int y, int width, int height)
	{
		X = x; Y = y; W = width; H = height;
	}
	public void nullifySpeed(Particle containedParticle)
	{
		if (this.bounds().contains(containedParticle.getLocation()))
		{
			//containedParticle.color = Color.GREEN;
			if (containedParticle.vel != 0)
			{
				containedParticle.stop();
				//System.out.println(containedParticle.vel);
				
				//if (Math.abs(containedParticle.vel) < 100)
				//{
					//System.out.println("STOPPING");
					//containedParticle.velocityX = 0;
					//containedParticle.velocityY = 0;
					//containedParticle.vel = 0;
					//System.out.println(containedParticle.velocityX);
				//}
			}
		}
	}
	public Rectangle bounds()
	{
		return new Rectangle(X, Y, W, H);
	}
	public void drawVoidRect(Graphics G)
	{
		G.drawRect(X, Y, W, H);
	}
}

