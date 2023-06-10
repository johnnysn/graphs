package com.uriel.graphs;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {

    private final ConsoleApplication consoleApplication;

    public Application(ConsoleApplication consoleApplication) {
        this.consoleApplication = consoleApplication;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        consoleApplication.start();
    }
}
