package com.example.junit;

import static org.junit.Assert.*;

import org.junit.Test;

public class GreetingImplTest {

	@Test
	public void greetShouldReturnAValidValue() {
		//fail("Not yet implemented");
		Greeting greeting = new GreetingImpl();
		String result = greeting.greet("junit");
		assertNotNull(result);
		assertEquals("Hello junit", result);
	}

}
