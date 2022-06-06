package com.springbookstore_app.springbookstore_app.service;

import com.springbookstore_app.springbookstore_app.dto.BookDTO;
import com.springbookstore_app.springbookstore_app.model.Book;

import java.util.List;

public interface IBookService {
    Book insertBook (BookDTO bookDTO);
    List<Book> getAllBooks();
    Book getBookById(int bookId);

    void deleteBookById(int bookId);
    public List<Book> getBookByName(String bookName);
    Book updateBookById(int bookId, BookDTO bookDTO);
    public List<Book> sortingBookInAsce();
    public List<Book> sortingBookInDesc();
    public List<Book> getBookByAuthorName(String authorName);
}
