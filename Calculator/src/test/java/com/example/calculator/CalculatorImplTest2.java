package com.example.calculator;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 * Parameterized JUnit test
 * 
 * Steps:
 * 1. Identify input data and results
 *    EX: for add(): num1, num2, expectedResult
 * 2. Create fields in the test cases, and modify the test cases to use them
 *    EX: private int num1, num2, expectedResult
 * 3. Create a constructor that accepts the input data/result for each invocation
 * 4. Create a static method to feed the data to each invocation
 *    EX: @Parameters public static Collection<Integer[]> data () {...}
 * 5. Mark the test case "@RunWith(org.junit.runners.Parameterized.class)"
 */
@RunWith(Parameterized.class)
public class CalculatorImplTest2 {

	private int num1;
	private int num2;
	private int expectedResult;
	
	public CalculatorImplTest2(int num1, int num2, int expectedResult) {
		this.num1 = num1;
		this.num2 = num2;
		this.expectedResult = expectedResult;
	}
	
	@Parameters
	public static Collection<Integer[]> data() {
		return Arrays.asList(new Integer[][] {
			{-1,2,1},
			{1,2,3},
			{6,7,13}
		});
	}
	
	@Test
	public void addShouldReturnAResult() {
		Calculator c = new CalculatorImpl();
		int result = c.add(num1, num2);
		assertEquals(expectedResult, result);
	}

}
