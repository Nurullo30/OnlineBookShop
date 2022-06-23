package com.company.servlets.userServlets;

import com.company.DTO.UserDTO;
import com.company.Exceptions.UserNotFoundException;
import com.company.constants.BeanConstants;
import com.company.constants.Constants;
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

@WebServlet(value = "/editProfile")
public class ProfileEditServlet extends HttpServlet {

    UserService userService;
    HttpSession session;
    RequestDispatcher dispatcher;
    public ProfileEditServlet(){
        super();
        if (userService == null){
            userService = (UserService) ClassFactory.getServices(BeanConstants.USER_SERVICE);
        }
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (session == null){
            session = req.getSession();
        }
        String userId = (String)session.getAttribute("userId");
        dispatcher = req.getRequestDispatcher("editProfile.jsp");
        try {
            UserDTO userDTO = userService.getUserProfile(userId);
            req.setAttribute("userData", userDTO );
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }
        dispatcher.forward(req,resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String userId = (String)session.getAttribute("userId");


        UserDTO userDTO = new UserDTO();
        userDTO.setName((String) req.getParameter("name"));
        userDTO.setSurname((String) req.getParameter("surname"));
        userDTO.setGender((String) req.getParameter("gender"));
        userDTO.setAge((String) req.getParameter("age"));
        userDTO.setUID(userId);

        String updateStatus = userService.updateUserCredentials(userDTO);

        if (updateStatus.equals(Constants.SUCCESSFUL)){
            dispatcher = req.getRequestDispatcher("editProfile.jsp");
            req.setAttribute("status",Constants.SUCCESSFUL);
        } else {
            req.setAttribute("status",Constants.FAILED);
        }
        dispatcher.forward(req,resp);
    }
}
