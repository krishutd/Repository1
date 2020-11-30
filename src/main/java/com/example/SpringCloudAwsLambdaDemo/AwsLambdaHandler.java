package com.example.SpringCloudAwsLambdaDemo;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import org.springframework.cloud.function.adapter.aws.SpringBootRequestHandler;

public class AwsLambdaHandler extends SpringBootRequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {
}
