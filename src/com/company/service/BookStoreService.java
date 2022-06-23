package com.company.service;

import com.company.DTO.BookDTO;
import com.company.Exceptions.NoBookFoundException;

import java.util.List;

public interface BookStoreService {
    BookDTO getBookById(String id) throws NoBookFoundException;

    List<BookDTO> getBooks(List<BookDTO> books) throws NoBookFoundException;

    List<BookDTO> searchByGenre(String genre) throws NoBookFoundException;

    List<BookDTO> searchByGenreWithPageLimit(String genre, int start, int total) throws NoBookFoundException;

    BookDTO[] newlyAddedBooks() throws NoBookFoundException;

    List<BookDTO> getNewBooksWithPageLimit(int start, int total) throws NoBookFoundException;

    List<BookDTO> getAllBooks() throws NoBookFoundException;

    List<BookDTO> getBooksWithPagination(int start, int total) throws NoBookFoundException;
}
