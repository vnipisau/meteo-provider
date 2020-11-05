/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo.provider.misc;

import java.security.Key;
import java.security.spec.KeySpec;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Chiper {

    Logger log = LoggerFactory.getLogger(Chiper.class);

    private final String algorithm = "AES";
    private String password;
    private String salt;
    private final String keygenspec = "PBKDF2WithHmacSHA1";
    private final String cipherspec = "AES/CBC/PKCS5Padding";

    public Chiper(String password, String salt) {
        super();
        this.password = password;
        this.salt = salt;
    }

    public String encrypt(String data) throws Exception {
        Key key = getKey();
        Cipher cipher = Cipher.getInstance(cipherspec);
        cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(new byte[16]));
        byte[] encVal = cipher.doFinal(data.getBytes());
        String encryptedValue = Base64.getEncoder().encodeToString(encVal);
        log.info("Encrypted value of " + data + ": " + encryptedValue);
        return encryptedValue;
    }

    public String decrypt(String encryptedData) throws Exception {
        Key key = getKey();
        Cipher cipher = Cipher.getInstance(cipherspec);
        cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(new byte[16]));
        byte[] decordedValue = Base64.getDecoder().decode(encryptedData);
        byte[] decValue = cipher.doFinal(decordedValue);
        String decryptedValue = new String(decValue);
        log.info("Decrypted value of " + encryptedData + ": " + decryptedValue);
        return decryptedValue;
    }

    private Key getKey() throws Exception {
        SecretKeyFactory factory = SecretKeyFactory.getInstance(keygenspec);
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt.getBytes(), 65536, 128);
        SecretKey tmp = factory.generateSecret(spec);
        SecretKey secret = new SecretKeySpec(tmp.getEncoded(), algorithm);
        //log.info("key: " + DatatypeConverter.printHexBinary(secret.getEncoded()));
        return secret;
    }

    public static Chiper getFromEnv(String key) {
        String pswd = System.getenv(key + "_PASSWORD");
        String salt = System.getenv(key + "_SALT");
        return new Chiper(pswd, salt);
    }



}
