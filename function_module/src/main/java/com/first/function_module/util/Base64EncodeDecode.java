package com.first.function_module.util;

import java.util.Base64;

public class Base64EncodeDecode {

    private static Base64.Encoder encoder = Base64.getEncoder();
    private static Base64.Decoder decoder = Base64.getDecoder();

    public static String encode(String value) {

        return encoder.encodeToString(value.getBytes());
    }

    public static String decode(String value) {

        byte[] decode = decoder.decode(value);

        return new String(decode);
    }

    public static byte[] encodePhoto(byte[] photo) {

        return encoder.encode(photo);
    }

    public static byte[] decodePhoto(byte[] photo) {

        return decoder.decode(photo);
    }

}
