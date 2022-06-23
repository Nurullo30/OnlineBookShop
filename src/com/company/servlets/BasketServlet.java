package com.company.servlets;

import com.company.DTO.BookDTO;
import com.company.Exceptions.NoBookFoundException;
import com.company.constants.BeanConstants;
import com.company.factory.ClassFactory;
import com.company.service.BookStoreService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(value = "/myBasket")
public class BasketServlet extends HttpServlet {
    BookStoreService bookStoreService;

    public BasketServlet(){
        if (bookStoreService == null){
            bookStoreService = (BookStoreService) ClassFactory.getServices(BeanConstants.BOOK_STORE_SERVICE);
        }
    }

//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
//        String productId = req.getParameter("product");
//        HttpSession session = req.getSession();
//        Map<String, Integer> cartList = (HashMap<String, Integer>) session.getAttribute("cartList");
//        boolean exist= false;
//        if (cartList == null){
//            cartList = new HashMap<>();
//            cartList.put(productId, 1);
//            session.setAttribute("cartList", cartList);
//            resp.sendRedirect("seeAllBooks?panel=seeAllBooks&page=1");
//        } else if (cartList.containsKey(productId)){
//           exist = true;
//            resp.sendRedirect("basket.jsp");
//        } else{
//            cartList.put(productId, 1);
//            resp.sendRedirect("seeAllBooks?panel=seeAllBooks&page=1");
//        }
//        if (!exist){
//            List<BookDTO> bookDTOS = new ArrayList<>();
//            cartList.entrySet().stream().forEach(v -> {
//                BookDTO bookDTO = new BookDTO();
//                bookDTO.setBookId(v.getKey());
//                bookDTO.setAmount(v.getValue());
//                bookDTOS.add(bookDTO);
//            });
//
//            try {
//                List<BookDTO> basketBooks = bookStoreService.getBooks(bookDTOS);
//                session.setAttribute("basketBooks", basketBooks);
//            } catch (NoBookFoundException e) {
//                e.printStackTrace();
//            }
//        }
//    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        String productId = req.getParameter("product");
        HttpSession session = req.getSession();
        List<BookDTO> basketBooks = (List<BookDTO>) session.getAttribute("basketBooks");

        if (basketBooks != null && basketBooks.stream().anyMatch(v -> v.getBookId().equals(productId))){
            resp.sendRedirect("basket.jsp");
        } else {
            try {
                BookDTO bookFound = bookStoreService.getBookById(productId);
                bookFound.setAmount(1);
                if (basketBooks == null){
                    basketBooks = new ArrayList<>();
                }
                basketBooks.add(bookFound);
            } catch (NoBookFoundException e) {
                e.printStackTrace();
            }
            resp.sendRedirect("seeAllBooks?panel=seeAllBooks&page=1");
        }
        session.setAttribute("basketBooks", basketBooks);
    }

}
