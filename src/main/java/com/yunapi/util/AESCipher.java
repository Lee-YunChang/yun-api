package com.yunapi.util;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

@Slf4j
public class AESCipher {

	static Logger logger = LoggerFactory.getLogger(AESCipher.class);
	static Encoder encoder = Base64.getEncoder();
	static Decoder decoder = Base64.getDecoder();
	
	private static String ALGORITHM = "AES/CBC/PKCS5Padding";
	private static SecretKeySpec KEY = null;
	private static IvParameterSpec IV = null;

	static {
		try {
			KEY = new SecretKeySpec(System.getenv("ENCRYPTION_KEY").getBytes(), "AES");
			IV = new IvParameterSpec(System.getenv("ENCRYPTION_IV").getBytes());			
		} catch(Exception e) {
			logger.error("ENCRYPTION_KEY 또는 ENCRYPTION_IV 환경변수가 설정되어있지 않습니다.");
		}
	}

	public static String encrypt(String data) {
		if(data == null)
			return null;
		try {
			final Cipher c = Cipher.getInstance(ALGORITHM);
			c.init(Cipher.ENCRYPT_MODE, KEY, IV);
			final String encryptedData = new String(encoder.encode(c.doFinal(data.getBytes())), "UTF-8");
			return encryptedData;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static String decrypt(String data) {
		if(StringUtils.isBlank(data))
			return data;
		
		try {
			final Cipher c = Cipher.getInstance(ALGORITHM);
			c.init(Cipher.DECRYPT_MODE, KEY, IV);
			final String decryptedData = new String(c.doFinal(decoder.decode(data.getBytes("UTF-8"))));
			return decryptedData;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static String encrypt2(String data,String uuid)  {
		if(data == null)
			return null;
		try {
			SecretKeySpec key = new SecretKeySpec(("yun"+uuid).substring(0,32).getBytes(), "AES");
			IvParameterSpec iv = new IvParameterSpec(uuid.substring(0, 16).getBytes());
			final Cipher c = Cipher.getInstance(ALGORITHM);
			c.init(Cipher.ENCRYPT_MODE, key, iv);
			final String encryptedData = new String(encoder.encode(c.doFinal(data.getBytes())), "UTF-8");
			return encryptedData;
		} catch (Exception e) {
			System.out.println(e);
			throw new RuntimeException(e);
		}
	}

	public static String decrypt2(String data,String uuid)  {
		if(StringUtils.isBlank(data))
			return data;

		try {
			SecretKeySpec key = new SecretKeySpec(("yun"+uuid).substring(0,32).getBytes(), "AES");
			IvParameterSpec iv = new IvParameterSpec(uuid.substring(0, 16).getBytes());
			final Cipher c = Cipher.getInstance(ALGORITHM);
			c.init(Cipher.DECRYPT_MODE, key, iv);
			final String decryptedData = new String(c.doFinal(decoder.decode(data.getBytes("UTF-8"))));
			return decryptedData;
		} catch (Exception e) {
			System.out.println(e);
			throw new RuntimeException(e);
		}
	}

}
