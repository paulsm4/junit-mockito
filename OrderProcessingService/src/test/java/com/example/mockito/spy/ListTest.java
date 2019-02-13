package com.example.mockito.spy;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

public class ListTest {

	// We are *partially* mocking "myList" with @Spy
	@Spy
	private List<String> myList = new ArrayList<String>();
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void test1() {
		// Here, we're calling the *real* method ".add()"
		myList.add("ABC");
		myList.add("123");
		
		// Here, we're stubbing out ".size()", using "doReturn()"
		// The "real" size is "2"; we're returning "3".
		Mockito.doReturn(3).when(myList).size();
		
		// Test passes: myList.size() returns "3" (mock) instead of 2 (real)
		assertSame(3, myList.size());
	}

	@Test
	public void test2() {
		//myList.add("ABC");
		//myList.add("123");

		// This will FAIL* with out-of-bounds exception unless we populate myList with real ".add()"
		when(myList.get(0)).thenReturn("XYZ");
		doReturn(3).when(myList).size();
		assertSame("XYZ", myList.get(0));
	}
}
