package com.springbookstore_app.springbookstore_app.service;

import com.springbookstore_app.springbookstore_app.Util.TokenUtility;
import com.springbookstore_app.springbookstore_app.exception.BookStoreCustomException;
import com.springbookstore_app.springbookstore_app.model.Book;
import com.springbookstore_app.springbookstore_app.dto.BookDTO;
import com.springbookstore_app.springbookstore_app.repository.BookRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService implements IBookService {
    @Autowired
    BookRepository bookStoreRepository;
    @Autowired
    TokenUtility util;

    @Override
    public String createBook(BookDTO bookDTO) {
        Book bookData = new Book(bookDTO);
        bookStoreRepository.save(bookData);
        String token = util.createToken(bookData.getBookId());
        return token;
    }

    @Override
    public Book getBookDataById(String token) {
        int id = util.decodeToken(token);
        Optional<Book> book = bookStoreRepository.findById(id);
        if (book.isPresent()) {
            return book.get();
        } else {
            throw new BookStoreCustomException("Exception with id" + id + "does not exist!!");
        }
    }

    @Override
    public List<Book> getAllBookData(String token) {
        int id = util.decodeToken(token);
        Optional<Book> bookData = bookStoreRepository.findById(id);
        if (bookData.isPresent()) {
            List<Book> listOfBooks = bookStoreRepository.findAll();
            return listOfBooks;
        } else {
            System.out.println("Exception ...Token not found!");
            return null;
        }
    }

    @Override
    public Book updataBooksByQuantity(String token, int quantity) {
        int id = util.decodeToken(token);
        Optional<Book> book = bookStoreRepository.findById(id);
        if (book.isPresent()) {
            Book bookData1 = new Book();
            bookData1.setQuantity(quantity);
            bookStoreRepository.save(bookData1);
            return bookData1;
        } else {
            throw new BookStoreCustomException("Bookdata record does not found");
        }
    }

    @Override
    public Book updateRecordById(String token, BookDTO bookDTO) {
        int id = util.decodeToken(token);
        Optional<Book> bookData = bookStoreRepository.findById(id);
        if (bookData.isPresent()) {
            Book updateData = new Book(id, bookDTO);
            bookStoreRepository.save(updateData);
            return updateData;
        } else {
            throw new BookStoreCustomException("Bookdata record does not found");
        }
    }

    @Override
    public String deleteRecordById(String token) {
        int id = util.decodeToken(token);
        Optional<Book> book = bookStoreRepository.findById(id);
        if (book.isPresent()) {
            bookStoreRepository.deleteById(id);
        } else {
            throw new BookStoreCustomException("Book record does not found");
        }
        return token;
    }
    @Override
    public List<Book> getBookByName(String bookName) {
        List<Book> findBook = bookStoreRepository.findByBookName(bookName);
        if (findBook.isEmpty()) {
            throw new BookStoreCustomException(" Details for provided Book is not found");
        }
        return findBook;
    }

    @Override
    public List<Book> sortedListOfBooksInAscendingOrder() {
        List<Book> getSortedList = bookStoreRepository.sortingInAsce();
        return getSortedList;
    }

    @Override
    public List<Book> sortedListOfBooksInDescendingOrder() {
        List<Book> getSortedListInDesc = bookStoreRepository.sortingInDesc();
        return getSortedListInDesc;
    }

    @Override
    public List<Book> getBookByAuthorName(String authorName) {
        List<Book> findBook = bookStoreRepository.findByBookAuthorName(authorName);
        if (findBook.isEmpty()) {
            throw new BookStoreCustomException(" Details for provided Book is not found");
        }
        return findBook;
    }
}

