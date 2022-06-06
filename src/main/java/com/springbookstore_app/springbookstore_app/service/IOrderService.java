package com.springbookstore_app.springbookstore_app.service;

import com.springbookstore_app.springbookstore_app.dto.OrderDTO;
import com.springbookstore_app.springbookstore_app.model.Order;

import java.util.List;

public interface IOrderService {
    Order insertOrder(OrderDTO orderdto);

    List<Order> getAllOrderRecords();

    Order getOrderRecord(Integer id);

    Order updateOrderRecord(Integer id, OrderDTO dto);

    Order deleteOrderRecord(Integer id);
}
