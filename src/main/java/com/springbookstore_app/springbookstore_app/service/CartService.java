package com.springbookstore_app.springbookstore_app.service;

import com.springbookstore_app.springbookstore_app.Util.EmailSenderService;
import com.springbookstore_app.springbookstore_app.Util.TokenUtility;
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
public class CartService implements ICartService {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private UserRegistrationRepository userRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    EmailSenderService mailService;

    @Autowired
    TokenUtility util;

    @Override
    public String insertItems(CartDTO cartdto) {
        Optional<Book> book = bookRepository.findById(cartdto.getBookId());
        Optional<UserRegistration> userRegistration = userRepository.findById(cartdto.getUserId());
        if (book.isPresent() && userRegistration.isPresent()) {
            Cart newCart = new Cart(cartdto.getQuantity(), book.get(), userRegistration.get());
            cartRepository.save(newCart);
            String token = util.createToken(newCart.getCartId());
            return token;
        } else {
            throw new BookStoreCustomException("Book or User does not exists");
        }
    }


    @Override
    public List<Cart> getCartDetails(String token) {
        int id = util.decodeToken(token);
        Optional<Cart> cartData = cartRepository.findById(id);
        if (cartData.isPresent()) {
            List<Cart> listOfCartdata = cartRepository.findAll();
            log.info("ALL cart records retrieved successfully");
            return listOfCartdata;
        } else {
            System.out.println("Exception ...Token not found!");
            return null;
        }
    }

    @Override
    public Cart getCartDetailsById(String token) {
        int id = util.decodeToken(token);
        Optional<Cart> CartData = cartRepository.findById(id);
        if (CartData.isPresent()) {
            return CartData.get();
        } else {
            throw new BookStoreCustomException(" Didn't find any record for this particular cartId");
        }
    }

    @Override
    public void deleteCartItemById(String token) {
        int id = util.decodeToken(token);
        Optional<Cart> delete = cartRepository.findById(id);
        if (delete.isPresent()) {
            cartRepository.deleteById(id);
        } else {
            throw new BookStoreCustomException(" Did not get any cart for specific cart id ");
        }
    }

    @Override
    public Cart updateRecordById(String token, CartDTO cartDTO) {
        int id = util.decodeToken(token);
        Optional<Cart> cart = cartRepository.findById(id);
        Optional<Book> book = bookRepository.findById(cartDTO.getBookId());
        Optional<UserRegistration> user = userRepository.findById(cartDTO.getUserId());
        if (cart.isPresent()) {
            if (book.isPresent() && user.isPresent()) {
                Cart cartData = new Cart(id, cartDTO.getQuantity(), book.get(), user.get());
                cartRepository.save(cartData);
                log.info("Cart record updated successfully for id " + id);
                return cartData;
            } else {
                throw new BookStoreCustomException("Book or User doesn't exists");
            }
        } else {
            throw new BookStoreCustomException("Cart Record doesn't exists");
        }
    }
}
