package com.project.test.fileserv;

import org.springframework.boot.SpringApplication;

public class TestFileServApplication {

    public static void main(String[] args) {
        SpringApplication.from(FileServApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
