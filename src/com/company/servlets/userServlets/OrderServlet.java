package com.company.servlets.userServlets;

import com.company.DTO.BookDTO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(value = "/checkout")
public class OrderServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        HttpSession session = req.getSession();
        List<BookDTO> basketBooks = (List<BookDTO>) session.getAttribute("basketBooks");
        if (basketBooks != null && basketBooks.size() != 0){
            RequestDispatcher dispatcher = req.getRequestDispatcher("checkout.jsp");
            dispatcher.forward(req,resp);
        }

    }
}
