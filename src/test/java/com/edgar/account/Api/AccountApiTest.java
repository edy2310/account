package com.edgar.account.Api;

import com.edgar.account.Models.Account;
import com.edgar.account.Util.URIs;
import static org.junit.Assert.*;

import static org.hamcrest.CoreMatchers.*;

import org.hamcrest.Matchers;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.math.BigInteger;
import java.net.URI;
import java.net.URISyntaxException;

@DisplayName("Account API Test")
public class AccountApiTest {

    private RestTemplate rt = new RestTemplate();

    @Test
    public void testGetAccountById() throws URISyntaxException {
        URI uri = new URI(URIs.SCHEME + URIs.HOST + URIs.ACCOUNTS + 1001);
        Account acc = rt.getForObject(uri, Account.class);

        assertThat(acc, instanceOf(Account.class));
    }

    @Test
    public void failGetAccountById() throws URISyntaxException {
        URI uri = new URI(URIs.SCHEME + URIs.HOST + URIs.ACCOUNTS + 2001);
        Account acc = rt.getForObject(uri, Account.class);

        assertNull(acc);
    }

    @Test
    public void testCreateAccount() throws URISyntaxException {
        URI uri = new URI(URIs.SCHEME + URIs.HOST + URIs.ACCOUNTS);
        Account acc = new Account("Edgar", "Munoz", 1111, null, BigInteger.ZERO);
        ResponseEntity postAccount = rt.postForEntity(uri, acc, Account.class);

        assertThat(postAccount.getBody(), instanceOf(Account.class));
        assertEquals(201, postAccount.getStatusCodeValue());
    }

    @Test
    public void failTestCreateAccount() throws URISyntaxException {
        URI uri = new URI(URIs.SCHEME + URIs.HOST + URIs.ACCOUNTS);
        Account acc = new Account();
        assertThrows(HttpClientErrorException.class, ()->{
            ResponseEntity postAccount = rt.postForEntity(uri, acc, Account.class);
        });
    }

    @Test
    public void getBalanceTest() throws URISyntaxException {
        URI uri = new URI(URIs.SCHEME + URIs.HOST + URIs.ACCOUNTS + URIs.BALANCE + 1001);
        BigInteger currentBalance = rt.getForObject(uri, BigInteger.class);

        assertThat(currentBalance, Matchers.greaterThan(BigInteger.valueOf(-1)));
    }
}
