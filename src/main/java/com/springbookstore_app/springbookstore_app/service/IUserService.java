package com.springbookstore_app.springbookstore_app.service;

import com.springbookstore_app.springbookstore_app.dto.ResponseDTO;
import com.springbookstore_app.springbookstore_app.dto.UserDTO;
import com.springbookstore_app.springbookstore_app.dto.UserLoginDTO;
import com.springbookstore_app.springbookstore_app.model.UserRegistration;

import java.util.List;

public interface IUserService {
    String addUser(UserDTO userDTO);
    String verifyUser(String token);
    List<UserRegistration> getAllUsers();

    ResponseDTO loginUser(UserLoginDTO userLoginDTO);

    Object getUserByToken(String token);

    UserRegistration updateUser(String email, UserDTO userDTO);

    List<UserRegistration> getAllUserDataByToken(String token);

    UserRegistration updateRecordById(Integer id, UserDTO userDTO);

}
