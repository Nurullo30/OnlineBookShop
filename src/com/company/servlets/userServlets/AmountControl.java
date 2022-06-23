package com.company.servlets.userServlets;

import com.company.DTO.BookDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(value = "/amountControl")
public class AmountControl extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String productId = req.getParameter("id");

        HttpSession session = req.getSession();
        List<BookDTO> books = (List<BookDTO>) session.getAttribute("basketBooks");
        if (books != null) {
            for (BookDTO book: books) {
                if (book.getBookId().equals(productId)){
                    int amount = book.getAmount();
                    if (req.getParameter("action").equals("inc")){
                        amount++;
                        book.setAmount(amount);
                    } else if (req.getParameter("action").equals("dec") && book.getAmount() > 1){
                        amount--;
                        book.setAmount(amount);
                    }
                }
            }
        }
        resp.sendRedirect("basket.jsp");
    }
}
