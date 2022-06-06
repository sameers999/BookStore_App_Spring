package com.springbookstore_app.springbookstore_app.service;

import com.springbookstore_app.springbookstore_app.dto.BookDTO;
import com.springbookstore_app.springbookstore_app.model.Book;

import java.util.List;

public interface IBookService {
    String createBook(BookDTO bookDTO);
    List<Book> getAllBookData(String token);
    Book getBookDataById(String token);
    List<Book> getBookByName(String bookName);
    List<Book> sortedListOfBooksInAscendingOrder();
    List<Book> sortedListOfBooksInDescendingOrder();
    String deleteRecordById(String token);
    Book updateRecordById(String token,BookDTO bookDTO);
    List<Book> getBookByAuthorName(String authorName);

    Book updataBooksByQuantity(String token, int quantity);
}
