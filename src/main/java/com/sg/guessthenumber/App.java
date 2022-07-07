/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.sg.guessthenumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author stwal
 */
@SpringBootApplication
public class App {
    
    @Autowired
    private JdbcTemplate jdbc;
    
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
