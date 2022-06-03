package com.springbookstore_app.springbookstore_app.service;

import com.springbookstore_app.springbookstore_app.dto.BookDTO;
import com.springbookstore_app.springbookstore_app.model.Book;

import java.util.List;

public interface IBookService {
    com.springbookstore_app.springbookstore_app.model.Book insertBook (BookDTO bookDTO);
    List<com.springbookstore_app.springbookstore_app.model.Book> getAllBooks();
    com.springbookstore_app.springbookstore_app.model.Book getBookById(int bookId);
    void deleteBookById(int bookId);
    public List<com.springbookstore_app.springbookstore_app.model.Book> getBookByName(String bookName);
    com.springbookstore_app.springbookstore_app.model.Book updateBookById(int bookId, BookDTO bookDTO);
    public List<com.springbookstore_app.springbookstore_app.model.Book> sortingBookInAsce();
    public List<Book> sortingBookInDesc();
}
