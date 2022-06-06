package com.springbookstore_app.springbookstore_app.service;

import com.springbookstore_app.springbookstore_app.dto.OrderDTO;
import com.springbookstore_app.springbookstore_app.model.Order;

import java.util.List;

public interface IOrderService {
    String insertOrder(OrderDTO orderdto);

    List<Order> getOrderRecord(String token);

    List<Order> getAllOrderRecords(String token);


    Order cancelOrder(String token, int userId);
}
