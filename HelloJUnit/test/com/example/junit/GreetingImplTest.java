package com.example.junit;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GreetingImplTest {

	private Greeting greeting;

	@Before
	public void setup() {
		System.out.println("setup()...");
		greeting = new GreetingImpl();
	}
	
	@After
	public void teardown() {
		System.out.println("teardown()...");
		greeting = null;
	}
	
	@Test
	public void greetShouldReturnAValidValue() {
		System.out.println("greetShouldReturnAValidValue()...");
		//fail("Not yet implemented");    // This will fail
		//Greeting greeting = new GreetingImpl();
		String result = greeting.greet("junit");
		assertNotNull(result);
		assertEquals("Hello junit", result);
	}

	@Test(expected = IllegalArgumentException.class)
	public void greetShouldThrowExceptionIfNameIsNull() {
		System.out.println("greetShouldThrowExceptionIfNameIsNull()...");
		//Greeting greeting = new GreetingImpl();
		//String result = greeting.greet("junit");  // This will fail
		String result = greeting.greet(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void greetShouldThrowExceptionIfNameEmpty() {
		System.out.println("greetShouldThrowExceptionIfNameEmpty()...");
		//Greeting greeting = new GreetingImpl();
		//String result = greeting.greet("junit");  // This will fail
		String result = greeting.greet("");
	}
}
