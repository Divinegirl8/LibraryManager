package org.libraryManager.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.bind.annotation.RestController;

@Data
@AllArgsConstructor
public class ApiResponse {
    private boolean isSuccessful;
    private Object data;
}
