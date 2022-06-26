package com.company.servlets.userServlets;

import com.company.DTO.BookDTO;
import com.company.Exceptions.NoBookFoundException;
import com.company.constants.BeanConstants;
import com.company.constants.Constants;
import com.company.factory.ClassFactory;
import com.company.service.BookStoreService;
import com.company.service.userPanel.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet(value = "/processOrder")
public class ProcessOrderServlet extends HttpServlet {
    UserService userService;
    BookStoreService bookStoreService;
    String orderStatus;
    public ProcessOrderServlet(){
        if (userService == null){
            userService = (UserService) ClassFactory.getServices(BeanConstants.USER_SERVICE);
        }
        if (bookStoreService == null){
            bookStoreService = (BookStoreService) ClassFactory.getServices(BeanConstants.BOOK_STORE_SERVICE);
        }
    }
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        resp.sendRedirect("index.jsp");
    }


    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        HttpSession session = req.getSession();
        List<BookDTO> basketBooks = (List<BookDTO>) session.getAttribute("basketBooks");
        String userID = (String) session.getAttribute("userId");

        if (basketBooks != null && basketBooks.size() > 0){
            orderStatus  = userService.saveUserOrders(basketBooks , userID);
            basketBooks.clear();
            session.setAttribute("orderStatus",orderStatus.equals(Constants.SUCCESSFUL) ? "success" : "failed");
            resp.sendRedirect("orderFinalizedStatus.jsp");
        } else {
            resp.sendRedirect("index.jsp");
        }
    }
}
