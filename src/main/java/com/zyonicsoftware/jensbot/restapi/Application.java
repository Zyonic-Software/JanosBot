package com.zyonicsoftware.jensbot.restapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;

@SpringBootApplication
public class Application {

    public void startSpring(final int connectionPort) {
        final SpringApplication application = new SpringApplication(Application.class);
        application.setDefaultProperties(Collections.singletonMap("server.port", connectionPort));
        application.run();
    }

}
