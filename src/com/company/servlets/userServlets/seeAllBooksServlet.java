package com.company.servlets.userServlets;

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
import java.util.List;

@WebServlet(value = "/seeAllBooks")
public class seeAllBooksServlet extends HttpServlet {
    private BookStoreService bookStoreService;
    private String sPageNo;
    private String sPanel;

    public seeAllBooksServlet() {
        super();
        if (bookStoreService == null){
            bookStoreService = (BookStoreService) ClassFactory.getServices(BeanConstants.BOOK_STORE_SERVICE);
        }
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        sPageNo = req.getParameter("page");
        sPanel = req.getParameter("panel");

        if (sPageNo != null && sPanel != null){
            int pageId = Integer.parseInt(sPageNo);
            int total = 4;
            if (pageId == 1){}
            else {
                pageId = pageId-1;
                pageId= pageId * total+1;
            }
            try {
                int totalBookNum = 0;
                List<BookDTO> bookDTOS = new ArrayList<>();

                switch (sPanel){
                    case "seeAllBooks":
                        totalBookNum = bookStoreService.getAllBooks().size();
                        bookDTOS = bookStoreService.getBooksWithPagination(pageId, total);
                        req.setAttribute("panel", "seeAllBooks");
                        break;
                    case "genre":
                        String genre = req.getParameter("genreVal");
                        totalBookNum = bookStoreService.searchByGenre(genre).size();
                        bookDTOS = bookStoreService.searchByGenreWithPageLimit(genre, pageId, total);
                        req.setAttribute("panel", "genre");
                        break;
                    case "newBooks":
                        totalBookNum = bookStoreService.newlyAddedBooks().length;
                        bookDTOS = bookStoreService.getNewBooksWithPageLimit(pageId, total);

                        req.setAttribute("panel", "newBooks");
                }
                req.setAttribute("books", bookDTOS);
                req.setAttribute("status", "success");
                req.setAttribute("bookNumber", pageId);
                req.setAttribute("pageNum", Integer.parseInt(sPageNo));
                req.setAttribute("totalBookNum", totalBookNum);

            } catch (NoBookFoundException e) {
                req.setAttribute("status", "failed");
            }
            req.getRequestDispatcher("seAllBooksView.jsp").forward(req,resp);
        } else {
            req.getRequestDispatcher("seAllBooksView.jsp").forward(req,resp);
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        sPageNo = req.getParameter("page");
        sPanel = req.getParameter("panel");
        if (sPanel != null && sPageNo != null){
            int pageId = Integer.parseInt(sPageNo);
            int total = 10;
            if (pageId == 1){}
            else {
                pageId = pageId-1;
                pageId= pageId * total+1;
            }

            RequestDispatcher dispatcher = req.getRequestDispatcher("seAllBooksView.jsp");
            try {
                int totalBookNum = 0;
                List<BookDTO> bookDTOS = new ArrayList<>();
                switch (sPanel){
                    case "genre":
                        String genre = req.getParameter("genreVal");
                        totalBookNum = bookStoreService.searchByGenre(genre).size();
                        bookDTOS = bookStoreService.searchByGenreWithPageLimit(genre, pageId, total);
                        req.setAttribute("panel", "genre");
                        break;
                }
                req.setAttribute("books", bookDTOS);
                req.setAttribute("status", "success");
                req.setAttribute("bookNumber", pageId);
                req.setAttribute("pageNum", Integer.parseInt(sPageNo));
                req.setAttribute("totalBookNum", totalBookNum);

            } catch (NoBookFoundException e) {
                req.setAttribute("status", "failed");
            }
            dispatcher.forward(req,resp);
        }  else {
            req.getRequestDispatcher("seAllBooksView.jsp").forward(req,resp);
        }
    }
}
