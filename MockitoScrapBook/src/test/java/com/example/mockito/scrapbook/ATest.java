package com.example.mockito.scrapbook;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

public class ATest {

	private A a;
	
	@Mock
	private B b;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		a = new A(b);
	}
	
	/* Implicitly stub out void method */
	@Test
	public void usesVoidMethodShouldCallTheVoidMethod() throws Exception {
		// Scenario 1: passes
		// a.usesVoidMethod();
		// verify(b).voidMethod();
		
		// Scenario 2: FAILS: Wanted 1 time ... but was 2 times.  Undesired invocation.
		// a.usesVoidMethod();
		// assertEquals(1, a.usesVoidMethod());
		// verify(b).voidMethod();

		// Scenario 3: passes
		a.usesVoidMethod();
		assertEquals(1, a.usesVoidMethod());
		verify(b, atLeastOnce()).voidMethod();		
	}
	
	/* Explicitly stub out void method: Mockito "doNothing()" stubber */
	@Test
	public void explicitlyStubOutVoidMethod() throws Exception {
		doNothing().when(b).voidMethod();
		assertSame(1, a.usesVoidMethod());
		verify(b).voidMethod();		
	}

	@Test(expected=RuntimeException.class)  // JUnit4 syntax
	public void usesVoidMethodShouldThrowRuntimeException() throws Exception {
		doThrow(Exception.class).when(b).voidMethod();
		a.usesVoidMethod();
	}
	
	/* Call a.usesVoidMethod() multiple times, see that we get different outputs each time
	 * 
	 * SAMPLE OUPUT:
	 *   "Chaining" doNothing() and doThrow()...
	 *   calling a.usesVoidMethod() the first time...
	 *   calling a.usesVoidMethod() again...
	 */
	@Test(expected=RuntimeException.class)  // JUnit4 syntax
	public void testConsecutiveCalls() throws Exception {
		System.out.println("\"Chaining\" doNothing() and doThrow()...");
		doNothing().doThrow(Exception.class).when(b).voidMethod();

		System.out.println("calling a.usesVoidMethod() the first time...");
		a.usesVoidMethod();
		verify(b).voidMethod();
		
		// NOTE: If we comment this out, test will FAIL: "Expected exception"
		System.out.println("calling a.usesVoidMethod() again...");
		a.usesVoidMethod();
		System.out.println("We should *not* get here; the 2nd call should have generated an exception...");
		verify(b).voidMethod();
	}
	
	
}
