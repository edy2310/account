package com.edgar.account.Api;

import com.edgar.account.Models.Account;
import com.edgar.account.Models.Transaction;
import com.edgar.account.Repositories.AccountRepository;
import com.edgar.account.Repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.Optional;

@RestController
@RequestMapping("/transactions")
public class TransactionApi {

    @Autowired
    TransactionRepository transactionRepo;

    @Autowired
    AccountRepository accountRepo;

    //Create Transaction
    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Transaction> createTransaction(@RequestBody Transaction transaction){
        try{
            Transaction savedTransaction = transactionRepo.save(transaction);
            if(savedTransaction != null)
                return new ResponseEntity<Transaction>(transaction, null, HttpStatus.CREATED);
        }
        catch (Exception e){
            System.out.println(e);
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.badRequest().build();
    }

    //External consult service
    //Debit
    @PostMapping(path = "/debit/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Integer> createDebit(@RequestBody Transaction  transaction, @PathVariable int id){
        Optional<Account> optionalAccount = accountRepo.findById(id);
        if (optionalAccount.isPresent()){
            Account transactionAccount = optionalAccount.get();
            if(transactionAccount.getBalance().subtract(transaction.getAmount()).compareTo(BigInteger.ZERO) == -1){
                return ResponseEntity.badRequest().build();
            }
            else{
                transactionAccount.setBalance(transactionAccount.getBalance().subtract(transaction.getAmount()));
                accountRepo.save(transactionAccount);
                Transaction newTransaction = new Transaction(transaction.getTransactionType(), transaction.getAmount().multiply(BigInteger.valueOf(-1)), transaction.getDescription(), transactionAccount);
                Transaction transactionSaved = transactionRepo.save(newTransaction);
                return new ResponseEntity<Integer>(transactionSaved.getId(), null, HttpStatus.CREATED);
            }
        }
        else
            return ResponseEntity.badRequest().build();
    }

}