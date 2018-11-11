package com.jagadeeshposni.amazon.s3.Utils;

import java.util.Base64;

public class Base64Util {

    public String encode(String input) {
        byte[] encodedString;
        encodedString = Base64.getEncoder().encode(input.getBytes());
        return encodedString.toString();
    }


    public String decode(String encodedString) {
        return Base64.getDecoder().decode(encodedString).toString();
    }


}
