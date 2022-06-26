package com.company.DAO;

import com.company.DTO.BookDTO;
import com.company.Exceptions.NoBookFoundException;
import com.company.entities.Book;
import com.company.factory.ClassFactory;
import com.company.shelves.Shelves;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static com.company.constants.Constants.CUSTOMER_PATH;
import static com.company.constants.Constants.SHELVES;
// @Repository
public class BookRepository implements BookDAO{
    private File file;

    public Book getBook(String id) throws NoBookFoundException{
        try {
            Connection conn = ClassFactory.connectToDB();
            PreparedStatement ps = conn.prepareStatement("select * from books where bookid='" + id + "'");
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                return new Book().toEntity(rs);
            }
            conn.close();
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
        throw new NoBookFoundException();
    }
    public Book[] getGenre(String genre) throws NoBookFoundException{
        try {
            Connection conn = ClassFactory.connectToDB();
            PreparedStatement ps = conn.prepareStatement("select * from books where genre='" + genre + "'");
            ResultSet rs = ps.executeQuery();
            List<Book> mainBookList = new ArrayList<>();
            while (rs.next()){
                mainBookList.add(new Book().toEntity(rs));
            }
            conn.close();
            return mainBookList.toArray(new Book[mainBookList.size()]);
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
        throw new NoBookFoundException();
    }


    public Book[] getGenreWithPageLimit(String genre, int start, int total) throws NoBookFoundException{
        try {
            Connection conn = ClassFactory.connectToDB();
            PreparedStatement ps = conn.prepareStatement("select * from books where genre='" + genre + "' "
                    + "limit " + total +" offset "+ (start-1));
            ResultSet rs = ps.executeQuery();
            List<Book> mainBookList = new ArrayList<>();
            while (rs.next()){
                mainBookList.add(new Book().toEntity(rs));
            }
            conn.close();
            return mainBookList.toArray(new Book[mainBookList.size()]);
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
        throw new NoBookFoundException();
    }


    public Book[] getWithDate(String startDate, String endDate) throws NoBookFoundException {
        Connection conn = ClassFactory.connectToDB();
        try {

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM books WHERE date >= '" +
                    startDate + "'" + " and " +  " date <= " + "'" + endDate + "'");

            List<Book> books = new ArrayList<>();
            while (rs.next()){
                books.add(new Book().toEntity(rs));
            }
            conn.close();
            return books.toArray(new Book[books.size()]);
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
        throw new NoBookFoundException();
    }

    public Book[] getWithPageLimitAndDate(String startDate, String endDate, int start, int total) throws NoBookFoundException {
        try {
            Connection conn = ClassFactory.connectToDB();
            PreparedStatement ps = conn.prepareStatement("select * from books WHERE date >= '" +
                    startDate + "'" + " and " +  " date <= " + "'" + endDate + "'"
                    + "limit " + total +" offset "+ (start-1));
            ResultSet rs = ps.executeQuery();
            List<Book> mainBookList = new ArrayList<>();
            while (rs.next()){
                mainBookList.add(new Book().toEntity(rs));
            }
            conn.close();
            return mainBookList.toArray(new Book[mainBookList.size()]);
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
        throw new NoBookFoundException();
    }


    @Override
    public List<Book> getBooks(List<BookDTO> books) throws NoBookFoundException {
        try {
            Connection conn = ClassFactory.connectToDB();
            List<Book> resultBooks = new ArrayList<>();
            for (BookDTO book: books) {
                PreparedStatement ps = conn.prepareStatement("select * from books where bookid=?");
                ps.setString(1, book.getBookId());
                ResultSet rs = ps.executeQuery();
                while (rs.next()){
                    Book resultBook = new Book();
                    resultBook.toEntity(rs);
                    resultBook.setAmount(book.getAmount());
                    resultBook.setPrice(resultBook.getAmount() * resultBook.getPrice());
                    resultBooks.add(resultBook);
                }
            }
            conn.close();
            return resultBooks;
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
        throw new NoBookFoundException();
    }

    public List<Book> getAllBooks() throws NoBookFoundException {
        try {
            Connection conn = ClassFactory.connectToDB();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM books");
            if (rs != null){
                List<Book> mainBookList = new ArrayList<>();
                while (rs.next()){
                    Book book  = new Book();
                    book.setBookId(rs.getString("bookId"));
                    book.setName(rs.getString("name"));
                    book.setAuthor(rs.getString("author"));
                    book.setGenre(rs.getString("genre"));
                    book.setPrice(Integer.parseInt(rs.getString("price")));
                    book.setAmount(Integer.parseInt(rs.getString("amount")));
                    book.setDate(new SimpleDateFormat("dd-mm-yyyy").parse(rs.getString("date")));
                    mainBookList.add(book);
                }
                return mainBookList;
            }
            conn.close();
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
        throw new NoBookFoundException();
    }

    public List<Book> getBooksWithPagination(int start, int total) throws NoBookFoundException{
        try {
            Connection conn = ClassFactory.connectToDB();
            PreparedStatement ps = conn.prepareStatement("select * from books order by id ASC limit " + total +" offset "+ (start-1));
            ResultSet rs = ps.executeQuery();
                List<Book> mainBookList = new ArrayList<>();
                while (rs.next()){
                    mainBookList.add(new Book().toEntity(rs));
                }
                conn.close();
                return mainBookList;
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
        throw new NoBookFoundException();
    }

    public List<String> loadCustomers(Map<String, String> filePath) throws IOException {
        file = new File(filePath.get(CUSTOMER_PATH));
        Scanner customerScanner = new Scanner(file);
        List<String> purchaseList = new ArrayList<>();

        while(customerScanner.hasNextLine()){
            purchaseList.add(customerScanner.nextLine());
        }
        return purchaseList;
    }

    public int loadBalance(String filePath){
        file = new File(filePath);
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int balance = 0;
        while (scanner.hasNextLine()){
            String word = scanner.nextLine();
            String [] allWords = word.split(":");
            balance += Integer.parseInt(allWords[1]);
        }
        return balance;
    }

    public void topUpBalance(String filePath, String bookPrice){
        file = new File(filePath);
        try {
            Scanner scanner = new Scanner(file);
            String ourBalanceIs;
            String [] balanceSentence = new String[2];
            while (scanner.hasNextLine()){
             balanceSentence = scanner.nextLine().split(":");
            }
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(balanceSentence[0] + ":" + (balanceSentence[1] + bookPrice));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Shelves> loadShelveList(Map<String, String> filePath) throws FileNotFoundException {
        file = new File(filePath.get(SHELVES));
        Scanner scanner = new Scanner(file);
        List<Shelves> shelvesList = new ArrayList<>();

        while (scanner.hasNextLine()){
            String word = scanner.nextLine();
            String [] splitLine = word.split(":");
            shelvesList.add(new Shelves(Integer.parseInt(splitLine[0]),splitLine[1]));
        }
        return shelvesList;
    }

    public void reloadShelveList(Map<String, String> filePath,List<Shelves> shelvesList) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath.get(SHELVES));
        for (Shelves shelves: shelvesList) {
            fileWriter.write(shelves.getId() + ":" + shelves.getType() +"\n");
        }
        fileWriter.flush();
        fileWriter.close();
    }

    public void saveBooks(List<BookDTO> bookList, String userID) throws SQLException {
            Connection conn = ClassFactory.connectToDB();
            for (BookDTO order: bookList) {
                String sql = "insert into orders (userid,bookid) values (?,?)";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, userID);
                ps.setString(2, order.getBookId());
                ps.executeUpdate();
            }
            conn.close();
    }



    @Override
    public void saveSingleBook(BookDTO book, String userID) throws SQLException {
        Connection conn = ClassFactory.connectToDB();
        String sql = "insert into orders (userid,bookid) values (?,?)";
        PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, userID);
            ps.setString(2, book.getBookId());
            ps.executeUpdate();
        conn.close();
    }

    @Override
    public void saveUserPurchase(String userID, String bookId, String path) {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(new File(path) ,true);
            fileWriter.write( userID + ":" + bookId + "\n");
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
