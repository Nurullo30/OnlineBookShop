package com.company.servlets.userServlets;

import com.company.DTO.BookDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(value = "/removeProduct")
public class removeProductServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String bookID = (String) req.getParameter("id");

        if (bookID != null){
            List<BookDTO> books =  (List<BookDTO>) req.getSession().getAttribute("basketBooks");
            for (BookDTO book: books) {
                if (book.getBookId().equals(bookID)){
                    books.remove(book);
                    break;
                }
            }
            resp.sendRedirect("basket.jsp");
        }

    }
}
