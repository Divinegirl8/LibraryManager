package org.libraryManager.dtos.request;

import lombok.Data;

@Data
public class FindBookRequest {
    private String author;
    private String title;

}
