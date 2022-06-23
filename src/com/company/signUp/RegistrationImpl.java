package com.company.signUp;

import com.company.constants.CommonConstants;
import com.company.commonService.Validation;
import com.company.constants.Constants;
import com.company.entities.EdsUser;
import com.company.DAO.UserDAO;
import com.company.commonService.UserRole;
import com.company.enums.UserCredentials;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

// @Service
public class RegistrationImpl implements RegistrationService{
    private UserDAO userDAO;
    private Validation validation;
    private Connection conn;

    public RegistrationImpl(UserDAO userDAO){
        this.userDAO = userDAO;
        validation = new Validation();
    }

    @Override
    public String registration(String name, String surname, String age, String login, String password ,String gender) {
        boolean isLoginExist = userDAO.checkUserCredentials(UserCredentials.LOGIN , login);
        if (isLoginExist){
            return Constants.FAILED;
        }

        EdsUser newEdsUser = new EdsUser();
        newEdsUser.setUID("U" + (userDAO.getUsers().size() + 1));
        newEdsUser.setName(name);
        newEdsUser.setSurname(surname);
        newEdsUser.setAge(age);
        newEdsUser.setLogin(login);
        newEdsUser.setPassword(password);
        newEdsUser.setGender(gender);
        newEdsUser.setUserRole(UserRole.USER);
        String newUserStatus = null;
        try {
            newUserStatus = userDAO.registerNewUser(newEdsUser);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newUserStatus;
    }

    public String checkIfLoginExist(String detail){
        String newUserLogin =(validation.checkIfLoginExist(detail) ? CommonConstants.LOGIN_FREE : CommonConstants.USER_EXIST);
        return newUserLogin;
    }
}
