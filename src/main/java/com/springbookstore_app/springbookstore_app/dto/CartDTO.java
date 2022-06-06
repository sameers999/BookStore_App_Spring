package com.springbookstore_app.springbookstore_app.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

public @Data class CartDTO {
    private Integer userId;
    private Integer bookId;
    @NotNull(message = "Book quantity need to be added")
    private Integer quantity;
}
