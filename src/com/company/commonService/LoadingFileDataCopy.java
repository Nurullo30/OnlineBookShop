package com.company.commonService;

import com.company.DTO.BookDTO;
import com.company.constants.Constants;
import com.company.entities.Book;
import com.company.shelves.Shelves;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import static com.company.constants.Constants.AMOUNT_LEFT_BOOK;
import static com.company.constants.Constants.AMOUNT_OF_BOOK;
import static com.company.constants.Constants.AUTHOR_OF_BOOK;
import static com.company.constants.Constants.BALANCE_PATH;
import static com.company.constants.Constants.BOOK_DATE;
import static com.company.constants.Constants.BOOK_LOAD_PATH;
import static com.company.constants.Constants.CUSTOMER_PATH;
import static com.company.constants.Constants.GENRE_OF_BOOK;
import static com.company.constants.Constants.PRICE_OF_BOOK;
import static com.company.constants.Constants.SHELVES;

public class LoadingFileDataCopy {
    private HashMap<String , String> filePath = new HashMap<>();
    private List<String> purchaseList = new ArrayList<>();
    private List<Book> mainBookList = new ArrayList<>();
    private HashMap<Integer, Book> bookGenre = new HashMap<>();
    private List<Shelves> shelvesList = new ArrayList<>();


    private int balance = 0;
    public LoadingFileDataCopy() throws IOException, ParseException {
        loadFilePath();
        loadBooks();
        loadCustomers();
        loadShelveList();
    }

    public int getBalance() {
        return balance;
    }

    public BookDTO getBookDetails() {
        BookDTO bookDTO= new BookDTO();
        bookDTO.setAllBooks(mainBookList);

        return bookDTO;
    }

    public List<String> getPurchaseList() {
        return purchaseList;
    }

    public HashMap<Integer, Book> getBookGenre() {
        return bookGenre;
    }

    public HashMap<String, String> getFilePath() {
        return filePath;
    }


    public void loadFilePath(){
        File file = new File("D:\\Desk\\BookStoreWithJDBC\\src\\com\\company\\directories\\settings.txt");
        try {
            Scanner path= new Scanner(file);
            while(path.hasNextLine()){
                String singlePath = path.nextLine();
                String [] save = singlePath.split(":");
                filePath.put(save[0], save[1]);
            }
            path.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void loadBooks() throws FileNotFoundException, ParseException {
        File file = new File( filePath.get(BOOK_LOAD_PATH));
        Scanner scanner = new Scanner(file);

        while(scanner.hasNextLine()){
            String book = scanner.nextLine();
            String [] infoOfBook = book.split(":");
            try {
                Date date = new SimpleDateFormat("dd/MM/yyyy").parse("12/12/2022");
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Book newBook = new Book(
                    Integer.parseInt(infoOfBook[Constants.ID_OF_BOOK]),
                    infoOfBook[Constants.NAME_OF_BOOK],
                    infoOfBook[AUTHOR_OF_BOOK],
                    infoOfBook[GENRE_OF_BOOK],
                    Integer.parseInt(infoOfBook[PRICE_OF_BOOK]),
                    Integer.parseInt(infoOfBook[AMOUNT_OF_BOOK]),
                    Integer.parseInt(infoOfBook[AMOUNT_LEFT_BOOK]),
                    new SimpleDateFormat("dd/MM/yyyy").parse(infoOfBook[BOOK_DATE])
            );
            mainBookList.add(newBook);
        }
    }
    public void loadCustomers() throws IOException {
        File file = new File(filePath.get(CUSTOMER_PATH));
        Scanner customerScanner = new Scanner(file);

        while(customerScanner.hasNextLine()){
            purchaseList.add(customerScanner.nextLine());
        }
    }
    public void loadBalance() throws IOException {
        File file = new File(filePath.get(BALANCE_PATH));
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()){
            String word = scanner.nextLine();
            String [] allWords = word.split(":");
            balance += Integer.parseInt(allWords[1]);
        }

        // Rewrite balance into file
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write("Our balance is:" + balance);
        fileWriter.flush();
        fileWriter.close();
    }
    public void loadShelveList() throws FileNotFoundException {
        File file = new File(filePath.get(SHELVES));
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()){
            String word = scanner.nextLine();
            String [] splitLine = word.split(":");
            shelvesList.add(new Shelves(Integer.parseInt(splitLine[0]),splitLine[1]));
        }
    }
    public List<Shelves> getShelvesList() {
        return shelvesList;
    }

    public void setShelvesList(List<Shelves> shelvesList) {
        this.shelvesList = shelvesList;
    }
    public void reloadShelveList() throws IOException {
        FileWriter fileWriter = new FileWriter(filePath.get(SHELVES));
        for (Shelves shelves: shelvesList) {
            fileWriter.write(shelves.getId() + ":" + shelves.getType() +"\n");
        }
        fileWriter.flush();
        fileWriter.close();
    }



}
