package com.edgar.account.Console;

import com.edgar.account.Models.Account;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.powermock.reflect.Whitebox;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

@DisplayName("Register Test")
public class RegisterTest {

    @InjectMocks
    @Spy
    Register register;
    Account acc = mock(Account.class);

    @Test
    public void failTryLoginTest() {
        assertThrows(Exception.class, ()->{
            Whitebox.invokeMethod(register, "createAccount", acc);
        });

    }
}
