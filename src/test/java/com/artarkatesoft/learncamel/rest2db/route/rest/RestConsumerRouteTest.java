package com.artarkatesoft.learncamel.rest2db.route.rest;

import org.apache.camel.RoutesBuilder;
import org.apache.camel.test.junit5.CamelTestSupport;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class RestConsumerRouteTest extends CamelTestSupport {
    @Override
    protected RoutesBuilder createRouteBuilder() throws Exception {
        return new RestConsumerRoute();
    }

    @Test
    void testRestRoute() {
        //given
        String code = "ua";
        //when
        Object requestBody = template.requestBody("direct:restCall", code);
        //then
        assertNotNull(requestBody);
    }
}