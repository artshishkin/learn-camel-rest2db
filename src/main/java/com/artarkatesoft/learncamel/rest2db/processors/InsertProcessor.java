package com.artarkatesoft.learncamel.rest2db.processors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InsertProcessor implements Processor {

    private Logger log = LoggerFactory.getLogger(InsertProcessor.class);

    @Override
    public void process(Exchange exchange) throws Exception {
        String input = exchange.getIn().getBody(String.class);
        log.info("Input is: {}", input);

        JSONParser parser = new JSONParser();
        Object parse = parser.parse(input);

        JSONObject jsonObject = (JSONObject) parse;
        String name = (String) jsonObject.get("name");
        String capital = (String) jsonObject.get("capital");

        String insertQuery = "INSERT INTO rest_countries (name, capital) VALUES ('" + name + "','" + capital + "')";
        exchange.getIn().setBody(insertQuery);
    }
}
