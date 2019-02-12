package com.example.order.bo;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.example.order.bo.exception.BOException;
import com.example.order.dao.OrderDAO;
import com.example.order.dto.Order;

import static org.mockito.Mockito.*;

import java.sql.SQLException;

/**
 * Initial "Hello world" unit test with Mockito: test OrderBOImpl class
 * 
 * - USAGE:
 *   1) Eclipse >  OrderBOImpl > New > JUnit Test Case => create this file
 *   2) Initial method: placeOrderShouldCreateAnOrder()
 *   3) "Stub out" class of the dependency: 
 *        private OrderDAO dao => @Mock
 *   4) Add @Before setup() => MockitoAnnotations.initMocks(this);
 *   5) "Set Expectation": 
 *        when(dao.create(order)).thenReturn(new Integer(1));
 *   6) Verify results:
 *        assertTrue(result);
 *        verify(dao).create(order);
 *    
 * - INITIAL PROBLEM:
 *     Stupid "Create project > Maven" using JDK 1.5
 *   SOLUTION: 
 *     Update to Java 1.8 in build path; add explicit "source/target" properties in pom.xml
 *
 * - NOTE:
 *     Using JUnit4 syntax; Maven
 */
public class OrderBOImplTest {

	// Stubbed out class of the dependency
	@Mock
	private OrderDAO dao;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void placeOrderShouldCreateAnOrder() throws SQLException, BOException {
		OrderBOImpl bo = new OrderBOImpl();
		bo.setDao(dao);
		Order order = new Order();
		when(dao.create(order)).thenReturn(new Integer(1));
		
		boolean result = bo.placeOrder(order);
		
		// Verify bo "placeOrder()" returned "true"
		assertTrue(result);
		
		// Verify bo actually called mock "create()"
		Mockito.verify(dao).create(order);
	}

}
