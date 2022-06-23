package com.company.entities;

import com.company.DTO.BookDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Book {
    private int id;
    private String bookId;
    private String name;
    private String author;
    private String genre;
    private String imgName;
    private int price;
    private int amount;
    private int amountLeft;
    private Date date;


    public Book(int id, String name, String author, String genre, int price, int amount, int amountLeft, Date date) {
        this.name = name;
        this.author = author;
        this.genre = genre;
        this.id = id;
        this.price = price;
        this.amount = amount;
        this.amountLeft =amountLeft;
        this.date = date;
    }



    public Book(){

    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getAmountLeft() {
        return amountLeft;
    }

    public void setAmountLeft(int amountLeft) {
        this.amountLeft = amountLeft;
    }


    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public BookDTO toDTO(){
        BookDTO bookDTO = new BookDTO();
        bookDTO.setBookId(getBookId());
        bookDTO.setName(getName());
        bookDTO.setAuthor(getAuthor());
        bookDTO.setGenre(getGenre());
        bookDTO.setAmount(getAmount());
        bookDTO.setPrice(getPrice());
        bookDTO.setDate(getDate());
        bookDTO.setImgName(getImgName());
        return bookDTO;
    }

    public Book toEntity(ResultSet rs) throws SQLException, ParseException {
        setBookId(rs.getString("bookId"));
        setName(rs.getString("name"));
        setAuthor(rs.getString("author"));
        setGenre(rs.getString("genre"));
        setImgName(rs.getString("imgName"));
        setPrice(Integer.parseInt(rs.getString("price")));
        setAmount(Integer.parseInt(rs.getString("amount")));
        setDate(new SimpleDateFormat("dd-mm-yyyy").parse(rs.getString("date")));
        return this;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", genre='" + genre + '\'' +
                ", price=" + price +
                ", amount=" + amount +
                ", amountLeft=" + amountLeft +
                ", date=" + new SimpleDateFormat("dd/MM/YYYY").format(date) +
                '}';
    }
}
