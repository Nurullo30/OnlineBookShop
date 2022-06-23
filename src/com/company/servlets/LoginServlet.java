package com.company.servlets;

import com.company.DAO.UserDAO;
import com.company.DTO.UserDTO;
import com.company.Exceptions.UserNotFoundException;
import com.company.commonService.UserRole;
import com.company.constants.BeanConstants;
import com.company.factory.ClassFactory;
import com.company.signIn.SignInImpl;
import com.company.signIn.SignInService;
import com.company.signUp.RegistrationImpl;
import com.company.signUp.RegistrationService;
import org.apache.catalina.Session;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(value = "/login")
public class LoginServlet extends HttpServlet {
    private SignInService signInService;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        HttpSession session = req.getSession();
        String userId = (String) session.getAttribute("userId");
        RequestDispatcher dispatcher = null;
        if (userId != null) {
            dispatcher = req.getRequestDispatcher("/seeAllBooks?panel=seeAllBooks&page=1");
        } else{
            dispatcher = req.getRequestDispatcher("Login.jsp");
        }
        dispatcher.forward(req,resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = null;
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        if (signInService == null){
            signInService = (SignInService) ClassFactory.getServices(BeanConstants.SIGN_IN_SERVICE);
        }
        try {
           UserDTO userDTO =  signInService.checkUserExist(login, password);
           if (userDTO.getUserRole().equals(UserRole.USER)){
               HttpSession session = req.getSession();
               session.setAttribute("userId", userDTO.getUID());
               dispatcher = req.getRequestDispatcher("index.jsp");
           }
        } catch (UserNotFoundException u) {
           req.setAttribute("status", "failed");
           dispatcher = req.getRequestDispatcher("Login.jsp");

        }
        dispatcher.forward(req,resp);
    }
}
