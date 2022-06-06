package com.springbookstore_app.springbookstore_app.service;

import com.springbookstore_app.springbookstore_app.Util.EmailSenderService;
import com.springbookstore_app.springbookstore_app.Util.TokenUtility;
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
    private OrderRepository orderRepo;

    @Autowired
    private BookRepository bookRepo;

    @Autowired
    private UserRegistrationRepository userRepo;
    @Autowired
    EmailSenderService mailService;
    @Autowired
    TokenUtility util;

    @Override
    public String insertOrder(OrderDTO orderdto) {
        Optional<Book> book = bookRepo.findById(orderdto.getBookId());
        Optional<UserRegistration> user = userRepo.findById(orderdto.getUserId());
        if (book.isPresent() && user.isPresent()) {
            if (orderdto.getQuantity() <= book.get().getQuantity()) {
                int quantity = book.get().getQuantity() - orderdto.getQuantity();
                book.get().setQuantity(quantity);
                bookRepo.save(book.get());
                int totalPrice = book.get().getPrice() * orderdto.getQuantity();
                Order newOrder = new Order(totalPrice, orderdto.getQuantity(), orderdto.getAddress(), book.get(), user.get(), orderdto.isCancel());
                orderRepo.save(newOrder);
                log.info("Order record inserted successfully");
                String token = util.createToken(newOrder.getOrderID());
                mailService.sendEmail(newOrder.getUser().getEmail(), "Test Email", "Order placed successfully, hii: "
                        + newOrder.getUser().getEmail() + "Please Click here to get data-> "
                        + "http://localhost:8087/order/getById" + token);
                log.info("Order record inserted successfully");
                return token;
            } else {
                throw new BookStoreCustomException("Requested quantity is out of stock");
            }
        } else {
            throw new BookStoreCustomException("Book or User doesn't exists");
        }
    }
    @Override
    public List<Order> getOrderRecord(String token) {
        Integer id = util.decodeToken(token);
        Optional<Order> order = orderRepo.findById(id);
        if (order.isPresent()) {
            List<Order> listOrder = orderRepo.findAll();
            log.info("Order record retrieved successfully for id " + id);
            mailService.sendEmail("syedmahammadsameer999@gmail.com", "Test Email", "Get your data with this token, hii: "
                    + order.get().getUser().getEmail()+ "Please Click here to get all data-> "
                    + "http://localhost:8087/order/getById/" + token);
            return listOrder;
        } else {
            throw new BookStoreCustomException("Order Record doesn't exists");
        }
    }

    @Override
    public List<Order> getAllOrderRecords(String token) {
        Integer id = util.decodeToken(token);
        Optional<Order> orderData = orderRepo.findById(id);
        if (orderData.isPresent()) {
            List<Order> listOrderData = orderRepo.findAll();
            log.info("ALL order records retrieved successfully");
            mailService.sendEmail("syedmahammadsameer999@gmail.com", "Test Email", "Get your data with this token, hii: "
                    + orderData.get().getUser().getEmail() + "Please Click here to get all data-> "
                    + "http://localhost:8087/order/getAllOrders/" + token);
            return listOrderData;
        } else {
            System.out.println("Exception ...Token not found!");
            return null;
        }

    }

    @Override
    public Order cancelOrder(String token, int userId) {
        Integer id=util.decodeToken(token);
        Optional<Order> order = orderRepo.findById(id);
        Optional<UserRegistration> user = userRepo.findById(userId);
        if (order.isPresent() && user.isPresent()) {
            order.get().setCancel(true);
            orderRepo.save(order.get());
            mailService.sendEmail(order.get().getUser().getEmail(), "Test Email", "canceled order SuccessFully, hii: "
                    +order.get().getOrderID()+"Please Click here to get data of updated id-> "
                    +"http://localhost:8087/order/cancelOrder/"+token);
            return order.get();
        } else {
            throw new BookStoreCustomException("Order Record doesn't exists");
        }
    }
}
