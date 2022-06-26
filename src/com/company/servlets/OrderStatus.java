package com.company.servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(value = "/orderStatus")
public class OrderStatus extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

        String orderStatus = (String) req.getAttribute("status");
        HttpSession session = req.getSession();
        if (orderStatus != null &&  orderStatus.equals("success")){
            session.setAttribute("orderStatus", "success");
            resp.sendRedirect("orderFinalizedStatus.jsp");
        } else {
            resp.sendRedirect("index.jsp");
        }
    }
}
