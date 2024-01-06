package org.libraryManager.data.repositories;

import org.libraryManager.data.models.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TransactionRepository extends MongoRepository<Transaction,String> {
    Transaction findByTransId(String transId);

}
