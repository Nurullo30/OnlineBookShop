package com.company.service.adminPanel;

import com.company.entities.Book;

import java.io.IOException;
import java.util.List;

public interface AdminService{

    Book addBook(String name, String author, String genre, int price, int amount);

    Book changeDetails(String authorOrName, int bookId, String newChange) throws IOException;

    void removeBook(int id);

    boolean checkStatus(int id);

    void deductBookAmount(int bookId,  List<Book> chosenBooks);

    void printMostSold();

    void addDiscount ();

    void createShelve(String shelveType);
}
