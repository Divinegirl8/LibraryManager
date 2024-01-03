package org.libraryManager.data.models;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
@Data
public class Transaction {
    @Id
    private String transId;
    private String userId;
    private String bookId;
    private Date dueDate;
    private LocalDateTime dateIssued;
    private String amountCharge;
}
