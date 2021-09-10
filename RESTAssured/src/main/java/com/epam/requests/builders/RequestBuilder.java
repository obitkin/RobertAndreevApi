package com.epam.requests.builders;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.specification.RequestSpecification;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public abstract class RequestBuilder {

    public static final URI BASE_UDI = URI.create("https://api.trello.com");
    protected String BASE_PATH;

    protected final Map<String, String> queryParameters = new HashMap<>();
    protected final Map<String, String> pathParameters = new HashMap<>();
    protected Method requestMethod;

    public static RequestSpecification requestSpecification() {
        return new RequestSpecBuilder()
                .setAccept(ContentType.JSON)
                .setContentType(ContentType.JSON)
                .addQueryParam("UUID", UUID.randomUUID().toString())
                .setBaseUri(BASE_UDI)
                .build();
    }

}
