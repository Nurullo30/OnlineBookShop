package com.company.servlets.userServlets;

import com.company.DTO.UserDTO;
import com.company.Exceptions.UserNotFoundException;
import com.company.constants.BeanConstants;
import com.company.constants.CommonConstants;
import com.company.factory.ClassFactory;
import com.company.service.userPanel.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(value = "/myProfile")
public class MyProfileServlet extends HttpServlet {
    UserService userService;
    public MyProfileServlet(){
        super();
        if (userService == null){
            userService = (UserService) ClassFactory.getServices(BeanConstants.USER_SERVICE);
        }
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String userId = (String)session.getAttribute("userId");
        RequestDispatcher dispatcher = req.getRequestDispatcher("myProfile.jsp");
        try {
         UserDTO userDTO = userService.getUserProfile(userId);
            req.setAttribute("userData", userDTO );
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }
        dispatcher.forward(req,resp);
    }
}
