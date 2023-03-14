package com.duoer.campus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication
public class CampusCatsWithMpApplication {

    public static void main(String[] args) throws UnknownHostException {
        SpringApplication.run(CampusCatsWithMpApplication.class, args);
        System.out.println(InetAddress.getLocalHost().getHostAddress());
    }

}
