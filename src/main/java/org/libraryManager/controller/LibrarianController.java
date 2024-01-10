package org.libraryManager.controller;

import org.libraryManager.data.models.Book;
import org.libraryManager.data.models.User;
import org.libraryManager.dtos.request.*;
import org.libraryManager.dtos.response.*;
import org.libraryManager.service.BookManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LibrarianController {
    @Autowired
    BookManagerService bookManagerService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) {
        RegisterResponse registerResponse = new RegisterResponse();
        try {
            User user = bookManagerService.register(registerRequest);
            registerResponse.setMessage("Your Id is " + user.getUserId());
            return new ResponseEntity<>(new ApiResponse(true, registerResponse), HttpStatus.CREATED);
        } catch (Exception exception) {
            registerResponse.setMessage(exception.getMessage());
            return new ResponseEntity<>(new ApiResponse(false, registerResponse), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        LoginResponse loginResponse = new LoginResponse();
        try {
            bookManagerService.login(loginRequest);
            loginResponse.setMessage("you have login");
            return new ResponseEntity<>(new ApiResponse(true, loginResponse), HttpStatus.CREATED);
        } catch (Exception exception) {
            loginResponse.setMessage(exception.getMessage());
            return new ResponseEntity<>(new ApiResponse(false, loginResponse), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestBody LogoutRequest logoutRequest) {
        LogoutResponse logoutResponse = new LogoutResponse();
        try {
            bookManagerService.logout(logoutRequest);
            logoutResponse.setMessage("you have logout");
            return new ResponseEntity<>(new ApiResponse(true, logoutResponse), HttpStatus.CREATED);
        } catch (Exception exception) {
            logoutResponse.setMessage(exception.getMessage());
            return new ResponseEntity<>(new ApiResponse(false, logoutResponse), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findBook")
    public ResponseEntity<?> findBook(@RequestBody FindBookRequest findBookRequest) {

    FindBookResponse findBookResponse = new FindBookResponse();

    try {
      Book book= bookManagerService.findBook(findBookRequest);
        findBookResponse.setMessage(book+"");
        return new ResponseEntity<>(new ApiResponse(true,findBookResponse), HttpStatus.CREATED);
    }catch (Exception exception){
        findBookResponse.setMessage(exception.getMessage());
        return new ResponseEntity<>(new ApiResponse(false,findBookResponse), HttpStatus.BAD_REQUEST);
    }
}

@GetMapping("/bookList")
    public ResponseEntity<?> bookList(){
   BookListResponse bookListResponse = new BookListResponse();

   try {
       List<Book> bookList = bookManagerService.listOfBooks();
       bookListResponse.setMessage(bookList+"");
       return new ResponseEntity<>(new ApiResponse(true,bookListResponse), HttpStatus.CREATED);
   }catch (Exception exception){
       bookListResponse.setMessage(exception.getMessage());
       return new ResponseEntity<>(new ApiResponse(false,bookListResponse), HttpStatus.BAD_REQUEST);
   }
}

@GetMapping("/authorBookList/{author}")
    public ResponseEntity<?> authorBook(@PathVariable("author") String author){
        BookListResponse bookListResponse = new BookListResponse();
        try {
            List<Book> books = bookManagerService.findListOfBooksByAuthor(author);
            bookListResponse.setMessage(books+"");
            return new ResponseEntity<>(new ApiResponse(true,bookListResponse), HttpStatus.CREATED);
        }catch (Exception exception){
            bookListResponse.setMessage(exception.getMessage());
            return new ResponseEntity<>(new ApiResponse(false,bookListResponse), HttpStatus.BAD_REQUEST);
        }
}

@PostMapping("/checkIn")
    public ResponseEntity<?> checkIn(@RequestBody CheckInRequest checkInRequest){
        CheckInResponse checkInResponse = new CheckInResponse();

        try {
            bookManagerService.checkIn(checkInRequest);
            checkInResponse.setMessage("you have checked in");
            return new ResponseEntity<>(new ApiResponse(true,checkInResponse), HttpStatus.CREATED);
        }catch (Exception exception){
            checkInResponse.setMessage(exception.getMessage());
            return new ResponseEntity<>(new ApiResponse(false,checkInResponse), HttpStatus.BAD_REQUEST);
        }
}

@PostMapping("/checkOut")
    public ResponseEntity<?> checkOut(@RequestBody CheckoutRequest checkoutRequest){
  CheckOutResponse checkOutResponse = new CheckOutResponse();
  try {
      bookManagerService.checkOut(checkoutRequest);
      checkOutResponse.setMessage("you have checked out");
      return new ResponseEntity<>(new ApiResponse(true,checkOutResponse), HttpStatus.CREATED);
  }catch (Exception exception){
      checkOutResponse.setMessage(exception.getMessage());
      return new ResponseEntity<>(new ApiResponse(false,checkOutResponse), HttpStatus.BAD_REQUEST);
  }
}


}
