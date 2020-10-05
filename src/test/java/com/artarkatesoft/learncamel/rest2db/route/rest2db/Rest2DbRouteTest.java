package com.artarkatesoft.learncamel.rest2db.route.rest2db;

import org.apache.camel.CamelContext;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.spi.Registry;
import org.apache.camel.support.DefaultRegistry;
import org.apache.camel.test.junit5.CamelTestSupport;
import org.junit.jupiter.api.Test;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class Rest2DbRouteTest extends CamelTestSupport {

    @Override
    protected CamelContext createCamelContext() throws Exception {
        Registry registry = new DefaultRegistry();
        registry.bind("myDataSource", createDataSource());
        return new DefaultCamelContext(registry);
    }

    private DataSource createDataSource() {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setUrl("jdbc:postgresql://localhost:5432/learn_camel");
        dataSource.setUser("postgres");
        dataSource.setPassword("123");
        return dataSource;
    }

    @Override
    protected RoutesBuilder createRouteBuilder() throws Exception {
        return new Rest2DbRoute();
    }

    @Test
    void rest2DbTest() {
        //when
        ArrayList arrayList = consumer.receiveBody("direct:outputDb", ArrayList.class);

        //then
        assertNotNull(arrayList);
        assertNotEquals(0, arrayList.size());
    }
}