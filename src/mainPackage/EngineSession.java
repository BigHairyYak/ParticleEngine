package mainPackage;

public class EngineSession implements Runnable
{
	final int start, fin;
	public EngineSession(int startIndex, int endIndex)
	{
		start = startIndex; fin = endIndex;
	}
	public void run()
	{
		while (true)
		{
			//if (Driver.viewFrame.gameTimer.isRunning())
			{
				Particle A, B;
				for (int q = start; q < fin; q++)
				{
					//System.out.println(q);
					try
					{
						A = Driver.viewFrame.VP.field.get(q);
						
						for (int q2 = 0; q2 < Driver.viewFrame.VP.artificialParticles.size(); q2++)
						{
							B = Driver.viewFrame.VP.artificialParticles.get(q2);
							UniversalFunctions.determineGravitation(A, B);
						}
					}
					catch(Exception e)
					{
						System.out.println("WHOOPS: ");
						System.out.println(q);
						//A = Driver.viewFrame.VP.field.get(q-1);
					}
				}
				
				try {Thread.sleep(100);}
				catch (InterruptedException e){e.printStackTrace();}
			}
			
		}
	}
}
