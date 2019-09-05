package org.kairosdb.metrics4j_testapp;

import org.kairosdb.metrics4j.annotation.Key;
import org.kairosdb.metrics4j.collectors.DurationCollector;
import org.kairosdb.metrics4j.collectors.LongCollector;

public interface TestStats
{
	LongCollector countSomething();

	LongCollector countSomethingWithTag(@Key("status") String status);

	DurationCollector timeSomething();
}
