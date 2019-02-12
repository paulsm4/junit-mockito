package com.example.order.bo;

import java.sql.SQLException;

import com.example.order.bo.exception.BOException;
import com.example.order.dao.OrderDAO;
import com.example.order.dto.Order;

public class OrderBOImpl implements OrderBO {

	private OrderDAO dao;
	
	public boolean placeOrder(Order order) throws BOException {
		try {
			int result = dao.create(order);
			if (result == 0)
				return false;
		} catch (SQLException e) {
			throw new BOException (e);
		}
		return true;
	}

	public boolean cancelOrder(int id) throws BOException {
		try {
			Order order = dao.read(id);
			order.setStatus("cancelled");
			int result = dao.update(order);
			if (result == 0)
				return false;
		} catch (SQLException e) {
			throw new BOException (e);
		}
		return true;
	}

	public boolean deleteOrder(int id) throws BOException {
		try {
			int result = dao.delete(id);
			if (result == 0)
				return false;
		} catch (SQLException e) {
			throw new BOException (e);
		}
		return true;
	}

	public OrderDAO getDao() {
		return dao;
	}

	public void setDao(OrderDAO dao) {
		this.dao = dao;
	}
	
}