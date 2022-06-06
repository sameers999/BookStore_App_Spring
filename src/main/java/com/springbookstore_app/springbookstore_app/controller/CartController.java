package com.springbookstore_app.springbookstore_app.controller;

import com.springbookstore_app.springbookstore_app.dto.CartDTO;
import com.springbookstore_app.springbookstore_app.dto.ResponseDTO;
import com.springbookstore_app.springbookstore_app.model.Cart;
import com.springbookstore_app.springbookstore_app.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private ICartService iCartService;

    @PostMapping("/insert")
    public ResponseEntity<ResponseDTO> insertItem(@Valid @RequestBody CartDTO cartdto) {
        System.out.println(cartdto);
        Cart newCart = iCartService.insertItems(cartdto);
        ResponseDTO responseDTO = new ResponseDTO("User registered successfully !", newCart);
        return new ResponseEntity(responseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    public ResponseDTO getAllCartDetails() {
        ResponseDTO responseDTO = iCartService.getCartDetails();
        return responseDTO;
    }

    @GetMapping("/getById/{cartId}")
    public ResponseEntity<ResponseDTO> getCartDetailsById(@PathVariable Integer cartId) {
        Cart specificCartDetail = iCartService.getCartDetailsById(cartId);
        ResponseDTO responseDTO = new ResponseDTO("Cart details retrieved successfully", specificCartDetail);
        return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/{cartId}")
    public ResponseEntity<ResponseDTO> deleteCartById(@PathVariable Integer cartId) {
        Cart delete = iCartService.deleteCartItemById(cartId);
        ResponseDTO responseDTO = new ResponseDTO("Cart delete successfully", delete);
        return new ResponseEntity(responseDTO, HttpStatus.OK);
    }

    @PutMapping("/updateById/{cartId}")
    public ResponseEntity<ResponseDTO> updateCartById(@PathVariable Integer cartId, @Valid @RequestBody CartDTO cartDTO) {
        Cart updateRecord = iCartService.updateRecordById(cartId, cartDTO);
        ResponseDTO dto = new ResponseDTO(" Cart Record updated successfully by Id", updateRecord);
        return new ResponseEntity(dto, HttpStatus.ACCEPTED);
    }
}
