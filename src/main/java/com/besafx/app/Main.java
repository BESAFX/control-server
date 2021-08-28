package com.besafx.app;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import javax.swing.*;

@SpringBootApplication
public class Main implements ApplicationRunner {

    public static void main(String[] args) {
        final SpringApplicationBuilder builder = new SpringApplicationBuilder(Main.class);
        builder.headless(false).run(args);
    }

    @Override
    public void run(ApplicationArguments args) {
        JOptionPane.showMessageDialog(null, "Control server is up and running now.", "Control Server", JOptionPane.INFORMATION_MESSAGE);
    }
}
