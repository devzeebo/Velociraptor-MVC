package com.zeebo.velociraptor.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ModelTest
{
	@Before
	public void setUp()
	{

	}

	@After
	public void tearDown()
	{

	}

	@Test
	public void reflectionTest()
		throws Exception
	{
		ReflectionModel m = new ReflectionModel();
		m.setValue("number", 5);
		System.err.println(m.callMethod("printNumber"));
	}

	class ReflectionModel extends Model
	{
		private int	number	= 10;

		public int printNumber()
		{
			return number;
		}

		public void printNumber(int x)
		{
			System.err.println(x);
		}
	}
}
