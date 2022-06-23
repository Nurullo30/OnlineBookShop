package com.company.servlets;


import com.company.DTO.BookDTO;
import com.company.Exceptions.NoBookFoundException;
import com.company.constants.BeanConstants;
import com.company.factory.ClassFactory;
import com.company.service.BookStoreService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(value = "/buySingleItem")
public class BuySingleItemServlet extends HttpServlet {
    BookStoreService bookStoreService;
    public BuySingleItemServlet(){
        if (bookStoreService == null){
            bookStoreService = (BookStoreService) ClassFactory.getServices(BeanConstants.BOOK_STORE_SERVICE);
        }
    }


    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String productId = (String) req.getParameter("product");
        try {
            BookDTO book = bookStoreService.getBookById(productId);
            book.setAmount(1);
            RequestDispatcher dispatcher = req.getRequestDispatcher("buySingleItem.jsp");
            req.setAttribute("book", book);
            dispatcher.forward(req, resp);
        } catch (NoBookFoundException e) {
            e.printStackTrace();
        }
    }
}
