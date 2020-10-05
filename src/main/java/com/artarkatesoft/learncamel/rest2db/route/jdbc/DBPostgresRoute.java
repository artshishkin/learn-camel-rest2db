package com.artarkatesoft.learncamel.rest2db.route.jdbc;

import com.artarkatesoft.learncamel.rest2db.processors.InsertProcessor;
import org.apache.camel.builder.RouteBuilder;

public class DBPostgresRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("direct:dbInput")
                .process(new InsertProcessor())
                .log("SQL Query is ${body}")
                .to("jdbc:myDataSource")
                .to("sql:select * from rest_countries")
                .to("log:?level=INFO&showBody=true");
    }
}
