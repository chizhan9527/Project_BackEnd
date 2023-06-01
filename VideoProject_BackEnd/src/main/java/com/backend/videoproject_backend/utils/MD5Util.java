package com.backend.videoproject_backend.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class MD5Util {


  public static String encrypt(String source) {
    return encodeMd5(source.getBytes());
  }

  private static String encodeMd5(byte[] source) {
    try {
      return encodeHex(MessageDigest.getInstance("MD5").digest(source));
    } catch (NoSuchAlgorithmException e) {
      throw new IllegalStateException(e.getMessage(), e);
    }
  }

  private static String encodeHex(byte[] bytes) {
    StringBuffer buffer = new StringBuffer(bytes.length * 2);
    for (int i = 0; i < bytes.length; i++) {
      if (((int) bytes[i] & 0xff) < 0x10) {
        buffer.append("0");
      }
      buffer.append(Long.toString((int) bytes[i] & 0xff, 16));
    }
    return buffer.toString();
  }
  //解密算法
  private static byte[] decodeHex(String hexString) {
    int length = hexString.length();
    if (length % 2 != 0) {
      throw new IllegalArgumentException("Invalid hex string");
    }

    byte[] bytes = new byte[length / 2];
    for (int i = 0; i < length; i += 2) {
      String hexByte = hexString.substring(i, i + 2);
      int decimal = Integer.parseInt(hexByte, 16);
      bytes[i / 2] = (byte) decimal;
    }

    return bytes;
  }


}