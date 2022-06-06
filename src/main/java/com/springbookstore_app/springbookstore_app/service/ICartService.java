package com.springbookstore_app.springbookstore_app.service;

import com.springbookstore_app.springbookstore_app.dto.CartDTO;
import com.springbookstore_app.springbookstore_app.dto.ResponseDTO;
import com.springbookstore_app.springbookstore_app.model.Cart;

import java.util.List;

public interface ICartService {
    String  insertItems(CartDTO cartdto);

    List<Cart> getCartDetails(String token);

    Cart getCartDetailsById(String token);

    void deleteCartItemById(String token);

    Cart updateRecordById(String token, CartDTO cartDTO);
}
