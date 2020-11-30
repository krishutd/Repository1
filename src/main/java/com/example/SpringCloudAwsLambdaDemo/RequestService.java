package com.example.SpringCloudAwsLambdaDemo;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.function.Function;

@Component
public class RequestService implements Function<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {


    @Autowired
    CurrencyConversion currencyConversion;

    @Override
    public APIGatewayProxyResponseEvent apply(APIGatewayProxyRequestEvent inputEvent) {
        APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent();
        setResponse(inputEvent, response);
        return response;
    }


    private void setResponse(APIGatewayProxyRequestEvent inputEvent, APIGatewayProxyResponseEvent response) {

        Double exchangeRateForConversion = currencyConversion.getExchangeRateForConversion(inputEvent);
        Double convertedValue = currencyConversion.getConvertedValue(inputEvent,exchangeRateForConversion );

        response.setStatusCode(200);

        try {
            response.setBody("Base currency = " + inputEvent.getQueryStringParameters().get("base") +
                    "\tBase Price = " + inputEvent.getQueryStringParameters().get("baseprice") +
                    "\tExchange Rate = " + exchangeRateForConversion +
                    "\t\tBase price equivalent in " + inputEvent.getQueryStringParameters().get("symbols") +
                    "= " + convertedValue);
        } catch (NullPointerException e) {
            response.setBody("Enter all query values");
        }
    }
}
