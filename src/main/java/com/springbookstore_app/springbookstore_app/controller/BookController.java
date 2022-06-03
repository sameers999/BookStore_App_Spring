package com.springbookstore_app.springbookstore_app.controller;

import com.springbookstore_app.springbookstore_app.dto.BookDTO;
import com.springbookstore_app.springbookstore_app.dto.ResponseDTO;
import com.springbookstore_app.springbookstore_app.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.springbookstore_app.springbookstore_app.model.Book;
import javax.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    IBookService bookService;

    @PostMapping("/insert")
    public ResponseEntity<ResponseDTO> insert(@Valid @RequestBody BookDTO bookDTO) {
        Book book = bookService.insertBook(bookDTO);
        ResponseDTO responseDTO = new ResponseDTO("created Book data successfully", book);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/get")
    public ResponseEntity<ResponseDTO> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        ResponseDTO responseDTO = new ResponseDTO("Get all books call Success", books);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/get/{bookId}")
    public ResponseEntity<ResponseDTO> getBookById(@PathVariable("bookId") int bookId) {
        Book book = bookService.getBookById(bookId);
        ResponseDTO responseDTO = new ResponseDTO("GetBookById call Success", book);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{bookId}")
    public ResponseEntity<ResponseDTO> deleteBookById(@PathVariable("bookId") int bookId) {
        bookService.deleteBookById(bookId);
        ResponseDTO responseDTO = new ResponseDTO("deleted successfully", bookId);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }
    @GetMapping("/getbook/{bookName}")
    public ResponseEntity<ResponseDTO> getBookByBookName(@PathVariable("bookName") String bookName) {
        List<Book> books = null;
        books = bookService.getBookByName(bookName);
        ResponseDTO responseDTO = new ResponseDTO("Get book search by BookName is successful!", books);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }
    @PutMapping("/update/{bookId}")
    public ResponseEntity<ResponseDTO> updateBookByID(@PathVariable int bookId, @Valid @RequestBody BookDTO bookDTO) {
        Book updatedBooks = bookService.updateBookById(bookId, bookDTO);
        ResponseDTO responseDTO = new ResponseDTO("Books are Updated by ID successfully", updatedBooks);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }
    @GetMapping("/sortingAsce")
    public ResponseEntity<ResponseDTO> sortingByAsce() {
        List<Book> books = null ;
        books =bookService.sortingBookInAsce();
        ResponseDTO responseDTO = new ResponseDTO("Sorting Ascending call is successful! ", books);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }
    @GetMapping("/sortingDesc")
    public ResponseEntity<ResponseDTO> sortingByDesce() {
        List<Book> books = null ;
        books =bookService.sortingBookInDesc();
        ResponseDTO responseDTO = new ResponseDTO("Sorting decending call is successful! ", books);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }
}
