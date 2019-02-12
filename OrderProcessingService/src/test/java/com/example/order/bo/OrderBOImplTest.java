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

	private OrderBOImpl bo;
	private final int ID = 123;

	// Stubbed out class of the dependency
	@Mock
	private OrderDAO dao;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		bo = new OrderBOImpl();
		bo.setDao(dao);
	}
	
	@Test
	public void placeOrderShouldCreateAnOrder() throws SQLException, BOException {
		Order order = new Order();
		when(dao.create(order)).thenReturn(new Integer(1));
		boolean result = bo.placeOrder(order);
		
		assertTrue(result);
		Mockito.verify(dao).create(order);
	}

	@Test
	public void placeOrderShouldNotCreateAnOrder() throws SQLException, BOException {
		Order order = new Order();
		when(dao.create(order)).thenReturn(new Integer(0));		
		boolean result = bo.placeOrder(order);

		assertFalse(result);
		verify(dao).create(order);
	}

	@Test(expected=BOException.class)  // JUnit4 syntax
	public void placeOrderShouldThrowBOException() throws SQLException, BOException {
		Order order = new Order();
		when(dao.create(order)).thenThrow(SQLException.class);		
		boolean result = bo.placeOrder(order);
	}

	@Test
	public void cancelOrderShouldCancelTheOrder() throws SQLException, BOException {
		// Set expections
		Order order = new Order();
		when(dao.read(ID)).thenReturn(order);	
		when(dao.update(order)).thenReturn(1);	
		
		boolean result = bo.cancelOrder(ID);
		
		// Verify results
		assertTrue(result);
		verify(dao).read(ID);
		verify(dao).update(order);
	}

	@Test
	public void cancelOrderShouldNotCancelTheOrder() throws SQLException, BOException {
		// Set expections
		Order order = new Order();
		when(dao.read(ID)).thenReturn(order);	
		when(dao.update(order)).thenReturn(0);	
		
		boolean result = bo.cancelOrder(ID);
		
		// Verify results
		assertFalse(result);
		verify(dao).read(ID);
		verify(dao).update(order);
	}

	@Test(expected=BOException.class)  // JUnit4 syntax
	public void cancelOrderShouldThrowBOExceptionOnRead() throws SQLException, BOException {
		// Set ONE expection, DELETE everything else
		when(dao.read(ID)).thenThrow(SQLException.class);	
		boolean result = bo.cancelOrder(ID);
	}

	@Test(expected=BOException.class)  // JUnit4 syntax
	public void cancelOrderShouldThrowBOExceptionOnUpdate() throws SQLException, BOException {
		Order order = new Order();
		when(dao.read(ID)).thenReturn(order);	// We need to do this...
		when(dao.update(order)).thenThrow(SQLException.class);	// ... before we can test this
		boolean result = bo.cancelOrder(ID);
	}
	
	@Test
	public void deleteOrderShouldDeleteTheOrder() throws SQLException, BOException {
		when(dao.delete(ID)).thenReturn(1);	
		boolean result = bo.deleteOrder(ID);
		assertTrue(result);
		verify(dao).delete(ID);
	}
	
	@Test
	public void deleteOrderShouldNotDeleteTheOrder() throws SQLException, BOException {
		when(dao.delete(ID)).thenReturn(0);	
		boolean result = bo.deleteOrder(ID);
		assertFalse(result);
		verify(dao).delete(ID);
	}

	@Test(expected=BOException.class)  // JUnit4 syntax
	public void deletelOrderShouldThrowBOException() throws SQLException, BOException {
		when(dao.delete(ID)).thenThrow(SQLException.class);	// ... before we can test this
		boolean result = bo.deleteOrder(ID);
	}
	
	@Test
	public void getDaoShouldReturnDao() {
		OrderDAO result = bo.getDao();
		assertNotNull(result);
		assertEquals(result, dao);
	}
	
}
