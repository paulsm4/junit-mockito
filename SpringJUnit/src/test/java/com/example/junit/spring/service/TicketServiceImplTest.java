package com.example.junit.spring.service;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.junit.spring.dao.TicketDAO;
import com.example.junit.spring.dto.Ticket;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations="classpath:application-context.xml")
public class TicketServiceImplTest {

	private static final int EXPECTED_RESULT = 1;
	private static final String PASSENGER_NAME = "ABC";
	private static final String PHONE = "111-222-3333";

	@Mock
	TicketDAO dao;
	
	@Autowired
	@InjectMocks
	private TicketServiceImpl service;
	
	@Before
	public void setup () {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void butTicketShouldReturnAValidValue() {
		// TicketServiceImpl service = new TicketServiceImpl(); // We'll let Spring inject this
		Mockito.when(dao.createTicket(any(Ticket.class))).thenReturn(1);
		int result = service.buyTicket(PASSENGER_NAME, PHONE);
		assertEquals(result, EXPECTED_RESULT);
	}

}
