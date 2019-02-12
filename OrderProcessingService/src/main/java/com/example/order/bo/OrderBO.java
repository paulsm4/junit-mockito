package com.example.order.bo;

import com.example.order.bo.exception.BOException;
import com.example.order.dto.Order;

public interface OrderBO {
	boolean placeOrder (Order order) throws BOException;
	boolean cancelOrder(int id) throws BOException;
	boolean deleteOrder(int id) throws BOException;

}
