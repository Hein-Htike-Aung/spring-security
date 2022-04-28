package com.example.module04formlogin.util;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class OptGenerator {

    public static int generateOpt() {

        int code;

        try {
            code = SecureRandom.getInstanceStrong().nextInt(9000) + 1000;

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Exception occurred during Generating opt!");
        }

        return code;
    }
}
