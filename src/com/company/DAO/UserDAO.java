package com.company.DAO;

import com.company.DTO.UserDTO;
import com.company.Exceptions.UserNotFoundException;
import com.company.entities.EdsUser;
import com.company.enums.UserCredentials;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface UserDAO {
    List<EdsUser> getUsers();

    String registerNewUser(EdsUser edsUser) throws SQLException;

    Boolean checkUserCredentials(UserCredentials userCredential,  String userInfo);

    EdsUser checkForUser(String login , String password) throws UserNotFoundException;

    EdsUser getUserProfileByUserId(String userId) throws UserNotFoundException;

    String updateUserCredentials(UserDTO newUserData);

}
