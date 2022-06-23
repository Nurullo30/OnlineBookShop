package com.company.servlets;

import com.company.factory.ClassFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(value = "/main")
public class mainServlet extends HttpServlet {
    private ClassFactory classFactory;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (classFactory == null){
            classFactory = new ClassFactory();
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }
}
