package com.edgar.account.Api;

import com.edgar.account.Models.Account;
import com.edgar.account.Repositories.AccountRepository;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.Optional;


@RestController
@RequestMapping("/accounts")
public class AccountApi {

    @Autowired
    AccountRepository repo;

    //Get Account by Id
    @GetMapping(path="/{id}", produces = "application/json")
    public Account getAccount(@PathVariable int id){
        Account newAccount;
        Optional<Account> optionalAccount = repo.findById(id);
        if(optionalAccount.isPresent())
            newAccount  = optionalAccount.get();
        else
            newAccount = null;
        return newAccount;
    }

    //Create Account
    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Account> createAccount(@RequestBody Account account){
        try{
            Account savedAccount = repo.save(account);
            if(savedAccount != null)
                return new ResponseEntity<Account>(account, null, HttpStatus.CREATED);
        }
        catch (Exception e){
            System.out.println("Exception: " + e);
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.badRequest().build();
    }

    //Update Account
    @PutMapping(path = "/{id}", consumes = "application/json", produces = "application/json")
    @JsonIgnore
    public void updateAccount(@RequestBody Account account, @PathVariable int id){
        Account accountToUpdate;
        try{
            Optional<Account> optionalAccount = repo.findById(id);
            if (optionalAccount.isPresent()){
                accountToUpdate = optionalAccount.get();
                accountToUpdate.setBalance(account.getBalance());
                repo.save(accountToUpdate);
            }
        }catch (Exception e){
            System.out.println(e);
        }

    }

    //Get Balance
    @GetMapping(path = "/balance/{id}")
    public BigInteger getBalance(@PathVariable int id){
        Optional<Account> optionalAccount = repo.findById(id);
        if(optionalAccount.isPresent())
            return optionalAccount.get().getBalance();
        else
            return BigInteger.valueOf(-1);
    }
}
