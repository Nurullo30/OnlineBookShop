package com.company.DAO;

import com.company.DTO.BookDTO;
import com.company.Exceptions.NoBookFoundException;
import com.company.entities.Book;
import com.company.shelves.Shelves;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

public interface BookDAO {
    Book getBook(String id) throws NoBookFoundException;

    List<Book> getBooks(List<BookDTO> books) throws NoBookFoundException;

    List<Book> getAllBooks() throws NoBookFoundException;

    List<Book> getBooksWithPagination(int start, int total) throws NoBookFoundException;

    Book[] getWithDate(String StartDate, String EndDate) throws NoBookFoundException;

    Book[] getWithPageLimitAndDate(String startDate, String endDate, int start, int total) throws NoBookFoundException;

    Book[] getGenreWithPageLimit(String genre, int total, int start) throws NoBookFoundException;

    Book[] getGenre(String genre) throws NoBookFoundException;

    List<String> loadCustomers(Map<String, String> filePath) throws IOException;

    int loadBalance(String filePath);

    List<Shelves> loadShelveList(Map<String, String> filePath) throws FileNotFoundException;

    void reloadShelveList(Map<String, String> filePath,List<Shelves> shelvesList) throws IOException;

    void saveBooks(List<BookDTO> bookList, String userID) throws SQLException;

    void saveSingleBook(BookDTO book, String userId) throws SQLException;

    void saveUserPurchase(String userID, String bookId , String path);

    void topUpBalance(String filePath, String bookPrice);
}
