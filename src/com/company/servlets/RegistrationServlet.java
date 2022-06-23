package com.company.servlets;

import com.company.DAO.UserDAO;
import com.company.constants.BeanConstants;
import com.company.constants.Constants;
import com.company.factory.ClassFactory;
import com.company.signUp.RegistrationImpl;
import com.company.signUp.RegistrationService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/Registration")
public class RegistrationServlet extends HttpServlet {
    RegistrationService regService;
    public RegistrationServlet() {
        super();
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/userRegistration.jsp");
        dispatcher.forward(req,resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter("firstname");
        String lastname = req.getParameter("lastname");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String age = req.getParameter("age");
        String gender = req.getParameter("gender");

        if (regService == null){
            regService = (RegistrationService) ClassFactory.getServices(BeanConstants.REGISTRATION_SERVICE);
        }

        String regStatus =  regService.registration(firstName, lastname,  age , login, password, gender);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/userRegistration.jsp");
        if (regStatus.equals(Constants.SUCCESSFUL)){
            req.setAttribute("status",Constants.SUCCESSFUL);
            req.setAttribute("lastname",lastname);
            req.setAttribute("firstname",firstName);
            dispatcher.forward(req,resp);
        } else {
            req.setAttribute("status",Constants.FAILED);
            dispatcher.forward(req,resp);
        }

    }

}
