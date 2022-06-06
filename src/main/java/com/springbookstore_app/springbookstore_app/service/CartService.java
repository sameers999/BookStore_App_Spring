package com.springbookstore_app.springbookstore_app.service;

import com.springbookstore_app.springbookstore_app.dto.CartDTO;
import com.springbookstore_app.springbookstore_app.dto.ResponseDTO;
import com.springbookstore_app.springbookstore_app.exception.BookStoreCustomException;
import com.springbookstore_app.springbookstore_app.model.Book;
import com.springbookstore_app.springbookstore_app.model.Cart;
import com.springbookstore_app.springbookstore_app.model.UserRegistration;
import com.springbookstore_app.springbookstore_app.repository.BookRepository;
import com.springbookstore_app.springbookstore_app.repository.CartRepository;
import com.springbookstore_app.springbookstore_app.repository.UserRegistrationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CartService implements ICartService{
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private UserRegistrationRepository userRepository;
    @Autowired
    private BookRepository bookRepository;

    @Override
    public Cart insertItems(CartDTO cartdto) {
        System.out.println(cartdto);
        Optional<Book> book = bookRepository.findById(cartdto.getBookId());
        Optional<UserRegistration> userRegistration = userRepository.findById(cartdto.getUserId());
        if (book.isPresent() && userRegistration.isPresent()) {
            Cart newCart = new Cart(cartdto.getQuantity(), book.get(), userRegistration.get());
            System.out.println(newCart);
            return cartRepository.save(newCart);
        } else {
            throw new BookStoreCustomException("Book or User does not exists");
        }
    }

    @Override
    public ResponseDTO getCartDetails() {
        List<Cart> getCartDetails = cartRepository.findAll();
        ResponseDTO dto = new ResponseDTO();
        if (getCartDetails.isEmpty()) {
            String message = " Not found Any Cart details ";
            dto.setMessage(message);
            dto.setData(0);
            return dto;
        } else {
            dto.setMessage("the list of cart items is sucussfully retrived");
            dto.setData(getCartDetails);
            return dto;
        }
    }

    @Override
    public Cart getCartDetailsById(Integer cartId) {
        Optional<Cart> getCartData = cartRepository.findById(cartId);
        if (getCartData.isPresent()) {
            return getCartData.get();
        } else {
            throw new BookStoreCustomException(" Didn't find any record for this particular cartId");
        }
    }

    @Override
    public Cart deleteCartItemById(Integer cartId) {
        Optional<Cart> deleteData = cartRepository.findById(cartId);
        if (deleteData.isPresent()) {
            cartRepository.deleteById(cartId);
            return deleteData.get();
        } else {
            throw new BookStoreCustomException(" Did not get any cart for specific cart id ");
        }

    }

    @Override
    public Cart updateRecordById(Integer cartId, CartDTO cartDTO) {
        Optional<Cart> cart = cartRepository.findById(cartId);
        Optional<Book> book = bookRepository.findById(cartDTO.getBookId());
        Optional<UserRegistration> user = userRepository.findById(cartDTO.getUserId());
        if (cart.isPresent()) {
            if (book.isPresent() && user.isPresent()) {
                Cart newCart = new Cart(cartDTO.getQuantity(), book.get(), user.get());
                cartRepository.save(newCart);
                log.info("Cart record updated successfully for id " + cartId);
                return newCart;
            } else {
                throw new BookStoreCustomException("Book or User doesn't exists");
            }
        } else {
            throw new BookStoreCustomException("Cart Record doesn't exists");
        }
    }
}
