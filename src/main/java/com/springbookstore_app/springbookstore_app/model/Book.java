package com.springbookstore_app.springbookstore_app.model;

import com.springbookstore_app.springbookstore_app.dto.BookDTO;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "book_store")
public @Data class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "book_Id")
    private Integer bookId;

    private String bookName;

    private String authorName;

    private String bookDescription;

    private String bookImg;

    private String price;

    private String quantity;

    public Book() {

    }

    public Book(BookDTO bookDTO) {
        this.updateBook(bookDTO);
    }

    public void updateBook(BookDTO bookDTO) {
        this.bookName = bookDTO.getBookName();
        this.authorName = bookDTO.getAuthorName();
        this.bookDescription = bookDTO.getBookDescription();
        this.bookImg = bookDTO.getBookImg();
        this.price = bookDTO.getPrice();
        this.quantity = bookDTO.getQuantity();

    }
}
