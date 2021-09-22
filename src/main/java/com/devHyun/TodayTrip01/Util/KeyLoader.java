package com.devHyun.TodayTrip01.Util;

import org.springframework.context.annotation.Bean;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class KeyLoader {
    private String serviceKey;

    public KeyLoader(){
        try {
            serviceKey = new Scanner(new File("Key")).next();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Fail to read ServiceKey file");
        }

    }

    @Bean
    public String getServiceKey() {
        return serviceKey;
    }
}
