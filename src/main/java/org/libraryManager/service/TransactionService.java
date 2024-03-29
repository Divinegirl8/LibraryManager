package org.libraryManager.service;

import org.libraryManager.data.models.Date;
import org.libraryManager.data.models.Transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface TransactionService {
    Transaction checkIn(String transId, String userId, String title, String author, Date dueDate, LocalDateTime dateIssued, BigDecimal amountCharge);
    Transaction checkOut(String transactionId, String title,String author,Date dateReturned);

}
