package org.libraryManager.dtos.request;

import lombok.Data;
import org.libraryManager.data.models.Date;

import java.time.LocalDateTime;

@Data
public class CheckoutRequest {
    private String transactionId;
    private String title;
    private String author;
    private Date dueDate;
    private LocalDateTime dateIssued;
}
