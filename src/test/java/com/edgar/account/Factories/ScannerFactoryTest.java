package com.edgar.account.Factories;

import static org.junit.Assert.*;

import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.Scanner;

@DisplayName("Scanner Factory Test")
public class ScannerFactoryTest {

    @Test
    public void createScanner(){
        Scanner sc = ScannerFactory.getScanner();
        assertNotNull(sc);
        assertThat(sc, CoreMatchers.instanceOf(Scanner.class));
    }
}
