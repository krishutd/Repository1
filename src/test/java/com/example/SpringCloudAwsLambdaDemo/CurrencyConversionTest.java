package com.example.SpringCloudAwsLambdaDemo;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.example.SpringCloudAwsLambdaDemo.client.ExternalAPI;
import com.example.SpringCloudAwsLambdaDemo.dto.ExchangeRateResponse;
import com.example.SpringCloudAwsLambdaDemo.dto.Rates;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.MockitoAnnotations;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class CurrencyConversionTest {

    @Mock
    ExternalAPI externalAPI;

    @Mock
    APIGatewayProxyRequestEvent inputEvent;

    @InjectMocks
    CurrencyConversion currencyConversion;
    Map<String, String> hm;


    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        hm = new HashMap<>();
        hm.put("base", "USD");
        hm.put("baseprice", "100");
        hm.put("symbols", "INR");
        when(inputEvent.getQueryStringParameters()).thenReturn(hm);

    }


    @Test
    public void getConvertedValueTest() {
        Double expected = 792.1235;
        assertEquals(expected, currencyConversion.getConvertedValue(inputEvent,7.921235));
        verify(inputEvent).getQueryStringParameters();
    }

    @DisplayName("Testing method which gets Exchange Rate for Conversion")
    @Test
    public void getExchangeRateForConversionTest() {
        Rates rate = new Rates();
        rate.setINR(74.0669449082);

        ExchangeRateResponse exchangeRateResponse = new ExchangeRateResponse();
        exchangeRateResponse.setRates(rate);
        exchangeRateResponse.setBase("USD");
        exchangeRateResponse.setDate("2020-11-30");

        when(externalAPI.getExchangeRates("USD", "INR")).thenReturn(exchangeRateResponse);



        Double returnedexchangeRate = currencyConversion.getExchangeRateForConversion(inputEvent);
        verify(inputEvent, times(3)).getQueryStringParameters();

    }

    @Test
    @DisplayName("Testing method which gets the Query String parameters from URL")
    public void getQueryStringParameterTest() {
        String dummyReturnedValue = currencyConversion.getQueryStringParameter(inputEvent, "base");
        assertEquals("USD", dummyReturnedValue);
        verify(inputEvent).getQueryStringParameters();

    }

}