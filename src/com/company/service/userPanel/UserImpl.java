package com.company.service.userPanel;

import com.company.DAO.BookDAO;
import com.company.DTO.BookDTO;
import com.company.DTO.UserDTO;
import com.company.Exceptions.UserNotFoundException;
import com.company.commonService.UserRole;
import com.company.constants.CommonConstants;
import com.company.entities.Book;
import com.company.entities.EdsUser;
import com.company.constants.Constants;
import com.company.DAO.UserDAO;
import com.company.factory.ClassFactory;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserImpl  implements UserService {

    private UserDAO userDAO;
    private BookDAO bookDAO;
    private Connection conn;
    private UserDTO userDTO;

    public UserImpl(BookDAO bookDAO, UserDAO userDAO){
        this.bookDAO = bookDAO;
        this.userDAO = userDAO;
        this.conn = ClassFactory.connectToDB();
    }



    @Override
    public UserDTO getUserProfile(String userId) throws UserNotFoundException {
        EdsUser edsUser = userDAO.getUserProfileByUserId(userId);
        userDTO = new UserDTO();
        userDTO.setUID(edsUser.getUID());
        userDTO.setName(edsUser.getName());
        userDTO.setSurname(edsUser.getSurname());
        userDTO.setAge(edsUser.getAge());
        userDTO.setGender(edsUser.getGender());
        userDTO.setUserRole(edsUser.getUserRole().toString().equals(UserRole.USER.toString()) ? UserRole.USER : UserRole.ADMIN);
        return userDTO;
    }

    public String updateUserCredentials(UserDTO newUserData){
        String [] userData = {newUserData.getName(), newUserData.getSurname() ,newUserData.getAge(), newUserData.getGender()};
        for (String data: userData) {
            if (data.length() == 0)
                return Constants.FAILED;
        }
        String updateStatus = userDAO.updateUserCredentials(newUserData);
        if (updateStatus.equals(Constants.SUCCESSFUL)){
            return Constants.SUCCESSFUL;
        }
        return Constants.FAILED;
    }



    @Override
    public void saveUserOrders(List<BookDTO> orders, String userID) {
        // deduct book
        // top up balance
        bookDAO.saveBooks(orders, userID);
    }

    public void saveSingleItem(BookDTO bookDTO, String userId){
        bookDAO.saveSingleBook(bookDTO, userId);
    }


}
