package com.springbookstore_app.springbookstore_app.repository;

import com.springbookstore_app.springbookstore_app.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
}
