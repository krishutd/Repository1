package com.example.SpringCloudAwsLambdaDemo;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.example.SpringCloudAwsLambdaDemo.client.ExternalAPI;
import com.example.SpringCloudAwsLambdaDemo.dto.ExchangeRateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class CurrencyConversion {

    @Autowired
    ExternalAPI externalAPI;

    public Double getConvertedValue(APIGatewayProxyRequestEvent inputEvent, Double exchangeRateForConversion) {
        return exchangeRateForConversion * Double.parseDouble(getQueryStringParameter(inputEvent, "baseprice"));
    }

    public Double getExchangeRateForConversion(APIGatewayProxyRequestEvent inputEvent) {
        ExchangeRateResponse exchangeRateResponse = externalAPI.getExchangeRates(getQueryStringParameter(inputEvent, "base"), getQueryStringParameter(inputEvent, "symbols"));
        return (Double) exchangeRateResponse.getRates().get(getQueryStringParameter(inputEvent, "symbols"));
    }

    public String getQueryStringParameter(APIGatewayProxyRequestEvent inputEvent, String queryParameter) {
        Map<String,String> parameters = inputEvent.getQueryStringParameters();
        return parameters.get(queryParameter);
    }

}
