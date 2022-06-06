package com.springbookstore_app.springbookstore_app.service;

import com.springbookstore_app.springbookstore_app.dto.OrderDTO;
import com.springbookstore_app.springbookstore_app.exception.BookStoreCustomException;
import com.springbookstore_app.springbookstore_app.model.Book;
import com.springbookstore_app.springbookstore_app.model.Order;
import com.springbookstore_app.springbookstore_app.model.UserRegistration;
import com.springbookstore_app.springbookstore_app.repository.BookRepository;
import com.springbookstore_app.springbookstore_app.repository.OrderRepository;
import com.springbookstore_app.springbookstore_app.repository.UserRegistrationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class OrderService implements IOrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRegistrationRepository userRepository;


    @Override
    public Order insertOrder(OrderDTO orderdto) {
        Optional<Book> book = bookRepository.findById(orderdto.getBookId());
        Optional<UserRegistration> user = userRepository.findById(orderdto.getUserId());
        if (book.isPresent() && user.isPresent()) {
            if (orderdto.getQuantity() <= book.get().getQuantity()) {
                int quantity = book.get().getQuantity() - orderdto.getQuantity();
                book.get().setQuantity(quantity);
                bookRepository.save(book.get());
                Order newOrder = new Order(book.get().getPrice(), orderdto.getQuantity(), orderdto.getAddress(), book.get(), user.get(), orderdto.isCancel());
                orderRepository.save(newOrder);
                log.info("Order record inserted successfully");
                return newOrder;
            } else {
                throw new BookStoreCustomException("Requested quantity is out of stock");
            }
        } else {
            throw new BookStoreCustomException("Book or User doesn't exists");
        }
    }


    @Override
    public List<Order> getAllOrderRecords() {
        List<Order> orderList = orderRepository.findAll();
        log.info("ALL order records retrieved successfully");
        return orderList;
    }

    @Override
    public Order getOrderRecord(Integer id) {
        Optional<Order> order = orderRepository.findById(id);
        if (order.isPresent()) {
            log.info("Order record retrieved successfully for id " + id);
            return order.get();

        } else {
            throw new BookStoreCustomException("Order Record doesn't exists");
        }
    }

    @Override
    public Order updateOrderRecord(Integer id, OrderDTO dto) {
        Optional<Order> order = orderRepository.findById(id);
        Optional<Book> book = bookRepository.findById(dto.getBookId());
        Optional<UserRegistration> user = userRepository.findById(dto.getUserId());
        if (order.isPresent()) {
            if (book.isPresent() && user.isPresent()) {
                if (dto.getQuantity() <= book.get().getQuantity()) {
                    int quantity = book.get().getQuantity() - dto.getQuantity();
                    book.get().setQuantity(quantity);
                    bookRepository.save(book.get());
                    Order newOrder = new Order(id, book.get().getPrice(), dto.getQuantity(), dto.getAddress(), book.get(), user.get(), dto.isCancel());
                    orderRepository.save(newOrder);
                    log.info("Order record updated successfully for id " + id);
                    return newOrder;
                } else {
                    throw new BookStoreCustomException("Requested quantity is not available");
                }
            } else {
                throw new BookStoreCustomException("Book or User doesn't exists");

            }

        } else {
            throw new BookStoreCustomException("Order Record doesn't exists");
        }
    }

    @Override
    public Order deleteOrderRecord(Integer id) {
        Optional<Order> order = orderRepository.findById(id);
        if (order.isPresent()) {
            orderRepository.deleteById(id);
            log.info("Order record deleted successfully for id " + id);
            return order.get();

        } else {
            throw new BookStoreCustomException("Order Record doesn't exists");
        }
    }
}
