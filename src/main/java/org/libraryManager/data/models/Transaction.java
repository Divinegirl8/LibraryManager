package org.libraryManager.data.models;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Data
public class Transaction {
    @Id
    private String transId;
    private String userId;
    private String title;
    private String author;
    private Date dueDate;
    private LocalDateTime dateIssued;
    private BigDecimal amountCharge;
    private boolean isReturned;
}
