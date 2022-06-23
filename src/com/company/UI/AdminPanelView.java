package com.company.UI;

import com.company.constants.AdminConstant;
import com.company.constants.CommonConstants;
import com.company.entities.Book;
import com.company.constants.Constants;
import com.company.service.BookStoreService;
import com.company.service.adminPanel.AdminService;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class AdminPanelView implements AdminConstant {
    private AdminService adminService;
    private List<Book> bookList;
    private boolean exitMenu;
    private Scanner scanner;
    private BookStoreService bookStoreService;


    public AdminPanelView(AdminService adminService, BookStoreService bookStoreService) {
        this.adminService = adminService;
        scanner = new Scanner(System.in);
        this.bookStoreService = bookStoreService;
    }


    public void startMenu() {
        while (true) {
            System.out.println(WELCOME_ADMIN);
            Scanner scanner = new Scanner(System.in);
            System.out.println("\n" + CommonConstants.ONE + " " + CommonConstants.ADD_BOOK); // Цыфры с переди
            System.out.println(CommonConstants.TWO + " " + DELETE_BOOK);
            System.out.println(CommonConstants.THREE + " " + CommonConstants.CHANGE_BOOK);
            System.out.println(CommonConstants.FOUR + " " + CommonConstants.SEARCH);
            System.out.println(CommonConstants.FIVE + " " + CommonConstants.SEARCH_BY_GENRE);
            System.out.println(CommonConstants.SIX + " " + CommonConstants.SEE_ALL_BOOKS);
            System.out.println(CommonConstants.SEVEN + " " + MOST_SOLD);
            System.out.println(CommonConstants.EIGHT + " " + LEAST_SOLD);
            System.out.println(CommonConstants.NINE + " " + ADD_NEW_SHELF + "\n");
            System.out.println(CommonConstants.CHOOSE_FUNCTION);
            boolean check = true;
            while (check) {
                try {
                    int menuNumber = scanner.nextInt();
                    switch (menuNumber) {
                        case 1:
                            System.out.println(CommonConstants.ONE + " " + CommonConstants.ADD_BOOK);
                            addBook();
                            check = false;
                            break;
                        case 2:
                            System.out.println(CommonConstants.TWO + " " + DELETE_BOOK);
                            removeBook();
                            check = false;
                            break;
                        case 3:
                            System.out.println(CommonConstants.THREE + " " + CommonConstants.CHANGE_BOOK);
                            changeDetails();
                            check = false;
                            break;
                        case 4:
                            System.out.println(CommonConstants.FOUR + " " + CommonConstants.SEARCH);
                            searchBook();
                            check = false;
                            break;
                        case 5:
                            System.out.println(CommonConstants.FIVE + " " + CommonConstants.SEARCH_BY_GENRE);
                            searchByGenre();
                            check = false;
                            break;
                        case 6:
                            System.out.println(CommonConstants.SIX + " " + CommonConstants.SEE_ALL_BOOKS);
                            printAllBooks();
                            check = false;
                            break;
                        case 7:
                            System.out.println(CommonConstants.SEVEN + " " + MOST_SOLD);
                            printMostSold();
                            check = false;
                            break;
                        default:
                            System.out.println(CommonConstants.TRY_AGAIN);
                    }
                } catch (Exception e) {
                    scanner.nextLine();
                    System.out.println(CommonConstants.TRY_AGAIN);
                }
            }
        }
    }

    public void addBook() {
        boolean IsUserInAddMenu = true;
        while (IsUserInAddMenu) {
            String name = registerName();
            if (name.equals(CommonConstants.ONE)){
                IsUserInAddMenu = false;
                break;
            }
            String author = registerAuthor();
            if (author.equals(CommonConstants.ONE)){
                IsUserInAddMenu = false;
                break;
            }
            String genre = registerGenre();
            if (genre.equals(CommonConstants.ONE)){
                IsUserInAddMenu = false;
                break;
            }
            String price = registerPrice();
            if (price.equals(CommonConstants.ONE)){
                IsUserInAddMenu = false;
                break;
            }
            String amount = registerAmount();
            if (amount.equals(CommonConstants.ONE)){
                IsUserInAddMenu = false;
                break;
            }

            Book book = adminService.addBook(name, author, genre, Integer.parseInt(price), Integer.parseInt(amount));

            System.out.println(book != null ? (CommonConstants.SUCCESSFULLY_ADDED + "\n" + book + "\n")
                    : (CommonConstants.FAILED_ADDING + " \n"));

            exitMenu = true;
            while (exitMenu) {
                System.out.println(CommonConstants.ONE + " " + CommonConstants.ADD_BOOK + "/ *" + CommonConstants.MAIN_MENU);
                switch (scanner.nextLine()) {
                    case CommonConstants.STAR:
                        IsUserInAddMenu = false;
                        exitMenu = false;
                        break;
                    case CommonConstants.ONE:
                        exitMenu = false;
                        break;
                    default:
                        System.out.println(CommonConstants.TRY_AGAIN);
                }
            }
        }
    }

    private String registerName(){
        System.out.println(CommonConstants.BOOK_NAME + ": " + " / " + CommonConstants.ONE + "." + CommonConstants.MAIN_MENU);
        String name = scanner.nextLine();
        return name;
    }
    private String registerAuthor(){
        System.out.println(CommonConstants.BOOK_AUTHOR + ": " + " / " + CommonConstants.ONE + "." + CommonConstants.MAIN_MENU);
        String author = scanner.nextLine();
        return author;
    }

    private String registerGenre(){
        System.out.println(CommonConstants.BOOK_GENRE + ": " + CommonConstants.MAIN_MENU);
        String genre = scanner.nextLine();
        return genre;
    }

    private String registerPrice(){
        System.out.println(CommonConstants.BOOK_PRICE + ": " + CommonConstants.MAIN_MENU);
        int price = 0;
        while (true) {
            try {
                price = scanner.nextInt();
                break;
            } catch (Exception e){
                System.out.println(CommonConstants.TRY_AGAIN);
            }
        }
        return String.valueOf(price);
    }
    private String registerAmount(){
        System.out.println(CommonConstants.BOOK_AMOUNT + ": " + CommonConstants.MAIN_MENU);
        int amount = 0;
        while (true) {
            try{
                amount = scanner.nextInt();
                break;
            }catch (Exception e){
                System.out.println(CommonConstants.TRY_AGAIN);
            }
        }

       return String.valueOf(amount);
    }

    public boolean numCheck(String userValue) {

        String[] numbers = {CommonConstants.ZERO, CommonConstants.ONE, CommonConstants.TWO, CommonConstants.THREE, CommonConstants.FOUR, CommonConstants.FIVE,
                CommonConstants.SIX, CommonConstants.SEVEN, CommonConstants.EIGHT, CommonConstants.NINE,};
        for (int i = 0; i < userValue.length(); i++) {
            for (int j = 0; j < numbers.length; j++) {
                String letter = "" + userValue.charAt(i);
                if (letter.equals(numbers[j])) {
                    break;
                } else if (numbers.length - 1 == j) {
                    return false;
                }
            }
        }
        return true;
    }

    public void printAllBooks() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("List of our all books: ");
       // bookList = bookStoreService.getAllBooks();
        for (Book books : bookList) {
            System.out.println(books);
        }
        exitMenu = true;
        while (exitMenu) {
            System.out.println("\n" + "Главное меню *");
            switch (scanner.nextLine()) {
                case "*":
                    exitMenu = false;
                    break;
                default:
                    System.out.println("Не правильно ввели");
            }
        }
    }

    public void removeBook() {
        //bookList = bookStoreService.getAllBooks();
        for (Book book : bookList) {
            System.out.println(book);
        }
        System.out.println("\n Пожалуйста отправьте ID книги которую хотите удалить из базы: " + "  /" + CommonConstants.MAIN_MENU);
        Scanner scanner = new Scanner(System.in);
        String bookId = scanner.nextLine();
        if (!(bookId.equals("*"))) {

            adminService.removeBook(Integer.parseInt(bookId));
            exitMenu = true;
            while (exitMenu) {
                System.out.println("\n" + "Главное меню *");
                switch (scanner.nextLine()) {
                    case "*":
                        exitMenu = false;
                        break;
                    default:
                        System.out.println("Не правильно ввели");
                }
            }
        }
    }

    public void changeDetails() throws IOException {
      //  bookList = bookStoreService.getAllBooks();
        if (bookList != null && bookList.size() != 0) {
            Scanner scanner = new Scanner(System.in);
            while (true) {
                for (Book books : bookList) {
                    System.out.println(books);
                }
                System.out.println("Пожалуйста выберите Id книги которую вы хотите изменить: " + " /" + CommonConstants.MAIN_MENU);
                String id = scanner.nextLine();

                if (!id.equals("*")) {
                    if (adminService.checkStatus(Integer.parseInt(id))) {
                        System.out.println(CommonConstants.ONE + "." + CommonConstants.CHANGE_AUTHOR +
                                CommonConstants.TWO + "." + CommonConstants.CHANGE_NAME);
                        int num = scanner.nextInt();
                        Book book;
                        switch (num) {
                            case 1:
                                System.out.println(CommonConstants.NEW_AUTHOR);
                                scanner.nextLine();
                                String author = scanner.nextLine();
                                book = adminService.changeDetails(Constants.AUTHOR, Integer.parseInt(id), author);
                                System.out.println(book != null ? CommonConstants.NEW_RESULT + ":" +
                                        book : CommonConstants.CHANGES_FAILED);
                                break;
                            case 2:
                                System.out.println(CommonConstants.NEW_NAME + ":");
                                scanner.nextLine();
                                String name = scanner.nextLine();
                                book = adminService.changeDetails(Constants.NAME, Integer.parseInt(id), name);
                                System.out.println(book != null ? "Новый результат: " + book : "Мы не смогли сделать изменение");
                                break;
                            default:
                                System.out.println("Неправильный ввели. Попробуйте еще раз");
                                break;
                        }
                    } else {
                        System.out.println("Извините у нас сейчас нет книг");
                    }
                } else {
                    break;
                }
            }
        }
    }

    public void searchBook() {

        boolean mainMenu = true;
        while(mainMenu){
            Scanner scanner = new Scanner(System.in);
            System.out.println("Пожалуйста напишите название или ключивое слово для поиска книги ? " + CommonConstants.MAIN_MENU);
            String keyword = scanner.nextLine();
            if (keyword.equals(CommonConstants.STAR)) {
                break;
            }
//            try {
//                bookList = bookStoreService.searchByGenre(keyword);
//            } catch (NoBookFoundException e) {
//                System.err.println("No book found");
//                continue;
//            }
//            bookList.stream().forEach(book -> System.out.println(book));

            exitMenu = true;
            while (exitMenu) {
                System.out.println("\n" + CommonConstants.MAIN_MENU + " " + CommonConstants.STAR);
                switch (scanner.nextLine()) {
                    case CommonConstants.STAR:
                        exitMenu = false;
                        mainMenu = false;
                        break;
                    default:
                        System.out.println(CommonConstants.TRY_AGAIN);
                }
            }
        }
    }

    public void searchByGenre() {
        boolean isInMainMenu = true;
        while(isInMainMenu){
            Scanner scanner = new Scanner(System.in);
            System.out.print(CommonConstants.INTERESTED_GENRE + "("
                    + CommonConstants.DETECTIVE + "," + CommonConstants.FANTACY + "," +
                    CommonConstants.ADVENTURE + "," + CommonConstants.NOVEL + "\n");
            String genre = scanner.nextLine();

            List<Book> bookGenre = null;
//            try {
//            //    bookGenre = bookStoreService.searchByGenre(genre);
//            } catch (NoBookFoundException e) {
//                System.err.println(CommonConstants.NOT_FOUND);
//                continue;
//            }
            bookGenre.stream().forEach(book -> System.out.println(book));
            exitMenu = true;
            while (exitMenu) {
                System.out.println("\n" + CommonConstants.MAIN_MENU);
                switch (scanner.nextLine()) {
                    case CommonConstants.STAR:
                        exitMenu = false;
                        isInMainMenu = false;
                        break;
                    default:
                        System.out.println(CommonConstants.TRY_AGAIN);
                }
            }
        }
    }

    public void printMostSold() {
        Scanner scanner = new Scanner(System.in);
        adminService.printMostSold();
        exitMenu = true;
        System.out.println();
        while (exitMenu) {
            System.out.println(CommonConstants.MAIN_MENU);
            switch (scanner.nextLine()) {
                case CommonConstants.STAR:
                    exitMenu = false;
                    break;
                default:
                    System.out.println(CommonConstants.TRY_AGAIN);
            }
        }
    }

    public void addDiscount() {
        adminService.addDiscount();
    }

    public void createShelve() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Пожалуйста напишите название новой полка");
        String shelveType = scanner.nextLine();

        adminService.createShelve(shelveType);
    }

    public void buyBook() {
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Название книги которую вы хотите купить: ");
//        String bookName = scanner.nextLine();
//        List<Book> searchResult = adminService.searchBook(bookName);
//
//
//        if (searchResult != null && searchResult.size() != 0) {
//            System.out.println(CommonConstants.RESULT + ":");
//            bookList(searchResult);
//
//            while (true) {
//                System.out.println("Пожалуйста введите ID книги которую вы хотите купить: ");
//                String bookId = scanner.nextLine();
//                if (Integer.parseInt(bookId) > searchResult.get(searchResult.size() - 1).getId() || Integer.parseInt(bookId) < 1) {
//                    System.out.println("Вы неправильно ввели ID книги! Попробуйте еще раз пожалуйста");
//                } else {
//                    System.out.println("Как вас зовут? ");
//                    String userName = scanner.nextLine();
//                    try {
//                        adminService.saleBook(Integer.parseInt(bookId), userName, searchResult);
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                    break;
//                }
//            }
//        } else {
//            System.out.println(CommonConstants.NOT_FOUND);
//        }
//    }
    }
}
