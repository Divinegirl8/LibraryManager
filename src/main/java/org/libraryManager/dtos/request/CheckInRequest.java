package org.libraryManager.dtos.request;

import lombok.Data;
import org.libraryManager.data.models.Date;

import java.time.LocalDateTime;
@Data
public class CheckInRequest {
    private String transId;
    private String userId;
    private String title;
    private String author;
    private Date dueDate;
    private LocalDateTime dateIssued;
    private String amountCharge;

}
