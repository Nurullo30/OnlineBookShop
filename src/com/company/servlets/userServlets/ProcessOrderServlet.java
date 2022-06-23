package com.company.servlets.userServlets;

import com.company.DTO.BookDTO;
import com.company.Exceptions.NoBookFoundException;
import com.company.constants.BeanConstants;
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
import java.util.List;

@WebServlet(value = "/processOrder")
public class ProcessOrderServlet extends HttpServlet {
    UserService userService;
    BookStoreService bookStoreService;
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
        String productId = req.getParameter("product");
        HttpSession session = req.getSession();
        RequestDispatcher dispatcher = null;
        if (productId != null){
            try {
                BookDTO book = bookStoreService.getBookById(productId);
                userService.saveSingleItem(book, session.getAttribute("userId").toString());

                dispatcher = req.getRequestDispatcher("/orderStatus");
                req.setAttribute("status","success");
                dispatcher.forward(req,resp);
            } catch (NoBookFoundException e) {
                e.printStackTrace();
            }
        } else {
            List<BookDTO> basketBooks = (List<BookDTO>) session.getAttribute("basketBooks");
            if (basketBooks != null && basketBooks.size() != 0){
                String userID = (String) session.getAttribute("userId");
                userService.saveUserOrders(basketBooks , userID);
                basketBooks.clear();
                dispatcher = req.getRequestDispatcher("orderSuccess.jsp");
                req.setAttribute("status","success");
                dispatcher.forward(req,resp);
            } else {
                resp.sendRedirect("index.jsp");
            }

        }




    }
}
