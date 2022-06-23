package com.company.service.userPanel;

import com.company.DTO.BookDTO;
import com.company.DTO.UserDTO;
import com.company.Exceptions.UserNotFoundException;

import java.util.List;

public interface UserService {

    void saveUserOrders(List<BookDTO> orders, String userID);

    void saveSingleItem(BookDTO bookDTO, String userId);

    UserDTO getUserProfile(String userId) throws UserNotFoundException;

    String updateUserCredentials(UserDTO newUserData);

}
