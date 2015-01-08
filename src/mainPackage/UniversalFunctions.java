package mainPackage;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Random;
import java.util.Vector;

public abstract class UniversalFunctions 
{
	public static Random Q = new Random();
	static final float G = (float) 5;
	public static float systemMass;
	public static int PHYSICS_MODE = 1;
	public static int COLOR_MODE = 1;
	public static float FORCEFACTOR = (float) .4;
	public static boolean JITTER = true;
	static Particle massCenter = null;
	public static Particle centerOfMass(ArrayList<Particle> field)
	{
		Particle placeholderParticle = null;
		float centerX = 0, centerY = 0;//, systemMass = 0;
		float numeratorX = 0, numeratorY = 0;
		for (int q = 0; q < field.size(); q++)
		{
			placeholderParticle = field.get(q);
			numeratorX += (placeholderParticle.mass * placeholderParticle.X);
			numeratorY += (placeholderParticle.mass * placeholderParticle.Y);
			systemMass += placeholderParticle.mass;
		}
		centerX = numeratorX/systemMass;
		centerY = numeratorY/systemMass;
		placeholderParticle.X = centerX; placeholderParticle.Y = centerY; placeholderParticle.mass = systemMass;
		
		massCenter = new Particle(centerX, centerY, systemMass);
		//System.out.println(massCenter.mass + ", " + massCenter.X + ", " + massCenter.Y);
		
		centerX = 0; centerY = 0; systemMass = 0;
		numeratorX = 0; numeratorY = 0;
		return massCenter;
	}
	
	/**
	 * Fg = (G * Mass1 * Mass2)/(Distance^2)
	 * Fg1 = Mass1 * Acceleration
	 * Mass1 * Acceleration = Mass1 * Velocity^2 / Distance
	 * Acceleration = Velocity^2 / Distance
	 * 
	 * Velocity^2 = (((G*Mass1*Mass2)/(Distance^2)) / Mass1) * (Distance)
	 * Velocity^2 = ((G*Mass2)/Distance)
	 * THEREFORE:
	 * Velocity = Math.sqrt((G*Mass2)/distance)
	 */

	public static void determineGravitation(Particle Planet1, Particle Planet2)
	{
		//System.out.println("Determining Gravity");
		float mass2 = Planet2.mass;
		float x1 = Planet1.X; float x2 = Planet2.X;
		float y1 = Planet1.Y; float y2 = Planet2.Y;
		float distX, distY, distance, velocity, angle, velocityX, velocityY;
	
		distX = (x2-x1); distY = (y2-y1);
		distance = (float) Math.sqrt(Math.pow(distX, 2) + Math.pow(distY, 2));
		//System.out.println(" Distance: " + distance);
		
		if (distance != 0)
			velocity = (float) Math.sqrt((G*mass2)/distance) * FORCEFACTOR;
		else if (distance == 0)
			velocity = 0;
		else
			velocity = 9000;
		
		//System.out.println(" Velocity: " + velocity);
		
		angle = (float) (Math.atan2(distY, distX));//Trig functions to find X and Y velocity
		
		if (PHYSICS_MODE == 2) //"Spiral" PhysMode
		{
			angle += (Math.PI)/2;
		}
		
		//angle is given facing the actual other body - true gravity
		//adding PI/2 gives it an orbital tangential velocity
		velocityX = (float) ((velocity*Math.cos(angle))/90.00); 
		velocityY = (float) ((velocity*Math.sin(angle))/90.00);
		
		if (JITTER)
		{
			velocityX += Q.nextInt(3)-1;
			velocityY += Q.nextInt(3)-1;
		}
		
		if ((PHYSICS_MODE == 1) || (PHYSICS_MODE == 3))
		{	
			Planet1.velocityX += velocityX; Planet1.velocityY += velocityY;
		}
		if (PHYSICS_MODE  == 2)
		{
			Planet1.velocityX = velocityX; Planet1.velocityY = velocityY;
		}
		if (PHYSICS_MODE == 4)
		{
			Planet1.velocityX += velocityX; Planet1.velocityY = velocityY;
		}
			
		Planet1.vel = velocity;
		Planet1.move(); 
		if (COLOR_MODE != 9)
			Planet1.velocityColor(COLOR_MODE);
		else if (COLOR_MODE == 9)
			Planet1.velocityColorByDistance((int)distance);
		
	}
	
	public static void adjustForceFactor(float adjustment)
	{
		if (PHYSICS_MODE == 3)
			FORCEFACTOR *= adjustment;
		else
			FORCEFACTOR = (float) .4;
	}
	public static void forceAdjustment(Float FA)
	{
		FORCEFACTOR = FA;
	}
	public static void setColorMode(int newColorMode)
	{
		COLOR_MODE = newColorMode;
	}
	public static void setPhysMode(int newPhysMode)
	{
		PHYSICS_MODE = newPhysMode;
	}
	public static void switchJitter()
	{
		JITTER = !JITTER;
	}
	public static void borderRepulsion(Particle a) 
	{
		float distX = a.X, distY = a.Y;
		float velocityX = (float) Math.sqrt((G*(a.mass))/distX);
		
		float velocityY = (float) Math.sqrt((G*(a.mass))/distY);
		
		float endDistX = 900 - a.X; float endDistY = 900 - a.Y;
		velocityX += (float) Math.sqrt((G*ViewPanel.fieldDensity*a.mass)/endDistX);
		velocityY += (float) Math.sqrt((G*ViewPanel.fieldDensity*a.mass)/endDistY);
		System.out.println(velocityY);
		
		//a.velocityX += velocityX; a.velocityY += velocityY;	
	}
}
