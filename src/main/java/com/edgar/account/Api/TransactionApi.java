package com.edgar.account.Api;

import com.edgar.account.Models.Transaction;
import com.edgar.account.Repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
public class TransactionApi {

    @Autowired
    TransactionRepository repo;

    //Create Transaction
    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Transaction> createTransaction(@RequestBody Transaction transaction){
        try{
            Transaction savedTransaction = repo.save(transaction);
            if(savedTransaction != null)
                return new ResponseEntity<Transaction>(transaction, null, HttpStatus.CREATED);
        }
        catch (Exception e){
            System.out.println(e);
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.badRequest().build();
    }
}