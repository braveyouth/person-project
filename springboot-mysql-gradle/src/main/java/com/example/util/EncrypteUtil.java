package com.example.util;

import com.jrwhjd.security.crypto.AESCoder;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @author 涂鼎 [eMail: tuding27@gmail.com]
 * @version 0.1 [2020/3/3]
 */
public class EncrypteUtil {


  public static final String AES_KEY = "*Ufe34fFBVli$(f";


  public static String encrypt(String plainText)
      throws InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, NoSuchPaddingException {
    return AESCoder.encryptToHex(plainText.getBytes(), AES_KEY, 128);
  }


  public static String decrypte(String cipherText)
      throws DecoderException, InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, NoSuchPaddingException {
    byte[] decripted = AESCoder.decrypt(Hex.decodeHex(cipherText.toCharArray()), AES_KEY, 128);
    return new String(decripted);
  }


  public static void main(String[] args)
      throws IllegalBlockSizeException, NoSuchPaddingException, BadPaddingException, NoSuchAlgorithmException, InvalidKeyException, DecoderException {
    System.out.println(encrypt("12345678"));
    System.out.println(decrypte("A0689A71B44743FD7EC253A03A03564F"));
  }

}
