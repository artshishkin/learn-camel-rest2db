package com.artarkatesoft.learncamel.rest2db.route.rest;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;

public class RestConsumerRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("direct:restCall")
                .log("Received message ${body}")
                .setHeader(Exchange.HTTP_METHOD, constant("GET"))
                .setHeader(Exchange.HTTP_URI, simple("https://restcountries.eu/rest/v2/alpha/${body}"))
                .to("https://restcountries.eu/rest/v2/alpha/${body}")
                .log("Answer from api: ${body} and Headers are ${headers}")
                .to("log:?showBody=true&level=INFO");
    }
}
