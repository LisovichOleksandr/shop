package com.selm.catalogue.test_ed;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * BCryptPasswordEncoder ето стандартний енкодер которий входит в состав Spring Security
 * */

public class Scratch {
    public static void main(String[] args) {
        System.out.println(new BCryptPasswordEncoder()
                .encode("password"));
    }
}
