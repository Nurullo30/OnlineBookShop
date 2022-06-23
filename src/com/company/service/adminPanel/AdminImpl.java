package com.company.service.adminPanel;
import com.company.DAO.BookDAO;
import com.company.entities.Book;
import com.company.constants.Constants;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class AdminImpl implements AdminService {
    private BookDAO bookDAO;
    private Map<String, String> booksPath;
    protected List<Book> bookList;

    public AdminImpl(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
        init();
    }

    private void init(){
    }

    @Override
    public Book addBook(String name, String author, String genre, int  price , int amount){
        bookList.add(new Book(bookList.size()+1, name, author, genre, price, amount, amount, new Date()));
//            bookDAO.saveBooks(booksPath.get(Constants.BOOK_LOAD_PATH),bookList);

        return bookList.get(bookList.size()-1);
    }

    @Override
    public boolean checkStatus(int id){
        //checkIdRange = validation.checkIdRange(id, getAllBooks());
        //checkBookId = bookValidation.checkBookId(id, getAllBooks());
       // return checkIdRange && checkBookId;
        return false;
    }

    @Override
    public void deductBookAmount(int bookId, List<Book> chosenBooks) {

    }

    @Override
    public Book changeDetails(String authorOrName , int bookId ,String newChange) throws IOException {
        for (Book book : bookList) {
            if (book.getId() == bookId){
                switch (authorOrName) {
                    case Constants.AUTHOR:
                        book.setAuthor(newChange);
//                        bookDAO.saveBooks(booksPath.get(Constants.BOOK_LOAD_PATH), bookList);
                        return book;
                    case Constants.NAME:
                        book.setName(newChange);
//                        bookDAO.saveBooks(booksPath.get(Constants.BOOK_LOAD_PATH), bookList);
                        return book;
                }
            }
        }
        return null;
    }

    @Override
    public void removeBook(int id) {
//        checkIdRange = validation.checkIdRange(id , bookList);
//        boolean checkBookId = bookValidation.checkBookId(id , bookList);
//        if (checkIdRange && checkBookId){
//            for (Book book: bookList) {
//                if (book.getId() == id){
//                    bookList.remove(book);
//                    System.out.println("Removed: \n" + book);
//                    break;
//                }
//            }
//            try {
//                bookDAO.saveBooks(booksPath, bookList);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        } else {
//            System.out.println("Извините нет у нас книги с такой ID");
//        }
    }

    @Override
    public void printMostSold(){
        int maxAmount = bookList.get(0).getAmount()-bookList.get(0).getAmountLeft();

        for (int i = 0; i < bookList.size()-1; i++) {
            System.out.println(bookList.get(i) + " was sold : " + " " + (bookList.get(i).getAmount()-bookList.get(i).getAmountLeft()));

            int amountSold = bookList.get(i+1).getAmount()-bookList.get(i+1).getAmountLeft();
            if (maxAmount < amountSold){
                maxAmount = amountSold;
            }
        }
        System.out.println(bookList.get(bookList.size()-1) + " was sold : " + " " +
                (bookList.get(bookList.size()-1).getAmount()-bookList.get(bookList.size()-1).getAmountLeft()) + "\n");

        if (maxAmount == 0){
            System.out.println("Anything has not been sold yet");
        } else {
            // checking in case if 2 books was sold at the same amount
            for (Book book: bookList) {
                if (maxAmount == (book.getAmount()-book.getAmountLeft())){
                    System.out.println( book + "is the most sold book for now");
                }
            }
        }
    }

    @Override
    public void addDiscount() {

    }

    @Override
    public void createShelve(String shelveType) {

    }


}
