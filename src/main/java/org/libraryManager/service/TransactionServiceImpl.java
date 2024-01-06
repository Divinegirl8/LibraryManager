package org.libraryManager.service;

import org.libraryManager.data.models.Book;
import org.libraryManager.data.models.Date;
import org.libraryManager.data.models.Transaction;
import org.libraryManager.data.models.User;
import org.libraryManager.data.repositories.BookRepository;
import org.libraryManager.data.repositories.TransactionRepository;
import org.libraryManager.data.repositories.UserRepository;
import org.libraryManager.exceptions.BookNotFoundException;
import org.libraryManager.exceptions.TransactionNotFoundException;
import org.libraryManager.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Service
public class TransactionServiceImpl implements TransactionService{
    @Autowired
    UserRepository userRepository;
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    BookRepository bookRepository;

    @Override
    public Transaction checkIn(String transId, String userId, String title, String author, Date dueDate, LocalDateTime dateIssued, BigDecimal amountCharge) {
        User user = userRepository.findByUserId(userId);
        if (user == null)  throw new UserNotFoundException(userId + " not found");

        Book book = bookRepository.findBookByAuthorAndTitle(author,title);
        if (book == null) throw new BookNotFoundException("Book not found");

        Transaction transaction = new Transaction();
        transaction.setTransId(transId);
        transaction.setUserId(user.getUserId());
        transaction.setTitle(book.getTitle());
        transaction.setAuthor(book.getAuthor());
        transaction.setDueDate(dueDate);
        transaction.setDateIssued(dateIssued);
        transaction.setAmountCharge(amountCharge);
        transactionRepository.save(transaction);
        return transaction;

    }

    @Override
    public Transaction checkOut(String transactionId, String title, String author) {
        Book book = bookRepository.findBookByAuthorAndTitle(author,title);
        if (book == null) throw new BookNotFoundException("Book not found");

        Transaction transaction = transactionRepository.findByTransId(transactionId);
        if (transaction == null) throw new TransactionNotFoundException(transactionId + " not found");

        if (!transaction.getAuthor().equals(book.getAuthor()) && transaction.getTitle().equals(book.getTitle())){
            throw new BookNotFoundException("Invalid details");
        }



        transaction.setDueDate(transaction.getDueDate());
        transaction.setReturned(true);
        transactionRepository.save(transaction);
        bookRepository.save(book);
        return transaction;

    }
}

