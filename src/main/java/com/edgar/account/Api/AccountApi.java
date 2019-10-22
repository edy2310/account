package com.edgar.account.Api;

import com.edgar.account.Models.Account;
import com.edgar.account.Repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;


@RestController
@RequestMapping("/accounts")
public class AccountApi {

    @Autowired
    AccountRepository repo;

    //Get Account by Id
    @GetMapping("/{id}")
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
}
