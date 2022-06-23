package com.company.signIn;


import com.company.DAO.UserDAO;
import com.company.DTO.UserDTO;
import com.company.Exceptions.UserNotFoundException;
import com.company.commonService.UserRole;
import com.company.entities.EdsUser;

import java.util.ArrayList;
import java.util.List;

public class SignInImpl implements SignInService {
    private List<EdsUser> edsUsers;
    private UserDAO userDAO;
    private UserDTO userDTO;

    public SignInImpl(UserDAO userDAO) {
        edsUsers = new ArrayList<>();
        this.userDAO = userDAO;
    }

    @Override
    public UserDTO checkUserExist(String login, String password) throws UserNotFoundException {
        EdsUser edsUser = userDAO.checkForUser(login, password);
          UserDTO userDTO = new UserDTO();
            userDTO.setUID(edsUser.getUID());
            userDTO.setName(edsUser.getName());
            userDTO.setSurname(edsUser.getSurname());
            userDTO.setAge(edsUser.getAge());
            userDTO.setGender(edsUser.getGender());
            userDTO.setUserRole(edsUser.getUserRole().toString().equals(UserRole.USER.toString()) ?
                    UserRole.USER : UserRole.ADMIN);
         return userDTO;
    }

    public void relocateUserToItsPanel(UserRole userRole){
//        switch (userRole){
//            case USER:
//                try {
//                    userPanelView.startMenu(edsUser.getUID());
//                } catch (UserNotFoundException e) {
//                    e.printStackTrace();
//                }
//            case ADMIN:
//                adminPanelView.startMenu();
//        }
    }
}
