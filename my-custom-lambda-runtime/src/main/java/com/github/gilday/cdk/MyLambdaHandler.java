package com.github.gilday.cdk;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayV2HTTPResponse;

public class MyLambdaHandler
    implements RequestHandler<
        com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent,
        APIGatewayV2HTTPResponse> {
  @Override
  public APIGatewayV2HTTPResponse handleRequest(
      final APIGatewayProxyRequestEvent input, final Context context) {
    return null;
  }
}
