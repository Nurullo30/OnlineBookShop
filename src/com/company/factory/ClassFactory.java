package com.company.factory;

import com.company.DAO.BookDAO;
import com.company.DAO.BookRepository;
import com.company.DAO.UserDAO;
import com.company.DAO.UserRepository;
import com.company.constants.BeanConstants;
import com.company.service.BookStoreImpl;
import com.company.service.BookStoreService;
import com.company.service.userPanel.UserImpl;
import com.company.service.userPanel.UserService;
import com.company.signIn.SignInImpl;
import com.company.signIn.SignInService;
import com.company.signUp.RegistrationImpl;
import com.company.signUp.RegistrationService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ClassFactory {

    private static UserDAO userDAO = new UserRepository();
    private static BookDAO bookDAO = new BookRepository();

    private static RegistrationService registrationService;
    private static SignInService signInService;
    private static UserService userService;
    private static BookStoreService bookStoreService;

    public static Connection connectToDB() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            return  DriverManager.getConnection("jdbc:postgresql://localhost:5432/bookStore","postgres", "1");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Object getServices(String beanName){
        Object object = new Object();

        switch (beanName){
            case BeanConstants.REGISTRATION_SERVICE:
                if (registrationService == null){
                    registrationService = new RegistrationImpl(userDAO);
                }
                object = registrationService;
                break;

            case BeanConstants.SIGN_IN_SERVICE:
                if (signInService == null){
                    signInService = new SignInImpl(userDAO);
                }
                object = signInService;
                break;
            case BeanConstants.USER_SERVICE:
                if (userService == null){
                    userService = new UserImpl(bookDAO, userDAO);
                }
                object = userService;
                break;
            case BeanConstants.BOOK_STORE_SERVICE:
                if (bookStoreService == null){
                    bookStoreService = new BookStoreImpl(bookDAO);
                }
                object = bookStoreService;
                break;
        }
        return object;
    }
}
