package org.libraryManager.controller;

import org.libraryManager.data.models.Book;
import org.libraryManager.data.models.User;
import org.libraryManager.dtos.request.AddBookRequest;
import org.libraryManager.dtos.request.RemoveBookRequest;
import org.libraryManager.dtos.response.AddBookResponse;
import org.libraryManager.dtos.response.ApiResponse;
import org.libraryManager.dtos.response.FindAccountResponse;
import org.libraryManager.dtos.response.RemoveBookResponse;
import org.libraryManager.service.BookManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BookController {
    @Autowired
    BookManagerService bookManagerService;

    @GetMapping("/findAccount{username}")
    public ResponseEntity<?> findAccount(@PathVariable("username") String username){
        FindAccountResponse findAccountResponse = new FindAccountResponse();

        try {
            User user = bookManagerService.findAccountBelongingTo(username);
            findAccountResponse.setMessage(user+"");
            return new ResponseEntity<>(new ApiResponse(true,findAccountResponse), HttpStatus.CREATED);
        } catch (Exception exception){
            findAccountResponse.setMessage(exception.getMessage());
            return new ResponseEntity<>(new ApiResponse(false,findAccountResponse), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/addBook")
    public ResponseEntity<?> addBook(@RequestBody AddBookRequest addBookRequest){
        AddBookResponse addBookResponse = new AddBookResponse();

        try {
         Book book = bookManagerService.addBook(addBookRequest);
            addBookResponse.setMessage("Book id " + book);
            return new ResponseEntity<>(new ApiResponse(true,addBookResponse), HttpStatus.CREATED);
        }catch (Exception exception){
            addBookResponse.setMessage(exception.getMessage());
            return new ResponseEntity<>(new ApiResponse(false,addBookResponse), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/removeBook")
    public ResponseEntity<?> removeBook(@RequestBody RemoveBookRequest removeBookRequest){
        RemoveBookResponse removeBookResponse = new RemoveBookResponse();

        try {
            bookManagerService.removeBook(removeBookRequest);
            removeBookResponse.setMessage("Book removed successfully");
            return new ResponseEntity<>(new ApiResponse(true,removeBookResponse), HttpStatus.CREATED);
        }catch (Exception exception){
            removeBookResponse.setMessage(exception.getMessage());
            return new ResponseEntity<>(new ApiResponse(false,removeBookResponse), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deleteBook")
    public ResponseEntity<?> deleteBooks(){
        RemoveBookResponse removeBookResponse = new RemoveBookResponse();

        try {
            bookManagerService.deleteAllBooks();
            removeBookResponse.setMessage("All books removed");
            return new ResponseEntity<>(new ApiResponse(true,removeBookResponse), HttpStatus.CREATED);
        }catch (Exception exception){
            removeBookResponse.setMessage(exception.getMessage());
            return new ResponseEntity<>(new ApiResponse(false,removeBookResponse), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("deleteAuthor")
    public ResponseEntity<?> deleteAuthor(@RequestBody String author){
        RemoveBookResponse removeBookResponse = new RemoveBookResponse();
        try {
            bookManagerService.deleteAllBooksByAuthor(author);
            removeBookResponse.setMessage("Books removed");
            return new ResponseEntity<>(new ApiResponse(true,removeBookResponse), HttpStatus.CREATED);
        } catch (Exception exception){
            removeBookResponse.setMessage(exception.getMessage());
            return new ResponseEntity<>(new ApiResponse(false,removeBookResponse), HttpStatus.BAD_REQUEST);
        }
    }
}
