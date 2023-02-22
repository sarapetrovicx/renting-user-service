package com.raf.restdemo.security.activationCode;

import java.nio.charset.Charset;
import java.util.Random;

public class ActivationCodeGenerator {

    public String generateCode(){
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        StringBuilder sb = new StringBuilder(6);

        for (int i = 0; i < 6; i++) {
            int index
                    = (int)(AlphaNumericString.length()
                    * Math.random());
            sb.append(AlphaNumericString
                    .charAt(index));
        }

        return sb.toString();
    }
}
