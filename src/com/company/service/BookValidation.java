package com.company.service;

import com.company.entities.Book;

import java.util.List;

public class BookValidation {

    public Boolean checkBookId(int bookId, List<Book> bookList){
        for (Book book: bookList) {
            if (book.getId() == bookId && book.getAmount() !=0){
                return true;
            }
        }
        return false;
    }

    public Boolean checkBookAmount(Book book){
        return book.getAmount() !=0;
    }







}
