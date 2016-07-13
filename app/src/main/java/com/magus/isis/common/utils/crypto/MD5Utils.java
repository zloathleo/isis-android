/**
 * 
 */
package com.magus.isis.common.utils.crypto;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 类描述: 创建时间: 2015年12月3日 下午4:35:04 <br/>
 * 
 * @author 仲李
 * @version
 */
public class MD5Utils {

	public static final String Utf8 = "UTF-8";
	public static final Charset CharsetUtf8 = Charset.forName(Utf8);
	
	private static final Logger Logger = LoggerFactory.getLogger(MD5Utils.class);

	public static String ToMD5(String str) {
		MessageDigest algorithm;
		try {
			algorithm = MessageDigest.getInstance("MD5");
			algorithm.reset();
			algorithm.update(str.getBytes(CharsetUtf8));
			byte[] messageDigest = algorithm.digest();
			return toHexString(messageDigest);
		} catch (NoSuchAlgorithmException e) {
			Logger.error("MD5异常", e);
		}
		return str.toUpperCase();
	}

	private static final char[] hexCode = "0123456789abcdef".toCharArray();

	public static String toHexString(byte[] data) {
		if (data == null) {
			return null;
		}
		StringBuilder r = new StringBuilder(data.length * 2);
		for (byte b : data) {
			r.append(hexCode[(b >> 4) & 0xF]);
			r.append(hexCode[(b & 0xF)]);
		}
		return r.toString();
	}

}
