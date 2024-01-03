package org.libraryManager.data.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
@Data
public class Book {
    @Id
    private String bookId;
    private String title;
    private String author;
    private String isbn;
    private boolean isReturned;
}
