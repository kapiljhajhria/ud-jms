package com.jhajhria.jms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JmsApplication {

    public static void main(String[] args) throws Exception {
//       below configuration was set explicitly. Doesn't need to set it up as spring-boot will automatically configure the app when it notices the class associated with

//        ActiveMQServer server = ActiveMQServers.newActiveMQServer(new ConfigurationImpl()
//                .setPersistenceEnabled(false)
//                .setJournalDirectory("target/data/journal")
//                .setSecurityEnabled(false)
//                .addAcceptorConfiguration("invm", "vm://0"));
//
//        server.start();
        SpringApplication.run(JmsApplication.class, args);
    }



}