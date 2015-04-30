package mainPackage;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Random;
import java.util.Vector;

public abstract class UniversalFunctions 
{
	public static Random Q = new Random();
	static final float G = (float) 5;
	public static float MAXIMUM_SPEED = 800;
	public static int PHYSICS_MODE = 1;
	public static int COLOR_MODE = 1;
	public static float FORCEFACTOR = (float) .4;
	public static boolean JITTER = true;
	
	static Particle massCenter = null;

	public /*synchronized*/ static void determineGravitation(Particle Planet1, Particle Planet2)
	{
		//System.out.println("Determining Gravity");
		float mass2 = Planet2.mass;
		float distance, velocity, angle, velocityX, velocityY;
	
		float distX = (Planet2.X-Planet1.X); float distY = (Planet2.Y-Planet1.Y);
		distance = (float) Math.sqrt((distX*distX) + (distY*distY));
		//System.out.println(" Distance: " + distance);
		
		velocity = 0;

			velocity = (float) Math.sqrt((G*mass2)/distance) * FORCEFACTOR;
			if (velocity > MAXIMUM_SPEED)
				velocity = MAXIMUM_SPEED;
		
		angle = (float) (Math.atan2(distY, distX));//Trig functions to find X and Y velocity
		
		velocityX = (float) ((velocity*(distX/distance))/90.00); 
		velocityY = (float) ((velocity*(distY/distance))/90.00);
		
		if (JITTER)
		{
			velocityX += Q.nextInt(3)-1;
			velocityY += Q.nextInt(3)-1;
		}
		
		Planet1.velocityX += velocityX; Planet1.velocityY += velocityY;

	}
	
	public static void switchJitter()
	{
		JITTER = !JITTER;
	}
}
