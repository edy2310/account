package com.edgar.account.Console;

import com.edgar.account.Models.Account;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.mockito.*;

import org.powermock.reflect.Whitebox;


@DisplayName("Login test")
public class LoginTest {

    @InjectMocks
    @Spy
    Login login;

    @Test
    public void failTryLoginTest() {
        assertThrows(Exception.class, ()->{
            Whitebox.invokeMethod(login, "tryLogin", 2001, 1111);
        });

    }
}
