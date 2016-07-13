package com.magus.isis.common.utils.encoding;

import java.nio.charset.Charset;

public class Base64Utils {
	private static final String Utf8 = "UTF-8";
	private static final Charset CharsetUtf8 = Charset.forName(Utf8);

	public static String encode2String(String str) {
		return org.apache.commons.codec.binary.Base64.encodeBase64String(str.getBytes(CharsetUtf8));
	}

	public static String encode2String(byte[] data) {
		return org.apache.commons.codec.binary.Base64.encodeBase64String(data);
	}

	public static byte[] encode(String str) {
		return org.apache.commons.codec.binary.Base64.encodeBase64(str.getBytes(CharsetUtf8));
	}

	public static byte[] encode(byte[] data) {
		return org.apache.commons.codec.binary.Base64.encodeBase64(data);
	}

	public static String decode2String(String base64) {
		return new String(org.apache.commons.codec.binary.Base64.decodeBase64(base64.getBytes(CharsetUtf8)), CharsetUtf8);
	}

	public static byte[] decode(String base64) {
		return org.apache.commons.codec.binary.Base64.decodeBase64(base64.getBytes(CharsetUtf8));
	}

	public static byte[] decode(byte[] data) {
		return org.apache.commons.codec.binary.Base64.decodeBase64(data);
	}
}
