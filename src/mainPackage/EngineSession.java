package mainPackage;

public class EngineSession implements Runnable
{
	int start, fin;
	public EngineSession(int startIndex, int endIndex)
	{
		start = startIndex; fin = endIndex;
	}
	public void run()
	{
		while (true)
		{
			if (Driver.viewFrame.gameTimer.isRunning())
			{
				Particle A, B;
				for (int q = start; q < fin; q++)
				{
					A = Driver.viewFrame.VP.field.get(q);
					for (int q2 = 0; q2 < Driver.viewFrame.VP.artificialParticles.size(); q2++)
					{
						B = Driver.viewFrame.VP.artificialParticles.get(q2);
						UniversalFunctions.determineGravitation(A, B);
					}
				}
				
				try {Thread.sleep(10);}
				catch (InterruptedException e){e.printStackTrace();}
			}
			
		}
	}
}
