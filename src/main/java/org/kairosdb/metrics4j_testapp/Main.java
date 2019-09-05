package org.kairosdb.metrics4j_testapp;

import net.sourceforge.argparse4j.ArgumentParsers;
import net.sourceforge.argparse4j.inf.ArgumentParser;

public class Main
{
	public static void main(String[] args) throws InterruptedException
	{
		ArgumentParser parser = ArgumentParsers.newFor("metrics4j_testapp").build()
				.description("Metrics4j Test Application");

		CountSomething countSomething = new CountSomething();
		countSomething.start();

		Thread.sleep(10000);

		countSomething.stop();

		System.out.println("Done");
	}
}
