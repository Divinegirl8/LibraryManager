package org.libraryManager.service;

import org.libraryManager.data.models.Book;

import java.util.List;

public interface BookService {
    Book addBook(String bookId, String title, String author, String isbn);
    Book findBook(String title,String author);
    void removeBook(String title,String author);

    List<Book> listOfBooks();
    List<Book> findListOfBooksByAuthor(String author);
    void deleteAllBooks();
    void deleteAllBooksByAuthor(String author);
}
