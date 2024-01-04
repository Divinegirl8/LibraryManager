package org.libraryManager.dtos.request;

import lombok.Data;

@Data
public class AddBookRequest {
    private String title;
    private String author;
    private String isbn;
}
