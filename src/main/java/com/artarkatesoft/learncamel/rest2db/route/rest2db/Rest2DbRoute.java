package com.artarkatesoft.learncamel.rest2db.route.rest2db;

import com.artarkatesoft.learncamel.rest2db.processors.ExceptionProcessor;
import com.artarkatesoft.learncamel.rest2db.processors.InsertProcessor;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;

public class Rest2DbRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {

        onException(Exception.class)
                .handled(true)
                .log("Exception while inserting data")
                .process(new ExceptionProcessor());

        from("timer:learnTimer?period=10s")
                .setHeader(Exchange.HTTP_METHOD, constant("GET"))
//                .setHeader(Exchange.HTTP_URI, simple("http://restcountries.eu/rest/v2/alpha/uesdsds"))
                .to("http://restcountries.eu/rest/v2/alpha/us")
                .convertBodyTo(String.class)
                .log("Answer from api: ${body} and Headers are ${headers}")
                .process(new InsertProcessor())
                .log("SQL Query is ${body}")
                .to("jdbc:myDataSource")
                .to("sql:select * from rest_countries limit 1")
                .to("log:?level=INFO&showBody=true")
//                .to("direct:outputDb")
        ;
    }
}
