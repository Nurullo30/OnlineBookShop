package com.company.signIn;

import com.company.DTO.UserDTO;
import com.company.Exceptions.UserNotFoundException;
import com.company.commonService.UserRole;
import com.company.entities.EdsUser;

public interface SignInService {

    UserDTO checkUserExist(String login, String password) throws UserNotFoundException;

    void relocateUserToItsPanel(UserRole userRole);

}
