package com.example.mockito.spy;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

public class ListTest2 {

	// In "ListTest", we used @Spy to partially mock.
	// Here, we'll go back to @Mock...
	@Mock
	//private List<String> myList = new ArrayList<String>();  // This fails (with below error)
	private ArrayList<String> myList = new ArrayList<String>();  // This works (concrete, instead of abstract class)
	
	// Same as ListTest: initialize Mockito
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void test2() {
		// This will no longer fail with "index out of range" exception (myList is now a mock)
		when(myList.get(0)).thenReturn("XYZ");
		assertSame("XYZ", myList.get(0));

		/*
		 * NOTE: This does *NOT* seem to work as shown in class.
		 * 
		 * ERROR:
		 *   org.mockito.exceptions.base.MockitoException: 
		 *   Cannot call abstract real method on java object!
		 *   Calling real methods is only possible when mocking non abstract method.
		 *     //correct example:
		 *     when(mockOfConcreteClass.nonAbstractMethod()).thenCallRealMethod();
		 *   at com.example.mockito.spy.ListTest2.test2(ListTest2.java:48)
		 *   at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
		 *   ...
		 */
		Mockito.when(myList.size()).thenCallRealMethod();  // <-- Line 48
		assertSame(0, myList.size());
	}
}
