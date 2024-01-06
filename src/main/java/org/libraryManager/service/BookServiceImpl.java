package org.libraryManager.service;

import org.libraryManager.data.models.Book;
import org.libraryManager.data.repositories.BookRepository;
import org.libraryManager.exceptions.BookNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class BookServiceImpl implements BookService{
    @Autowired
    BookRepository bookRepository;
    @Override
    public Book addBook(String bookId, String title, String author, String isbn) {
        Book book = new Book();
        book.setBookId(bookId);
        book.setTitle(title);
        book.setAuthor(author);
        book.setIsbn(isbn);
        bookRepository.save(book);

        return book;
    }

    @Override
    public Book findBook(String title, String author) {
        Book book = bookRepository.findBookByAuthorAndTitle(title,author);

        if (book == null) throw new BookNotFoundException("Book not found");
        return book;
    }

    @Override
    public void removeBook(String title, String author) {
        Book book = bookRepository.findBookByAuthorAndTitle(title,author);

        if (book == null) throw new BookNotFoundException("Book not found");
        bookRepository.delete(book);
    }

    @Override
    public List<Book> listOfBooks() {
        return new ArrayList<>(bookRepository.findAll());
    }

    @Override
    public List<Book> findListOfBooksByAuthor(String author) {
        List<Book> books = bookRepository.findBookByAuthor(author);

        if (books.isEmpty()) throw new BookNotFoundException("Book not found");
        return new ArrayList<>(books);

    }

    @Override
    public void deleteAllBooks() {
        bookRepository.deleteAll();
    }

    @Override
    public void deleteAllBooksByAuthor(String author) {
        List<Book> books = bookRepository.findBookByAuthor(author);
        bookRepository.deleteAll(books);
    }



}
