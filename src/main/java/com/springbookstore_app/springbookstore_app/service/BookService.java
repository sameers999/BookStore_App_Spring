package com.springbookstore_app.springbookstore_app.service;

import com.springbookstore_app.springbookstore_app.exception.BookStoreCustomException;
import com.springbookstore_app.springbookstore_app.model.Book;
import com.springbookstore_app.springbookstore_app.dto.BookDTO;
import com.springbookstore_app.springbookstore_app.repository.BookRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService implements IBookService {
    @Autowired
    private BookRepository bookRepository;

    @Override
    public Book insertBook(BookDTO bookDTO) {
        Book book = null;
        book = new Book(bookDTO);
        return bookRepository.save(book);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book getBookById(int bookId) {
        return bookRepository.findById(bookId)
                .orElseThrow(() ->
                        new BookStoreCustomException("Book with this id " + bookId + " doest not exists!"));
    }

    @Override
    public void deleteBookById(int bookId) {
        Book book = this.getBookById(bookId);
        bookRepository.delete(book);
    }

    @Override
    public List<Book> getBookByName(String bookName) {
        return bookRepository.findBookByName(bookName);
    }

    @Override
    public Book updateBookById(int bookId, BookDTO bookDTO) {
        Book book = this.getBookById(bookId);
        book.updateBook(bookDTO);
        return bookRepository.save(book);
    }

    @Override
    public List<Book> sortingBookInAsce() {
        return bookRepository.sortingInAsce();
    }

    @Override
    public List<Book> sortingBookInDesc() {
        return bookRepository.sortingInDesc();
    }

    @Override
    public List<Book> getBookByAuthorName(String authorName) {
        List<Book> findBook = bookRepository.findBookByAuthorName(authorName);
        if (findBook.isEmpty()) {
            throw new BookStoreCustomException(" Details for provided Book is not found");
        }
        return findBook;
    }
    }

