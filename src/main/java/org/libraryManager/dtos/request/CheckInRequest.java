package org.libraryManager.dtos.request;

import lombok.Data;
import org.libraryManager.data.models.Date;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Data
public class CheckInRequest {
    private String userId;
    private String title;
    private String author;
    private Date dueDate;
    private LocalDateTime dateIssued;
    private BigDecimal amountCharge;

}
