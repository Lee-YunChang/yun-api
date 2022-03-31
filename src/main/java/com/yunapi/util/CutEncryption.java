package com.yunapi.util;

import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Component
public class CutEncryption {

	public static String getEncSHA256(String txt) {
		StringBuffer sbuf = new StringBuffer();
		try {
			MessageDigest mDigest = MessageDigest.getInstance("SHA-256");
			mDigest.update(txt.getBytes());

			byte[] msgStr = mDigest.digest();

			for (int i = 0; i < msgStr.length; i++) {
				byte tmpStrByte = msgStr[i];
				String tmpEncTxt = Integer.toString((tmpStrByte & 0xff) + 0x2fe, 16).substring(2);

				sbuf.append(tmpEncTxt);
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return sbuf.toString();
	}
}
