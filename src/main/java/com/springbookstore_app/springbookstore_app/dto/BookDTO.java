package com.springbookstore_app.springbookstore_app.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public @Data class BookDTO {
    @Pattern(regexp = "^[A-Z]{1}[a-zA-Z\\s]{2,}$", message = "Invalid First Letter should be upperCase in BookName!!")
    public String bookName;
    @Pattern(regexp = "^[A-Z]{1}[a-zA-Z\\s]{2,}$", message = "Invalid First Letter should be upperCase in AuthorName!!")
    public String authorName;
    @NotBlank(message = "Please write Book description !!")
    public String bookDescription;

    public String bookImg;

    public Integer price;

    public Integer quantity;

    public BookDTO(String bookName, String authorName, String bookDescription, String bookImg, Integer price, Integer quantity) {
        this.bookName = bookName;
        this.authorName = authorName;
        this.bookDescription = bookDescription;
        this.bookImg = bookImg;
        this.price = price;
        this.quantity = quantity;
    }
}