package com.example.junit;

import static org.junit.Assert.*;

import org.junit.Test;

public class GreetingImplTest {

	@Test
	public void greetShouldReturnAValidValue() {
		//fail("Not yet implemented");    // This will fail
		Greeting greeting = new GreetingImpl();
		String result = greeting.greet("junit");
		assertNotNull(result);
		assertEquals("Hello junit", result);
	}

	@Test(expected = IllegalArgumentException.class)
	public void greetShouldThrowExceptionIfNameIsNull() {
		Greeting greeting = new GreetingImpl();
		//String result = greeting.greet("junit");  // This will fail
		String result = greeting.greet(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void greetShouldThrowExceptionIfNameEmpty() {
		Greeting greeting = new GreetingImpl();
		//String result = greeting.greet("junit");  // This will fail
		String result = greeting.greet("");
	}
}
