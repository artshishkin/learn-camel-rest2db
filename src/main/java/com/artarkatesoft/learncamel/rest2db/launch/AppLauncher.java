package com.artarkatesoft.learncamel.rest2db.launch;

import com.artarkatesoft.learncamel.rest2db.route.rest2db.Rest2DbRoute;
import org.apache.camel.main.Main;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;

public class AppLauncher {
    private Main main;

    public static void main(String[] args) throws Exception {
        AppLauncher appLauncher = new AppLauncher();
        appLauncher.boot();
    }


    public void boot() throws Exception {
        // create a Main instance
        main = new Main();

        DataSource dataSource = setupDataSource();

        main.bind("myDataSource", dataSource);

        // enable hangup support so you can press ctrl + c to terminate the JVM
//        main.enableHangupSupport();
        // add routes
        main.configure().addRoutesBuilder(new Rest2DbRoute());

        // run until you terminate the JVM
        System.out.println("Starting Camel REST to DB Route. Use ctrl + c to terminate the JVM.\n");
        main.run();
    }

    private DataSource setupDataSource() {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setUrl("jdbc:postgresql://localhost:5432/learn_camel");
        dataSource.setUser("postgres");
        dataSource.setPassword("123");
        return dataSource;
    }

}
