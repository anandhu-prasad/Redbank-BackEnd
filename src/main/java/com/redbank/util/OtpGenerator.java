package com.redbank.util;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class OtpGenerator {

    private final SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");

    public OtpGenerator() throws NoSuchAlgorithmException {
    }

    public String generate(int maxLength) {
        final StringBuilder otp = new StringBuilder(maxLength);
        for (int i = 0; i < maxLength; i++) {
            otp.append(secureRandom.nextInt(9));
        }
        return otp.toString();
    }
}