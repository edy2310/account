package com.edgar.account.Api;

import com.edgar.account.Models.Transaction;
import com.edgar.account.Util.Enum.TransactionType;
import com.edgar.account.Util.URIs;
import static org.junit.Assert.*;

import static org.hamcrest.CoreMatchers.*;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.math.BigInteger;
import java.net.URI;
import java.net.URISyntaxException;

@DisplayName("Transaction API test")
public class TransactionApiTest {

    RestTemplate rt = new RestTemplate();

    @Test
    public void testCreateTransaction() throws URISyntaxException {
        Transaction newTransaction = new Transaction(TransactionType.DEBIT_CHECKS, BigInteger.ZERO, "Debit check from system", null);
        URI uri = new URI(URIs.SCHEME + URIs.HOST + URIs.TRANSACTIONS);
        ResponseEntity transactionEntity =  rt.postForEntity(uri, newTransaction, Transaction.class);

        assertEquals(201, transactionEntity.getStatusCodeValue());
        assertThat(transactionEntity.getBody(), instanceOf(Transaction.class));
    }

    @Test
    public void failTestCreateTransaction() throws URISyntaxException {
        Transaction newTransaction = new Transaction(null, null, null, null);
        URI uri = new URI(URIs.SCHEME + URIs.HOST + URIs.TRANSACTIONS);

        Assertions.assertThrows(HttpClientErrorException.class, ()->{
            rt.postForEntity(uri, newTransaction, Transaction.class);
        });
    }

    @Test
    public void testCreateDebit() throws URISyntaxException {
        Transaction newTransaction = new Transaction(TransactionType.WITHDRAWAL, BigInteger.valueOf(10), "Testing external withdrawal");
        URI uri = new URI(URIs.SCHEME + URIs.HOST + URIs.TRANSACTIONS + "/debit/1001");
        ResponseEntity transactionEntity =  rt.postForEntity(uri, newTransaction, Integer.class);

        assertEquals(201, transactionEntity.getStatusCodeValue());
    }

    @Test
    public void failTestCreateDebit() throws URISyntaxException {
        Transaction newTransaction = new Transaction(null, null, null);
        URI uri = new URI(URIs.SCHEME + URIs.HOST + URIs.TRANSACTIONS + "/debit/1001");

        Assertions.assertThrows(HttpServerErrorException.class, ()->{
            ResponseEntity transactionEntity =  rt.postForEntity(uri, newTransaction, Integer.class);
        });
    }

    @Test
    public void failUserTestCreateDebit() throws URISyntaxException {
        Transaction newTransaction = new Transaction(TransactionType.WITHDRAWAL, BigInteger.valueOf(10), "Testing external withdrawal");
        URI uri = new URI(URIs.SCHEME + URIs.HOST + URIs.TRANSACTIONS + "/debit/2001");

        Assertions.assertThrows(HttpClientErrorException.class, ()->{
            ResponseEntity transactionEntity =  rt.postForEntity(uri, newTransaction, Integer.class);
        });
    }
}
