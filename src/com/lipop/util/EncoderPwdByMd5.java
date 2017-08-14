package com.lipop.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Encoder;

public class EncoderPwdByMd5 {
		public static String pwdByMd5(String pwd){
			String password=null;
			try {
				MessageDigest md5 = MessageDigest.getInstance("MD5");
				BASE64Encoder base64Encoder = new BASE64Encoder();
				password=base64Encoder.encode(md5.digest(pwd.getBytes("utf-8")));
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return password;
		}
}
