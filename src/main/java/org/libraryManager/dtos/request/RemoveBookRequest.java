package org.libraryManager.dtos.request;

import lombok.Data;

@Data
public class RemoveBookRequest {
    private String author;
    private String title;
}
