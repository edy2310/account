package com.edgar.account.Factories;

import org.springframework.web.client.RestTemplate;

public class RestTemplateFactory {
    public static RestTemplate rt;

    private RestTemplateFactory(){}

    public static RestTemplate getRestTemplate(){
        if(rt != null)
            return rt;
        else{
            rt = new RestTemplate();
            return rt;
        }
    }
}
