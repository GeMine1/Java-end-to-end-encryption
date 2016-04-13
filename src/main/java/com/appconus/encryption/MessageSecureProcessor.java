package com.appconus.encryption;

import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
import com.sun.org.apache.xml.internal.security.utils.Base64;

public class MessageSecureProcessor {
    private static final String ENCODE_START = "@ENCODE@";
    private static final String SPLITER = "@_@";
    protected static final String ENCRYPTED_FORM = ENCODE_START + "%s" + SPLITER + "%s ";

    public static String encrypt(String message, byte[] publicKey) {
        byte[] aesKeyBytes = AESCipher.generateAESKey();
        String base64EncryptedMessage = Base64.encode(AESCipher.encrypt(message.getBytes(), aesKeyBytes));
        String base64EncryptedKey = Base64.encode(RSACipher.encrypt(aesKeyBytes, publicKey));
        return String.format(ENCRYPTED_FORM, base64EncryptedKey, base64EncryptedMessage);
    }

    public static String decrypt(String message, byte[] privateKey) {
        String[] parts = message.split(SPLITER);
        String base64EncryptedKey = parts[0].replace(ENCODE_START, "");
        String base64EncryptedMessage = parts[1];
        try {
            byte[] aesKey = RSACipher.decrypt(Base64.decode(base64EncryptedKey), privateKey);
            return new String(AESCipher.decrypt(Base64.decode(base64EncryptedMessage), aesKey));
        } catch (Base64DecodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}

