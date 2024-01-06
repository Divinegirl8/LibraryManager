package org.libraryManager.data.repositories;

import org.libraryManager.data.models.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BookRepository extends MongoRepository<Book,String> {
    Book findBookByAuthorAndTitle(String author,String title);
    List<Book> findBookByAuthor(String author);

}
