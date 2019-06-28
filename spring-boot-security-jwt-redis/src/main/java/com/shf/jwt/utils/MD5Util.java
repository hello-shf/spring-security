package com.shf.jwt.utils;

import java.security.MessageDigest;

/**
 * 描述：
 *
 * @Author shf
 * @Date 2019/6/28 17:13
 * @Version V1.0
 **/
public class MD5Util {
    static public String EncryptMD5Byte(byte[] arrbt) {
        String code = "";
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(arrbt);
            byte[] arr = md5.digest();
            code = byteArrayToHex(arr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return code;
    }


    static public String encryptMd5Password(String pass) {
        String result = "";
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(pass.getBytes());
            byte[] arr = md5.digest();
            result = arr.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    static public String encryptMd5Password2(String pass) {
        String result = "";
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(pass.getBytes());
            byte[] arr = md5.digest();
            result = byteArrayToHex(arr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String byteArrayToHex(byte[] byteArray) {
        char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        char[] resultCharArray = new char[byteArray.length * 2];
        int index = 0;
        for (byte b : byteArray) {
            resultCharArray[index++] = hexDigits[b >>> 4 & 0xf];
            resultCharArray[index++] = hexDigits[b & 0xf];

        }
        return new String(resultCharArray);
    }

    public static void main(String[] args) {
        String pw = encryptMd5Password2("1");
        System.out.println(pw);
    }
}
