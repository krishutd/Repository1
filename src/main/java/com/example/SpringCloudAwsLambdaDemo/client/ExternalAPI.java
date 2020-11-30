package com.example.SpringCloudAwsLambdaDemo.client;

import com.example.SpringCloudAwsLambdaDemo.dto.ExchangeRateResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(url="https://api.exchangeratesapi.io/latest", name="ExchangeRateAPI")
public interface ExternalAPI {
    @GetMapping
    ExchangeRateResponse getExchangeRates(@RequestParam("base") String base, @RequestParam("symbols") String symbols);
}
