package com.example.calculator;

import static org.junit.Assert.*;

import org.junit.Test;

public class CalculatorImplTest {

	@Test
	public void addShouldReturnAResult() {
		Calculator c = new CalculatorImpl();
		int result = c.add(10,  20);
		//assertEquals(20, result); // FAILS
		assertEquals(30, result);  // Passes
	}

}
