package com.company.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/orderStatus")
public class OrderStatus extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

        String orderStatus = (String) req.getAttribute("status");

        if (orderStatus.equals("success")){
            resp.sendRedirect("orderSuccess.jsp");
        } else {
            resp.sendRedirect("orderSuccess.jsp");
        }
    }
}
