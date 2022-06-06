package com.springbookstore_app.springbookstore_app.repository;

import com.springbookstore_app.springbookstore_app.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order,Integer> {
}
