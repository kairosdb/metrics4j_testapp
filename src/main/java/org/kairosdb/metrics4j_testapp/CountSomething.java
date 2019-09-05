package org.kairosdb.metrics4j_testapp;

import org.kairosdb.metrics4j.MetricSourceManager;
import org.kairosdb.metrics4j.collectors.helpers.BlockTimer;

import java.util.Random;

public class CountSomething
{
	private static TestStats s_testStats = MetricSourceManager.getSource(TestStats.class);

	private boolean m_keepGoing = true;
	private Thread m_thread;

	public void stop() throws InterruptedException
	{
		m_keepGoing = false;
		m_thread.join();
	}

	public void start()
	{
		m_thread = new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				try
				{
					doSomething();
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});

		m_thread.start();
	}

	public void doSomething() throws Exception
	{
		Random random = new Random(System.currentTimeMillis());

		while (m_keepGoing)
		{
			try (BlockTimer ignored = s_testStats.timeSomething().time())
			{
				int value = random.nextInt(10);
				s_testStats.countSomething().put(value);

				if (value > 5)
					s_testStats.countSomethingWithTag("high").put(1);
				else
					s_testStats.countSomethingWithTag("low").put(1);
			}
		}
	}
}
