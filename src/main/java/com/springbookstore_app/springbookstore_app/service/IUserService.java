package com.springbookstore_app.springbookstore_app.service;

import com.springbookstore_app.springbookstore_app.dto.ResponseDTO;
import com.springbookstore_app.springbookstore_app.dto.UserDTO;
import com.springbookstore_app.springbookstore_app.dto.UserLoginDTO;
import com.springbookstore_app.springbookstore_app.model.UserRegistration;

import java.util.List;

public interface IUserService {
    UserRegistration createUser(UserDTO userDTO);

    List<UserRegistration> getAllUsers();

    UserRegistration getById(int id);

    ResponseDTO loginUser(UserLoginDTO userLoginDTO);

    List<UserRegistration> getByEmailId(String email_id);

    UserRegistration updateUserBookStoreData(int id, UserDTO userDTO);
}
