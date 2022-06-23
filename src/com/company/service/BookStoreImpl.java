package com.company.service;

import com.company.DAO.BookDAO;
import com.company.DTO.BookDTO;
import com.company.Exceptions.NoBookFoundException;
import com.company.commonService.Validation;
import com.company.entities.Book;
import com.company.shelves.Shelves;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static com.company.constants.Constants.BOOK_LOAD_PATH;
import static com.company.constants.Constants.DISCOUNT_PATH;

// @Service
public class BookStoreImpl implements BookStoreService {

    protected List<Book> bookList;
    private List<String> purchaseList;
    private HashMap<Integer, Book> bookGenre;
    private HashMap<String , String> filePath;
    private List<Shelves> shelves;
    protected BookValidation bookValidation;
    protected Validation validation;
    private HashMap<Shelves, Book> bookData = new HashMap<>();
    private BookDAO bookDAO;
    private Map<String,String> booksPath;

    public BookStoreImpl(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
        init();
    }
    public void init() {
        bookValidation = new BookValidation();
        validation = new Validation();
    }


    public BookDTO getBookById(String id) throws NoBookFoundException{
        Book book = bookDAO.getBook(id);
        BookDTO bookDTO = book.toDTO();
        return bookDTO;
    }



    public List<BookDTO> getBooksWithPagination(int start, int total) throws NoBookFoundException{
        List<BookDTO> bookDTOS = new ArrayList<>();
        for (Book book :  bookDAO.getBooksWithPagination(start, total)) {
            bookDTOS.add(book.toDTO());
        }
        return bookDTOS;
    }

    @Override
    public BookDTO[] newlyAddedBooks() throws NoBookFoundException {
        SimpleDateFormat dnt = new SimpleDateFormat("YYYY-MM-dd");
        Date date = new Date();
        String startDate = dnt.format(date);
        String endDate = LocalDate.now().plusDays(2).format(DateTimeFormatter.ofPattern("YYYY-MM-dd"));
        Book [] edsBooks = bookDAO.getWithDate(startDate, endDate);
        BookDTO [] bookDTOS = new BookDTO[edsBooks.length];
        for (int i = 0; i < edsBooks.length; i++) {
            bookDTOS[i] = edsBooks[i].toDTO();
        }
        return bookDTOS;
    }
    @Override
    public List<BookDTO> getNewBooksWithPageLimit(int start, int total) throws NoBookFoundException {
        SimpleDateFormat dnt = new SimpleDateFormat("YYYY-MM-dd");
        Date date = new Date();
        String startDate = dnt.format(date);
        String endDate = LocalDate.now().plusDays(2).format(DateTimeFormatter.ofPattern("YYYY-MM-dd"));
        Book [] edsBooks = bookDAO.getWithPageLimitAndDate(startDate, endDate, start , total);
        if (edsBooks.length != 0){
            List<BookDTO> bookDTOS = new ArrayList<>();
            for (int i = 0; i < edsBooks.length; i++) {
                bookDTOS.add(edsBooks[i].toDTO());
            }
            return bookDTOS;
        }
        throw new NoBookFoundException();
    }


    @Override
    public List<BookDTO> getBooks(List<BookDTO> books) throws NoBookFoundException {
        List<BookDTO> bookDTOS = new ArrayList<>();
        for (Book book :  bookDAO.getBooks(books)) {
            bookDTOS.add(book.toDTO());
        }
        return bookDTOS;

    }

    public List<BookDTO> searchByGenreWithPageLimit(String genre, int start, int total) throws NoBookFoundException {
        Book[] results = bookDAO.getGenreWithPageLimit(genre, start, total);

        if (results != null && results.length != 0 ){
            List<BookDTO> bookDTOS = new ArrayList<>();
            for (Book book: results) {
                bookDTOS.add(book.toDTO());
            }
            return bookDTOS;
        }
        throw new NoBookFoundException();
    }

    public List<BookDTO> searchByGenre(String genre) throws NoBookFoundException{
        Book[] results = bookDAO.getGenre(genre);

        if (results != null && results.length != 0 ){
            List<BookDTO> bookDTOS = new ArrayList<>();
            for (Book book: results) {
                bookDTOS.add(book.toDTO());
            }
            return bookDTOS;
        }
        throw new NoBookFoundException();
    }

    public Boolean detailedWordSearch(String userEnteredVal, String databaseVal) {
        int length = Math.abs(databaseVal.length() - userEnteredVal.length());
        if (databaseVal.toLowerCase().equals(userEnteredVal.toLowerCase()) || length > 3){
            return false;
        }
        int count= 0;
        for (int i = 0; i < databaseVal.length(); i++) {
            if (userEnteredVal.length()-1 > i){
                if(databaseVal.charAt(i) != userEnteredVal.charAt(i)){
                    count++;
                }
            }

        }
        return count < 3 ? true : false;
    }
    public List<BookDTO> getAllBooks() throws NoBookFoundException {
        List<BookDTO> bookDTOS = new ArrayList<>();
        for (Book book :  bookDAO.getAllBooks()) {
           bookDTOS.add(book.toDTO());
        }
        return bookDTOS;
    }

    public void saveBooks() throws IOException {
//        bookDAO.saveBooks(booksPath.get(BOOK_LOAD_PATH), bookList);
    }

    public void saleBook(Book book, String customerName) throws IOException {

//                deductBookAmount(bookId, searchResult);
//                processUserPurchase(bookId, customerName);
    }

    public void processUserPurchase(int bookId, String userName) throws IOException {
//        File file = new File(filePath.get(CUSTOMER_PATH));
//        FileWriter fileWriter = new FileWriter(file);
//
//        for (Book book : bookList){
//            if (book.getId() == bookId){
//                purchaseList.add(
//                        book.getId() + ":" +
//                                book.getName() + ":" +
//                                book.getAuthor() + ":" +
//                                book.getAmount() + ":" +
//                                book.getAmountLeft() + ":" +
//                                book.getPrice() +
//                                " - " + userName
//                );
//                balance = book.getPrice();
//                break;
//            }
//        }
//        for (int i = 0; i < purchaseList.size(); i++) {
//            fileWriter.write(purchaseList.get(i) + (purchaseList.size()-1 != i ? "\n" : ""));
//        }
//        loadingFileData.loadBalance();
//        fileWriter.flush();
//        fileWriter.close();
    }


    public void deductBookAmount(int bookId,  List<Book> chosenBooks) {
        for (Book book: chosenBooks) {
            if (book.getId() == bookId){
                book.setAmountLeft(book.getAmountLeft()-1);
                System.out.println(book.getAmountLeft());
                try {
                    saveBooks();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }

    public Boolean checkBookExist(int bookId,  List<Book> chosenBooks){
        for (Book book: chosenBooks) {
            if (book.getId() == bookId && book.getAmount() !=0){
                return true;
            }
        }
        return false;
    }



    public void addDiscount (){
        Scanner scanner = new Scanner(System.in);
        for (Book books: bookList) {
            System.out.println(books);
        }
        System.out.println("Please type in id of the book that you want to add a discount: ");
        String chooseId = scanner.nextLine();
        System.out.println("Please add a discount(in percent): ");
        int discount = scanner.nextInt();
        String discountString = String.valueOf(discount);
        try {
            FileWriter fileWriter = new FileWriter("src/com/company/DataBase/DiscountedBooks.txt");

            fileWriter.write(
            bookList.get(Integer.parseInt(chooseId)).getId() + ":" +
                bookList.get(Integer.parseInt(chooseId)).getName() + ":" +
                bookList.get(Integer.parseInt(chooseId)).getAuthor() + ":" +
                bookList.get(Integer.parseInt(chooseId)).getPrice() + ":" +
                bookList.get(Integer.parseInt(chooseId)).getAmount() + ":" +
                bookList.get(Integer.parseInt(chooseId)).getAmountLeft() + ":" +
                discountString
            );
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void calculateDiscount() throws FileNotFoundException {
        File file = new File(filePath.get(DISCOUNT_PATH));

        Scanner scanner = new Scanner(file);

        while(scanner.hasNextLine()){
            String word = scanner.nextLine();
            String[] bookLine = word.split(":");
            int amount = bookList.get(Integer.parseInt(bookLine[0])).getPrice() / Integer.parseInt(bookLine[bookLine.length-1]);
            bookList.get(Integer.parseInt(bookLine[0])).setAmount(amount);
        }
    }


}
