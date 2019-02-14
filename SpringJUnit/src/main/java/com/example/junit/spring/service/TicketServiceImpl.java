package com.example.junit.spring.service;

import org.springframework.stereotype.Component;

import com.example.junit.spring.dao.TicketDAO;
import com.example.junit.spring.dto.Ticket;

@Component
public class TicketServiceImpl implements TicketService {

	private TicketDAO dao;
	
	@Override
	public int buyTicket(String passengerName, String phone) {
		Ticket ticket = new Ticket();
		ticket.setPassengerName(passengerName);;
		ticket.setPhone(phone);
		return dao.createTicket(ticket);
	}

}
