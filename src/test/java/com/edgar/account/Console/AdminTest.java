package com.edgar.account.Console;

import com.edgar.account.Models.Account;
import com.edgar.account.Models.Transaction;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.powermock.reflect.Whitebox;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

@DisplayName("Admin Test")
public class AdminTest {

    @InjectMocks
    @Spy
    Admin admin;
    Account acc = mock(Account.class);
    Transaction transaction = mock(Transaction.class);

    @Test
    public void failMakeDeposit() {
        assertThrows(Exception.class, ()->{
            Whitebox.invokeMethod(admin, "makeDeposit", acc);
        });
    }

    @Test
    public void failMakeWithdrawal() {
        assertThrows(Exception.class, ()->{
            Whitebox.invokeMethod(admin, "makeWithdrawal", acc);
        });
    }

    @Test
    public void failConsultBalance() {
        assertThrows(Exception.class, ()->{
            Whitebox.invokeMethod(admin, "consultBalance", acc);
        });
    }

    @Test
    public void failUpdateAccount() {
        assertThrows(Exception.class, ()->{
            Whitebox.invokeMethod(admin, "updateAccount", acc);
        });
    }

    @Test
    public void failCreateTransaction() {
        assertThrows(Exception.class, ()->{
            Whitebox.invokeMethod(admin, "createTransaction", transaction);
        });
    }
}
