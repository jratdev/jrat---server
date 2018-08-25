package net.jrat.utils;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class Encryptor
{
	public static byte[] encrypt(String key, byte[] text) throws Exception
	{
		byte[] keyBytes = key.getBytes(Charset.forName("UTF-8"));
		final MessageDigest digest = MessageDigest.getInstance("SHA-256");
		
		keyBytes = digest.digest(keyBytes);
		keyBytes = Arrays.copyOf(keyBytes, 16);
		
		final SecretKeySpec secretKey = new SecretKeySpec(keyBytes, "AES");
		
		final Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.ENCRYPT_MODE, secretKey);
		
		return cipher.doFinal(text);
	}
	
	public static byte[] decrypt(String key, byte[] text) throws Exception
	{
		byte[] keyBytes = key.getBytes(Charset.forName("UTF-8"));
		final MessageDigest digest = MessageDigest.getInstance("SHA-256");
		
		keyBytes = digest.digest(keyBytes);
		keyBytes = Arrays.copyOf(keyBytes, 16);
		
		final SecretKeySpec secretKey = new SecretKeySpec(keyBytes, "AES");
		
		final Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.DECRYPT_MODE, secretKey);
		
		return cipher.doFinal(text);
	}
}