package com.edgar.account.Repositories;

import com.edgar.account.Models.Transaction;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<Transaction, Integer> {
}
