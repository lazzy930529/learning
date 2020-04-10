package com.lw.consistenthash;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author liuwei
 * @date 2020-04-10 15:17
 */
public class HashUtil {
    private static MessageDigest md5;
    private static MessageDigest sha1;

    static {
        try {
            md5 = MessageDigest.getInstance("MD5");
            sha1 = MessageDigest.getInstance("SHA");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public static String getMD5Hash(byte[] bytes) {
        byte[] md5Bytes = md5.digest(bytes);
        return bytesToHex(md5Bytes);
    }

    public static String getSHA1Hash(byte[] bytes) {
        byte[] md5Bytes = sha1.digest(bytes);
        return bytesToHex(md5Bytes);
    }


    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte aByte : bytes) {
            String hex = Integer.toHexString(aByte & 0xFF);
            if (hex.length() < 2) {
                sb.append(0);
            }
            sb.append(hex);
        }
        return sb.toString();
    }

}
