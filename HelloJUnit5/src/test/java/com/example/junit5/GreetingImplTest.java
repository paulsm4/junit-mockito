package com.example.junit5;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GreetingImplTest {

	private Greeting greeting;

	@BeforeEach
	public void setup() {
		System.out.println("setup()...");
		greeting = new GreetingImpl();
	}
	
	@AfterEach
	public void teardown() {
		System.out.println("teardown()...");
		greeting = null;
	}
	
	@Test
	public void greetShouldReturnAValidValue() {
		System.out.println("greetShouldReturnAValidValue()...");
		//fail("Not yet implemented");
		String result = greeting.greet("junit");
		assertNotNull(result);
		assertEquals("Hello junit", result);
	}

	@Test
	public void greetShouldThrowExceptionIfNameIsNull() {
		System.out.println("greetShouldThrowExceptionIfNameIsNull()...");
		Assertions.assertThrows(IllegalArgumentException.class, ()->{
			//String result = greeting.greet("abc");
			String result = greeting.greet(null);
		});
	}

	@Test
	public void greetShouldThrowExceptionIfNameEmpty() {
		System.out.println("greetShouldThrowExceptionIfNameEmpty()...");
		Assertions.assertThrows(IllegalArgumentException.class, ()->{
			//String result = greeting.greet("abc");
			String result = greeting.greet("");
		});
	}
}
