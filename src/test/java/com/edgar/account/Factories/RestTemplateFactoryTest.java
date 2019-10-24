package com.edgar.account.Factories;

import static org.junit.Assert.*;

import static org.hamcrest.CoreMatchers.*;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.web.client.RestTemplate;

@DisplayName("Rest Template Factory Test")
public class RestTemplateFactoryTest {

    @Test
    public void creatRestTemplate(){
        RestTemplate rt = RestTemplateFactory.getRestTemplate();
        assertNotNull(rt);
        assertThat(rt, instanceOf(RestTemplate.class));
    }
}
